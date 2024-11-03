package com.example.restaurant.controller;

import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.example.restaurant.config.auth.UserUtil;
import com.example.restaurant.domain.RtFood;
import com.example.restaurant.domain.RtMessage;
import com.example.restaurant.domain.base.AjaxResult;
import com.example.restaurant.domain.base.R;
import com.example.restaurant.domain.req.RtOrderCountReq;
import com.example.restaurant.domain.req.RtOrderListReq;
import com.example.restaurant.service.IRtMessageService;
import com.example.restaurant.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.domain.RtOrder;
import com.example.restaurant.service.IRtOrderService;


/**
 * 订单Controller
 *
 * @author ruoyi
 * @date 2024-10-16
 */
@RestController
@Api(tags = "订单管理")
@RequestMapping("/order")
public class RtOrderController extends BaseController {
    @Autowired
    private IRtOrderService rtOrderService;
    @Autowired
    private IRtMessageService rtMessageService;

    /**
     * 查询订单列表
     */

    @GetMapping("/list")
    @ApiOperation("查询订单列表")
    public R list(RtOrderListReq rtOrderListReq) {
        PageHelper.startPage(rtOrderListReq.getPageNum(), rtOrderListReq.getPageSize());
        RtOrder rtOrder = new RtOrder();
        BeanUtils.copyProperties(rtOrderListReq, rtOrder);

        List<RtOrder> rtOrders = rtOrderService.selectRtOrderList(rtOrder);
        if (rtOrders.size() > 0) {
            PageInfo<RtOrder> pageInfo = new PageInfo<>(rtOrders);
            return R.ok(pageInfo);
        }
        return R.ok(new PageInfo<RtOrder>(Collections.emptyList()));
    }


    /**
     * 获取订单详细信息
     */

    @GetMapping(value = "/{id}")
    @ApiOperation("获取订单详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(rtOrderService.selectRtOrderById(id));
    }

    /**
     * 新增订单
     */


    @PostMapping
    @ApiOperation("新增订单")
    @Transactional
    public AjaxResult add(@RequestBody RtOrder rtOrder) {

        String id = UUIDUtil.get8UUID();
        rtOrder.setId(id);
        int rows = rtOrderService.insertRtOrder(rtOrder);

        //发送通知
        RtMessage rtMessage = new RtMessage();
        rtMessage.setOrderId(id);
        rtMessage.setFrom(UserUtil.getCurrentUsername());
        rtMessage.setTo("admin");
        rtMessage.setMessage("您有一份新订单");
        rtMessage.setTable(rtOrder.getTable());
        rtMessageService.insertRtMessage(rtMessage);
        return toAjax(rows);
    }

    /**
     * 修改订单
     */


    @PutMapping
    @ApiOperation("修改订单")
    @Transactional
    public AjaxResult edit(@RequestBody RtOrder rtOrder) {


        //发送通知
        RtMessage rtMessage = new RtMessage();
        rtMessage.setOrderId(rtOrder.getId());
        rtMessage.setFrom("admin");
        rtMessage.setTo(rtOrder.getCreateBy());
        rtMessage.setMessage("您的订单已完成");
        rtMessage.setTable(rtOrder.getTable());
        rtMessageService.insertRtMessage(rtMessage);


        return toAjax(rtOrderService.updateRtOrder(rtOrder));
    }

    /**
     * 删除订单
     */


    @DeleteMapping("/{ids}")
    @ApiOperation("删除订单")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(rtOrderService.deleteRtOrderByIds(ids));
    }


    @GetMapping("/count")
    public AjaxResult count(RtOrderCountReq rtOrderCountReq){

        return AjaxResult.success("success",rtOrderService.count(rtOrderCountReq));

    }

    @GetMapping("/pay/{table}")
    public AjaxResult getPay(@PathVariable("table")String table){

        return AjaxResult.success("success",rtOrderService.selectRtOrderListPay(table));

    }

    @PostMapping("/pay")
    public AjaxResult pay(@RequestBody List<String>ids){

        return AjaxResult.success("success",rtOrderService.pay(ids));

    }
}

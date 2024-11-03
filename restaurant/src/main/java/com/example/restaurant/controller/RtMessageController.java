package com.example.restaurant.controller;

import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.example.restaurant.domain.RtFood;
import com.example.restaurant.domain.base.AjaxResult;
import com.example.restaurant.domain.base.R;
import com.example.restaurant.domain.req.RtMessageListReq;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.domain.RtMessage;
import com.example.restaurant.service.IRtMessageService;


/**
 * 消息Controller
 *
 * @author ruoyi
 * @date 2024-10-16
 */
@RestController
@Api(tags = "通知管理")
@RequestMapping("/message")
public class RtMessageController extends BaseController
{
    @Autowired
    private IRtMessageService rtMessageService;

    /**
     * 查询消息列表
     */
    
    @GetMapping("/list")
    @ApiOperation("查询消息列表")
    public R list(RtMessageListReq rtMessageListReq)
    {
        PageHelper.startPage(rtMessageListReq.getPageNum(), rtMessageListReq.getPageSize());
        RtMessage rtMessage = new RtMessage();
        BeanUtils.copyProperties(rtMessageListReq,rtMessage);

        List<RtMessage> rtMessages = rtMessageService.selectRtMessageList(rtMessage);
        if(rtMessages.size()>0){
            PageInfo<RtMessage> pageInfo = new PageInfo<>(rtMessages);
            return R.ok(pageInfo);
        }
        return R.ok(new PageInfo<RtMessage>(Collections.emptyList()));
    }

    /**
     * 获取消息详细信息
     */
    
    @GetMapping(value = "/{id}")
    @ApiOperation("获取消息详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(rtMessageService.selectRtMessageById(id));
    }

    /**
     * 新增消息
     */
    
    
    @PostMapping
    @ApiOperation("新增消息")
    public AjaxResult add(@RequestBody RtMessage rtMessage)
    {
        return toAjax(rtMessageService.insertRtMessage(rtMessage));
    }

    /**
     * 修改消息
     */
    
    
    @PutMapping
    @ApiOperation("修改消息")
    public AjaxResult edit(@RequestBody RtMessage rtMessage)
    {
        return toAjax(rtMessageService.updateRtMessage(rtMessage));
    }

    /**
     * 删除消息
     */
    
    
    @DeleteMapping("/{ids}")
    @ApiOperation("删除消息")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rtMessageService.deleteRtMessageByIds(ids));
    }
}

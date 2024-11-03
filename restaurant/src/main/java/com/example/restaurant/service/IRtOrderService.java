package com.example.restaurant.service;

import java.util.List;
import com.example.restaurant.domain.RtOrder;
import com.example.restaurant.domain.RtOrderCountVO;
import com.example.restaurant.domain.base.AjaxResult;
import com.example.restaurant.domain.req.RtOrderCountReq;

/**
 * 订单Service接口
 *
 * @author ruoyi
 * @date 2024-10-16
 */
public interface IRtOrderService
{
    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    public RtOrder selectRtOrderById(Long id);

    /**
     * 查询订单列表
     *
     * @param rtOrder 订单
     * @return 订单集合
     */
    public List<RtOrder> selectRtOrderList(RtOrder rtOrder);

    /**
     * 新增订单
     *
     * @param rtOrder 订单
     * @return 结果
     */
    public int insertRtOrder(RtOrder rtOrder);

    /**
     * 修改订单
     *
     * @param rtOrder 订单
     * @return 结果
     */
    public int updateRtOrder(RtOrder rtOrder);

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteRtOrderByIds(Long[] ids);

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    public int deleteRtOrderById(Long id);

    List<RtOrderCountVO> count(RtOrderCountReq rtOrderCountReq);

    public List<RtOrder> selectRtOrderListPay(String table);

    int pay(List<String> ids);

}
package com.example.restaurant.service.impl;

import java.util.Date;
import java.util.List;

import com.example.restaurant.config.auth.UserUtil;
import com.example.restaurant.domain.RtMessage;
import com.example.restaurant.domain.RtOrderCountVO;
import com.example.restaurant.domain.req.RtOrderCountReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restaurant.mapper.RtOrderMapper;
import com.example.restaurant.domain.RtOrder;
import com.example.restaurant.service.IRtOrderService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-16
 */
@Service
public class RtOrderServiceImpl implements IRtOrderService
{
    @Autowired
    private RtOrderMapper rtOrderMapper;

    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public RtOrder selectRtOrderById(Long id)
    {
        return rtOrderMapper.selectRtOrderById(id);
    }

    /**
     * 查询订单列表
     *
     * @param rtOrder 订单
     * @return 订单
     */
    @Override
    public List<RtOrder> selectRtOrderList(RtOrder rtOrder)
    {
        return rtOrderMapper.selectRtOrderList(rtOrder);
    }

    /**
     * 新增订单
     *
     * @param rtOrder 订单
     * @return 结果
     */
    @Override
    public int insertRtOrder(RtOrder rtOrder)
    {
        rtOrder.setCreateTime(new Date());
        rtOrder.setCreateBy(UserUtil.getCurrentUsername());
        rtOrder.setUpdateTime(new Date());
        return rtOrderMapper.insertRtOrder(rtOrder);
    }

    /**
     * 修改订单
     *
     * @param rtOrder 订单
     * @return 结果
     */
    @Override
    public int updateRtOrder(RtOrder rtOrder)
    {
        rtOrder.setUpdateTime(new Date());
        return rtOrderMapper.updateRtOrder(rtOrder);
    }

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteRtOrderByIds(Long[] ids)
    {
        return rtOrderMapper.deleteRtOrderByIds(ids);
    }

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteRtOrderById(Long id)
    {
        return rtOrderMapper.deleteRtOrderById(id);
    }

    @Override
    public List<RtOrderCountVO> count(RtOrderCountReq rtOrderCountReq) {
        return rtOrderMapper.count(rtOrderCountReq);
    }

    @Override
    public List<RtOrder> selectRtOrderListPay(String table) {
        return rtOrderMapper.selectRtOrderListPay(table);
    }

    @Override
    @Transactional
    public int pay(List<String> ids) {

        return rtOrderMapper.updateBatch(ids);
    }
}

package com.example.restaurant.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restaurant.mapper.RtMessageMapper;
import com.example.restaurant.domain.RtMessage;
import com.example.restaurant.service.IRtMessageService;

/**
 * 消息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-16
 */
@Service
public class RtMessageServiceImpl implements IRtMessageService
{
    @Autowired
    private RtMessageMapper rtMessageMapper;

    /**
     * 查询消息
     *
     * @param id 消息主键
     * @return 消息
     */
    @Override
    public RtMessage selectRtMessageById(Long id)
    {
        return rtMessageMapper.selectRtMessageById(id);
    }

    /**
     * 查询消息列表
     *
     * @param rtMessage 消息
     * @return 消息
     */
    @Override
    public List<RtMessage> selectRtMessageList(RtMessage rtMessage)
    {
        return rtMessageMapper.selectRtMessageList(rtMessage);
    }

    /**
     * 新增消息
     *
     * @param rtMessage 消息
     * @return 结果
     */
    @Override
    public int insertRtMessage(RtMessage rtMessage)
    {
        rtMessage.setCreateTime(new Date());
        return rtMessageMapper.insertRtMessage(rtMessage);
    }

    /**
     * 修改消息
     *
     * @param rtMessage 消息
     * @return 结果
     */
    @Override
    public int updateRtMessage(RtMessage rtMessage)
    {
        rtMessage.setUpdateTime(new Date());
        return rtMessageMapper.updateRtMessage(rtMessage);
    }

    /**
     * 批量删除消息
     *
     * @param ids 需要删除的消息主键
     * @return 结果
     */
    @Override
    public int deleteRtMessageByIds(Long[] ids)
    {
        return rtMessageMapper.deleteRtMessageByIds(ids);
    }

    /**
     * 删除消息信息
     *
     * @param id 消息主键
     * @return 结果
     */
    @Override
    public int deleteRtMessageById(Long id)
    {
        return rtMessageMapper.deleteRtMessageById(id);
    }
}

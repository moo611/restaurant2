package com.example.restaurant.mapper;

import java.util.List;
import com.example.restaurant.domain.RtMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息Mapper接口
 *
 * @author ruoyi
 * @date 2024-10-16
 */
@Mapper
public interface RtMessageMapper
{
    /**
     * 查询消息
     *
     * @param id 消息主键
     * @return 消息
     */
    public RtMessage selectRtMessageById(Long id);

    /**
     * 查询消息列表
     *
     * @param rtMessage 消息
     * @return 消息集合
     */
    public List<RtMessage> selectRtMessageList(RtMessage rtMessage);

    /**
     * 新增消息
     *
     * @param rtMessage 消息
     * @return 结果
     */
    public int insertRtMessage(RtMessage rtMessage);

    /**
     * 修改消息
     *
     * @param rtMessage 消息
     * @return 结果
     */
    public int updateRtMessage(RtMessage rtMessage);

    /**
     * 删除消息
     *
     * @param id 消息主键
     * @return 结果
     */
    public int deleteRtMessageById(Long id);

    /**
     * 批量删除消息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRtMessageByIds(Long[] ids);
}

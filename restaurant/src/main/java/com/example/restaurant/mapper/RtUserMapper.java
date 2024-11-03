package com.example.restaurant.mapper;

import java.util.List;
import com.example.restaurant.domain.RtUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper接口
 *
 * @author ruoyi
 * @date 2024-10-16
 */
@Mapper
public interface RtUserMapper
{
    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    public RtUser selectRtUserById(Long id);

    /**
     * 查询用户列表
     *
     * @param rtUser 用户
     * @return 用户集合
     */
    public List<RtUser> selectRtUserList(RtUser rtUser);

    /**
     * 新增用户
     *
     * @param rtUser 用户
     * @return 结果
     */
    public int insertRtUser(RtUser rtUser);

    /**
     * 修改用户
     *
     * @param rtUser 用户
     * @return 结果
     */
    public int updateRtUser(RtUser rtUser);

    /**
     * 删除用户
     *
     * @param id 用户主键
     * @return 结果
     */
    public int deleteRtUserById(Long id);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRtUserByIds(Long[] ids);

    RtUser selectUserByUsername(String username);

}

package com.example.restaurant.service;

import java.util.List;
import com.example.restaurant.domain.RtUser;
import com.example.restaurant.domain.req.LoginReq;

/**
 * 用户Service接口
 *
 * @author ruoyi
 * @date 2024-10-16
 */
public interface IRtUserService
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
     * 批量删除用户
     *
     * @param ids 需要删除的用户主键集合
     * @return 结果
     */
    public int deleteRtUserByIds(Long[] ids);

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 结果
     */
    public int deleteRtUserById(Long id);

    int login(LoginReq loginReq);

    RtUser selectRtUserByUsername(String username);

}

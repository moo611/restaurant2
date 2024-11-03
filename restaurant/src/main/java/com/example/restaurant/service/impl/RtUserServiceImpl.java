package com.example.restaurant.service.impl;

import java.util.Date;
import java.util.List;

import com.example.restaurant.domain.req.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.restaurant.mapper.RtUserMapper;
import com.example.restaurant.domain.RtUser;
import com.example.restaurant.service.IRtUserService;

/**
 * 用户Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-16
 */
@Service
public class RtUserServiceImpl implements IRtUserService
{

    @Autowired
    private PasswordEncoder passwordEncoder;  // 注入 PasswordEncode

    @Autowired
    private RtUserMapper rtUserMapper;

    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public RtUser selectRtUserById(Long id)
    {
        return rtUserMapper.selectRtUserById(id);
    }

    /**
     * 查询用户列表
     *
     * @param rtUser 用户
     * @return 用户
     */
    @Override
    public List<RtUser> selectRtUserList(RtUser rtUser)
    {
        return rtUserMapper.selectRtUserList(rtUser);
    }

    /**
     * 新增用户
     *
     * @param rtUser 用户
     * @return 结果
     */
    @Override
    public int insertRtUser(RtUser rtUser)
    {

        RtUser old = rtUserMapper.selectUserByUsername(rtUser.getUsername());
        if (old != null){
            return -32001;
        }
        String encodedPassword = passwordEncoder.encode(rtUser.getPassword());
        rtUser.setPassword(encodedPassword);
        rtUser.setCreateTime(new Date());
        return rtUserMapper.insertRtUser(rtUser);
    }

    /**
     * 修改用户
     *
     * @param rtUser 用户
     * @return 结果
     */
    @Override
    public int updateRtUser(RtUser rtUser)
    {

        RtUser old = rtUserMapper.selectUserByUsername(rtUser.getUsername());
        if (!old.getId().equals(rtUser.getId())){
            return -32001;
        }

        rtUser.setUpdateTime(new Date());
        return rtUserMapper.updateRtUser(rtUser);
    }

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteRtUserByIds(Long[] ids)
    {
        return rtUserMapper.deleteRtUserByIds(ids);
    }

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteRtUserById(Long id)
    {
        return rtUserMapper.deleteRtUserById(id);
    }

    @Override
    public int login(LoginReq loginReq) {
        RtUser userDao = rtUserMapper.selectUserByUsername(loginReq.getUsername());

        if (userDao == null) {
            //用户不存在
            return -32001;
        }

        if (!userDao.getPassword().equals(loginReq.getPassword())) {
            //密码不正确
            return -32002;
        }

        return 1;
    }

    @Override
    public RtUser selectRtUserByUsername(String username) {

        return rtUserMapper.selectUserByUsername(username);

    }



}

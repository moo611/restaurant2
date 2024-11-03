package com.example.restaurant.controller;

import java.util.Collections;
import java.util.List;

import com.example.restaurant.config.auth.MyUserDetailsService;
import com.example.restaurant.config.auth.UserUtil;
import com.example.restaurant.domain.base.AjaxResult;
import com.example.restaurant.domain.base.R;
import com.example.restaurant.domain.req.LoginReq;
import com.example.restaurant.domain.req.RtUserListReq;
import com.example.restaurant.config.auth.JwtUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.domain.RtUser;
import com.example.restaurant.service.IRtUserService;


/**
 * 用户Controller
 *
 * @author ruoyi
 * @date 2024-10-16
 */
@RestController
@Api(tags = "用户管理")
@RequestMapping("/user")
public class RtUserController extends BaseController
{
    @Autowired
    private IRtUserService rtUserService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    @ApiOperation("登录")
    public AjaxResult login(@RequestBody LoginReq loginReq) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword())
            );
        }catch (Exception e){
            return AjaxResult.error("用户名或密码错误");
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginReq.getUsername());
        return AjaxResult.success("ok",jwtUtil.generateToken(userDetails.getUsername()));

    }

    /**
     * 查询用户列表
     */
    
    @GetMapping("/list")
    @ApiOperation("查询用户列表")
    public R list(RtUserListReq rtUserListReq)
    {
        PageHelper.startPage(rtUserListReq.getPageNum(), rtUserListReq.getPageSize());
        RtUser rtUser = new RtUser();
        BeanUtils.copyProperties(rtUserListReq,rtUser);

        List<RtUser> rtUsers = rtUserService.selectRtUserList(rtUser);
        if(rtUsers.size()>0){
            PageInfo<RtUser> pageInfo = new PageInfo<>(rtUsers);
            return R.ok(pageInfo);
        }
        return R.ok(new PageInfo<RtUser>(Collections.emptyList()));
    }



    /**
     * 获取用户详细信息
     */
    
    @GetMapping(value = "/{id}")
    @ApiOperation("获取用户详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(rtUserService.selectRtUserById(id));
    }


    /**
     * 获取用户详细信息
     */

    @GetMapping(value = "/info")
    @ApiOperation("获取用户详细信息")
    public AjaxResult getInfo()
    {
        String username = UserUtil.getCurrentUsername();
        return success(rtUserService.selectRtUserByUsername(username));
    }


    /**
     * 新增用户
     */
    
    
    @PostMapping
    @ApiOperation("新增用户")
    public AjaxResult add(@RequestBody RtUser rtUser)
    {

        int res = rtUserService.insertRtUser(rtUser);
        if (res == -32001){
            AjaxResult.error("用户名已存在");
        }
        return toAjax(res);
    }

    /**
     * 修改用户
     */
    
    
    @PutMapping
    @ApiOperation("修改用户")
    public AjaxResult edit(@RequestBody RtUser rtUser)
    {
        int res = rtUserService.updateRtUser(rtUser);
        if (res == -32001){
            AjaxResult.error("用户名已存在");
        }

        return toAjax(res);
    }

    /**
     * 删除用户
     */
    
    
    @DeleteMapping("/{ids}")
    @ApiOperation("删除用户")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rtUserService.deleteRtUserByIds(ids));
    }
}

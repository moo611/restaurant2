package com.example.restaurant.domain;


import com.example.restaurant.domain.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户对象 rt_user
 *
 * @author ruoyi
 * @date 2024-10-16
 */
@ApiModel("用户实体")
public class RtUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty("id")
    private Long id;

    /** $column.columnComment */
    @ApiModelProperty("用户名")
    private String username;

    /** $column.columnComment */
    @ApiModelProperty("密码")
    //@JsonIgnore
    private String password;

    /** $column.columnComment */
    @ApiModelProperty("昵称")
    private String nickname;

    /** $column.columnComment */
    @ApiModelProperty("角色")
    private String role;

    /** $column.columnComment */
    private String delFlag;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getNickname()
    {
        return nickname;
    }
    public void setRole(String role)
    {
        this.role = role;
    }

    public String getRole()
    {
        return role;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

 
}

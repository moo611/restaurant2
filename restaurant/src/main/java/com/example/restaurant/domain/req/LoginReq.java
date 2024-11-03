package com.example.restaurant.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("登录请求")
public class LoginReq implements Serializable {

    private String username;

    private String password;
}

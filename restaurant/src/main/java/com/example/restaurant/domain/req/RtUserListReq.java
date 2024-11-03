package com.example.restaurant.domain.req;

import com.example.restaurant.domain.base.BaseReq;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("用户列表请求")
public class RtUserListReq extends BaseReq {

    String role;
}

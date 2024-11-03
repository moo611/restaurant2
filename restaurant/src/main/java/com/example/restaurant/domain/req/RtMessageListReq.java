package com.example.restaurant.domain.req;

import com.example.restaurant.domain.base.BaseReq;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("消息列表请求")
public class RtMessageListReq extends BaseReq {

    String to;
}

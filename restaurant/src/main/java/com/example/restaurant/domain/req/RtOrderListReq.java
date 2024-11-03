package com.example.restaurant.domain.req;

import com.example.restaurant.domain.base.BaseReq;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("订单列表请求")
public class RtOrderListReq extends BaseReq {

    String status;

    String createBy;
}

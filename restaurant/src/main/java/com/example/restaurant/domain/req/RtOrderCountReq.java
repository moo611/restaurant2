package com.example.restaurant.domain.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class RtOrderCountReq implements Serializable {

    String startTime;

    String endTime;

}

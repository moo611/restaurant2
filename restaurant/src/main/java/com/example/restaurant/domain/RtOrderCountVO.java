package com.example.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RtOrderCountVO implements Serializable {

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date date;

    int price;
}

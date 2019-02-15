package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class OrderInfo {
    private String orderId;
    private Boolean priceFixed;
    private Double price;
}

package com.octopus.taxcube.eds.pojo.order;

import lombok.Data;

@Data
public class OrderBase {
    private String orderId;
    private String orderStatus;
    private Long customerId;
    private String customerName;
    private Long prodId;
    private String prodName;
    private String orderDate;
}

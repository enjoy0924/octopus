package com.octopus.taxcube.eds.pojo.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderToPay extends OrderBase {
    private Double priceQuotation; //应付多少钱
    private Double pricePay;       //实付多少
    private String payStatus;      //支付状态

    private List<OrderPayItem> orderPayItems;

}

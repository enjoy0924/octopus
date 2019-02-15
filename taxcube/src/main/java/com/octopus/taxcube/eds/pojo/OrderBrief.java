package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class OrderBrief {
    private String id;
    private String orderStatus;
    private String quotationStatus;
    private String payStatus;
    private String distributeStatus;
    private String serveStatus;
    private String checkStatus;
    private String statusDesc;
    private Double pricePay;       //实际支付金额
    private Double priceQuotation; //报价金额
    private Long prodId;
    private String prodName;
    private String prodImageUri;
    private String createTime;
    private Long empId;
    private String empName;
    private Long customerId;
    private String customerName;
    private String location;
}

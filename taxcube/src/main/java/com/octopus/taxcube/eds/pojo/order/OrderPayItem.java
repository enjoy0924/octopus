package com.octopus.taxcube.eds.pojo.order;

import lombok.Data;

@Data
public class OrderPayItem {
    private Double price;
    private String payType;   //MONEY | CREDIT
    private String payTime;
    private Long payBy;
    private String payer;
    private String status;    //
    private String remark;
    private String payMethod; //ONLINE | OFFLINE
}

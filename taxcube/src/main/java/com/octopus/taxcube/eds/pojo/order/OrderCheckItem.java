package com.octopus.taxcube.eds.pojo.order;

import lombok.Data;

@Data
public class OrderCheckItem {
    private Long checkId;
    private String checkStatus;
    private Long checkBy;
    private String checker;
    private String checkTime;
    private String checkRemark;
}

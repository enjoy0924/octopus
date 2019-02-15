package com.octopus.taxcube.eds.pojo.order;

import lombok.Data;

@Data
public class OrderDistributeItem {
    private Long empId;
    private String empName;
    private String distributor;
    private String distributeTime;
}

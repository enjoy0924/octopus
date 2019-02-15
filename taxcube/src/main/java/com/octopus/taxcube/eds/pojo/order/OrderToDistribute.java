package com.octopus.taxcube.eds.pojo.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderToDistribute extends OrderToPay {
    private Long empId;
    private String empName;
    private Double creditForEmp;
    private String distributeStatus;
    private String location;
    private List<OrderDistributeItem> orderDistributeItems;
}

package com.octopus.taxcube.eds.pojo.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderToTask extends OrderBase {
    private Long empId;
    private String empName;
    private List<OrderTaskItem> orderTaskItems;
}

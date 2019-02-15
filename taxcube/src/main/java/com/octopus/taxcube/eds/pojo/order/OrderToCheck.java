package com.octopus.taxcube.eds.pojo.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderToCheck extends OrderToTask{
    private String checkStatus;
    private List<OrderCheckItem> orderCheckItems;
}

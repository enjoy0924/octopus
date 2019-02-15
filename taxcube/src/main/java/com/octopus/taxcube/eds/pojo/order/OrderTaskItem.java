package com.octopus.taxcube.eds.pojo.order;

import lombok.Data;

@Data
public class OrderTaskItem {
    private Long id;
    private String name;
    private String remark;
    private String progress;
    private String status;
    private String refreshTime;
}

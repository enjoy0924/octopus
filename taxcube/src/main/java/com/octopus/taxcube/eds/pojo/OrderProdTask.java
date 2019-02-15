package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class OrderProdTask {
    private Long id;
    private Long prodTaskId;
    private String prodTaskName;
//    private Long orderId;
    private String status;
    private String period;
    private String remark;
    private String progress;
}

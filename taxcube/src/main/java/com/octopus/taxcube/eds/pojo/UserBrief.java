package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class UserBrief {
    private Long empId;
    private String name;
    private String phone;
    private String offerType;
    private EmpSummary summary;
}

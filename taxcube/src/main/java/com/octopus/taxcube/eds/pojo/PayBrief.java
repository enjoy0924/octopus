package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class PayBrief {
    private String type;
    private String method;
    private Double value;
}

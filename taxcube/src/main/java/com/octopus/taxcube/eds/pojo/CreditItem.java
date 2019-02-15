package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class CreditItem {
    private Long credit;
    private String reason;
    private String status;
    private String period;
    private String date;
}

package com.octopus.taxcube.eds.pojo.order;

import lombok.Data;

@Data
public class OrderQuotationItem {
    private Double price;
    private Long quotationBy;
    private String quotationAdminName;
    private String quotationTime;
}

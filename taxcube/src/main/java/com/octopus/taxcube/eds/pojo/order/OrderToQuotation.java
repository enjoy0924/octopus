package com.octopus.taxcube.eds.pojo.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderToQuotation extends OrderBase{
    private Double priceQuotation;
    private String quotationStatus;
    private List<OrderQuotationItem> orderQuotationItems;
}

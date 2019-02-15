package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class CreditItemPlus extends CreditItem{
    private Long accountId;
    private String remark;
}

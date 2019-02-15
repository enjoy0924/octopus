package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class EmpSummary {
    private Long creditTotal= 0L;
    private Long creditRemain= 0L;
    private Long orderQuantityDone= 0L;
    private Long orderQuantityServing= 0L;
}

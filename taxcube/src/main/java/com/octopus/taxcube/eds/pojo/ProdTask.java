package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class ProdTask {
    private Long id;
    private Long prodId;
    private String name;
    private String description;
    private String status;
}

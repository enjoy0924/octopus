package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class Popularization {
    private Long id;
    private Long popularizeId;
    private String popularizeName;
    private Long popularizedId;
    private String popularizedName;
    private String status;
    private String date;
}

package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class Prod {
    private Long id;
    private String name;
    private String titleImage;
    private String descImage;
    private String description;
    private String priceType;
    private String descPrice;
    private String creditType;
    private String status;
    private Long catalogId;
    private Integer price;
    private String catalogName;
}

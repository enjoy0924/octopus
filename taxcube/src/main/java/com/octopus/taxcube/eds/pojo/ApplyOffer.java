package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class ApplyOffer {
    private Long applyId;
    private Long applierId;
    private String name;
    private String phone;
    private String idcardImagesStr;
    private String idNumber;
    private String certificateImagesStr;
    private String locationsStr;
    private String offersStr;
    private String status;
}

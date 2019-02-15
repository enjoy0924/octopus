package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class EnterpriseInfo {
    private String taxCode;       //纳税人识别号
    private String name;          //企业名称
    private String address;       //企业地址
    private String telephone;     //联系电话
}

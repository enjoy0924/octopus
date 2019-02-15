package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class Permission {

    private Long permissionId;
    private String permissionName;
    private String permissionCode;
    private String menuCode;
    private String menuName;
    private String createTime;
}

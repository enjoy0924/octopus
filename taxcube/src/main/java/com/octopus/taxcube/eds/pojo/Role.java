package com.octopus.taxcube.eds.pojo;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class Role {

    private Long roleId;
    private String roleName;
    private String description;
    private String createTime;

    private List<Permission> permissions;

    public Role(String role) {
        this.roleName = role;
    }
}

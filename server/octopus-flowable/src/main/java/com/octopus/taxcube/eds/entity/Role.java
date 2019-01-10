package com.octopus.taxcube.eds.entity;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class Role {

    private String roleName;

    public Role(String role) {
        this.roleName = role;
    }

    public List<Permission> getPermissions() {
        return Collections.emptyList();
    }
}

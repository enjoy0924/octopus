package com.octopus.taxcube.eds.entity;

import com.octopus.taxcube.common.CONST;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private String phone;
    private String status;
    private String type;
    private String name;
    private String password;
    private String wxOpenId;

    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(type));
        return roles;
    }
}

package com.octopus.taxcube.eds.pojo;

import com.octopus.taxcube.util.excel.annotation.ExcelField;
import com.octopus.taxcube.util.excel.annotation.ExcelField.Align;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class User {
    @ExcelField(title="用户ID", attrName="id", align=Align.CENTER, sort=30)
    private Long id;

    @ExcelField(title="手机电话", attrName="phone", align=Align.CENTER, sort=40)
    private String phone="";

    @ExcelField(title="状态", attrName="status", align=Align.LEFT, sort=50)
    private String status;

    @ExcelField(title="角色类型", attrName="type", align=Align.CENTER, sort=70)
    private String type;

    @ExcelField(title="用户名称", attrName="name", align=Align.CENTER, sort=60)
    private String name;
    private String password="";
    private String wxOpenId;
    private String token;

    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(type));
        return roles;
    }
}

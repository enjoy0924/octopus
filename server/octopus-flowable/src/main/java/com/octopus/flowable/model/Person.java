package com.octopus.flowable.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户实体模型")
public class Person {
    @ApiModelProperty(value = "用户id", required = true)
    private Integer id;
    @ApiModelProperty(value = "用户名", required = true)
    private String name;
}
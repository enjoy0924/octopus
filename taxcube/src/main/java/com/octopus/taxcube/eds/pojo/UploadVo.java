package com.octopus.taxcube.eds.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadVo {
    private String fileName;
    //绝对路径
    private String url;
}

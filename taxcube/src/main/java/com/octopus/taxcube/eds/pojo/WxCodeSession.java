package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class WxCodeSession {
    private String openid;
    private String session_key;
    private String unionid;
    private Long errcode;
    private String errmsg;
    private String token;

}

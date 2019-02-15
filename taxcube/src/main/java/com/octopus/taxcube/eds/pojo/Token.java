package com.octopus.taxcube.eds.pojo;

import lombok.Data;

@Data
public class Token {
    private  String access_token;
    private  String expires_in;
    private  String refresh_token;
    private  String openid;
    private  String scope;
}

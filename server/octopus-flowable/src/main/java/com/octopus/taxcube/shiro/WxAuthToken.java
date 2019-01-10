package com.octopus.taxcube.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class WxAuthToken implements AuthenticationToken {

    private String token;

    public WxAuthToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

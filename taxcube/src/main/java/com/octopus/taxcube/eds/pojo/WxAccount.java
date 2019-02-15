package com.octopus.taxcube.eds.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WxAccount {

        private String openid;

        private String nickname;

        private Integer sex;

        private String province;

        private String city;

        private String country;

        private String headimgurl;

        private List<String> privilege;

        private String unionid;

        private String refreshToken;

        private Date refreshTokenTime;
}

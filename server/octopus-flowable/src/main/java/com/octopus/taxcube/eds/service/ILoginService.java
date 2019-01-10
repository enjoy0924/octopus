package com.octopus.taxcube.eds.service;

import com.octopus.taxcube.eds.entity.User;

public interface ILoginService {
    User findByName(String name);
    User findByPhone(String phone);
    User findByWxOpenId(String openId);
}

package com.octopus.taxcube.utils;

import com.octopus.taxcube.eds.entity.AccountEntity;
import com.octopus.taxcube.eds.entity.User;

public class EdsUtils {
    public static User convertToUser(AccountEntity accountEntity) {
        if(null == accountEntity)
            return null;

        User user = new User();
        user.setStatus(accountEntity.getStatus());
        user.setName(accountEntity.getName());
        user.setPhone(accountEntity.getPhone());
        user.setPassword(accountEntity.getPassword());
        user.setWxOpenId(accountEntity.getWechatId());
        user.setType(accountEntity.getType());

        return user;
    }
}

package com.octopus.taxcube.eds.service.impl;

import com.octopus.taxcube.eds.dao.AccountEntityMapper;
import com.octopus.taxcube.eds.entity.AccountEntity;
import com.octopus.taxcube.eds.entity.User;
import com.octopus.taxcube.eds.service.ILoginService;
import com.octopus.taxcube.utils.EdsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private AccountEntityMapper accountEntityMapper;

    @Override
    public User findByPhone(String phone) {
        AccountEntity accountEntity = accountEntityMapper.selectByPhone(phone);
        return EdsUtils.convertToUser(accountEntity);
    }

    @Override
    public User findByWxOpenId(String openId) {
        return null;
    }

    @Override
    public User findByName(String name) {
        return null;
    }
}

package com.octopus.taxcube.eds.service;

import com.octopus.taxcube.eds.pojo.WxAccount;
import com.octopus.taxcube.eds.pojo.WxCodeSession;

public interface IWxService {
    WxAccount geWxAccountByCode(String code);

    WxCodeSession getJs2SessionStateByCode(String code, String role);
}

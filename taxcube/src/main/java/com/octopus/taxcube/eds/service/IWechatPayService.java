package com.octopus.taxcube.eds.service;

import java.util.Map;

public interface IWechatPayService {

    int PAY_TYPE_SCAN_CODE = 1;
    int PAY_TYPE_APP_DIRECT = 2;
    int PAY_TYPE_PUBLIC_JSAPI = 3;

    Map<String, String> queryOrderStatusByWechatPay(String orderNo) throws Exception;

    Map<String, String> generateOrderInfoByWechatPay(String orderId, int totalFee, String goodsDesc, int payType, String goodsId,String openId) throws Exception;
}

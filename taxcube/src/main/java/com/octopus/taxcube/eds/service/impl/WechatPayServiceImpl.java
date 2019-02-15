package com.octopus.taxcube.eds.service.impl;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.octopus.taxcube.eds.service.IGlobalConfigService;
import com.octopus.taxcube.eds.service.IWechatPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WechatPayServiceImpl implements IWechatPayService {

    @Value("${pay.wechat.url.paynotify}")
    private String paynotifyUrl;

    @Autowired
    private IGlobalConfigService globalConfigService;

    @Override
    public Map<String, String> queryOrderStatusByWechatPay(String orderNo) throws Exception {

        WXPay wxpay = new WXPay(globalConfigService.getWechatPayConfig());

        Map<String, String> data = new HashMap<>();
        data.put("out_trade_no", orderNo);
        return wxpay.orderQuery(data);
    }

    @Override
    public Map<String, String> generateOrderInfoByWechatPay(String orderId, int totalFee, String goodsDesc, int payType, String goodsId, String openId) throws Exception {

        WXPay wxpay = new WXPay(globalConfigService.getWechatPayConfig(), this.paynotifyUrl);//正式
//        WXPay wxpay = new WXPay(globalConfigService.getWechatPayConfig(), "", false, true);

        Map<String, String> data = new HashMap<>();
        data.put("body", goodsDesc.trim());//商品描述
        data.put("out_trade_no", orderId.trim());//商户订单号
        data.put("device_info", "WEB");//设备号，PC网页或公众号内支付可以传"WEB"
        data.put("fee_type", "CNY");//标价币种，默认人民币：CNY
        data.put("total_fee", String.valueOf(totalFee));//标价金额，单位为分，
        data.put("attach",globalConfigService.getWechatPayAttach());
//        data.put("spbill_create_ip", "127.0.0.1");//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
//        data.put("notify_url", this.paynotifyUrl);//异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
        if(payType == IWechatPayService.PAY_TYPE_PUBLIC_JSAPI) {
            data.put("trade_type", "JSAPI");  //交易类型   JSAPI：公众号支付，NATIVE：扫码支付，APP：APP支付 此处指定为扫码支付
            data.put("openid", openId.trim());
        }else if(payType == IWechatPayService.PAY_TYPE_APP_DIRECT){
            data.put("trade_type", "APP");
        }else if(payType == IWechatPayService.PAY_TYPE_SCAN_CODE){
            data.put("trade_type", "NATIVE");
        }

        data.put("product_id", goodsId);//trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义。

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            String return_code= resp.get("return_code");
            String result_code= resp.get("result_code");
            String prepay_id = resp.get("prepay_id");   //获取到prepay_id;
            if (null != return_code && null != result_code
                    && return_code.contains("SUCCESS")
                    && result_code.contains("SUCCESS")
                    && null != prepay_id && !prepay_id.trim().isEmpty()) {

                long currentTimeMillis = System.currentTimeMillis();//生成时间戳
                long second = currentTimeMillis / 1000L;    //（转换成秒）
                String seconds = String.valueOf(second).substring(0, 10); //（截取前10位）

                SortedMap<String, String> signParam = new TreeMap<>();

                String codeUrl = resp.get("code_url");
                if (null != codeUrl && !codeUrl.trim().isEmpty()) {
                    signParam.put("timestamp", seconds);//北京时间时间戳
                    signParam.put("code_url", codeUrl);
                } else {
                    if (!"".equals(openId) && payType == PAY_TYPE_PUBLIC_JSAPI) {  // 表示是公众号支付
                        signParam.put("package", "prepay_id=" + prepay_id);
                        signParam.put("appId", globalConfigService.getWechatPayConfig().getAppID());//app_id
                        signParam.put("nonceStr", resp.get("nonce_str")); // 随机字符串
                        signParam.put("signType", "HMAC-SHA256");
                        signParam.put("timeStamp", seconds);//北京时间时间戳
                        String signAgain = WXPayUtil.generateSignature(signParam, globalConfigService.getPartnerKey(), WXPayConstants.SignType.HMACSHA256);
//                        String signAgain = createSign(paternerKey, signParam);//再次生成签名
                        signParam.put("sign", signAgain);
                    }
//                    else {
//                        signParam.put(WechatPayService.WX_STR_TIMESTAMP, seconds);//北京时间时间戳
//                        signParam.put(WechatPayService.WX_STR_APPID, appId);//app_id
//                        signParam.put(WechatPayService.WX_STR_PACKAGE, "Sign=WXPay");//默认sign=WXPay
//                        signParam.put(WechatPayService.WX_STR_PREPAY_ID.replace("_", ""), prepay_id);
//                        signParam.put(WechatPayService.WX_STR_NONCE_STR.replace("_", ""), uuid);//自定义不重复的长度不长于32位
//
//                        String signAgain = createSign(paternerKey, signParam);//再次生成签名
//                        signParam.put(WechatPayService.WX_STR_SIGN, signAgain);
//                    }
//                    signParam.put(WechatPayService.WX_STR_PARTNER_ID, mchId);   //商户号

                }
                return signParam;
            }

            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyMap();
    }
}
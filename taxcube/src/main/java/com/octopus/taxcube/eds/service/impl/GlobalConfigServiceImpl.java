package com.octopus.taxcube.eds.service.impl;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.octopus.taxcube.eds.service.IGlobalConfigService;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Data
@Service
public class GlobalConfigServiceImpl implements IGlobalConfigService, InitializingBean {

    @Value("${httpserver.http.prefix}")
    private String httpPrefix;

    @Value("${httpserver.localpath.img.idcard}")
    private String idcardImgLocalPath;

    @Value("${httpserver.localpath.img.avator}")
    private String avatarImgLocalPath;

    @Value("${httpserver.localpath.img.product}")
    private String productImgLocalPath;

    @Value("${httpserver.localpath.img.certificate}")
    private String certificateImgLocalPath;

    @Value("${httpserver.http.suffix.img.idcard}")
    private String idcardImgSuffixPath;

    @Value("${httpserver.http.suffix.img.avator}")
    private String avatarImgSuffixPath;

    @Value("${httpserver.http.suffix.img.product}")
    private String productImgSuffixPath;

    @Value("${httpserver.http.suffix.img.certificate}")
    private String certificateImgSuffixPath;

    @Override
    public String getIdcardImgFullPath() {
        return String.format("%s%s",this.httpPrefix,this.idcardImgSuffixPath);
    }

    @Override
    public String getProductImgFullPath() {
        return String.format("%s%s",this.httpPrefix,this.productImgSuffixPath);
    }

    @Override
    public String getAvatarImgFullPath() {
        return String.format("%s%s",this.httpPrefix,this.avatarImgSuffixPath);
    }

    @Override
    public String getCertificateImgFullPath() {
        return String.format("%s%s",this.httpPrefix,this.certificateImgSuffixPath);
    }

    @Override
    public String getCertificateImgFullPathByFileName(String filename) {
        if(null == filename || !filename.contains(".") || filename.trim().isEmpty()){
            return "";
        }
        return String.format("%s%s%s",getCertificateImgFullPath(), File.separator, filename);
    }

    @Override
    public String getIdcardImgFullPathByFileName(String filename) {
        if(null == filename || !filename.contains(".") || filename.trim().isEmpty()){
            return "";
        }
        return String.format("%s%s%s",getIdcardImgFullPath(), File.separator, filename);
    }

    @Override
    public String getProductImgFullPathByFileName(String filename) {
        if(null == filename || !filename.contains(".") || filename.trim().isEmpty()){
            return "";
        }
        return String.format("%s%s%s",getProductImgFullPath(), File.separator, filename);
    }

    @Override
    public String getAvatarImgFullPathByFileName(String filename) {
        if(null == filename || !filename.contains(".") || filename.trim().isEmpty()){
            return "";
        }
        return String.format("%s%s%s",getAvatarImgFullPath(), File.separator, filename);
    }


    @Value("${pay.wechat.appId}")
    private String appId;   //appId

    @Value("${pay.wechat.mchId}")
    private String mchId;

    @Value("${pay.wechat.paternerKey}")
    private String paternerKey;

    @Value("${pay.wechat.corpInfo}")
    private String corpInfo;

    @Value("${pay.wechat.url.unifiedorder}")
    private String unifiedorderUrl;

    @Value("${pay.wechat.url.orderquery}")
    private String orderqueryUrl;

    @Value("${pay.wechat.cert.path}")
    private String wechatPayCertPath;

    private byte[] certData;

    @Override
    public WXPayConfig getWechatPayConfig() {
        WXPayConfig wxPayConfig = new WXPayConfig() {
            @Override
            public String getAppID() {
                return getAppId();
            }

            @Override
            public String getMchID() {
                return getMchId();
            }

            @Override
            public String getKey() {
                return getPaternerKey();
            }

            @Override
            public InputStream getCertStream() {
                return new ByteArrayInputStream(getCertData());
            }

            @Override
            public IWXPayDomain getWXPayDomain() {
                IWXPayDomain iwxPayDomain = new IWXPayDomain() {
                    @Override
                    public void report(String domain, long elapsedTimeMillis, Exception ex) {

                    }
                    @Override
                    public DomainInfo getDomain(WXPayConfig config) {
                        return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
                    }
                };
                return iwxPayDomain;
            }
        };

        return wxPayConfig;
    }

    @Override
    public String getPartnerKey() {
        return paternerKey;
    }

    @Override
    public String getWechatPayAttach() {
        return getCorpInfo();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String certPath = getWechatPayCertPath();
        if(null == certPath || certPath.trim().isEmpty())
            return;

        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);

        certStream.close();
    }
}

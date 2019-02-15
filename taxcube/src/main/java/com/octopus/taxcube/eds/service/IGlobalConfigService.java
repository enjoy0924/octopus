package com.octopus.taxcube.eds.service;

import com.github.wxpay.sdk.WXPayConfig;

public interface IGlobalConfigService {

    String getIdcardImgLocalPath();

    String getProductImgLocalPath();

    String getAvatarImgLocalPath();

    String getCertificateImgLocalPath();

    String getHttpPrefix();

    String getIdcardImgSuffixPath();

    String getProductImgSuffixPath();

    String getAvatarImgSuffixPath();

    String getCertificateImgSuffixPath();

    String getIdcardImgFullPath();

    String getProductImgFullPath();

    String getAvatarImgFullPath();

    String getCertificateImgFullPath();

    String getCertificateImgFullPathByFileName(String filename);

    String getIdcardImgFullPathByFileName(String filename);

    String getProductImgFullPathByFileName(String filename);

    String getAvatarImgFullPathByFileName(String filename);

    WXPayConfig getWechatPayConfig();

    String getPartnerKey();

    String getWechatPayAttach();
}

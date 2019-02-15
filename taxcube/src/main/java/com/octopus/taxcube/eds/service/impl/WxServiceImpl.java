package com.octopus.taxcube.eds.service.impl;

import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.pojo.WxAccount;
import com.octopus.taxcube.eds.pojo.WxCodeSession;
import com.octopus.taxcube.eds.service.IWxService;
import com.octopus.taxcube.exception.NotFoundException;
import com.octopus.taxcube.util.AbstractHttpClient;
import com.octopus.taxcube.util.JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WxServiceImpl extends AbstractHttpClient implements IWxService {
    @Value("${wx.auth.employee.appid}")
    private String wxEmployeeAppId;

    @Value("${wx.auth.employee.secret}")
    private String wxEmployeeSecret;

    @Value("${wx.auth.customer.appid}")
    private String wxCustomerAppId;

    @Value("${wx.auth.customer.secret}")
    private String wxCustomerSecret;

    @Value("${wx.auth.url.access-token}")
    private String wxTokenUri;

    @Value("${wx.auth.url.jscode2session}")
    private String jscodeSessionUri;

    @Value("${wx.auth.url.user}")
    private String wxUserUri;

    public String getWxTokenUri() {
        return wxTokenUri;
    }

    public void setWxTokenUri(String wxTokenUri) {
        this.wxTokenUri = wxTokenUri;
    }

    public String getWxUserUri() {
        return wxUserUri;
    }

    public void setWxUserUri(String wxUserUri) {
        this.wxUserUri = wxUserUri;
    }

    @Override
    public WxAccount geWxAccountByCode(String code) {

//        String accessTokenByCodeUrl = String.format("%s?grant_type=authorization_code&appid=%s&secret=%s&jscode=%s",
//                wxTokenUri, wxAppId, wxSecret, code);
////        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx3c3f80735e783b73&secret=3c6a6e120100d2518cd19089267fbc5a&code=061j93aZ1lGXlZ0JPw8Z1t7J9Z1j93ai&grant_type=authorization_code";
//        String tokenContent = httpsRequest(accessTokenByCodeUrl, "GET", null);
//        if (null == tokenContent || tokenContent.contains(CONST.WX_ERROR)) {
//            return null;
//        }
//        Token token = JsonUtil.getObjet(tokenContent, Token.class);
//        String userInfoUrl = String.format("%s?access_token=%s&openid=%s", wxUserUri,
//                token.getAccess_token(), token.getOpenid());
////        String url2 = "https://api.weixin.qq.com/sns/userinfo?access_token=6_PLvnaoJ2xEeT3SMd9HnGsW16-189irP_TE13G-w8CXcFb1LZSI6Zr_GTi3rNabjoQxMa6KtiZ979jgGp6A8WpQ&openid=orwRaxLKp1BxUHuD4TuerSpRTFLw";
//        String userInfoContent = httpsRequest(userInfoUrl, "GET", null);
//        if (null == userInfoContent || userInfoContent.contains(CONST.WX_ERROR)) {
//            return null;
//        }
//        WxAccount wxAccountDto = JsonUtil.getObjet(userInfoContent, WxAccount.class);
//        wxAccountDto.setRefreshToken(token.getRefresh_token());
//        wxAccountDto.setRefreshTokenTime(new Date());
//        return wxAccountDto;

        return null;
    }

    @Override
    public WxCodeSession getJs2SessionStateByCode(String code, String role) {

        String accessTokenByCodeUrl;
        if(role.equals("EMPLOYEE")){
            accessTokenByCodeUrl = String.format(jscodeSessionUri, wxEmployeeAppId, wxEmployeeSecret, code);
        }else if(role.equals("CONSUMER")){
            accessTokenByCodeUrl = String.format(jscodeSessionUri, wxCustomerAppId, wxCustomerSecret, code);
        }else {
            throw new NotFoundException("不支持的客户端类型");
        }

//        String accessTokenByCodeUrl = ;
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx3c3f80735e783b73&secret=3c6a6e120100d2518cd19089267fbc5a&code=061j93aZ1lGXlZ0JPw8Z1t7J9Z1j93ai&grant_type=authorization_code";
        String tokenContent = httpsRequest(accessTokenByCodeUrl, "GET", null);
        if (null == tokenContent || tokenContent.contains(CONST.WX_ERROR)) {
            return null;
        }
        WxCodeSession wxCodeSession = JsonUtil.getObjet(tokenContent, WxCodeSession.class);

        return wxCodeSession;
    }


//    public Token geWxPrivilegeAcessToken() {
//
//        String accessToken = String.format("%s?grant_type=client_credential&appid=%s&secret=%s",
//                "https://api.weixin.qq.com/cgi-bin/token",
//                wxAppId, wxSecret);
////       https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx3c3f80735e783b73&secret=3c6a6e120100d2518cd19089267fbc5a
//        String tokenContent = httpsRequest(accessToken, "GET", null);
//        if (null == tokenContent) {
////            LoggerHelper.error("tokenContent:" + JsonHelper.allToJson(tokenContent));
////            LoggerHelper.error("accessToken:" + JsonHelper.allToJson(accessToken));
//            return null;
//        }
//        Token token = JsonUtil.getObjet(tokenContent, Token.class);
//        return token;
//    }


//    public String getWxTicketByAccessToken(String accessToken) {
//        // https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
//        String ticketUrl= String.format("%s?access_token=%s&type=jsapi",
//                "https://api.weixin.qq.com/cgi-bin/ticket/getticket",
//                accessToken);
//        // String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx3c3f80735e783b73&secret=3c6a6e120100d2518cd19089267fbc5a&code=061j93aZ1lGXlZ0JPw8Z1t7J9Z1j93ai&grant_type=authorization_code";
//        String ticketContent = httpsRequest(ticketUrl, "GET", null);
//
//        // String ticketContent = "{\"errcode\":0,\"errmsg\":\"ok\",\"ticket\":\"kgt8ON7yVITDhtdwci0qeRlD8TsIGS5cWwm5IDbDAhCJWSViN3MaT-_XTXzLkFeL9p7MaAEv4pPjNLFXBn-rhw\",\"expires_in\":7200}";
//
//        if (null == ticketContent ) {
//            return null;
//        }
//        TicketPrivilegeDto ticketPrivilegeDto = JsonHelper.valueOf(ticketContent, TicketPrivilegeDto.class);
//        if (null != ticketContent && ticketPrivilegeDto.getErrcode() == 40001){
//            LoggerHelper.error("ticketContent:" + JsonHelper.allToJson(ticketContent));
//            LoggerHelper.error("ticketUrl:" + JsonHelper.allToJson(ticketUrl));
//            return null;
//        }
//        return ticketPrivilegeDto.getTicket();
//    }
}

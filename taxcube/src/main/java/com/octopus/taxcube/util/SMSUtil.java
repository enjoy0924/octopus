package com.octopus.taxcube.util;

import lombok.extern.slf4j.Slf4j;

/*
* @Author zhangxin_an 
* @Date 2018/5/12 16:55
* @Description:
*/  
@Slf4j
public class SMSUtil {

    /*
    * @Author zhangxin_an 
    * @Date 2018/5/12 15:17
    * @Params []  
    * @Return java.lang.String  
    * @Description:随机验证码
    */  
    public static String createRandomVcode(){
        //验证码
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }

}


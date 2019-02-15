package com.octopus.taxcube.api;

import com.octopus.taxcube.common.ServerResponse;
import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.service.ISmsService;
import com.octopus.taxcube.util.JsonUtil;
import com.octopus.taxcube.util.RedisOperator;
import com.octopus.taxcube.util.SMSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
@Api(value = "短信",tags = "短信接口")
public class SMSApi {

    @Autowired
    private RedisOperator redisOperator;

    @Value("${sms.aliyun.templateId.validate}")
    private String validateTemplateId;

    @Value("${sms.timeout}")
    private Long smsTimeout;

    @Autowired
    private ISmsService smsService;

    @RequestMapping(value = "/sms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取短信")
    @ResponseBody
    public ServerResponse<String> sendSMS(
            @ApiParam(name = "phone", value = "手机号: 159xxxx9875", required = true) @RequestParam String phone
    ) {

        String captcha = SMSUtil.createRandomVcode();

        Map<String, String> params = new HashMap<>();
        params.put("code", captcha);
        int err = smsService.sendSMSCode(phone, captcha, validateTemplateId, JsonUtil.getJsonByObj(params));
        if(err == CONST.ERROR_CODE_OK) {
            redisOperator.set(phone, captcha, smsTimeout);
            return ServerResponse.createBySuccess();
        }else {
            return ServerResponse.createByErrorCodeMessage(-1, "发送短信失败!");
        }
    }

    @RequestMapping(value = "/sms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.DELETE})
    @ApiOperation("验证短信")
    @ResponseBody
    public ServerResponse<String> validateSMS(
            @ApiParam(name = "phone", value = "手机号: 159xxxx9875", required = true) @RequestParam String phone,
            @ApiParam(name = "captcha", value = "验证码", required = true) @RequestParam String captcha
    ) {

        String captchaToValidate = redisOperator.get(phone);
        redisOperator.del(phone);
        if(null != captchaToValidate && captchaToValidate.equals(captcha)){
            return ServerResponse.createBySuccess();
        }else {
            return ServerResponse.createByErrorCodeMessage(-1, "短信验证失败");
        }
    }
}
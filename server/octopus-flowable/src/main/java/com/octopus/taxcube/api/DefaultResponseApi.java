package com.octopus.taxcube.api;

import com.octopus.taxcube.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class DefaultResponseApi {

    @RequestMapping("login")
    @ResponseBody
    public Result defaultLogin(){
        return Result.error("未登录");
    }

    @RequestMapping("unauthorized")
    @ResponseBody
    public Result defaultError(){
        return Result.error("未授权");
    }
}

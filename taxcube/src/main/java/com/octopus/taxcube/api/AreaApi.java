package com.octopus.taxcube.api;

import com.octopus.taxcube.common.ServerResponse;
import com.octopus.taxcube.util.AreaUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
@Api(value = "区域",tags = "区域接口")
public class AreaApi {

    @RequestMapping(value = "/area", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取区域信息")
    @ResponseBody
    public ServerResponse<String> listOfArea() {

        return ServerResponse.createBySuccess(AreaUtils.getArea());
    }

}

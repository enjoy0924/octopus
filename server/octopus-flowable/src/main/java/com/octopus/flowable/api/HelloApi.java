package com.octopus.flowable.api;

import com.octopus.flowable.model.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
@Api(value = "测试类",tags = "测试接口")
public class HelloApi {

    @RequestMapping(value = "testData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation("测试读写分离")
    @ResponseBody
    public Person testDateSource(
            @ApiParam(name = "userCode", value = "用户id", required = true)
            @RequestParam Integer userCode) {
//        User user = testService.selectByUserCode(userCode);
//        Integer integer = testService.insertUser(user);
        Person person = new Person();
        person.setId(8888);
        person.setName("Andy");

        return person;
    }
}
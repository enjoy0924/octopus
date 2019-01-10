package com.octopus.taxcube.api;

import com.octopus.taxcube.common.CONST;
import com.octopus.taxcube.common.Result;
import com.octopus.taxcube.eds.entity.User;
import com.octopus.taxcube.model.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
@Api(value = "用户账户",tags = "用户接口")
public class AccountApi {

    @RequestMapping(value = "/sys/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation("系统用户登录接口")
    @ResponseBody
    public Result sysLogin(
            @ApiParam(name = "phone", value = "系统用户的电话", required = true) @RequestParam String phone,
            @ApiParam(name = "password", value = "系统用户的密码", required = true) @RequestParam String password) {

        try {

            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
            subject.login(token);

            return Result.ok("登录成功");

        } catch (DisabledAccountException e) {
            return Result.error("账户已被禁用");
        } catch (IncorrectCredentialsException e){
            return Result.error("账户密码错误");
        } catch (AuthenticationException e) {
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.DELETE})
    @ApiOperation("退出登录接口")
    @ResponseBody
    public Result sysLogout() {
        Subject subject = SecurityUtils.getSubject();
        if(null != subject && subject.isAuthenticated()){
            subject.logout();
        }

        return Result.ok();
    }


    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/sys/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取所有的系统用户")
    @ResponseBody
    public Result listOfUser() {

        Person person = new Person();
        person.setId(2131434);
        person.setName("Andy");
        List<Person> list = new ArrayList<>();
        list.add(person);

        return Result.ok().setData(list);
    }




    @RequestMapping(value = "/wechat/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("微信登录接口")
    @ResponseBody
    public Result wechatLogin(
            @ApiParam(name = "openId", value = "微信小程序的openId", required = true)
            @RequestParam String openId) {

        Person person = new Person();
        person.setId(8888);
        person.setName("Andy");

        return Result.ok().setData(person);
    }


//    @RequestMapping(value = "testData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
//    @ApiOperation("测试读写分离")
//    @ResponseBody
//    public Person testDateSource(
//            @ApiParam(name = "userCode", value = "用户id", required = true)
//            @RequestParam Integer userCode) {
//
//        Person person = new Person();
//        person.setId(8888);
//        person.setName("Andy");
//
//        return person;
//    }


}
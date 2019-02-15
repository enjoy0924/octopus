package com.octopus.taxcube.api;

import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.common.ServerResponse;
import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.pojo.*;
import com.octopus.taxcube.eds.service.IAccountService;
import com.octopus.taxcube.eds.service.ICertService;
import com.octopus.taxcube.eds.service.IWxService;
import com.octopus.taxcube.shiro.JWTUtil;
import com.octopus.taxcube.util.EdsUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("/api")
@Api(value = "用户账户",tags = "用户接口")
public class AccountApi {

    @Autowired
    private IWxService wxService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ICertService certService;


    @RequestMapping(value = "/login/{method}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation("用户登录接口")
    @ResponseBody
    public ServerResponse<User> sysLogin(
            @ApiParam(name = "method", value = "登录的方式(sys | wechat | sms)", required = true) @PathVariable("method") String method,
            @ApiParam(name = "username", value = "系统用户的用户名", required = true) @RequestParam String username,
            @ApiParam(name = "password", value = "系统用户的密码", required = false) @RequestParam(value = "password",defaultValue = "", required = true) String password) {

        try {

            User user = accountService.login(method, username, password);

            return ServerResponse.createBySuccess(user);

        } catch (DisabledAccountException e) {
            return ServerResponse.createDefaultErrorMessage("账户已被禁用");
        } catch (IncorrectCredentialsException e){
            return ServerResponse.createDefaultErrorMessage("账户密码错误");
        } catch (AuthenticationException e) {
            return ServerResponse.createDefaultErrorMessage(e.getMessage());
        }
    }

    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/sys/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取所有的系统用户")
    @ResponseBody
    public ServerResponse<List<User>> listOfUser() {

        List<User> users = accountService.findListOfAdminUsers();

        return ServerResponse.createBySuccess(users);
    }

    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/sys/user/{userId}/status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("(禁用/启用)系统用户")
    @ResponseBody
    public ServerResponse changeStatusOfUser(
            @ApiParam(name = "userId", value = "userId", required = true) @PathVariable("userId") Long userId,
            @ApiParam(name = "status", value = "status", required = true) @RequestParam("status") String status
    ) {

        accountService.updateUserStatusByIdAndStatus(userId, status);

        return ServerResponse.createBySuccess();
    }

    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/sys/user/{userId}/password", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("重置系统用户密码")
    @ResponseBody
    public ServerResponse resetPwdOfUser(
            @ApiParam(name = "userId", value = "userId", required = true) @PathVariable("userId") Long userId,
            @ApiParam(name = "password", value = "password", required = true) @RequestParam("password") String password
    ) {

        accountService.updateUserPasswordByIdAndStatus(userId, password);

        return ServerResponse.createBySuccess();
    }


    @RequestMapping(value = "/sys/user/password", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("用户修改密码")
    @ResponseBody
    public ServerResponse changePwdOfUser(
            @ApiParam(name = "oldPwd", value = "old password", required = true) @RequestParam("oldPwd") String oldPwd,
            @ApiParam(name = "password", value = "password", required = true) @RequestParam("password") String password
    ) {

        int err = accountService.updateUserPasswordByIdAndPassword(JWTUtil.getUserId(), oldPwd, password);
        if(err == CONST.ERROR_CODE_OK) {
            return ServerResponse.createBySuccess();
        }else {
            return ServerResponse.createByErrorCodeMessage(-1, "修改密码失败");
        }
    }

    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/sys/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation("新增系统用户")
    @ResponseBody
    public ServerResponse addUser(
            @ApiParam(name = "name", value = "name", required = true) @RequestParam("name") String name,
            @ApiParam(name = "phone", value = "phone", required = true) @RequestParam("phone") String phone,
            @ApiParam(name = "password", value = "password", required = true) @RequestParam("password") String password
    ) {

        Long userId = accountService.addUserByNameAndPhoneAndPassword(name, phone, password);

        return ServerResponse.createBySuccess(userId);
    }

    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/sys/user/{userId}/star", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("设置星标用户")
    @ResponseBody
    public ServerResponse starUser(
            @ApiParam(name = "userId", value = "用户Id", required = true) @PathParam("userId") Long userId
    ) {
        accountService.setUserOfferTypeById(userId, CONST.OFFER_TYPE_STAR);
        return ServerResponse.createBySuccess();
    }

    @RequiresRoles(value = CONST.ACCOUNT_TYPE_SYSADMIN)
    @RequestMapping(value = "/sys/user/{userId}/star", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.DELETE})
    @ApiOperation("撤销星标用户")
    @ResponseBody
    public ServerResponse delStarUser(
            @ApiParam(name = "userId", value = "用户Id", required = true) @PathParam("userId") Long userId
    ) {
        accountService.setUserOfferTypeById(userId, CONST.OFFER_TYPE_NORMAL);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "/wechat/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("微信授权接口")
    @ResponseBody
    public ServerResponse<WxCodeSession> wechatLogin(
            @ApiParam(name = "code", value = "微信code", required = true) @RequestParam String code,
            @ApiParam(name = "role", value = "角色类型{ CONSUMER | EMPLOYEE }", required = true) @RequestParam String role) {


        if (StringUtils.isEmpty(code)) {
            return ServerResponse.createDefaultErrorMessage("错误值");
        }

        WxCodeSession wxCodeSession = wxService.getJs2SessionStateByCode(code, role);

        if (null == wxCodeSession) {
            return ServerResponse.createDefaultErrorMessage("微信授权失败");
        }

        accountService.saveNewOpenId(wxCodeSession.getOpenid(), role);

        return ServerResponse.createBySuccess(wxCodeSession);
    }


    @RequiresRoles(CONST.ACCOUNT_TYPE_EMPLOYEE)
    @RequestMapping(value = "/apply/offer", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation("新增业务员申请服务")
    @ResponseBody
    public ServerResponse<Long> applyOfferService(
            @ApiParam(name = "name", value = "名字: 真实姓名", required = false) @RequestParam String name,
            @ApiParam(name = "phone", value = "手机号: 159xxxx9875", required = false) @RequestParam String phone,
            @ApiParam(name = "idNumber", value = "身份证号码: 510724xxxxxxxx4313", required = false) @RequestParam String idNumber,
            @ApiParam(name = "idcardImages", value = "身份证正反面照(逗号隔开)-如:img0.jpg,img1.jpg", required = false) @RequestParam String idcardImages,
            @ApiParam(name = "certificateImages", value = "资格证书-如:img0.jpg,img1.jpg", required = false) @RequestParam String certificateImages,
            @ApiParam(name = "locations", value = "区域名称(逗号隔开)-如:四川省-成都市-武侯区,四川省-成都市-天府新区", required = true) @RequestParam String locations,
            @ApiParam(name = "offers", value = "可提供的业务范围-如:1,4,5", required = true) @RequestParam String offers,
            @ApiParam(name = "status", value = "草稿: DRAFT  直接申请: APPLYING", required = true) @RequestParam String status
            ) {


        ApplyOffer applyOffer = EdsUtils.composeApplyOffer(JWTUtil.getUserId(), name, phone, idNumber, locations, offers, certificateImages, idcardImages);
        applyOffer.setStatus(status);

        Long applyId = accountService.applyOffer(applyOffer);

        return ServerResponse.createBySuccess(applyId);
    }


    @RequiresRoles(CONST.ACCOUNT_TYPE_EMPLOYEE)
    @RequestMapping(value = "/apply/offer/latest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取业务员最近一次的申请")
    @ResponseBody
    public ServerResponse<ApplyOfferPlus> latestApplyOfferService() {

        ApplyOfferPlus applyOffer = accountService.findLatestApplyOffedByApplierId(JWTUtil.getUserId());

        return ServerResponse.createBySuccess(applyOffer);
    }

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN, CONST.ACCOUNT_TYPE_EMPLOYEE, CONST.ACCOUNT_TYPE_CONSUMER}, logical = Logical.OR)
    @RequestMapping(value = "/account/phone", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("绑定手机号")
    @ResponseBody
    public ServerResponse changePhone(
            @ApiParam(name = "phone", value = "新电话号码", required = false) @RequestParam String phone,
            @ApiParam(name = "name", value = "名字", required = false) @RequestParam String name
    ) {

        accountService.changePhoneByUserIdAndPhoneNumberAndName(JWTUtil.getUserId(), phone, name);

        return ServerResponse.createBySuccess();
    }

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN,CONST.ACCOUNT_TYPE_SYSADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/sys/apply/offers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("系统业务员获取申请列表")
    @ResponseBody
    public ServerResponse<PageModel<ApplyOfferPlus>> applyOfferService(
            @ApiParam(name = "status", value = "申请: APPLYING 审核通过: DONE 审核拒绝: REFUSE", required = true) @RequestParam String status,
            @ApiParam(name = "pageNum", value = "当前页数", required = true) @RequestParam Integer pageNum,
            @ApiParam(name = "limit", value = "每页数据大小", required = true) @RequestParam Integer limit
    ) {

        PageModel pageModel = new PageModel<>();
        pageModel.setLimit(limit);
        pageModel.setPageNum(pageNum);
        pageModel.setOrderBy("create_time desc");

        PageModel<ApplyOfferPlus> applyOffers = accountService.findPageOfApplyOffersByStatus(status, pageModel);

        return ServerResponse.createBySuccess(applyOffers);
    }

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN,CONST.ACCOUNT_TYPE_SYSADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/sys/apply/offer/{applyId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("系统用户审核业务员申请")
    @ResponseBody
    public ServerResponse auditApplyOfferService(
            @ApiParam(name = "applyId", value = "已经申请的applyId", required = true) @PathVariable Long applyId,
            @ApiParam(name = "status", value = "审核通过: DONE 审核拒绝: REFUSE", required = true) @RequestParam String status,
            @ApiParam(name = "remark", value = "意见建议", required = true) @RequestParam String remark
    ) {

        int err = accountService.auditApplyOfferByAuditorIdAndApplyIdAndStatusAndRemark(JWTUtil.getUserId(), applyId,status,remark);
        if(err == CONST.ERROR_CODE_STATE){
            return ServerResponse.createByErrorCodeMessage(-1, "申请单状态不允许审核");
        }else if(err == CONST.ERROR_CODE_NOT_FOUND){
            return ServerResponse.createByErrorCodeMessage(-1, "找不到该申请单");
        }else {
            return ServerResponse.createBySuccess();
        }
    }

    @RequiresRoles(CONST.ACCOUNT_TYPE_EMPLOYEE)
    @RequestMapping(value = "/apply/offer/{applyId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("修改业务员申请服务")
    @ResponseBody
    public ServerResponse<Long> changeApplyOfferService(
            @ApiParam(name = "applyId", value = "已经申请的applyId", required = true) @PathVariable Long applyId,
            @ApiParam(name = "name", value = "名字: 真实姓名", required = false) @RequestParam String name,
            @ApiParam(name = "phone", value = "手机号: 159xxxx9875", required = false) @RequestParam String phone,
            @ApiParam(name = "idNumber", value = "身份证号码: 510724xxxxxxxx4313", required = false) @RequestParam String idNumber,
            @ApiParam(name = "idcardImages", value = "身份证正反面照(逗号隔开)-如:img0.jpg,img1.jpg", required = false) @RequestParam String idcardImages,
            @ApiParam(name = "certificateImages", value = "资格证书-如:img0.jpg,img1.jpg", required = false) @RequestParam String certificateImages,
            @ApiParam(name = "locations", value = "区域名称(逗号隔开)-如:四川省-成都市-武侯区,四川省-成都市-天府新区", required = true) @RequestParam String locations,
            @ApiParam(name = "offers", value = "可提供的业务范围-如:1,4,5", required = true) @RequestParam String offers
    ) {


        Long userId = JWTUtil.getUserId();
        ApplyOffer applyOfferLatest = accountService.findLatestApplyOffedByApplierId(userId);
        if(null != applyOfferLatest && applyOfferLatest.getStatus().equals(CONST.STATUS_APPLYING)){
            return ServerResponse.createByErrorCodeMessage(CONST.ERROR_CODE_FAILED, "请等待上一个申请审批完成!");
        }else {

            ApplyOffer applyOffer = EdsUtils.composeApplyOffer(userId, name, phone, idNumber, locations, offers, certificateImages,idcardImages);

            applyOffer.setApplyId(applyId);
            accountService.changeApplyOffer(applyOffer);

            return ServerResponse.createBySuccess();
        }
    }


    @RequestMapping(value = "/service/offer/{empId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取业务员提供的业务区域和范围")
    @ResponseBody
    public ServerResponse<List<ServiceOfferRange>> getRangeOfServiceOffered(
            @ApiParam(name = "empId", value = "业务员Id", required = true) @PathVariable Long empId
    ) {

        List<ServiceOfferRange> serviceOfferRanges = accountService.findServiceOfferedByEmpId(empId);

        return ServerResponse.createBySuccess(serviceOfferRanges);
    }

    @RequestMapping(value = "/service/{prodId}/employees", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取某个服务(某个区域)下面的业务员列表")
    @ResponseBody
    public ServerResponse<List<UserBrief>> getEmployeesOfServiceOffered(
            @ApiParam(name = "prodId", value = "产品Id", required = true) @PathVariable Long prodId,
            @ApiParam(name = "location", value = "区域信息", required = true) @RequestParam String location
    ) {

        List<UserBrief> userBriefs = accountService.findEmployeesOfServiceOfferedByProdIdAndLocation(prodId, location, true);

        return ServerResponse.createBySuccess(userBriefs);
    }

    @RequestMapping(value = "/cert/apply/{type}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("申请认证")
    @ResponseBody
    public ServerResponse certApply(
            @ApiParam(name = "type", value = "认证类型: certificate | idcard", required = true) @PathVariable String type,
            @ApiParam(name = "images", value = "图片名称列表: 逗号分隔", required = true) @RequestParam String images
    ){
        certService.applyNewCert(type, images, JWTUtil.getUserId());
        return ServerResponse.createBySuccess();
    }
}
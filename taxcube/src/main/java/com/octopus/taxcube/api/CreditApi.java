package com.octopus.taxcube.api;

import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.common.ServerResponse;
import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.pojo.CreditExchange;
import com.octopus.taxcube.eds.pojo.CreditItem;
import com.octopus.taxcube.eds.pojo.CreditItemPlus;
import com.octopus.taxcube.eds.pojo.CreditSummary;
import com.octopus.taxcube.eds.service.ICreditService;
import com.octopus.taxcube.shiro.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@Api(value = "积分",tags = "积分接口")
public class CreditApi {

    @Autowired
    private ICreditService creditService;

    @RequiresRoles(value = CONST.ACCOUNT_TYPE_EMPLOYEE)
    @RequestMapping(value = "/credit/apply/exchange",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation("业务员积分兑换申请")
    @ResponseBody
    public ServerResponse applyOrderCredit(
            @ApiParam(name = "creditVal", value = "申请兑换金额", required = true) @RequestParam(value = "creditVal") Long creditVal,
            @ApiParam(name = "exchangeType", value = "兑换类型", required = true) @RequestParam(value = "creditVal") String exchangeType,
            @ApiParam(name = "exchangeVal", value = "兑换数量", required = true) @RequestParam(value = "creditVal") Double exchangeVal
    ) {
        int err = creditService.createNewCreditExchange(JWTUtil.getUserId(), creditVal, exchangeType, exchangeVal);
        if(err == CONST.ERROR_CODE_OK)
            return ServerResponse.createBySuccess();
        else
            return ServerResponse.createByErrorCodeMessage(-1,"申请失败");
    }

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN, CONST.ACCOUNT_TYPE_SYSADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/credit/apply/exchange/{applyId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("系统人员处理业务员积分兑换申请")
    @ResponseBody
    public ServerResponse handleApplyOrderCredit(
            @ApiParam(name = "applyId", value = "申请Id", required = true) @PathVariable(value = "applyId") Long applyId,
            @ApiParam(name = "remark", value = "处理意见", required = true) @RequestParam(value = "remark") String remark,
            @ApiParam(name = "status", value = "处理状态", required = true) @RequestParam(value = "status") String status

    ) {

        creditService.handleCreditConsumeByApplyIdAndStatus(applyId, status, remark);

        return ServerResponse.createBySuccess();
    }


    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN, CONST.ACCOUNT_TYPE_SYSADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/credit/apply/exchanges", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("系统人员分页获取业务员积分兑换申请列表")
    @ResponseBody
    public ServerResponse<PageModel<CreditExchange>> listPageOfApplyOrderCredit(
            @ApiParam(name = "pageNum", value = "当前页数", required = true) @RequestParam Integer pageNum,
            @ApiParam(name = "limit", value = "每页数据大小", required = true) @RequestParam Integer limit,
            @ApiParam(name = "status", value = "处理状态", required = true) @RequestParam(value = "status") String status
    ) {

        PageModel pageModel = new PageModel<>();
        pageModel.setLimit(limit);
        pageModel.setPageNum(pageNum);
        pageModel.setOrderBy("create_time desc");

        PageModel<CreditExchange> creditExchangePageModel = creditService.findPageOfCreditExchangeByStatus(status, pageModel);

        return ServerResponse.createBySuccess(creditExchangePageModel);
    }


    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_CONSUMER, CONST.ACCOUNT_TYPE_EMPLOYEE}, logical = Logical.OR)
    @RequestMapping(value = "/credit/summary", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("(业务员/客户)积分详情查询")
    @ResponseBody
    public ServerResponse<CreditSummary> creditDetail() {

        List<CreditItem> creditItems = creditService.findCreditItemsByAccountId(JWTUtil.getUserId());

        CreditSummary creditSummary = new CreditSummary();
        creditSummary.setCreditItems(creditItems);

        return ServerResponse.createBySuccess(creditSummary);
    }


    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN, CONST.ACCOUNT_TYPE_SYSADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/credit/summary/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("系统管理员获取(业务员/客户)积分详情查询")
    @ResponseBody
    public ServerResponse<CreditSummary> creditDetailByUserId(
            @ApiParam(name = "userId", value = "用户ID", required = true) @PathVariable(value = "userId") Long userId
    ) {

        List<CreditItem> creditItems = creditService.findCreditItemsByAccountId(userId);
        CreditSummary creditSummary = new CreditSummary();
        creditSummary.setCreditItems(creditItems);

        return ServerResponse.createBySuccess(creditSummary);
    }

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN, CONST.ACCOUNT_TYPE_SYSADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/credit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation("系统管理员奖惩(增加或扣除)积分")
    @ResponseBody
    public ServerResponse createCreditItem(@RequestBody CreditItemPlus creditItemPlus) {

        creditService.createCreditByCreditItem(creditItemPlus, JWTUtil.getUserId());

        return ServerResponse.createBySuccess();
    }
}

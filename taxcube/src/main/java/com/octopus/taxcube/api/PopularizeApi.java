package com.octopus.taxcube.api;

import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.common.ServerResponse;
import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.pojo.Popularization;
import com.octopus.taxcube.eds.service.IPopularizeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
@Api(value = "积分",tags = "推广接口")
public class PopularizeApi {

    @Autowired
    private IPopularizeService popularizeService;

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_EMPLOYEE, CONST.ACCOUNT_TYPE_CONSUMER}, logical = Logical.OR)
    @RequestMapping(value = "/popularize", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation("推广关系绑定")
    @ResponseBody
    public ServerResponse popularize(
            @ApiParam(name = "popularizeId", value = "推广人Id", required = true) @RequestParam(value = "popularizeId") Long popularizeId,
            @ApiParam(name = "popularizedId", value = "被推广人Id", required = true) @RequestParam(value = "popularizedId") Long popularizedId

    ) {

        popularizeService.createNewPopularization(popularizeId, popularizedId);

        return ServerResponse.createBySuccess();
    }

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN, CONST.ACCOUNT_TYPE_SYSADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/popularize", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("分页获取推广关系")
    @ResponseBody
    public ServerResponse<PageModel<Popularization>> getPopularize(
            @ApiParam(name = "status", value = "推广状态(ENABLE DISABLE)", required = true) @RequestParam(value = "status") String status,
            @ApiParam(name = "pageNum", value = "当前页数", required = true) @RequestParam Integer pageNum,
            @ApiParam(name = "limit", value = "每页数据大小", required = true) @RequestParam Integer limit
    ) {

        PageModel pageModel = new PageModel<>();
        pageModel.setLimit(limit);
        pageModel.setPageNum(pageNum);
        pageModel.setOrderBy("create_time desc");

        PageModel<Popularization> popularizationPageModel = popularizeService.findPageOfPopularizationByStatus(status, pageModel);

        return ServerResponse.createBySuccess(popularizationPageModel);
    }

}
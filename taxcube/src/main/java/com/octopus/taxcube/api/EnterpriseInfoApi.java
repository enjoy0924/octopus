package com.octopus.taxcube.api;

import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.common.ServerResponse;
import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.pojo.EnterpriseInfo;
import com.octopus.taxcube.eds.service.IEnterpriseService;
import com.octopus.taxcube.shiro.JWTUtil;
import com.octopus.taxcube.util.JsonUtil;
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
@Api(value = "工商信息",tags = "工商信息接口")
public class EnterpriseInfoApi {

    @Autowired
    private IEnterpriseService enterpriseService;

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_SYSADMIN, CONST.ACCOUNT_TYPE_ADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/enterprise/info",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation("录入信息的公司工商信息")
    @ResponseBody
    public ServerResponse addEnterpriseInfo(
            @ApiParam(name = "info", value = "企业工商信息{\"taxCode\":\"9087674375437L\",\"name\":\"四川爱里尔科技有限公司\",\"telephone\":\"0289898783\",\"address\":\"四川省成都市高新区锦城大道666号\"}", required = true) @RequestParam(value = "info") String info
    ){
        EnterpriseInfo enterpriseInfo = JsonUtil.getObjet(info, EnterpriseInfo.class);

        try {
            enterpriseService.createNewEnterpriseInfo(enterpriseInfo, JWTUtil.getUserId());
        }catch (Exception e){
            if(null != e.getMessage() && e.getMessage().contains("Duplicate entry")){
                return ServerResponse.createByErrorCodeMessage(CONST.ERROR_CODE_EXIST,"数据已经存在");
            }
        }

        return ServerResponse.createBySuccess();
    }


    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_SYSADMIN, CONST.ACCOUNT_TYPE_ADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/enterprise/info/{taxId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.DELETE})
    @ApiOperation("删除公司工商信息")
    @ResponseBody
    public ServerResponse delEnterpriseInfo(
            @ApiParam(name = "taxId", value = "公司税号", required = true) @RequestParam(value = "taxId") String taxId
    ){
        enterpriseService.delEnterpriseInfo(taxId);
        return ServerResponse.createBySuccess();
    }


    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_SYSADMIN, CONST.ACCOUNT_TYPE_ADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/enterprise/info/{taxId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取公司工商信息")
    @ResponseBody
    public ServerResponse<EnterpriseInfo> getEnterpriseInfo(
            @ApiParam(name = "taxId", value = "公司税号", required = true) @RequestParam(value = "taxId") String taxId
    ){
        EnterpriseInfo enterpriseInfo = enterpriseService.findEnterpriseInfoByTaxId(taxId);
        if (null == enterpriseInfo){
            return ServerResponse.createByErrorCodeMessage(CONST.ERROR_CODE_NOT_FOUND, "查不到记录");
        }else {
            return ServerResponse.createBySuccess(enterpriseInfo);
        }
    }


    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_SYSADMIN, CONST.ACCOUNT_TYPE_ADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/enterprise/infos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("分页获取公司工商信息")
    @ResponseBody
    public ServerResponse<PageModel<EnterpriseInfo>> listPageOfEnterpriseInfos(
            @ApiParam(name = "status", value = "状态", required = true) @RequestParam String status,
            @ApiParam(name = "pageNum", value = "当前页数", required = true) @RequestParam Integer pageNum,
            @ApiParam(name = "limit", value = "每页数据大小", required = true) @RequestParam Integer limit
    ){

        PageModel pageModel = new PageModel<>();
        pageModel.setLimit(limit);
        pageModel.setPageNum(pageNum);
        pageModel.setOrderBy("name desc");

        PageModel<EnterpriseInfo> enterpriseInfoPageModel = enterpriseService.findPageOfEnterpriseInfosByStatus(status, pageModel);

        return ServerResponse.createBySuccess(enterpriseInfoPageModel);
    }
}

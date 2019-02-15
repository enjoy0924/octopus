package com.octopus.taxcube.api;

import com.octopus.taxcube.annotation.SysLog;
import com.octopus.taxcube.common.ServerResponse;
import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.pojo.Prod;
import com.octopus.taxcube.eds.pojo.ProdCatalog;
import com.octopus.taxcube.eds.pojo.ProdTask;
import com.octopus.taxcube.eds.service.IProdService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@Api(value = "产品",tags = "产品接口")
public class ProductApi {

    @Autowired
    private IProdService prodService;

    /**
     * 获取所有产品的大分类
     *
     * @return
     */
    @RequestMapping(value = "/product/catalogs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取产品大分类")
    @ResponseBody
    public ServerResponse<List<ProdCatalog>> listOfProdCatalogs() {

        List<ProdCatalog> list = prodService.findProdCatalogs();

        return ServerResponse.createBySuccess(list);
    }

    @RequestMapping(value = "/product/{id}/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取产品服务明细")
    @ResponseBody
    public ServerResponse<List<ProdTask>> listOfProdTasks(
            @ApiParam(name = "id", value = "产品Id", required = true) @PathVariable(value = "id") Long prodId
    ) {

        List<ProdTask> list = prodService.findProdTasksByProdId(prodId);

        return ServerResponse.createBySuccess(list);
    }


    @RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取产品列表")
    @ResponseBody
    public ServerResponse<List<Prod>> listOfProdServices() {

        List<Prod> list = prodService.findProdServices();

        return ServerResponse.createBySuccess(list);
    }


    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN,CONST.ACCOUNT_TYPE_SYSADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("修改产品信息")
    @ResponseBody
    public ServerResponse<List<Prod>> changeProd(
            @ApiParam(name = "id", value = "产品Id", required = true) @PathVariable(value = "id") Long prodId,
            @ApiParam(name = "prodJson", value = "产品对象的Json串: {\"catalogId\": 0,\"creditType\": \"{NONE | SEASON}\",\"descImage\": \"{img.jpg}\",\"description\":\"产品描述\",\"descPrice\": \"价格描述\",\"name\": \"产品名称\",\"price\": 0,\"priceType\": \"{FIXED | FLOAT}\",\"titleImage\": \"{img.jpg}\"}", required = true) String prodJson) {

        Prod prod = JsonUtil.getObjet(prodJson, Prod.class);

        prod.setId(prodId);
        prodService.changeOrCreateProdInfo(prod, JWTUtil.getUserId(), true);

        return ServerResponse.createBySuccess();
    }

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN,CONST.ACCOUNT_TYPE_SYSADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/product/{id}/rack", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("产品上下架")
    @ResponseBody
    @SysLog("产品上下架")
    public ServerResponse<List<Prod>> rackProd(
            @ApiParam(name = "id", value = "产品Id", required = true) @PathVariable(value = "id") Long prodId,
            @ApiParam(name = "status", value = "上下架状态 ONLINE OFFLINE", required = true) @RequestParam(value = "status") String status) {

        prodService.changeProdStatus(prodId, JWTUtil.getUserId(), status);

        return ServerResponse.createBySuccess();
    }

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN,CONST.ACCOUNT_TYPE_SYSADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation("新增产品信息")
    @ResponseBody
    public ServerResponse<List<Prod>> addProd(
            @ApiParam(name = "prodJson", value = "产品对象的Json串: {\"catalogId\": 0,\"creditType\": \"{NONE | SEASON}\",\"descImage\": \"{img.jpg}\",\"descPrice\": \"价格描述\",\"description\":\"产品描述\",\"name\": \"产品名称\",\"price\": 0,\"priceType\": \"{FIXED | FLOAT}\",\"titleImage\": \"{img.jpg}\"}", required = true) String prodJson
    ) {

        Prod prod = JsonUtil.getObjet(prodJson, Prod.class);
        prodService.changeOrCreateProdInfo(prod, JWTUtil.getUserId(), false);

        return ServerResponse.createBySuccess();
    }

}

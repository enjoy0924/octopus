package com.octopus.taxcube.api;

import com.octopus.taxcube.annotation.SysLog;
import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.common.ServerResponse;
import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.pojo.OrderBrief;
import com.octopus.taxcube.eds.pojo.OrderInfo;
import com.octopus.taxcube.eds.pojo.OrderProdTask;
import com.octopus.taxcube.eds.pojo.PayBrief;
import com.octopus.taxcube.eds.pojo.order.*;
import com.octopus.taxcube.eds.service.IOrderService;
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
import java.util.Map;

@Controller
@RequestMapping("/api")
@Api(value = "订单",tags = "订单接口")
public class OrderApi {

    @Autowired
    private IOrderService orderService;

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_SYSADMIN, CONST.ACCOUNT_TYPE_ADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/order/custom/{customerId}/orders", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("系统人员分页获取某个客户订单列表")
    @ResponseBody
    public ServerResponse<PageModel<OrderBrief>> listOfCustomerOrders(
            @ApiParam(name = "customerId", value = "客户Id", required = true) @PathVariable(value = "customerId") Long customerId,
            @ApiParam(name = "status", value = "全部'ALL',待报价'QUOTATION',废除'DISCARD',未支付'NOTPAY',待分配'NOTDISTRIBUTE',服务中'SERVING',已完成'DONE'", required = true) @RequestParam String status,
            @ApiParam(name = "pageNum", value = "当前页数", required = true) @RequestParam Integer pageNum,
            @ApiParam(name = "limit", value = "每页数据大小", required = true) @RequestParam Integer limit
    ) {

        PageModel pageModel = new PageModel<>();
        pageModel.setLimit(limit);
        pageModel.setPageNum(pageNum);
        pageModel.setOrderBy("create_time desc");

        PageModel<OrderBrief> orderBriefPageModel = orderService.findPageOfOrdersByCustomerIdAndStatus(customerId, status, pageModel);

        return ServerResponse.createBySuccess(orderBriefPageModel);
    }

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_CONSUMER})
    @RequestMapping(value = "/order/custom/orders", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("客户分页获取订单列表")
    @ResponseBody
    public ServerResponse<PageModel<OrderBrief>> listPageOfCustomerOrders(
            @ApiParam(name = "status", value = "全部'ALL',待报价'QUOTATION',废除'DISCARD',未支付'NOTPAY',待分配'NOTDISTRIBUTE',服务中'SERVING',已完成'DONE'", required = true) @RequestParam String status,
            @ApiParam(name = "pageNum", value = "当前页数", required = true) @RequestParam Integer pageNum,
            @ApiParam(name = "limit", value = "每页数据大小", required = true) @RequestParam Integer limit
    ) {

        PageModel pageModel = new PageModel<>();
        pageModel.setLimit(limit);
        pageModel.setPageNum(pageNum);
        pageModel.setOrderBy("create_time desc");

        PageModel<OrderBrief> orderBriefPageModel = orderService.findPageOfOrdersByCustomerIdAndStatus(JWTUtil.getUserId(), status, pageModel);

        return ServerResponse.createBySuccess(orderBriefPageModel);
    }


    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_SYSADMIN, CONST.ACCOUNT_TYPE_ADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/order/serve/{employeeId}/orders", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("系统人员分页获取业务员订单列表")
    @ResponseBody
    public ServerResponse<PageModel<OrderBrief>> listOfServeOrders(
            @ApiParam(name = "employeeId", value = "业务员Id", required = true) @PathVariable(value = "employeeId") Long employeeId,
            @ApiParam(name = "status", value = "全部'ALL',服务中'SERVING',已完成'DONE'", required = true) @RequestParam String status,
            @ApiParam(name = "pageNum", value = "当前页数", required = true) @RequestParam Integer pageNum,
            @ApiParam(name = "limit", value = "每页数据大小", required = true) @RequestParam Integer limit
    ) {

        PageModel pageModel = new PageModel<>();
        pageModel.setLimit(limit);
        pageModel.setPageNum(pageNum);
        pageModel.setOrderBy("create_time desc");

        PageModel<OrderBrief> orderBriefPageModel = orderService.findPageOfOrdersByEmpIdAndStatus(employeeId, status, pageModel);

        return ServerResponse.createBySuccess(orderBriefPageModel);
    }


    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_EMPLOYEE})
    @RequestMapping(value = "/order/serve/orders", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("业务员分页获取订单列表")
    @ResponseBody
    public ServerResponse<PageModel<OrderBrief>> listOfServeOrders(
            @ApiParam(name = "status", value = "全部'ALL',服务中'SERVING',已完成'DONE'", required = true) @RequestParam String status,
            @ApiParam(name = "pageNum", value = "当前页数", required = true) @RequestParam Integer pageNum,
            @ApiParam(name = "limit", value = "每页数据大小", required = true) @RequestParam Integer limit
    ) {

        PageModel pageModel = new PageModel<>();
        pageModel.setLimit(limit);
        pageModel.setPageNum(pageNum);
        pageModel.setOrderBy("create_time desc");

        PageModel<OrderBrief> orderBriefPageModel = orderService.findPageOfOrdersByEmpIdAndStatus(JWTUtil.getUserId(), status, pageModel);

        return ServerResponse.createBySuccess(orderBriefPageModel);
    }


    @RequiresRoles(value = CONST.ACCOUNT_TYPE_CONSUMER)
    @RequestMapping(value = "/order/custom",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation("客户下订单")
    @ResponseBody
    public ServerResponse<String> createNewOrder(
            @ApiParam(name = "prodId", value = "产品Id", required = true) @RequestParam(value = "prodId") Long prodId,
            @ApiParam(name = "location", value = "区域信息", required = true) @RequestParam(value = "location") String location

    ) {
        OrderInfo orderInfo = orderService.createOrderByCustomerIdAndProdId(JWTUtil.getUserId(), prodId, location);

        if(null != orderInfo) {
            orderService.createOrderPayInfo(JWTUtil.getUserId(), orderInfo);
            return ServerResponse.createBySuccess(orderInfo.getOrderId());
        } else {
            return ServerResponse.createByErrorCodeMessage(-1, "订单失败");
        }
    }

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN, CONST.ACCOUNT_TYPE_SYSADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/order/{orderId}",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation("设置订单的价格")
    @ResponseBody
    @SysLog("设置订单价格")
    public ServerResponse changeOrderPrice(
            @ApiParam(name = "orderId", value = "订单Id", required = true) @PathVariable(value = "orderId") String orderId,
            @ApiParam(name = "price", value = "订单价格", required = true) @RequestParam(value = "price") Double price

    ) {
        boolean succ = orderService.changeOrderQuotationPriceByOrderId(orderId, price, JWTUtil.getUserId());

        if(succ)
            return ServerResponse.createBySuccess();
        else
            return ServerResponse.createByErrorCodeMessage(-1,"订单失败");
    }

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN, CONST.ACCOUNT_TYPE_SYSADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/order/{orderId}",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.DELETE})
    @ApiOperation("删除作废一个订单")
    @ResponseBody
    @SysLog("删除作废一个订单")
    public ServerResponse discardOrder(
            @ApiParam(name = "orderId", value = "订单Id", required = true) @PathVariable(value = "orderId") String orderId
    ) {

        orderService.deleteOrderByOrderId(orderId);

        return ServerResponse.createBySuccess();
    }


    @RequestMapping(value = "/order/pay/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取订单支付明细")
    @ResponseBody
    public ServerResponse<List<PayBrief>> orderPayInfo(
            @ApiParam(name = "orderId", value = "订单Id", required = true) @PathVariable(value = "orderId") String orderId

    ) {

        List<PayBrief> payBriefs = orderService.findPayBriefByOrderId(orderId);

        return ServerResponse.createBySuccess(payBriefs);
    }

    @RequiresRoles(value = CONST.ACCOUNT_TYPE_CONSUMER)
    @RequestMapping(value = "/order/pay/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("支付订单")
    @ResponseBody
    public ServerResponse orderPay(
            @ApiParam(name = "orderId", value = "订单Id", required = true) @PathVariable(value = "orderId") String orderId

    ) {
        //TODO 支付
        Map<String, String> resp = orderService.prepayOrder(orderId);
        if(null != resp && !resp.isEmpty()) {
            return ServerResponse.createBySuccess(resp);
        }else {
            return ServerResponse.createByErrorCodeMessage(-1, "获取支付数据失败");
        }
    }


    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_SYSADMIN, CONST.ACCOUNT_TYPE_ADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/admin/order/pay/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("系统操作支付订单")
    @ResponseBody
    public ServerResponse sysOrderPay(
            @ApiParam(name = "orderId", value = "订单Id", required = true) @PathVariable(value = "orderId") String orderId,
            @ApiParam(name = "fee", value = "线下支付金额", required = true) @RequestParam(value = "fee") Double fee,
            @ApiParam(name = "remark", value = "线下支付说明", required = true) @RequestParam(value = "remark") String remark
    ) {

        try {
            orderService.notifyPaySuccess(orderId, fee, remark, JWTUtil.getUserId(), true);
        }catch (Exception e){
            return ServerResponse.createByErrorCodeMessage(-1, e.getMessage());
        }

        return ServerResponse.createBySuccess();
    }


    @RequestMapping(value = "/order/quotation/{orderId}",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取订单报价明细")
    @ResponseBody
    public ServerResponse orderQuotationInfo(
            @ApiParam(name = "orderId", value = "订单Id", required = true) @PathVariable(value = "orderId") String orderId

    ) {
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "/order/task/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取订单服务明细")
    @ResponseBody
    public ServerResponse<List<OrderProdTask>> orderTraceInfo(
            @ApiParam(name = "orderId", value = "订单Id", required = true) @PathVariable(value = "orderId") String orderId
    ) {

        List<OrderProdTask> orderProdTasks = orderService.findOrderProdTasksByOrderId(orderId);

        return ServerResponse.createBySuccess(orderProdTasks);
    }

    @RequestMapping(value = "/order/task/{orderId}/{orderTaskId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("修改订单服务明细")
    @ResponseBody
    public ServerResponse changeOrderTaskInfo(
            @ApiParam(name = "orderId", value = "订单Id", required = true) @PathVariable(value = "orderId") String orderId,
            @ApiParam(name = "orderTaskId", value = "订单明细任务Id", required = true) @PathVariable(value = "orderTaskId") Long orderTaskId,
            @ApiParam(name = "orderTaskJson", value = "订单明细的修改信息", required = true) @RequestParam(value = "orderTaskJson") String orderTaskJson
    ) {

        OrderProdTask orderProdTask = JsonUtil.getObjet(orderTaskJson, OrderProdTask.class);
        orderProdTask.setId(orderTaskId);

        orderService.changeOrderProdTaskByOrderProdTask(orderProdTask);

        return ServerResponse.createBySuccess();
    }

    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_SYSADMIN, CONST.ACCOUNT_TYPE_ADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/order/distribute/{orderId}",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("给订单分配新的业务员")
    @ResponseBody
    @SysLog("给订单分配新的业务员")
    public ServerResponse distributeOrder(
            @ApiParam(name = "orderId", value = "订单Id", required = true) @PathVariable(value = "orderId") String orderId,
            @ApiParam(name = "employeeId", value = "业务员Id", required = true) @RequestParam Long employeeId

    ) {

        orderService.changeOrderEmployeeByOrderIdAndEmployeeId(orderId, employeeId, JWTUtil.getUserId());

        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "/order/distribute/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("获取订单分配业务员明细")
    @ResponseBody
    @SysLog("获取订单分配业务员明细")
    public ServerResponse getEmployeesOfOrder(
            @ApiParam(name = "orderId", value = "订单Id", required = true) @PathVariable(value = "orderId") Long orderId

    ) {
//        orderService.changeOrderEmployeeByOrderIdAndEmployeeId(orderId, employeeId);

        return ServerResponse.createBySuccess();
    }


    @RequestMapping(value = "/order/apply/done/{orderId}",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.PUT})
    @ApiOperation("申请结束一个订单")
    @ResponseBody
    public ServerResponse finishOrder(
            @ApiParam(name = "orderId", value = "订单Id", required = true) @PathVariable(value = "orderId") Long orderId

    ) {
        return ServerResponse.createBySuccess();
    }

    @RequiresRoles(value = CONST.ACCOUNT_TYPE_EMPLOYEE)
    @RequestMapping(value = "/order/apply/credit/{orderId}",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.POST})
    @ApiOperation("申请订单积分奖励")
    @ResponseBody
    public ServerResponse applyOrderCredit(
            @ApiParam(name = "orderId", value = "订单Id", required = true) @PathVariable(value = "orderId") Long orderId

    ) {
        return ServerResponse.createBySuccess();
    }


    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN, CONST.ACCOUNT_TYPE_SYSADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/order/orders", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("系统用户获取订单列表")
    @ResponseBody
    public ServerResponse<PageModel<OrderBrief>> listOfOrders(
            @ApiParam(name = "status", value = "全部'ALL',待报价'QUOTATION',废除'DISCARD',未支付'NOTPAY',待分配'NOTDISTRIBUTE',服务中'SERVING',已完成'DONE'", required = true) @RequestParam String status,
            @ApiParam(name = "pageNum", value = "当前页数", required = true) @RequestParam Integer pageNum,
            @ApiParam(name = "limit", value = "每页数据大小", required = true) @RequestParam Integer limit
    ) {

        PageModel pageModel = new PageModel<>();
        pageModel.setLimit(limit);
        pageModel.setPageNum(pageNum);
        pageModel.setOrderBy("create_time desc");

        PageModel<OrderBrief> orderBriefPageModel = orderService.findPageOfOrdersByStatus(status, pageModel);

        return ServerResponse.createBySuccess(orderBriefPageModel);
    }


    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_ADMIN, CONST.ACCOUNT_TYPE_SYSADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/sys/order/{type}/orders", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = {RequestMethod.GET})
    @ApiOperation("系统用户获取订单列表")
    @ResponseBody
    public ServerResponse<Object> listOfOrdersByStatus(
            @ApiParam(name = "type", value = "订单列表类型: quotation-报价 payment-支付 distribution-分配 trace-追踪 check-校验 history-历史", required = true) @PathVariable(value = "type") String type,
            @ApiParam(name = "status", value = "报价-{'INPROGRESS','DONE'},支付-{'PAYED','NOTPAY'},分配-{'DISTRIBUTED','NOTDISTRIBUTE'},追踪-{'SERVING','DONE'},校验-{'DONE','PENDING'},历史-{已完成'DONE'}", required = true) @RequestParam String status,
            @ApiParam(name = "pageNum", value = "当前页数", required = true) @RequestParam Integer pageNum,
            @ApiParam(name = "limit", value = "每页数据大小", required = true) @RequestParam Integer limit
    ) {

        PageModel pageModel = new PageModel<>();
        pageModel.setLimit(limit);
        pageModel.setPageNum(pageNum);
        pageModel.setOrderBy("create_time desc");

        if (type.trim().equals("history")) {
            //获取订单历史列表
            PageModel<OrderBrief> orderBriefPageModel = orderService.findPageOfOrdersByStatus(status, pageModel);
            return ServerResponse.createBySuccess(orderBriefPageModel);
        } else if (type.trim().equals("quotation")) {
            PageModel<OrderToQuotation> orderToQuotationPageModel = orderService.findPageOfQuotationOrdersByStatus(status, pageModel);
            return ServerResponse.createBySuccess(orderToQuotationPageModel);
        } else if (type.trim().equals("payment")) {
            PageModel<OrderToPay> orderToPayPageModel = orderService.findPageOfPaymentOrdersByStatus(status, pageModel);
            return ServerResponse.createBySuccess(orderToPayPageModel);
        } else if (type.trim().equals("distribution")) {
            PageModel<OrderToDistribute> orderToDistributePageModel = orderService.findPageOfDistributionOrdersByStatus(status, pageModel);
            return ServerResponse.createBySuccess(orderToDistributePageModel);
        } else if (type.trim().equals("trace")) {
            PageModel<OrderToTask> orderToTaskPageModel = orderService.findPageOfTaskOrdersByStatus(status, pageModel);
            return ServerResponse.createBySuccess(orderToTaskPageModel);
        } else if (type.trim().equals("check")) {
            PageModel<OrderToCheck> orderToCheckPageModel = orderService.findPageOfCheckOrdersByStatus(status, pageModel);
            return ServerResponse.createBySuccess(orderToCheckPageModel);
        }
        return ServerResponse.createBySuccess();
    }
}

package com.octopus.taxcube.eds.service;

import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.eds.pojo.OrderBrief;
import com.octopus.taxcube.eds.pojo.OrderInfo;
import com.octopus.taxcube.eds.pojo.OrderProdTask;
import com.octopus.taxcube.eds.pojo.PayBrief;
import com.octopus.taxcube.eds.pojo.order.*;

import java.util.List;
import java.util.Map;

public interface IOrderService {

    List<OrderBrief> findOrdersByCustomerId(Long customerId);

    OrderInfo createOrderByCustomerIdAndProdId(Long customerId, Long prodId, String location);

    boolean changeOrderQuotationPriceByOrderId(String orderId, Double price, Long userId);

    List<OrderBrief> findOrdersByEmployeeId(Long employeeId);

    PageModel<OrderBrief> findPageOfOrdersByStatus(String status, PageModel pageModel);

    void deleteOrderByOrderId(String orderId);

    PageModel<OrderBrief> findPageOfOrdersByCustomerIdAndStatus(Long customerId, String status, PageModel pageModel);

    PageModel<OrderBrief> findPageOfOrdersByEmpIdAndStatus(Long employeeId, String status, PageModel pageModel);

    void changeOrderEmployeeByOrderIdAndEmployeeId(String orderId, Long employeeId, Long operatorId);

    List<PayBrief> findPayBriefByOrderId(String orderId);

    List<OrderProdTask> findOrderProdTasksByOrderId(String orderId);

//    Long createOrderProdTaskByOrderIdAndProdTaskId(Long orderId, Long prodTaskId);

    void changeOrderProdTaskByOrderProdTask(OrderProdTask orderProdTask);

    void notifyPaySuccess(String orderNo, Double fee, String remark, Long operatorId, boolean admin);

    Map<String, String> prepayOrder(String orderId);

    void createOrderPayInfo(Long userId, OrderInfo orderInfo);

    PageModel<OrderToQuotation> findPageOfQuotationOrdersByStatus(String status, PageModel pageModel);

    PageModel<OrderToPay> findPageOfPaymentOrdersByStatus(String status, PageModel pageModel);

    PageModel<OrderToDistribute> findPageOfDistributionOrdersByStatus(String status, PageModel pageModel);

    PageModel<OrderToTask> findPageOfTaskOrdersByStatus(String status, PageModel pageModel);

    PageModel<OrderToCheck> findPageOfCheckOrdersByStatus(String status, PageModel pageModel);

//    void payByAdmin(String orderId, String remark);

    /**
     * 刷新业务员的订单统计
     * @param empId
     */
    void refreshEmployeeOrderSummary(Long empId);
}

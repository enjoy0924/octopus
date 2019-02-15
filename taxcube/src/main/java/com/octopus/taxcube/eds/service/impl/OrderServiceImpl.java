package com.octopus.taxcube.eds.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.dao.*;
import com.octopus.taxcube.eds.entity.*;
import com.octopus.taxcube.eds.pojo.OrderBrief;
import com.octopus.taxcube.eds.pojo.OrderInfo;
import com.octopus.taxcube.eds.pojo.OrderProdTask;
import com.octopus.taxcube.eds.pojo.PayBrief;
import com.octopus.taxcube.eds.pojo.order.*;
import com.octopus.taxcube.eds.service.IGlobalConfigService;
import com.octopus.taxcube.eds.service.IOrderService;
import com.octopus.taxcube.eds.service.IWechatPayService;
import com.octopus.taxcube.exception.NotFoundException;
import com.octopus.taxcube.util.EdsUtils;
import com.octopus.taxcube.util.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderEntityMapper orderEntityMapper;

    @Autowired
    private OrderPayEntityMapper orderPayEntityMapper;

    @Autowired
    private ProdServiceEntityMapper prodServiceEntityMapper;

    @Autowired
    private AccountEntityMapper accountEntityMapper;

    @Autowired
    private IGlobalConfigService globalConfigService;

    @Autowired
    private OrderTaskEntityMapper orderTaskEntityMapper;

    @Autowired
    private OrderDistributeEntityMapper orderDistributeEntityMapper;

    @Autowired
    private OrderQuotationEntityMapper orderQuotationEntityMapper;

    @Autowired
    private ProdTaskEntityMapper prodTaskEntityMapper;

    @Autowired
    private CreditItemEntityMapper creditItemEntityMapper;

    @Autowired
    private OrderCheckEntityMapper orderCheckEntityMapper;

    @Autowired
    private IWechatPayService wechatPayService;

    @Autowired
    private EmpSummaryEntityMapper empSummaryEntityMapper;

    private void extractIdsFromOrderEntities(List<OrderEntity> orderEntities, List<Long> prodIds, List<Long> accountIds, List<String> orderIds){
        for(OrderEntity orderEntity : orderEntities){
            Long prodId = orderEntity.getServiceId();
            if(!prodIds.contains(prodId))
                prodIds.add(prodId);
            accountIds.add(orderEntity.getCustomerId());
            orderIds.add(orderEntity.getId());
        }
    }

    @Override
    public List<OrderBrief> findOrdersByCustomerId(Long customerId) {

        List<OrderEntity> orderEntities = orderEntityMapper.selectByCustomerId(customerId);

        return EdsUtils.convertOrderBriefs(orderEntities);
    }

    @Override
    public OrderInfo createOrderByCustomerIdAndProdId(Long customerId, Long prodId, String location) {

        Date date = new Date();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setServiceId(prodId);
        orderEntity.setId(NumberGenerator.generatorOrderNumber(customerId));

        ProdServiceEntity prodServiceEntity = prodServiceEntityMapper.selectByPrimaryKey(prodId);
        if(null == prodServiceEntity)
            throw new NotFoundException("找不到对应的商品");

        boolean priceFixed = prodServiceEntity.getPriceType().equals(CONST.PRICE_TYPE_FIXED);
        if(priceFixed) {  //价格固定的话，订单就处于未支付状态
            orderEntity.setQuotationStatus(CONST.STATUS_DONE);
            orderEntity.setPayStatus(CONST.STATUS_NOTPAY);
            orderEntity.setPriceQuotation(prodServiceEntity.getPrice().doubleValue());
            orderEntity.setPricePay(prodServiceEntity.getPrice().doubleValue());
        }else {  //价格不定的话，订单就处于报价状态
            orderEntity.setQuotationStatus(CONST.STATUS_QUOTATION_INPROGRESS);
            orderEntity.setPriceQuotation(0.0);
        }

        orderEntity.setLocation(location);
        orderEntity.setCustomerId(customerId);
        orderEntity.setCreateBy(customerId);
        orderEntity.setCreateTime(date);
        orderEntity.setUpdateBy(customerId);
        orderEntity.setUpdateTime(date);

        orderEntityMapper.insertSelective(orderEntity);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderEntity.getId());
        orderInfo.setPrice(orderEntity.getPriceQuotation());
        orderInfo.setPriceFixed(priceFixed);

        return orderInfo;
    }

    @Override
    public boolean changeOrderQuotationPriceByOrderId(String orderId, Double price, Long operatorId) {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);
        orderEntity.setPriceQuotation(price);
        orderEntity.setQuotationStatus(CONST.STATUS_DONE);
        orderEntity.setPayStatus(CONST.STATUS_NOTPAY);
        orderEntity.setUpdateTime(new Date());

        int i =orderEntityMapper.updateByPrimaryKeySelective(orderEntity);


        insertQuotationChange(orderId, price, operatorId);

        refreshOrderPay(orderId, price, operatorId);

        return i>0;
    }

    private void refreshOrderPay(String orderId, Double price, Long operatorId) {

        OrderPayEntity orderPayEntity = orderPayEntityMapper.selectByOrderIdAndType(orderId, CONST.PAY_TYPE_MONEY);
        if(null == orderPayEntity){
          orderPayEntity = new OrderPayEntity();
          orderPayEntity.setOrderId(orderId);
          orderPayEntity.setPayType(CONST.PAY_TYPE_MONEY);
          orderPayEntity.setPrice(price);
          orderPayEntity.setCreateBy(operatorId);
          orderPayEntity.setCreateTime(new Date());
          orderPayEntityMapper.insertSelective(orderPayEntity);
        } else {
            orderPayEntity.setPrice(price);
            orderPayEntityMapper.updateByPrimaryKeySelective(orderPayEntity);
        }
    }

    private void insertQuotationChange(String orderId, Double price, Long operatorId) {

        OrderQuotationEntity orderQuotationEntity = new OrderQuotationEntity();
        orderQuotationEntity.setOrderId(orderId);
        orderQuotationEntity.setPrice(price);
        orderQuotationEntity.setQuotationBy(operatorId);
        orderQuotationEntity.setQuotationTime(new Date());

        orderQuotationEntityMapper.insertSelective(orderQuotationEntity);

    }

    @Override
    public List<OrderBrief> findOrdersByEmployeeId(Long employeeId) {
        return null;
    }

    private PageModel<OrderBrief> pageOfOrders(List<OrderEntity> orderEntities, Long total){

        List<Long> prodIds = new ArrayList<>();
        List<Long> accountIds = new ArrayList<>();
        for(OrderEntity orderEntity : orderEntities){
            prodIds.add(orderEntity.getServiceId());

            Long empId = orderEntity.getEmpId();
            if(null != empId){
                accountIds.add(empId);
            }
            accountIds.add(orderEntity.getCustomerId());
        }

        if(!prodIds.isEmpty()) {
            List<ProdServiceEntity> prodServiceEntities = prodServiceEntityMapper.selectByIds(prodIds);
            Map<Long, String> prodId2Name = EdsUtils.convertToProdId2NameByProdServiceEntities(prodServiceEntities);

            if(!accountIds.isEmpty()) {
                List<AccountEntity> accountEntities = accountEntityMapper.selectByIds(accountIds);
                Map<Long, String> accountId2Name = EdsUtils.convertToAccountId2NameByAccountEntities(accountEntities);

                PageInfo<OrderBrief> pageInfo = new PageInfo<>(EdsUtils.convertToOrderBrief(orderEntities, prodId2Name, accountId2Name, globalConfigService));
                pageInfo.setTotal(total);
                return PageModel.convertToPageModel(pageInfo);
            }
        }

        return PageModel.convertToPageModel(new PageInfo<>(Collections.emptyList()));
    }

    @Override
    public PageModel<OrderBrief> findPageOfOrdersByStatus(String status, PageModel pageModel) {

        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
        PageHelper.orderBy(pageModel.getOrderBy());

        List<OrderEntity> orderEntities;
        long total;
        if(status.equals("ALL")){
            orderEntities = orderEntityMapper.selectAll();
            total = PageHelper.count(() -> orderEntityMapper.selectAll());
        }else {
            orderEntities = orderEntityMapper.selectByStatus(status);
            total = PageHelper.count(() -> orderEntityMapper.selectByStatus(status));
        }

        return pageOfOrders(orderEntities, total);

    }

    @Override
    public void deleteOrderByOrderId(String orderId) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);
        orderEntity.setOrderStatus(CONST.STATUS_DISCARD);
        orderEntity.setUpdateTime(new Date());

        orderEntityMapper.updateByPrimaryKeySelective(orderEntity);
    }

    @Override
    public PageModel<OrderBrief> findPageOfOrdersByCustomerIdAndStatus(Long customerId, String status, PageModel pageModel) {

        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
        PageHelper.orderBy(pageModel.getOrderBy());

        List<OrderEntity> orderEntities;
        long total;
        if(status.equals("ALL")){
            orderEntities = orderEntityMapper.selectAllByCustomerId(customerId);
            total = PageHelper.count(() -> orderEntityMapper.selectAllByCustomerId(customerId));
        }else {
            orderEntities = orderEntityMapper.selectByCustomerIdAndStatus(customerId,status);
            total = PageHelper.count(() -> orderEntityMapper.selectByCustomerIdAndStatus(customerId, status));
        }

        return pageOfOrders(orderEntities, total);
    }

    @Override
    public PageModel<OrderBrief> findPageOfOrdersByEmpIdAndStatus(Long employeeId, String status, PageModel pageModel) {

        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
        PageHelper.orderBy(pageModel.getOrderBy());

        List<OrderEntity> orderEntities;
        long total;
        if(status.equals("ALL")){
            orderEntities = orderEntityMapper.selectAllByEmpId(employeeId);
            total = PageHelper.count(() -> orderEntityMapper.selectAllByEmpId(employeeId));
        }else {
            orderEntities = orderEntityMapper.selectByEmpIdAndStatus(employeeId,status);
            total = PageHelper.count(() -> orderEntityMapper.selectByEmpIdAndStatus(employeeId, status));
        }

        return pageOfOrders(orderEntities, total);
    }

    @Override
    public void changeOrderEmployeeByOrderIdAndEmployeeId(String orderId, Long employeeId, Long operatorId) {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);
        orderEntity.setEmpId(employeeId);
        orderEntity.setServeStatus(CONST.STATUS_SERVING);
        orderEntity.setUpdateTime(new Date());

        orderEntityMapper.updateByPrimaryKeySelective(orderEntity);


        insertDistributeChange(orderId, employeeId, operatorId);

    }

    private void insertDistributeChange(String orderId, Long employeeId, Long operatorId) {

        OrderDistributeEntity orderDistributeEntity = new OrderDistributeEntity();
        orderDistributeEntity.setOrderId(orderId);
        orderDistributeEntity.setEmpId(employeeId);
        orderDistributeEntity.setCreateBy(operatorId);
        orderDistributeEntity.setCreateTime(new Date());

        orderDistributeEntityMapper.insertSelective(orderDistributeEntity);
    }

    @Override
    public List<PayBrief> findPayBriefByOrderId(String orderId) {

        List<OrderPayEntity> orderPayEntities = orderPayEntityMapper.selectByOrderId(orderId);

        return EdsUtils.convertToPayBrief(orderPayEntities);
    }

    @Override
    public List<OrderProdTask> findOrderProdTasksByOrderId(String orderId) {

        OrderEntity orderEntity = orderEntityMapper.selectByPrimaryKey(orderId);
        if(null == orderEntity){
            return Collections.emptyList();
        }

        Long prodId = orderEntity.getServiceId();
        ProdServiceEntity prodServiceEntity = prodServiceEntityMapper.selectByPrimaryKey(prodId);
        boolean season = prodServiceEntity.getCreditType().equals(CONST.CREDIT_TYPE_SEASON);

        List<ProdTaskEntity> prodTaskEntities = prodTaskEntityMapper.selectByProdId(prodId);
        Map<Long, ProdTaskEntity> taskId2ProdTaskEntity = new HashMap<>();
        for(ProdTaskEntity prodTaskEntity : prodTaskEntities){
            taskId2ProdTaskEntity.put(prodTaskEntity.getId(), prodTaskEntity);
        }

        List<OrderTaskEntity> orderTaskEntities = orderTaskEntityMapper.selectByOrderId(orderId);

        return EdsUtils.convertToOrderProdTasks(taskId2ProdTaskEntity, orderTaskEntities, season);

    }

//    @Override
//    public Long createOrderProdTaskByOrderIdAndProdTaskId(Long orderId, Long prodTaskId) {
//
//        OrderTaskEntity orderTaskEntity = new OrderTaskEntity();
//        orderTaskEntity.setOrderId(orderId);
//        orderTaskEntity.setTaskId(prodTaskId);
//        orderTaskEntity.setStatus(CONST.STATUS_DOING);
//        orderTaskEntity.setProgress(0);
//        orderTaskEntity.setCreateTime(new Date());
//
//        orderTaskEntityMapper.insertSelective(orderTaskEntity);
//
//        return orderTaskEntity.getId();
//    }

    @Override
    public void changeOrderProdTaskByOrderProdTask(OrderProdTask orderProdTask) {

        OrderTaskEntity orderTaskEntity = new OrderTaskEntity();
        orderTaskEntity.setId(orderProdTask.getId());
        orderTaskEntity.setProgress(orderProdTask.getProgress());
        orderTaskEntity.setRemark(orderProdTask.getRemark());

        orderTaskEntity.setUpdateTime(new Date());

        orderTaskEntityMapper.updateByPrimaryKeySelective(orderTaskEntity);
    }

    @Override
    public void notifyPaySuccess(String orderNo, Double fee, String remark, Long operatorId, boolean admin) {

        OrderEntity orderEntity = orderEntityMapper.selectByPrimaryKey(orderNo);
        if(null == orderEntity || CONST.STATUS_PAYED.equals(orderEntity.getPayStatus())){
            throw new NotFoundException("订单状态异常");
        }

        OrderPayEntity orderPayEntity = orderPayEntityMapper.selectByOrderIdAndType(orderNo, CONST.PAY_TYPE_MONEY);
        if(null == orderPayEntity){
            throw new NotFoundException("订单支付信息异常");
        }

        String payType = orderPayEntity.getPayType();
        if(!payType.equals(CONST.PAY_TYPE_MONEY) || !orderPayEntity.getPrice().equals(fee)){
            throw new NotFoundException("金额不匹配");
        }

        if(admin) {
            orderPayEntity.setPayMethod(CONST.STATUS_OFFLINE);
        }else {
            orderPayEntity.setPayMethod(CONST.STATUS_ONLINE);
        }
        orderPayEntity.setRemark(remark);
        orderPayEntity.setPayTime(new Date());
        if(null == operatorId)
            operatorId = orderEntity.getCustomerId();
        orderPayEntity.setPayBy(operatorId);
        orderPayEntityMapper.updateByPrimaryKeySelective(orderPayEntity);

        orderEntity.setPayStatus(CONST.STATUS_PAYED);
        orderEntity.setDistributeStatus(CONST.STATUS_NOTDISTRIBUTE);
        orderEntity.setUpdateTime(new Date());

        orderEntityMapper.updateByPrimaryKeySelective(orderEntity);
    }

    @Override
    public Map<String, String> prepayOrder(String orderId) {

        OrderEntity orderEntity = orderEntityMapper.selectByPrimaryKey(orderId);

        AccountEntity accountEntity = accountEntityMapper.selectByPrimaryKey(orderEntity.getCustomerId());

        Long prodId = orderEntity.getServiceId();
        ProdServiceEntity prodServiceEntity = prodServiceEntityMapper.selectByPrimaryKey(prodId);

        OrderPayEntity orderPayEntity = orderPayEntityMapper.selectByOrderIdAndType(orderId, CONST.PAY_TYPE_MONEY);

        try {
            return wechatPayService.generateOrderInfoByWechatPay(String.valueOf(orderId),
                    Double.valueOf(orderPayEntity.getPrice()*100).intValue(),
                    prodServiceEntity.getName(),
                    IWechatPayService.PAY_TYPE_PUBLIC_JSAPI,
                    String.valueOf(prodId),
                    accountEntity.getWechatId());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    @Override
    public void createOrderPayInfo(Long userId, OrderInfo orderInfo) {

        if(orderInfo.getPriceFixed()){
            OrderPayEntity orderPayEntity = new OrderPayEntity();
            orderPayEntity.setOrderId(orderInfo.getOrderId());
            orderPayEntity.setPrice(orderInfo.getPrice());
            orderPayEntity.setPayType(CONST.PAY_TYPE_MONEY);
            orderPayEntity.setCreateBy(userId);
            orderPayEntity.setCreateTime(new Date());
            orderPayEntityMapper.insertSelective(orderPayEntity);
        }
    }

    @Override
    public PageModel<OrderToQuotation> findPageOfQuotationOrdersByStatus(String status, PageModel pageModel) {

        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
        PageHelper.orderBy(pageModel.getOrderBy());

        List<OrderEntity> orderEntities;
        long total;
        if(status.equals("ALL")){
            orderEntities = orderEntityMapper.selectQuotationOrders();
            total = PageHelper.count(() -> orderEntityMapper.selectQuotationOrders());
        }else {
            orderEntities = orderEntityMapper.selectQuotationOrdersByQuotationStatus(status);
            total = PageHelper.count(() -> orderEntityMapper.selectQuotationOrdersByQuotationStatus(status));
        }

        return pageOfQuotationOrders(orderEntities, total);
    }

    private PageModel<OrderToQuotation> pageOfQuotationOrders(List<OrderEntity> orderEntities, long total) {

        List<Long> prodIds = new ArrayList<>();
        List<Long> accountIds = new ArrayList<>();
        List<String> orderIds = new ArrayList<>();
        extractIdsFromOrderEntities(orderEntities, prodIds, accountIds, orderIds);

        if(!prodIds.isEmpty()) {
            List<ProdServiceEntity> prodServiceEntities = prodServiceEntityMapper.selectByIds(prodIds);
            Map<Long, String> prodId2Name = EdsUtils.convertToProdId2NameByProdServiceEntities(prodServiceEntities);

            if(!orderIds.isEmpty()) {

                List<OrderQuotationEntity> orderQuotationEntities = orderQuotationEntityMapper.selectOrderQuotationByOrderIds(orderIds);
                if(null != orderQuotationEntities){
                    for(OrderQuotationEntity orderQuotationEntity : orderQuotationEntities){
                        accountIds.add(orderQuotationEntity.getQuotationBy());
                    }
                }

                if(!accountIds.isEmpty()) {
                    List<AccountEntity> accountEntities = accountEntityMapper.selectByIds(accountIds);
                    Map<Long, String> accountId2Name = EdsUtils.convertToAccountId2NameByAccountEntities(accountEntities);
                    Map<String, List<OrderQuotationItem>> orderId2OrderQuotationItems = EdsUtils.convertToOrderQuotationItems(orderQuotationEntities, accountId2Name);

                    PageInfo<OrderToQuotation> pageInfo = new PageInfo<>(EdsUtils.convertToQuotationOrders(
                            orderEntities, prodId2Name, accountId2Name, orderId2OrderQuotationItems));
                    pageInfo.setTotal(total);
                    return PageModel.convertToPageModel(pageInfo);

                }
            }
        }

        return PageModel.convertToPageModel(new PageInfo<>(Collections.emptyList()));
    }

    @Override
    public PageModel<OrderToPay> findPageOfPaymentOrdersByStatus(String status, PageModel pageModel) {

        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
        PageHelper.orderBy(pageModel.getOrderBy());

        List<OrderEntity> orderEntities;
        long total;
        if(status.equals("ALL")){
            orderEntities = orderEntityMapper.selectPaymentOrders();
            total = PageHelper.count(() -> orderEntityMapper.selectPaymentOrders());
        }else {
            orderEntities = orderEntityMapper.selectPaymentOrdersByPayStatus(status);
            total = PageHelper.count(() -> orderEntityMapper.selectPaymentOrdersByPayStatus(status));
        }

        return pageOfPaymentOrders(orderEntities, total);
    }

    private PageModel<OrderToPay> pageOfPaymentOrders(List<OrderEntity> orderEntities, long total) {

        List<Long> prodIds = new ArrayList<>();
        List<Long> accountIds = new ArrayList<>();
        List<String> orderIds = new ArrayList<>();
        extractIdsFromOrderEntities(orderEntities, prodIds, accountIds, orderIds);

        if(!prodIds.isEmpty()) {
            List<ProdServiceEntity> prodServiceEntities = prodServiceEntityMapper.selectByIds(prodIds);
            Map<Long, String> prodId2Name = EdsUtils.convertToProdId2NameByProdServiceEntities(prodServiceEntities);

            if(!orderIds.isEmpty()) {

                List<OrderPayEntity> orderPayEntities = orderPayEntityMapper.selectOrderPaymentByOrderIds(orderIds);
                if(null != orderPayEntities){
                    for(OrderPayEntity orderPayEntity : orderPayEntities){
                        accountIds.add(orderPayEntity.getCreateBy());
                        accountIds.add(orderPayEntity.getPayBy());
                    }
                }

                if(!accountIds.isEmpty()) {
                    List<AccountEntity> accountEntities = accountEntityMapper.selectByIds(accountIds);
                    Map<Long, String> accountId2Name = EdsUtils.convertToAccountId2NameByAccountEntities(accountEntities);
                    Map<String, List<OrderPayItem>> orderId2OrderPayItems = EdsUtils.convertToOrderPayItems(orderPayEntities, accountId2Name);

                    PageInfo<OrderToPay> pageInfo = new PageInfo<>(EdsUtils.convertToPaymentOrders(
                            orderEntities, prodId2Name, accountId2Name, orderId2OrderPayItems));
                    pageInfo.setTotal(total);
                    return PageModel.convertToPageModel(pageInfo);
                }
            }
        }

        return PageModel.convertToPageModel(new PageInfo<>(Collections.emptyList()));
    }

    @Override
    public PageModel<OrderToDistribute> findPageOfDistributionOrdersByStatus(String status, PageModel pageModel) {

        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
        PageHelper.orderBy(pageModel.getOrderBy());

        List<OrderEntity> orderEntities;
        long total;
        if(status.equals("ALL")){
            orderEntities = orderEntityMapper.selectDistributionOrders();
            total = PageHelper.count(() -> orderEntityMapper.selectDistributionOrders());
        }else {
            orderEntities = orderEntityMapper.selectDistributionOrdersByDistributeStatus(status);
            total = PageHelper.count(() -> orderEntityMapper.selectDistributionOrdersByDistributeStatus(status));
        }

        return pageOfDistributionOrders(orderEntities, total);
    }

    private PageModel<OrderToDistribute> pageOfDistributionOrders(List<OrderEntity> orderEntities, long total) {

        List<Long> prodIds = new ArrayList<>();
        List<Long> accountIds = new ArrayList<>();
        List<String> orderIds = new ArrayList<>();
        extractIdsFromOrderEntities(orderEntities, prodIds, accountIds, orderIds);

        if(!prodIds.isEmpty()) {
            List<ProdServiceEntity> prodServiceEntities = prodServiceEntityMapper.selectByIds(prodIds);
            Map<Long, String> prodId2Name = EdsUtils.convertToProdId2NameByProdServiceEntities(prodServiceEntities);

            if(!orderIds.isEmpty()) {

                List<OrderDistributeEntity> orderDistributeEntities = orderDistributeEntityMapper.selectOrderDistributionByOrderIds(orderIds);
                if(null != orderDistributeEntities){
                    for(OrderDistributeEntity orderDistributeEntity : orderDistributeEntities){
                        accountIds.add(orderDistributeEntity.getEmpId());
                        accountIds.add(orderDistributeEntity.getCreateBy());
                    }
                }

//                Map<String, Double> orderId2PricePay ;//= new HashMap<>();
                List<OrderPayEntity> orderPayEntities = orderPayEntityMapper.selectOrderPaymentByOrderIds(orderIds);
                Map<String, Double> orderId2PricePay = EdsUtils.convertToOrderId2PayedMoneyMap(orderPayEntities);

                if(!accountIds.isEmpty()) {
                    List<AccountEntity> accountEntities = accountEntityMapper.selectByIds(accountIds);
                    Map<Long, String> accountId2Name = EdsUtils.convertToAccountId2NameByAccountEntities(accountEntities);
                    Map<String, List<OrderDistributeItem>> orderId2OrderDistributeItems = EdsUtils.convertToOrderDistributeItems(orderDistributeEntities, accountId2Name);

                    PageInfo<OrderToDistribute> pageInfo = new PageInfo<>(EdsUtils.convertToDistributeOrders(
                            orderEntities, prodId2Name, accountId2Name, orderId2OrderDistributeItems, orderId2PricePay));
                    pageInfo.setTotal(total);
                    return PageModel.convertToPageModel(pageInfo);
                }
            }
        }

        return PageModel.convertToPageModel(new PageInfo<>(Collections.emptyList()));
    }

    @Override
    public PageModel<OrderToTask> findPageOfTaskOrdersByStatus(String status, PageModel pageModel) {

        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
        PageHelper.orderBy(pageModel.getOrderBy());

        List<OrderEntity> orderEntities;
        long total;
        if(status.equals("ALL")){
            orderEntities = orderEntityMapper.selectServeOrders();
            total = PageHelper.count(() -> orderEntityMapper.selectServeOrders());
        }else {
            orderEntities = orderEntityMapper.selectServeOrdersByServeStatus(status);
            total = PageHelper.count(() -> orderEntityMapper.selectServeOrdersByServeStatus(status));
        }

        return pageOfServeOrders(orderEntities, total, false);
    }

    private <T extends OrderToTask> PageModel<T> pageOfServeOrders(List<OrderEntity> orderEntities, long total, boolean withCheck) {
        List<Long> prodIds = new ArrayList<>();
        List<Long> accountIds = new ArrayList<>();
        List<String> orderIds = new ArrayList<>();
        extractIdsFromOrderEntities(orderEntities, prodIds, accountIds, orderIds);

        if(!prodIds.isEmpty()) {
            List<ProdServiceEntity> prodServiceEntities = prodServiceEntityMapper.selectByIds(prodIds);
            Map<Long, String> prodId2Name = EdsUtils.convertToProdId2NameByProdServiceEntities(prodServiceEntities);

            if(!orderIds.isEmpty()) {

                List<OrderTaskEntity> orderTaskEntities = orderTaskEntityMapper.selectOrderTasksByOrderIds(orderIds);
                if(null != orderTaskEntities){
                    for(OrderTaskEntity orderTaskEntity : orderTaskEntities){
                        accountIds.add(orderTaskEntity.getCreateBy());
                        accountIds.add(orderTaskEntity.getUpdateBy());
                    }
                }

                List<OrderCheckEntity> orderCheckEntities = null;
                if(withCheck){
                    orderCheckEntities = orderCheckEntityMapper.selectCheckByOrderIds(orderIds);
                    for(OrderCheckEntity orderCheckEntity : orderCheckEntities){
                        Long checkBy = orderCheckEntity.getCheckBy();
                        if(null != checkBy && !accountIds.contains(checkBy))
                            accountIds.add(checkBy);
                    }
                }

                if(!accountIds.isEmpty()) {
                    List<AccountEntity> accountEntities = accountEntityMapper.selectByIds(accountIds);
                    Map<Long, String> accountId2Name = EdsUtils.convertToAccountId2NameByAccountEntities(accountEntities);
                    Map<String, List<OrderTaskItem>> orderId2OrderTaskItems = EdsUtils.convertToOrderTaskItems(orderTaskEntities);


                    PageInfo pageInfo;
                    if(!withCheck) {
                        pageInfo = new PageInfo<>(EdsUtils.convertToTaskOrders(
                                orderEntities, prodId2Name, accountId2Name, orderId2OrderTaskItems
                        ));
                    } else {

                        Map<String, List<OrderCheckItem>> orderId2OrderCheckItems = EdsUtils.convertToOrderCheckItems(orderCheckEntities, accountId2Name);

                        pageInfo = new PageInfo<>(EdsUtils.convertToCheckOrders(
                                orderEntities, prodId2Name, accountId2Name, orderId2OrderCheckItems,orderId2OrderTaskItems
                        ));
                    }
                    pageInfo.setTotal(total);
                    return PageModel.convertToPageModel(pageInfo);
                }
            }
        }

        return PageModel.convertToPageModel(new PageInfo<>(Collections.emptyList()));
    }

    @Override
    public PageModel<OrderToCheck> findPageOfCheckOrdersByStatus(String status, PageModel pageModel) {
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
        PageHelper.orderBy(pageModel.getOrderBy());

        List<OrderEntity> orderEntities;
        long total;
        if(status.equals("ALL")){
            orderEntities = orderEntityMapper.selectCheckOrders();
            total = PageHelper.count(() -> orderEntityMapper.selectCheckOrders());
        }else {
            orderEntities = orderEntityMapper.selectCheckOrdersByCheckStatus(status);
            total = PageHelper.count(() -> orderEntityMapper.selectCheckOrdersByCheckStatus(status));
        }

        return pageOfServeOrders(orderEntities, total, true);
    }

    @Override
    public void refreshEmployeeOrderSummary(Long empId) {

        EmpSummaryEntity empSummaryEntity = new EmpSummaryEntity();
        empSummaryEntity.setEmpId(empId);
        empSummaryEntity.setOrderQuantityDone(PageHelper.count(() -> orderEntityMapper.selectByEmpIdAndOrderStatus(empId, CONST.STATUS_DONE)));
        empSummaryEntity.setOrderQuantityServing(PageHelper.count(() -> orderEntityMapper.selectByEmpIdAndServeStatus(empId, CONST.STATUS_SERVING)));
        empSummaryEntity.setUpdateTime(new Date());
        empSummaryEntity.setUpdateBy(0l);

        empSummaryEntityMapper.updateByPrimaryKeySelective(empSummaryEntity);
    }
}

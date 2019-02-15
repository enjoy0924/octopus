package com.octopus.taxcube.util;

import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.common.lang.DateUtils;
import com.octopus.taxcube.eds.entity.*;
import com.octopus.taxcube.eds.pojo.*;
import com.octopus.taxcube.eds.pojo.order.*;
import com.octopus.taxcube.eds.service.IGlobalConfigService;

import java.util.*;

public class EdsUtils {
    public static User convertToUser(AccountEntity accountEntity) {
        if(null == accountEntity)
            return null;

        User user = new User();
        user.setId(accountEntity.getId());
        user.setStatus(accountEntity.getStatus());
        user.setName(accountEntity.getName());
        user.setPhone(accountEntity.getPhone());
        if(null == accountEntity.getPassword()) {
            user.setPassword("");
        } else {
            user.setPassword(accountEntity.getPassword());
        }
        user.setWxOpenId(accountEntity.getWechatId());
        user.setType(accountEntity.getType());

        return user;
    }

    public static List<ProdCatalog> convertProdCatalogs(List<ProdCatalogEntity> prodCatalogEntities) {
        if(null == prodCatalogEntities || prodCatalogEntities.isEmpty()){
            return Collections.emptyList();
        }

        List<ProdCatalog> prodCatalogs = new ArrayList<>();
        for(ProdCatalogEntity prodCatalogEntity : prodCatalogEntities){
            ProdCatalog prodCatalog = new ProdCatalog();
            prodCatalog.setId(prodCatalogEntity.getId());
            prodCatalog.setName(prodCatalogEntity.getName());

            prodCatalogs.add(prodCatalog);
        }

        return prodCatalogs;
    }

    public static List<Prod> convertProds(List<ProdServiceEntity> prodServiceEntities, Map<Long, String> catalogId2Name, IGlobalConfigService globalConfigService) {
        if(null == prodServiceEntities || prodServiceEntities.isEmpty())
            return Collections.emptyList();

        List<Prod> prods = new ArrayList<>();
        for(ProdServiceEntity prodServiceEntity : prodServiceEntities){
            Prod prod = new Prod();
            prod.setCatalogId(prodServiceEntity.getCatalogId());
            prod.setCatalogName(catalogId2Name.get(prod.getCatalogId()));
            prod.setCreditType(prodServiceEntity.getCreditType());
            prod.setDescImage(globalConfigService.getProductImgFullPathByFileName(prodServiceEntity.getDescImage()));
            prod.setDescPrice(prodServiceEntity.getDescPrice());
            prod.setId(prodServiceEntity.getId());
            prod.setName(prodServiceEntity.getName());
            prod.setPriceType(prodServiceEntity.getPriceType());
            prod.setTitleImage(globalConfigService.getProductImgFullPathByFileName(prodServiceEntity.getTitleImage()));
            prod.setStatus(prodServiceEntity.getStatus());
            prod.setDescription(prodServiceEntity.getDescription());

            prods.add(prod);
        }

        return prods;
    }

    public static List<ProdTask> convertProdTasks(List<ProdTaskEntity> prodTaskEntities) {
        if(null == prodTaskEntities || prodTaskEntities.isEmpty())
            return Collections.emptyList();

        List<ProdTask> prodTasks = new ArrayList<>();
        for(ProdTaskEntity prodTaskEntity : prodTaskEntities){
            ProdTask prodTask = new ProdTask();
            prodTask.setId(prodTaskEntity.getId());
            prodTask.setName(prodTaskEntity.getName());
            prodTask.setDescription(prodTaskEntity.getDescription());
            prodTask.setProdId(prodTaskEntity.getServiceId());
            prodTask.setStatus(prodTaskEntity.getStatus());

            prodTasks.add(prodTask);
        }
        return prodTasks;
    }

    public static List<OrderBrief> convertOrderBriefs(List<OrderEntity> orderEntities) {
        if(null == orderEntities || orderEntities.isEmpty())
            return Collections.emptyList();

        List<OrderBrief> orderBriefs = new ArrayList<>();
        for(OrderEntity orderEntity : orderEntities){
            OrderBrief orderBrief = new OrderBrief();
            orderBrief.setId(orderEntity.getId());
            orderBrief.setPricePay(orderEntity.getPricePay());
            orderBrief.setPriceQuotation(orderEntity.getPriceQuotation());
//            orderBrief.setStatus(orderEntity.getStatus());
            orderBrief.setProdId(orderEntity.getServiceId());

            orderBriefs.add(orderBrief);
        }

        return orderBriefs;
    }

    public static List<User> convertToUsers(List<AccountEntity> accountEntities) {
        if(null == accountEntities || accountEntities.isEmpty())
            return Collections.emptyList();

        List<User> users = new ArrayList<>();
        for(AccountEntity accountEntity : accountEntities){
            users.add(convertToUser(accountEntity));
        }
        return users;
    }

    public static ApplyOffer convertToApplyOffer(OfferApplyEntity offerApplyEntity) {

        ApplyOffer applyOffer = new ApplyOffer();
        fillApplyOfferByOfferApplyEntity(applyOffer, offerApplyEntity);
//        applyOffer.setApplyId(offerApplyEntity.getId());
//        applyOffer.setApplierId(offerApplyEntity.getCreateBy());
//        applyOffer.setName(offerApplyEntity.getName());
//        applyOffer.setStatus(offerApplyEntity.getStatus());
//        applyOffer.setIdNumber(offerApplyEntity.getIdNumber());
//        applyOffer.setIdcardImagesStr(offerApplyEntity.getIdcardImages());
//        applyOffer.setCertificateImagesStr(offerApplyEntity.getCertificateImages());
//        applyOffer.setOffersStr(offerApplyEntity.getOffers());
//        applyOffer.setPhone(offerApplyEntity.getPhone());
//        applyOffer.setLocationsStr(offerApplyEntity.getLocations());

        return applyOffer;
    }

    public static ApplyOfferPlus convertToApplyOfferPlus(OfferApplyEntity offerApplyEntity) {

        ApplyOfferPlus applyOfferPlus = new ApplyOfferPlus();
        fillApplyOfferByOfferApplyEntity(applyOfferPlus, offerApplyEntity);
        return applyOfferPlus;
    }

    private static <T extends ApplyOffer> void fillApplyOfferByOfferApplyEntity(T applyOffer, OfferApplyEntity offerApplyEntity){
        applyOffer.setApplyId(offerApplyEntity.getId());
        applyOffer.setApplierId(offerApplyEntity.getCreateBy());
        applyOffer.setName(offerApplyEntity.getName());
        applyOffer.setStatus(offerApplyEntity.getStatus());
//        applyOffer.setIdNumber(offerApplyEntity.getIdNumber());
//        applyOffer.setIdcardImagesStr(offerApplyEntity.getIdcardImages());
//        applyOffer.setCertificateImagesStr(offerApplyEntity.getCertificateImages());
        applyOffer.setOffersStr(offerApplyEntity.getOffers());
        applyOffer.setPhone(offerApplyEntity.getPhone());
        applyOffer.setLocationsStr(offerApplyEntity.getLocations());
    }

    public static List<ApplyOffer> convertToApplyOffers(List<OfferApplyEntity> offerApplyEntities) {
        if(null == offerApplyEntities || offerApplyEntities.isEmpty())
            return Collections.emptyList();

        List<ApplyOffer> applyOffers = new ArrayList<>();
        for(OfferApplyEntity offerApplyEntity : offerApplyEntities){
            applyOffers.add(convertToApplyOffer(offerApplyEntity));
        }
        return applyOffers;
    }

    public static Map<Long, String> convertToOfferId2NameByProductCatalogEntities(List<ProdCatalogEntity> prodCatalogs) {

        if(null == prodCatalogs || prodCatalogs.isEmpty()){
            return Collections.emptyMap();
        }

        Map<Long, String> offerId2Name = new HashMap<>();
        for(ProdCatalogEntity prodCatalog : prodCatalogs){
            offerId2Name.put(prodCatalog.getId(), prodCatalog.getName());
        }

        return offerId2Name;
    }

    public static List<ApplyOfferPlus> convertToApplyOfferPluses(List<OfferApplyEntity> offerApplyEntities, Map<Long, String> offerId2Name, IGlobalConfigService globalConfigService) {
        if(null == offerApplyEntities || offerApplyEntities.isEmpty()){
            return Collections.emptyList();
        }

        List<ApplyOfferPlus> applyOfferPluses = new ArrayList<>();
        for(OfferApplyEntity offerApplyEntity : offerApplyEntities){
            ApplyOfferPlus applyOfferPlus = convertToApplyOfferPlus(offerApplyEntity);

            applyOfferPlus.setLocations(Arrays.asList(offerApplyEntity.getLocations().split(",")));

            String[] offerIds = applyOfferPlus.getOffersStr().split(",");
            for(String offerId : offerIds){
                applyOfferPlus.putOffer(Long.valueOf(offerId), offerId2Name.get(Long.valueOf(offerId)));
            }

//            String[] idcardImages = offerApplyEntity.getIdcardImages().split(",");
//            for(String idcardImage : idcardImages){
//                applyOfferPlus.addIdcardImage(globalConfigService.getIdcardImgFullPathByFileName(idcardImage));
//            }

//            String[] certificateImages = offerApplyEntity.getCertificateImages().split(",");
//            for(String certificateImage: certificateImages){
//                applyOfferPlus.addCertificateImage(globalConfigService.getCertificateImgFullPathByFileName(certificateImage));
//            }

            applyOfferPluses.add(applyOfferPlus);
        }
        return applyOfferPluses;
    }

    public static ApplyOffer composeApplyOffer(Long applierId, String name, String phone, String idNumber, String locations, String offers, String certificateImages, String idcardImages) {

        ApplyOffer applyOffer = new ApplyOffer();

        applyOffer.setApplierId(applierId);
        applyOffer.setName(name);
        applyOffer.setPhone(phone);
        applyOffer.setIdNumber(idNumber);
        applyOffer.setLocationsStr(locations);
        applyOffer.setOffersStr(offers);
        applyOffer.setCertificateImagesStr(certificateImages);
        applyOffer.setIdcardImagesStr(idcardImages);

        return applyOffer;
    }

    public static Map<Long, String> convertToProdId2NameByProdServiceEntities(List<ProdServiceEntity> prodServiceEntities) {
        if(null == prodServiceEntities || prodServiceEntities.isEmpty()){
            return Collections.emptyMap();
        }

        Map<Long, String> prodId2Name = new HashMap<>();
        for(ProdServiceEntity prodServiceEntity : prodServiceEntities){
            prodId2Name.put(prodServiceEntity.getId(),
                    String.format("%s@#@%s",prodServiceEntity.getName(), prodServiceEntity.getTitleImage()));
        }
        return prodId2Name;
    }

    public static Map<Long, String> convertToAccountId2NameByAccountEntities(List<AccountEntity> accountEntities) {
        if(null == accountEntities || accountEntities.isEmpty()){
            return Collections.emptyMap();
        }

        Map<Long, String> accountId2Name = new HashMap<>();
        for(AccountEntity accountEntity : accountEntities){
            accountId2Name.put(accountEntity.getId(), accountEntity.getName());
        }
        return accountId2Name;
    }

    public static List<OrderBrief> convertToOrderBrief(List<OrderEntity> orderEntities, Map<Long, String> prodId2Name, Map<Long, String> accountId2Name, IGlobalConfigService globalConfigService) {

        List<OrderBrief> orderBriefs = new ArrayList<>();
        for(OrderEntity orderEntity : orderEntities){
            OrderBrief orderBrief = new OrderBrief();
            orderBrief.setId(orderEntity.getId());
            orderBrief.setProdId(orderEntity.getServiceId());

            String prodNameAndImage = prodId2Name.get(orderBrief.getProdId());
            if(null != prodNameAndImage) {
                String[] prodNameAndImageArray = prodNameAndImage.split("@#@");
                orderBrief.setProdName(prodNameAndImageArray[0]);

                orderBrief.setProdImageUri(globalConfigService.getProductImgFullPathByFileName(prodNameAndImageArray[1]));
            }
            orderBrief.setQuotationStatus(orderEntity.getQuotationStatus());
            orderBrief.setPayStatus(orderEntity.getPayStatus());
            orderBrief.setDistributeStatus(orderEntity.getDistributeStatus());
            orderBrief.setServeStatus(orderEntity.getServeStatus());
            orderBrief.setCheckStatus(orderEntity.getCheckStatus());
            orderBrief.setOrderStatus(orderEntity.getOrderStatus());

            orderBrief.setStatusDesc(EdsUtils.convertOrderDisplayStatus(orderBrief));

//            orderBrief.setStatusDesc(EdsUtils.convertOrderStatusDescByStatus(orderBrief.getStatus()));
            orderBrief.setPriceQuotation(orderEntity.getPriceQuotation());
            orderBrief.setPricePay(orderEntity.getPricePay());
            orderBrief.setLocation(orderEntity.getLocation());
            orderBrief.setCreateTime(DateUtils.formatDateTime(orderEntity.getCreateTime()));
            orderBrief.setCustomerId(orderEntity.getCustomerId());
            orderBrief.setCustomerName(accountId2Name.get(orderBrief.getCustomerId()));

            Long empId = orderEntity.getEmpId();
            if(null != empId){
                orderBrief.setEmpId(empId);
                orderBrief.setEmpName(accountId2Name.get(orderBrief.getEmpId()));
            }

            orderBriefs.add(orderBrief);
        }

        return orderBriefs;
    }

    private static String convertOrderDisplayStatus(OrderBrief orderBrief) {

        String orderStatus = orderBrief.getOrderStatus();
        if(orderStatus.equals(CONST.STATUS_DISCARD)){
            return "已废除";
        }

        if(orderStatus.equals(CONST.STATUS_DONE)){
            return "已完成";
        }

        String quotationStatus = orderBrief.getQuotationStatus();
        if(quotationStatus.equals(CONST.STATUS_NOTSET)){
            return "待报价";
        }

        String payStatus = orderBrief.getPayStatus();
        if(!payStatus.equals(CONST.STATUS_PAYED)){
            return "待支付";
        }

        String distributeStatus = orderBrief.getDistributeStatus();
        if(distributeStatus.equals(CONST.STATUS_NOTDISTRIBUTE)){
            return "待分配";
        }

        String serveStatus = orderBrief.getServeStatus();
        if(!serveStatus.equals(CONST.STATUS_DONE)){
            return "服务中";
        }

        return "未知状态";
    }

    public static List<UserBrief> convertToUserBrief(List<AccountEntity> accountEntities, Map<Long, EmpSummary> empId2Summary) {
        if(null == accountEntities || accountEntities.isEmpty()){
            return Collections.emptyList();
        }

        List<UserBrief> userBriefs = new ArrayList<>();
        List<Long> accountIds = new ArrayList<>();
        for(AccountEntity accountEntity : accountEntities){

            Long accountId = accountEntity.getId();
            if(accountIds.contains(accountId))
                continue;
            accountIds.add(accountId);

            UserBrief userBrief = new UserBrief();
            userBrief.setEmpId(accountEntity.getId());
            userBrief.setName(accountEntity.getName());
            userBrief.setPhone(accountEntity.getPhone());
            userBrief.setOfferType(accountEntity.getOfferType());
            userBrief.setSummary(empId2Summary.get(userBrief.getEmpId()));
            userBriefs.add(userBrief);
        }
        return userBriefs;
    }

    public static List<PayBrief> convertToPayBrief(List<OrderPayEntity> orderPayEntities) {

        if(null == orderPayEntities || orderPayEntities.isEmpty())
            return Collections.emptyList();

        List<PayBrief> payBriefs = new ArrayList<>();
        for(OrderPayEntity orderPayEntity : orderPayEntities) {
            PayBrief payBrief = new PayBrief();
            payBrief.setType(orderPayEntity.getPayType());
            payBrief.setMethod(orderPayEntity.getPayMethod());
            payBrief.setValue(orderPayEntity.getPrice());

            payBriefs.add(payBrief);
        }

        return payBriefs;
    }

    public static List<OrderProdTask> convertToOrderProdTasks(Map<Long, ProdTaskEntity> taskId2ProdTaskEntity, List<OrderTaskEntity> orderTaskEntities, boolean season) {

        List<OrderProdTask> orderProdTasks = new ArrayList<>();

//        Set<Long> taskIdSet = new HashSet<>();
        if(null != orderTaskEntities && !orderTaskEntities.isEmpty()) {
            for (OrderTaskEntity orderTaskEntity : orderTaskEntities) {

                OrderProdTask orderProdTask = new OrderProdTask();
                Long taskId = orderTaskEntity.getTaskId();

                orderProdTask.setId(orderTaskEntity.getId());
                orderProdTask.setProdTaskId(taskId);
                orderProdTask.setStatus(orderTaskEntity.getStatus());
                orderProdTask.setRemark(orderTaskEntity.getRemark());
                orderProdTask.setProgress(orderTaskEntity.getProgress());
//                orderProdTask.setPeriod(orderProdTask.getPeriod());
                if(taskId.equals(0)){
                    orderProdTask.setProdTaskName(taskId2ProdTaskEntity.get(taskId).getName());
//                    taskIdSet.add(taskId); //预设任务Id
                    taskId2ProdTaskEntity.remove(taskId);
                }

                orderProdTasks.add(orderProdTask);
            }
        }

        for(Map.Entry<Long, ProdTaskEntity> entry : taskId2ProdTaskEntity.entrySet()){
            Long taskId = entry.getKey();
            OrderProdTask orderProdTask = new OrderProdTask();
            orderProdTask.setProdTaskId(taskId);
            orderProdTask.setStatus(CONST.STATUS_NOTSET);
            orderProdTask.setRemark(orderProdTask.getRemark());
            orderProdTask.setProdTaskName(entry.getValue().getName());

            orderProdTasks.add(orderProdTask);

        }

        return orderProdTasks;
    }

    public static List<CreditItem> convertToCreditItems(List<CreditItemEntity> creditItemEntities) {

        if(null == creditItemEntities || creditItemEntities.isEmpty()){
            return Collections.emptyList();
        }

        List<CreditItem> creditItems = new ArrayList<>();
        for(CreditItemEntity creditItemEntity : creditItemEntities){
            CreditItem creditItem = new CreditItem();
            creditItem.setCredit(creditItemEntity.getCredit().longValue());
            creditItem.setPeriod(creditItemEntity.getPeriod());
            creditItem.setReason(creditItemEntity.getReasonType());
            creditItem.setDate(DateUtils.formatDateTime(creditItemEntity.getCreateTime()));
            creditItem.setStatus(creditItemEntity.getStatus());

            creditItems.add(creditItem);
        }
        return creditItems;
    }

    public static List<CreditExchange> convertToCreditExchanges(List<CreditExchangeEntity> creditExchangeEntities, Map<Long, String> accountId2Name) {

        List<CreditExchange> creditExchanges = new ArrayList<>();
        for(CreditExchangeEntity creditExchangeEntity : creditExchangeEntities){
            CreditExchange creditExchange = new CreditExchange();
            creditExchange.setApplyId(creditExchangeEntity.getId());
            creditExchange.setAccountId(creditExchangeEntity.getAccountId());
            creditExchange.setAccountName(accountId2Name.get(creditExchange.getAccountId()));
            creditExchange.setDate(DateUtils.formatDateTime(creditExchangeEntity.getCreateTime()));
            creditExchange.setExchangeType(creditExchangeEntity.getExchangeType());
            creditExchange.setExchangeVal(creditExchangeEntity.getExchangeValue());
            creditExchange.setStatus(creditExchangeEntity.getStatus());
            creditExchange.setCreditVal(creditExchangeEntity.getCredit());
            creditExchange.setRemark(creditExchangeEntity.getRemark());

            creditExchanges.add(creditExchange);
        }

        return creditExchanges;
    }

    public static List<Popularization> convertToPopularization(List<PopularizationEntity> popularizationEntities, Map<Long, String> accountId2Name) {

        List<Popularization> popularizations = new ArrayList<>();
        for(PopularizationEntity popularizationEntity : popularizationEntities){
            Popularization popularization = new Popularization();
            popularization.setId(popularizationEntity.getId());
            popularization.setPopularizedId(popularizationEntity.getPopularizedId());
            popularization.setPopularizedName(accountId2Name.get(popularization.getPopularizedId()));
            popularization.setPopularizeId(popularizationEntity.getPopularizeId());
            popularization.setPopularizeName(accountId2Name.get(popularization.getPopularizeId()));
            popularization.setStatus(popularizationEntity.getStatus());

            popularization.setDate(DateUtils.formatDateTime(popularizationEntity.getCreateTime()));

            popularizations.add(popularization);
        }
        return popularizations;
    }

    public static List<OrderToQuotation> convertToQuotationOrders(List<OrderEntity> orderEntities,
                                                                  Map<Long, String> prodId2Name,
                                                                  Map<Long, String> accountId2Name,
                                                                  Map<String, List<OrderQuotationItem>> orderId2OrderQuotationItems) {

        List<OrderToQuotation> orderToQuotations = new ArrayList<>();
        for(OrderEntity orderEntity : orderEntities){
            OrderToQuotation orderToQuotation = new OrderToQuotation();
            copyOrderBaseInfoFromOrderEntity(orderToQuotation, orderEntity);
            orderToQuotation.setCustomerName(accountId2Name.get(orderToQuotation.getCustomerId()));


            String prodName = prodId2Name.get(orderToQuotation.getProdId());
            String[] prodNameArray = prodName.split("@#@");
            orderToQuotation.setProdName(prodNameArray[0]);

            orderToQuotation.setQuotationStatus(orderEntity.getQuotationStatus());
            orderToQuotation.setPriceQuotation(orderEntity.getPriceQuotation());

            List<OrderQuotationItem> orderQuotationItems = orderId2OrderQuotationItems.get(orderEntity.getId());
            if(null == orderQuotationItems)
                orderQuotationItems = Collections.emptyList();
            orderToQuotation.setOrderQuotationItems(orderQuotationItems);

            orderToQuotations.add(orderToQuotation);
        }


        return orderToQuotations;
    }

    public static Map<String, List<OrderQuotationItem>> convertToOrderQuotationItems(List<OrderQuotationEntity> orderQuotationEntities, Map<Long, String> accId2Name) {

        if(null == orderQuotationEntities || orderQuotationEntities.isEmpty()){
            return Collections.emptyMap();
        }

        Map<String, List<OrderQuotationItem>> orderId2OrderQuotationItems = new HashMap<>();
        for(OrderQuotationEntity orderQuotationEntity : orderQuotationEntities){

            String orderId = orderQuotationEntity.getOrderId();
            List<OrderQuotationItem> orderQuotationItems = orderId2OrderQuotationItems.computeIfAbsent(orderId, k -> new ArrayList<>());
            OrderQuotationItem orderQuotationItem = new OrderQuotationItem();
            orderQuotationItem.setQuotationBy(orderQuotationEntity.getQuotationBy());
            orderQuotationItem.setQuotationAdminName(accId2Name.get(orderQuotationEntity.getQuotationBy()));
            orderQuotationItem.setPrice(orderQuotationEntity.getPrice());
            orderQuotationItem.setQuotationTime(DateUtils.formatDateTime(orderQuotationEntity.getQuotationTime()));

            orderQuotationItems.add(orderQuotationItem);
        }

        return orderId2OrderQuotationItems;
    }

    public static Map<String, List<OrderPayItem>> convertToOrderPayItems(List<OrderPayEntity> orderPayEntities, Map<Long, String> accId2Name) {
        if(null == orderPayEntities || orderPayEntities.isEmpty()){
            return Collections.emptyMap();
        }

        Map<String, List<OrderPayItem>> orderId2OrderPayItems = new HashMap<>();
        for(OrderPayEntity orderPayEntity : orderPayEntities){

            String orderId = orderPayEntity.getOrderId();
            List<OrderPayItem> orderPayItems = orderId2OrderPayItems.computeIfAbsent(orderId, k -> new ArrayList<>());

            OrderPayItem orderPayItem = new OrderPayItem();
            orderPayItem.setPayBy(orderPayEntity.getPayBy());
            orderPayItem.setPayer(accId2Name.get(orderPayEntity.getPayBy()));
            orderPayItem.setPayMethod(orderPayEntity.getPayMethod());
            if(null == orderPayEntity.getPayTime()) {
                orderPayItem.setStatus(CONST.STATUS_NOTPAY);
            }else {
                orderPayItem.setStatus(CONST.STATUS_PAYED);
                orderPayItem.setPayTime(DateUtils.formatDateTime(orderPayEntity.getPayTime()));
            }
            orderPayItem.setPrice(orderPayEntity.getPrice());
            orderPayItem.setRemark(orderPayEntity.getRemark());

            orderPayItems.add(orderPayItem);
        }

        return orderId2OrderPayItems;
    }

    public static List<OrderToPay> convertToPaymentOrders(List<OrderEntity> orderEntities, Map<Long, String> prodId2Name, Map<Long, String> accountId2Name, Map<String, List<OrderPayItem>> orderId2OrderPayItems) {

        List<OrderToPay> orderToPays = new ArrayList<>();
        for(OrderEntity orderEntity : orderEntities){

            OrderToPay orderToPay = new OrderToPay();
            copyOrderBaseInfoFromOrderEntity(orderToPay, orderEntity);
            orderToPay.setCustomerName(accountId2Name.get(orderToPay.getCustomerId()));

            String prodName = prodId2Name.get(orderToPay.getProdId());
            String[] prodNameArray = prodName.split("@#@");
            orderToPay.setProdName(prodNameArray[0]);

            orderToPay.setPriceQuotation(orderEntity.getPriceQuotation());
            orderToPay.setPricePay(orderEntity.getPricePay());
            orderToPay.setPayStatus(orderEntity.getPayStatus());

            List<OrderPayItem> orderPayItems = orderId2OrderPayItems.get(orderEntity.getId());
            if(null == orderPayItems)
                orderPayItems = Collections.emptyList();
            orderToPay.setOrderPayItems(orderPayItems);

            orderToPays.add(orderToPay);
        }

        return orderToPays;
    }

    public static<T extends OrderBase> void copyOrderBaseInfoFromOrderEntity(T order, OrderEntity orderEntity){
        order.setOrderStatus(orderEntity.getOrderStatus());
        order.setOrderId(orderEntity.getId());
        order.setCustomerId(orderEntity.getCustomerId());
        order.setProdId(orderEntity.getServiceId());
        order.setOrderDate(DateUtils.formatDate(orderEntity.getCreateTime()));
    }

    public static Map<String, List<OrderDistributeItem>> convertToOrderDistributeItems(List<OrderDistributeEntity> orderDistributeEntities, Map<Long, String> accountId2Name) {
        if(null == orderDistributeEntities || orderDistributeEntities.isEmpty()){
            return Collections.emptyMap();
        }

        Map<String, List<OrderDistributeItem>> orderId2OrderDistributeItems = new HashMap<>();
        for(OrderDistributeEntity orderDistributeEntity : orderDistributeEntities){

            String orderId = orderDistributeEntity.getOrderId();
            List<OrderDistributeItem> orderDistributeItems = orderId2OrderDistributeItems.computeIfAbsent(orderId, k -> new ArrayList<>());

            OrderDistributeItem orderDistributeItem = new OrderDistributeItem();
            orderDistributeItem.setEmpId(orderDistributeEntity.getEmpId());
            orderDistributeItem.setEmpName(accountId2Name.get(orderDistributeEntity.getEmpId()));
            orderDistributeItem.setDistributor(accountId2Name.get(orderDistributeEntity.getCreateBy()));
            orderDistributeItem.setDistributeTime(DateUtils.formatDateTime(orderDistributeEntity.getCreateTime()));

            orderDistributeItems.add(orderDistributeItem);
        }

        return orderId2OrderDistributeItems;
    }

    public static List<OrderToDistribute> convertToDistributeOrders(List<OrderEntity> orderEntities, Map<Long, String> prodId2Name, Map<Long, String> accountId2Name, Map<String, List<OrderDistributeItem>> orderId2OrderDistributeItems, Map<String, Double> orderId2PricePay) {
        List<OrderToDistribute> orderToDistributes = new ArrayList<>();
        for(OrderEntity orderEntity : orderEntities){

            String orderId = orderEntity.getId();

            OrderToDistribute orderToDistribute = new OrderToDistribute();
            copyOrderBaseInfoFromOrderEntity(orderToDistribute, orderEntity);
            orderToDistribute.setCustomerName(accountId2Name.get(orderToDistribute.getCustomerId()));


            String prodName = prodId2Name.get(orderToDistribute.getProdId());
            String[] prodNameArray = prodName.split("@#@");
            orderToDistribute.setProdName(prodNameArray[0]);
//            orderToDistribute.setProdName(prodId2Name.get(orderToDistribute.getProdId()));

            orderToDistribute.setLocation(orderEntity.getLocation());
            orderToDistribute.setEmpId(orderEntity.getEmpId());
            orderToDistribute.setEmpName(accountId2Name.get(orderToDistribute.getEmpId()));
            orderToDistribute.setCreditForEmp(orderEntity.getCreditEmp());
            orderToDistribute.setPricePay(orderId2PricePay.get(orderId));
            orderToDistribute.setDistributeStatus(orderEntity.getDistributeStatus());

            List<OrderDistributeItem> orderDistributeItems = orderId2OrderDistributeItems.get(orderId);
            if(null == orderDistributeItems)
                orderDistributeItems = Collections.emptyList();
            orderToDistribute.setOrderDistributeItems(orderDistributeItems);

            orderToDistributes.add(orderToDistribute);
        }

        return orderToDistributes;
    }

    public static Map<String, List<OrderTaskItem>> convertToOrderTaskItems(List<OrderTaskEntity> orderTaskEntities) {
        if(null == orderTaskEntities || orderTaskEntities.isEmpty()){
            return Collections.emptyMap();
        }

        Map<String, List<OrderTaskItem>> orderId2OrderTaskItems = new HashMap<>();
        for(OrderTaskEntity orderTaskEntity : orderTaskEntities){

            String orderId = orderTaskEntity.getOrderId();
            List<OrderTaskItem> orderTaskItems = orderId2OrderTaskItems.computeIfAbsent(orderId, k -> new ArrayList<>());

            OrderTaskItem orderTaskItem = new OrderTaskItem();
            orderTaskItem.setId(orderTaskEntity.getId());
            orderTaskItem.setName(orderTaskEntity.getTaskName());
            orderTaskItem.setProgress(orderTaskEntity.getProgress());
            orderTaskItem.setRemark(orderTaskEntity.getRemark());
            orderTaskItem.setStatus(orderTaskEntity.getStatus());
            Date updateTime = orderTaskEntity.getUpdateTime();
            if(null != updateTime) {
                orderTaskItem.setRefreshTime(DateUtils.formatDateTime(updateTime));
            }

            orderTaskItems.add(orderTaskItem);
        }

        return orderId2OrderTaskItems;
    }

    public static List<OrderToTask> convertToTaskOrders(List<OrderEntity> orderEntities,
                                                        Map<Long, String> prodId2Name,
                                                        Map<Long, String> accountId2Name,
                                                        Map<String, List<OrderTaskItem>> orderId2OrderTaskItems) {
        List<OrderToTask> orderToTasks = new ArrayList<>();
        for(OrderEntity orderEntity : orderEntities){

            OrderToTask orderToTask = new OrderToTask();
            copyOrderBaseInfoFromOrderEntity(orderToTask, orderEntity);
            orderToTask.setCustomerName(accountId2Name.get(orderToTask.getCustomerId()));

            String prodName = prodId2Name.get(orderToTask.getProdId());
            String[] prodNameArray = prodName.split("@#@");
            orderToTask.setProdName(prodNameArray[0]);
//            orderToTask.setProdName(prodId2Name.get(orderToTask.getProdId()));

            orderToTask.setEmpId(orderEntity.getEmpId());
            orderToTask.setEmpName(accountId2Name.get(orderToTask.getEmpId()));

            List<OrderTaskItem> orderTaskItems = orderId2OrderTaskItems.get(orderEntity.getId());
            if(null == orderTaskItems)
                orderTaskItems = Collections.emptyList();
            orderToTask.setOrderTaskItems(orderTaskItems);

            orderToTasks.add(orderToTask);
        }

        return orderToTasks;
    }


    public static List<OrderToCheck> convertToCheckOrders(List<OrderEntity> orderEntities,
                                                        Map<Long, String> prodId2Name,
                                                        Map<Long, String> accountId2Name,
                                                        Map<String, List<OrderCheckItem>> orderId2OrderCheckItems,
                                                        Map<String, List<OrderTaskItem>> orderId2OrderTaskItems) {
        List<OrderToCheck> orderToChecks = new ArrayList<>();
        for(OrderEntity orderEntity : orderEntities){

            OrderToCheck orderToCheck = new OrderToCheck();
            copyOrderBaseInfoFromOrderEntity(orderToCheck, orderEntity);
            orderToCheck.setCustomerName(accountId2Name.get(orderToCheck.getCustomerId()));

            String prodName = prodId2Name.get(orderToCheck.getProdId());
            String[] prodNameArray = prodName.split("@#@");
            orderToCheck.setProdName(prodNameArray[0]);
//            orderToCheck.setProdName(prodId2Name.get(orderToCheck.getProdId()));

            orderToCheck.setCheckStatus(orderEntity.getCheckStatus());

            orderToCheck.setEmpId(orderEntity.getEmpId());
            orderToCheck.setEmpName(accountId2Name.get(orderToCheck.getEmpId()));

            List<OrderTaskItem> orderTaskItems = orderId2OrderTaskItems.get(orderEntity.getId());
            if(null == orderTaskItems)
                orderTaskItems = Collections.emptyList();
            orderToCheck.setOrderTaskItems(orderTaskItems);

            List<OrderCheckItem> orderCheckItems = orderId2OrderCheckItems.get(orderEntity.getId());
            if(null == orderCheckItems)
                orderCheckItems = Collections.emptyList();
            orderToCheck.setOrderCheckItems(orderCheckItems);

            orderToChecks.add(orderToCheck);
        }

        return orderToChecks;
    }

    public static Map<String, List<OrderCheckItem>> convertToOrderCheckItems(List<OrderCheckEntity> orderCheckEntities, Map<Long, String> accountId2Name) {
        if(null == orderCheckEntities || orderCheckEntities.isEmpty()){
            return Collections.emptyMap();
        }

        Map<String, List<OrderCheckItem>> orderId2OrderCheckItems = new HashMap<>();
        for(OrderCheckEntity orderCheckEntity : orderCheckEntities){

            String orderId = orderCheckEntity.getOrderId();
            List<OrderCheckItem> orderCheckItems = orderId2OrderCheckItems.computeIfAbsent(orderId, k -> new ArrayList<>());

            OrderCheckItem orderCheckItem = new OrderCheckItem();
            orderCheckItem.setCheckBy(orderCheckEntity.getCheckBy());
            orderCheckItem.setChecker(accountId2Name.get(orderCheckItem.getCheckBy()));
            orderCheckItem.setCheckId(orderCheckEntity.getId());
            orderCheckItem.setCheckStatus(orderCheckEntity.getCheckStatus());
            orderCheckItem.setCheckRemark(orderCheckEntity.getRemark());
            Date checkDate = orderCheckEntity.getCheckTime();
            if(null != checkDate) {
                orderCheckItem.setCheckTime(DateUtils.formatDateTime(checkDate));
            }

            orderCheckItems.add(orderCheckItem);
        }

        return orderId2OrderCheckItems;
    }

    public static EnterpriseInfo convertToEnterpriseInfo(EnterpriseInfoEntity enterpriseInfoEntity) {
        if(null == enterpriseInfoEntity)
            return null;

        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setTaxCode(enterpriseInfoEntity.getId());
        enterpriseInfo.setAddress(enterpriseInfoEntity.getAddress());
        enterpriseInfo.setTelephone(enterpriseInfoEntity.getTelephone());
        enterpriseInfo.setName(enterpriseInfoEntity.getName());

        return enterpriseInfo;
    }

    public static List<EnterpriseInfo> convertToEnterpriseInfos(List<EnterpriseInfoEntity> enterpriseInfoEntities) {

        if(null == enterpriseInfoEntities || enterpriseInfoEntities.isEmpty())
            return Collections.emptyList();

        List<EnterpriseInfo> enterpriseInfos = new ArrayList<>();
        for(EnterpriseInfoEntity enterpriseInfoEntity : enterpriseInfoEntities){
            enterpriseInfos.add(convertToEnterpriseInfo(enterpriseInfoEntity));
        }
        return enterpriseInfos;
    }

    public static Map<String, Double> convertToOrderId2PayedMoneyMap(List<OrderPayEntity> orderPayEntities) {
        if(null == orderPayEntities || orderPayEntities.isEmpty())
            return Collections.emptyMap();

        Map<String, Double> orderId2PayedMoney = new HashMap<>();
        for(OrderPayEntity orderPayEntity : orderPayEntities){
            if(null != orderPayEntity.getPayTime() && CONST.PAY_TYPE_MONEY.equals(orderPayEntity.getPayType())){
                orderId2PayedMoney.put(orderPayEntity.getOrderId(), orderPayEntity.getPrice());
            }
        }

        return orderId2PayedMoney;
    }

    public static List<Role> convertToRoles(List<RoleEntity> roleEntities) {
        if(null == roleEntities || roleEntities.isEmpty())
            return Collections.emptyList();

        List<Role> roles = new ArrayList<>();
        for(RoleEntity roleEntity : roleEntities){
            Role role = new Role(roleEntity.getRoleName());
            role.setDescription(roleEntity.getDescription());
            role.setRoleId(roleEntity.getId());
            role.setCreateTime(DateUtils.formatDateTime(roleEntity.getCreateTime()));

            roles.add(role);
        }

        return roles;
    }

    public static List<Long> convertStringToLong(String[] permissionIdArray) {
        if(null == permissionIdArray || permissionIdArray.length==0)
            return Collections.emptyList();

        List<Long> permissionIds = new ArrayList<>();
        for(String permissionId : permissionIdArray){
            if(null == permissionId || permissionId.trim().isEmpty()){
                continue;
            }

            Long permId = Long.valueOf(permissionId);
            permissionIds.add(permId);
        }

        return permissionIds;
    }

    public static List<RolePermissionEntity> convertToRolePermissionEntities(Long roleId, List<Long> permissionIds) {
        Date date = new Date();
        List<RolePermissionEntity> rolePermissionEntities = new ArrayList<>();
        for(Long permissionId : permissionIds){
            RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
            rolePermissionEntity.setCreateBy(1L);
            rolePermissionEntity.setCreateTime(date);
            rolePermissionEntity.setRoleId(roleId);
            rolePermissionEntity.setPermissionId(permissionId);

            rolePermissionEntities.add(rolePermissionEntity);
        }

        return rolePermissionEntities;
    }

    public static List<Permission> convertToPermissions(List<PermissionEntity> permissionEntities) {
        if(null == permissionEntities || permissionEntities.isEmpty())
            return Collections.emptyList();

        List<Permission> permissions = new ArrayList<>();
        for(PermissionEntity permissionEntity : permissionEntities){
            Permission permission = new Permission();
            permission.setPermissionId(permissionEntity.getId());
            permission.setPermissionCode(permissionEntity.getPermissionCode());
            permission.setPermissionName(permissionEntity.getPermissionName());
            permission.setMenuName(permissionEntity.getMenuName());
            permission.setMenuCode(permissionEntity.getMenuCode());
            permission.setCreateTime(DateUtils.formatDateTime(permissionEntity.getCreateTime()));

            permissions.add(permission);
        }

        return permissions;
    }
}

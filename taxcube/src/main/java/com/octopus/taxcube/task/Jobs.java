package com.octopus.taxcube.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Author: zc
 * Date: 2018/4/30
 * Description:定时任务
 */
@Component
@Slf4j
public class Jobs {
//    @Autowired
//    private StoreMapper storeMapper;
//
//    @Autowired
//    private SaleOrderMapper saleOrderMapper;
//
//    @Autowired
//    private SaleService saleService;
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//
//    //定时关单(每天凌晨关闭前一天为付款订单)
//    @Scheduled(cron = "0 0 0 * * ?")
//    public void HotSaleHandler() {
//        log.info("-------------定时关单开始-------------");
//        Example example = new Example(SaleOrder.class);
//        example.createCriteria()
//                .andLessThanOrEqualTo("createTime", DateUtils.addDays(new Date(), -1))
//                .andEqualTo("orderStatus", ConstEnum.SALESTATUS_NOPAYMENT.getCode());
//
//        List<SaleOrder> saleOrders = saleOrderMapper.selectByExample(example);
//        if (CollectionUtils.isNotEmpty(saleOrders)) {
//            saleOrders.forEach(x -> saleService.closeOrder(x.getId(),true));
//        }
//
//        log.info("----------------定时关单结束----------");
//
//    }
}

package com.octopus.taxcube.eds.service;

import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.eds.pojo.CreditExchange;
import com.octopus.taxcube.eds.pojo.CreditItem;
import com.octopus.taxcube.eds.pojo.CreditItemPlus;

import java.util.List;

public interface ICreditService {

    List<CreditItem> findCreditItemsByAccountId(Long userId);

    void handleCreditConsumeByApplyIdAndStatus(Long applyId, String status, String remark);

    int createNewCreditExchange(Long accountId, Long creditVal, String exchangeType, Double exchangeVal);

    PageModel<CreditExchange> findPageOfCreditExchangeByStatus(String status, PageModel pageModel);

    void createCreditByCreditItem(CreditItemPlus creditItemPlus, Long auditorId);

    List<CreditExchange> findAllCreditExchangeByStatus(String status);

    /**
     * 刷新业务员的积分数
     * @param empId
     */
    void refreshEmployeeCredit(Long empId);
}

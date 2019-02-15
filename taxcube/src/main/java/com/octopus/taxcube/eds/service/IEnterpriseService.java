package com.octopus.taxcube.eds.service;

import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.eds.pojo.EnterpriseInfo;

public interface IEnterpriseService {

    void createNewEnterpriseInfo(EnterpriseInfo enterpriseInfo, Long operatorId);

    void delEnterpriseInfo(String taxId);

    EnterpriseInfo findEnterpriseInfoByTaxId(String taxId);

    PageModel<EnterpriseInfo> findPageOfEnterpriseInfosByStatus(String status, PageModel pageModel);
}

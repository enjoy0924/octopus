package com.octopus.taxcube.eds.service;

import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.eds.pojo.Popularization;

public interface IPopularizeService {

    void createNewPopularization(Long popularizeId, Long popularizedId);

    PageModel<Popularization> findPageOfPopularizationByStatus(String status, PageModel pageModel);
}

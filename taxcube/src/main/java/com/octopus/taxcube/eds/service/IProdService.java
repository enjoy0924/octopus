package com.octopus.taxcube.eds.service;

import com.octopus.taxcube.eds.pojo.Prod;
import com.octopus.taxcube.eds.pojo.ProdCatalog;
import com.octopus.taxcube.eds.pojo.ProdTask;

import java.util.List;

public interface IProdService {

    List<ProdCatalog> findProdCatalogs();

    List<Prod> findProdServices();

    List<ProdTask> findProdTasksByProdId(Long prodId);

    void changeOrCreateProdInfo(Prod prod, Long userId, boolean update);

    void changeProdStatus(Long prodId, Long userId, String status);
}

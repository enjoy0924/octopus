package com.octopus.taxcube.eds.service.impl;

import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.dao.ProdCatalogEntityMapper;
import com.octopus.taxcube.eds.dao.ProdServiceEntityMapper;
import com.octopus.taxcube.eds.dao.ProdTaskEntityMapper;
import com.octopus.taxcube.eds.entity.ProdCatalogEntity;
import com.octopus.taxcube.eds.entity.ProdServiceEntity;
import com.octopus.taxcube.eds.entity.ProdTaskEntity;
import com.octopus.taxcube.eds.pojo.Prod;
import com.octopus.taxcube.eds.pojo.ProdCatalog;
import com.octopus.taxcube.eds.pojo.ProdTask;
import com.octopus.taxcube.eds.service.IGlobalConfigService;
import com.octopus.taxcube.eds.service.IProdService;
//import com.octopus.taxcube.utils.EdsUtils;
import com.octopus.taxcube.util.EdsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProdServiceImpl implements IProdService {

    @Autowired
    private ProdCatalogEntityMapper prodCatalogEntityMapper;

    @Autowired
    private ProdServiceEntityMapper prodServiceEntityMapper;

    @Autowired
    private ProdTaskEntityMapper prodTaskEntityMapper;

    @Autowired
    private IGlobalConfigService globalConfigService;

    @Override
    public List<ProdCatalog> findProdCatalogs() {

        List<ProdCatalogEntity> prodCatalogEntities = prodCatalogEntityMapper.selectAll();

        return EdsUtils.convertProdCatalogs(prodCatalogEntities);
    }

    @Override
    public List<Prod> findProdServices() {

        List<ProdCatalogEntity> prodCatalogEntities = prodCatalogEntityMapper.selectAll();

        Map<Long, String> id2Name = new HashMap<>();
        for(ProdCatalogEntity prodCatalogEntity : prodCatalogEntities){
            id2Name.put(prodCatalogEntity.getId(), prodCatalogEntity.getName());
        }

        List<ProdServiceEntity> prodServiceEntities = prodServiceEntityMapper.selectAll();

        return EdsUtils.convertProds(prodServiceEntities, id2Name, globalConfigService);
    }

    @Override
    public List<ProdTask> findProdTasksByProdId(Long prodId) {

        List<ProdTaskEntity> prodTaskEntities = prodTaskEntityMapper.selectByProdId(prodId);

        return EdsUtils.convertProdTasks(prodTaskEntities);
    }

    @Override
    public void changeOrCreateProdInfo(Prod prod, Long auditorId, boolean update) {

        ProdServiceEntity prodServiceEntity = new ProdServiceEntity();
        prodServiceEntity.setCatalogId(prod.getCatalogId());
        prodServiceEntity.setName(prod.getName());
        prodServiceEntity.setCreditType(prod.getCreditType());
        prodServiceEntity.setDescPrice(prod.getDescPrice());
        prodServiceEntity.setPrice(prod.getPrice());
        prodServiceEntity.setDescImage(prod.getDescImage());
        prodServiceEntity.setDescription(prod.getDescription());
        prodServiceEntity.setTitleImage(prod.getTitleImage());
        prodServiceEntity.setPriceType(prod.getPriceType());
        prodServiceEntity.setStatus(CONST.STATUS_OFFLINE);
        if(update) {
            prodServiceEntity.setId(prod.getId());
            prodServiceEntity.setUpdateBy(auditorId);
            prodServiceEntity.setUpdateTime(new Date());
            prodServiceEntityMapper.updateByPrimaryKeySelective(prodServiceEntity);
        }else {
            prodServiceEntity.setCreateBy(auditorId);
            prodServiceEntity.setCreateTime(new Date());
            prodServiceEntityMapper.insertSelective(prodServiceEntity);
        }
    }

    @Override
    public void changeProdStatus(Long prodId, Long userId, String status) {

        ProdServiceEntity prodServiceEntity = new ProdServiceEntity();
        prodServiceEntity.setId(prodId);
        prodServiceEntity.setUpdateBy(userId);
        prodServiceEntity.setUpdateTime(new Date());
        prodServiceEntity.setStatus(status);

        prodServiceEntityMapper.updateByPrimaryKeySelective(prodServiceEntity);
    }
}

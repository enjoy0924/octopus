package com.octopus.taxcube.eds.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.eds.dao.EnterpriseInfoEntityMapper;
import com.octopus.taxcube.eds.entity.EnterpriseInfoEntity;
import com.octopus.taxcube.eds.entity.ProdCatalogEntity;
import com.octopus.taxcube.eds.pojo.EnterpriseInfo;
import com.octopus.taxcube.eds.service.IEnterpriseService;
import com.octopus.taxcube.util.EdsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EnterpriseServiceImpl implements IEnterpriseService {

    @Autowired
    private EnterpriseInfoEntityMapper enterpriseInfoEntityMapper;

    @Override
    public void createNewEnterpriseInfo(EnterpriseInfo enterpriseInfo, Long operatorId) {

        EnterpriseInfoEntity enterpriseInfoEntity = new EnterpriseInfoEntity();
        enterpriseInfoEntity.setId(enterpriseInfo.getTaxCode());
        enterpriseInfoEntity.setAddress(enterpriseInfo.getAddress());
        enterpriseInfoEntity.setName(enterpriseInfo.getName());
        enterpriseInfoEntity.setTelephone(enterpriseInfo.getTelephone());
        enterpriseInfoEntity.setCreateTime(new Date());
        enterpriseInfoEntity.setCreateBy(operatorId);

        enterpriseInfoEntityMapper.insertSelective(enterpriseInfoEntity);

    }

    @Override
    public void delEnterpriseInfo(String taxId) {
        enterpriseInfoEntityMapper.deleteByPrimaryKey(taxId);
    }

    @Override
    public EnterpriseInfo findEnterpriseInfoByTaxId(String taxId) {

        EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoEntityMapper.selectByPrimaryKey(taxId);

        return EdsUtils.convertToEnterpriseInfo(enterpriseInfoEntity);
    }

    @Override
    public PageModel<EnterpriseInfo> findPageOfEnterpriseInfosByStatus(String status, PageModel pageModel) {

//        enterpriseInfoEntityMapper.selectByStatus(status);

        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
        PageHelper.orderBy(pageModel.getOrderBy());

        List<EnterpriseInfoEntity> enterpriseInfoEntities = enterpriseInfoEntityMapper.selectByStatus(status);

        PageInfo<EnterpriseInfo> pageInfo = new PageInfo<>(EdsUtils.convertToEnterpriseInfos(enterpriseInfoEntities));
        pageInfo.setTotal(
                PageHelper.count(() -> enterpriseInfoEntityMapper.selectByStatus(status))
        );

        return PageModel.convertToPageModel(pageInfo);
    }
}

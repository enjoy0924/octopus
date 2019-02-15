package com.octopus.taxcube.eds.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.dao.AccountEntityMapper;
import com.octopus.taxcube.eds.dao.PopularizationEntityMapper;
import com.octopus.taxcube.eds.entity.AccountEntity;
import com.octopus.taxcube.eds.entity.PopularizationEntity;
import com.octopus.taxcube.eds.pojo.Popularization;
import com.octopus.taxcube.eds.service.IPopularizeService;
import com.octopus.taxcube.util.EdsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PopularizeServiceImpl implements IPopularizeService {

    @Autowired
    private PopularizationEntityMapper popularizationEntityMapper;

    @Autowired
    private AccountEntityMapper accountEntityMapper;

    @Override
    public void createNewPopularization(Long popularizeId, Long popularizedId) {

        PopularizationEntity popularizationEntity = popularizationEntityMapper.selectByPopularizedId(popularizedId);
        if(null == popularizationEntity) {
            popularizationEntity = new PopularizationEntity();
            popularizationEntity.setPopularizedId(popularizedId);
            popularizationEntity.setPopularizeId(popularizeId);
            popularizationEntity.setStatus(CONST.STATUS_DISABLE);
            popularizationEntity.setCreateTime(new Date());

            popularizationEntityMapper.insert(popularizationEntity);
        }
    }

    @Override
    public PageModel<Popularization> findPageOfPopularizationByStatus(String status, PageModel pageModel) {

        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
        PageHelper.orderBy(pageModel.getOrderBy());

        List<PopularizationEntity> popularizationEntities;
        long total;
        if(status.equals("ALL")){
            popularizationEntities = popularizationEntityMapper.selectAll();
            total = PageHelper.count(() -> popularizationEntityMapper.selectAll());
        }else {
            popularizationEntities = popularizationEntityMapper.selectByStatus(status);
            total = PageHelper.count(() -> popularizationEntityMapper.selectByStatus(status));
        }

        return pageOfPopularizations(popularizationEntities, total);
    }

    private PageModel<Popularization> pageOfPopularizations(List<PopularizationEntity> popularizationEntities, long total) {

        List<Long> accountIds = new ArrayList<>();
        for(PopularizationEntity popularizationEntity : popularizationEntities){
            accountIds.add(popularizationEntity.getPopularizedId());
            accountIds.add(popularizationEntity.getPopularizeId());
        }

        if(!accountIds.isEmpty()) {
            List<AccountEntity> accountEntities = accountEntityMapper.selectByIds(accountIds);
            Map<Long, String> accountId2Name = EdsUtils.convertToAccountId2NameByAccountEntities(accountEntities);

            PageInfo<Popularization> pageInfo = new PageInfo<>(EdsUtils.convertToPopularization(popularizationEntities, accountId2Name));
            pageInfo.setTotal(total);
            return PageModel.convertToPageModel(pageInfo);
        }


        return PageModel.convertToPageModel(new PageInfo<>(Collections.emptyList()));

    }
}

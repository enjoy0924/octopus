package com.octopus.taxcube.eds.service.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.dao.AccountEntityMapper;
import com.octopus.taxcube.eds.dao.CreditExchangeEntityMapper;
import com.octopus.taxcube.eds.dao.CreditItemEntityMapper;
import com.octopus.taxcube.eds.dao.EmpSummaryEntityMapper;
import com.octopus.taxcube.eds.entity.AccountEntity;
import com.octopus.taxcube.eds.entity.CreditExchangeEntity;
import com.octopus.taxcube.eds.entity.CreditItemEntity;
import com.octopus.taxcube.eds.entity.EmpSummaryEntity;
import com.octopus.taxcube.eds.pojo.CreditExchange;
import com.octopus.taxcube.eds.pojo.CreditItem;
import com.octopus.taxcube.eds.pojo.CreditItemPlus;
import com.octopus.taxcube.eds.service.ICreditService;
import com.octopus.taxcube.exception.NotFoundException;
import com.octopus.taxcube.util.EdsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CreditServiceImpl implements ICreditService {

    @Autowired
    private CreditItemEntityMapper creditItemEntityMapper;

    @Autowired
    private CreditExchangeEntityMapper creditExchangeEntityMapper;

    @Autowired
    private AccountEntityMapper accountEntityMapper;

    @Autowired
    private EmpSummaryEntityMapper empSummaryEntityMapper;

    @Override
    public List<CreditItem> findCreditItemsByAccountId(Long accountId) {

        List<CreditItemEntity> creditItemEntities = creditItemEntityMapper.selectByAccountId(accountId);

        return EdsUtils.convertToCreditItems(creditItemEntities);
    }

    @Override
    public void handleCreditConsumeByApplyIdAndStatus(Long applyId, String status, String remark) {
        if(status.equals(CONST.STATUS_REFUSE)){
            //如果处于拒绝兑换申请的话，这里就需要还回积分

            CreditItemEntity item =creditItemEntityMapper.selectBySourceId(applyId);
            if(null == item){
                throw new NotFoundException("找不到积分扣除记录");
            }

            CreditItemEntity creditItemEntity = new CreditItemEntity();
            creditItemEntity.setSourceId(applyId);
            creditItemEntity.setAccountId(item.getAccountId());
            creditItemEntity.setReasonType(CONST.REASON_TYPE_REFUND);
            creditItemEntity.setStatus(CONST.STATUS_DONE);
            creditItemEntity.setRemark("退还");
            creditItemEntity.setCredit(-1 * item.getCredit());
            creditItemEntity.setCreateTime(new Date());

            creditItemEntityMapper.insertSelective(creditItemEntity);
        }

        CreditExchangeEntity creditExchangeEntity = new CreditExchangeEntity();
        creditExchangeEntity.setId(applyId);
        creditExchangeEntity.setStatus(status);
        creditExchangeEntity.setRemark(remark);
        creditExchangeEntity.setUpdateTime(new Date());

        creditExchangeEntityMapper.updateByPrimaryKeySelective(creditExchangeEntity);

    }

    @Override
    public int createNewCreditExchange(Long accountId, Long creditVal, String exchangeType, Double exchangeVal) {

        /**这里应该首先判断积分是不是够申请*/
        Long credit = creditItemEntityMapper.selectTotalCreditByAccountId(accountId);
        if(null == credit || credit < creditVal){
            return CONST.ERROR_CODE_FAILED;
        }

        //这里申请一个新的积分兑换
        CreditExchangeEntity creditExchangeEntity = new CreditExchangeEntity();
        creditExchangeEntity.setStatus(CONST.STATUS_PENDING);
        creditExchangeEntity.setAccountId(accountId);
        creditExchangeEntity.setCredit(creditVal.intValue());
        creditExchangeEntity.setExchangeType(exchangeType);
        creditExchangeEntity.setExchangeValue(exchangeVal);
        creditExchangeEntity.setCreateTime(new Date());
        creditExchangeEntity.setCreateBy(accountId);

        creditExchangeEntityMapper.insertSelective(creditExchangeEntity);

        Long applyId = creditExchangeEntity.getId();
        CreditItemEntity creditItemEntity = new CreditItemEntity();
        creditItemEntity.setSourceId(applyId);
        creditItemEntity.setAccountId(accountId);
        creditItemEntity.setReasonType(CONST.REASON_TYPE_CONSUME);
        creditItemEntity.setStatus(CONST.STATUS_DONE);
        creditItemEntity.setRemark("消费兑换");
        creditItemEntity.setCredit(-1 * creditVal.intValue());
        creditItemEntity.setCreateTime(new Date());

        creditItemEntityMapper.insertSelective(creditItemEntity);

        return CONST.ERROR_CODE_OK;
    }

    @Override
    public PageModel<CreditExchange> findPageOfCreditExchangeByStatus(String status, PageModel pageModel) {

        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
        PageHelper.orderBy(pageModel.getOrderBy());

        List<CreditExchangeEntity> creditExchangeEntities;
        long total;
        if(status.equals("ALL")){
            creditExchangeEntities = creditExchangeEntityMapper.selectAll();
            total = PageHelper.count(() -> creditExchangeEntityMapper.selectAll());
        }else {
            creditExchangeEntities = creditExchangeEntityMapper.selectByStatus(status);
            total = PageHelper.count(() -> creditExchangeEntityMapper.selectByStatus(status));
        }

        return pageOfCreditExchanges(creditExchangeEntities, total);
    }

    @Override
    public void createCreditByCreditItem(CreditItemPlus creditItemPlus, Long auditorId) {

        CreditItemEntity creditItemEntity = new CreditItemEntity();
        creditItemEntity.setAccountId(creditItemPlus.getAccountId());
        creditItemEntity.setRemark(creditItemPlus.getRemark());
        creditItemEntity.setCredit(creditItemPlus.getCredit().intValue());
        creditItemEntity.setSourceId(0l);
        creditItemEntity.setPeriod(CONST.PERIOD_TYPE_NONE);
        creditItemEntity.setStatus(CONST.STATUS_DONE);
        creditItemEntity.setReasonType(creditItemPlus.getReason());
        creditItemEntity.setCreateTime(new Date());
        creditItemEntity.setCreateBy(auditorId);

        creditItemEntityMapper.insertSelective(creditItemEntity);
    }

    @Override
    public List<CreditExchange> findAllCreditExchangeByStatus(String status) {

        List<CreditExchangeEntity> creditExchangeEntities = creditExchangeEntityMapper.selectByStatus(status);
        List<Long> accountIds = new ArrayList<>();
        for(CreditExchangeEntity creditExchangeEntity : creditExchangeEntities){
            Long accountId = creditExchangeEntity.getAccountId();
            if(null != accountId){
                accountIds.add(accountId);
            }
        }

        if(!accountIds.isEmpty()) {
            List<AccountEntity> accountEntities = accountEntityMapper.selectByIds(accountIds);
            Map<Long, String> accountId2Name = EdsUtils.convertToAccountId2NameByAccountEntities(accountEntities);

            return EdsUtils.convertToCreditExchanges(creditExchangeEntities, accountId2Name);
        }else {
            return Collections.emptyList();
        }
    }

    private PageModel<CreditExchange> pageOfCreditExchanges(List<CreditExchangeEntity> creditExchangeEntities, long total) {

        List<Long> accountIds = new ArrayList<>();
        for(CreditExchangeEntity creditExchangeEntity : creditExchangeEntities){
            Long accountId = creditExchangeEntity.getAccountId();
            if(null != accountId){
                accountIds.add(accountId);
            }
        }

        if(!accountIds.isEmpty()) {
            List<AccountEntity> accountEntities = accountEntityMapper.selectByIds(accountIds);
            Map<Long, String> accountId2Name = EdsUtils.convertToAccountId2NameByAccountEntities(accountEntities);

            PageInfo<CreditExchange> pageInfo = new PageInfo<>(EdsUtils.convertToCreditExchanges(creditExchangeEntities, accountId2Name));
            pageInfo.setTotal(total);
            return PageModel.convertToPageModel(pageInfo);
        }

        return PageModel.convertToPageModel(new PageInfo<>(Collections.emptyList()));
    }


    @Override
    public void refreshEmployeeCredit(Long empId) {

        EmpSummaryEntity empSummaryEntity = new EmpSummaryEntity();
        empSummaryEntity.setEmpId(empId);
        empSummaryEntity.setCreditTotal(PageHelper.count(() -> creditItemEntityMapper.selectTotalCreditByAccountId(empId)));
        empSummaryEntity.setCreditRemain(PageHelper.count(() -> creditItemEntityMapper.selectTotalCreditByAccountId(empId)));
        empSummaryEntity.setUpdateBy(0l);
        empSummaryEntity.setUpdateTime(new Date());

        empSummaryEntityMapper.updateByPrimaryKeySelective(empSummaryEntity);
    }

}

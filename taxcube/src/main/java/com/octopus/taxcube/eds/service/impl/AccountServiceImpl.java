package com.octopus.taxcube.eds.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.dao.*;
import com.octopus.taxcube.eds.entity.*;
import com.octopus.taxcube.eds.pojo.*;
import com.octopus.taxcube.eds.service.IAccountService;
import com.octopus.taxcube.eds.service.IGlobalConfigService;
import com.octopus.taxcube.shiro.JWTUtil;
import com.octopus.taxcube.util.EdsUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountEntityMapper accountEntityMapper;

    @Autowired
    private OfferApplyEntityMapper offerApplyEntityMapper;

    @Autowired
    private EmpOfferEntityMapper empOfferEntityMapper;

    @Autowired
    private EmpProfileEntityMapper empProfileEntityMapper;

    @Autowired
    private ProdCatalogEntityMapper prodCatalogEntityMapper;

    @Autowired
    private IGlobalConfigService globalConfigService;

    @Autowired
    private EmpSummaryEntityMapper empSummaryEntityMapper;

    @Override
    public User findByPhone(String phone) {
        AccountEntity accountEntity = accountEntityMapper.selectByPhone(phone);
        return EdsUtils.convertToUser(accountEntity);
    }

    @Override
    public User findByUserId(Long userId) {
        AccountEntity accountEntity = accountEntityMapper.selectByPrimaryKey(userId);
        return EdsUtils.convertToUser(accountEntity);
    }

    @Override
    public List<ServiceOfferRange> findServiceOfferedByEmpId(Long empId) {

        List<EmpOfferEntity> empOfferEntities = empOfferEntityMapper.selectByEmpId(empId);
        if(null == empOfferEntities || empOfferEntities.isEmpty())
            return Collections.emptyList();

        List<ProdCatalogEntity> prodCatalogs = prodCatalogEntityMapper.selectAll();
        Map<Long, String> offerId2Name = EdsUtils.convertToOfferId2NameByProductCatalogEntities(prodCatalogs);

        List<ServiceOfferRange> serviceOfferRanges = new ArrayList<>();
        for(EmpOfferEntity empOfferEntity : empOfferEntities){
            ServiceOfferRange serviceOfferRange = new ServiceOfferRange();
            serviceOfferRange.setCatalogId(empOfferEntity.getServiceId());
            serviceOfferRange.setCatalogName(offerId2Name.get(empOfferEntity.getServiceId()));
            serviceOfferRange.setLocation(empOfferEntity.getLocation());
            serviceOfferRange.setStatus(empOfferEntity.getStatus());

            serviceOfferRanges.add(serviceOfferRange);
        }

        return serviceOfferRanges;
    }

    @Override
    public void changePhoneByUserIdAndPhoneNumberAndName(Long userId, String phone, String name) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUpdateBy(userId);
        accountEntity.setId(userId);
        accountEntity.setUpdateTime(new Date());
        if(null != phone){
            accountEntity.setPhone(phone);
        }

        if(null != name){
            accountEntity.setName(name);
        }

        accountEntityMapper.updateByPrimaryKeySelective(accountEntity);
    }

    @Override
    public List<UserBrief> findEmployeesOfServiceOfferedByProdIdAndLocation(Long prodId, String location, boolean withStar) {

        String status = CONST.STATUS_ENABLE;
        List<EmpOfferEntity> empOfferEntities = empOfferEntityMapper.selectByProdIdAndLocationAndStatus(prodId, location, status);

        List<AccountEntity> accountEntities = null;
        if ((null != empOfferEntities && !empOfferEntities.isEmpty())) {

            List<Long> accountIds = new ArrayList<>();
            for (EmpOfferEntity empOfferEntity : empOfferEntities) {
                accountIds.add(empOfferEntity.getEmpId());
            }

            if (!accountIds.isEmpty()) {
                accountEntities = accountEntityMapper.selectByIds(accountIds);
            }
        }

        if(withStar) {
            if(null == accountEntities) {
                accountEntities = new ArrayList<>();
            }
            accountEntities.addAll(accountEntityMapper.selectBySpecialOfferType(CONST.OFFER_TYPE_STAR));
        }


        List<Long> empIds = new ArrayList<>();
        for(AccountEntity accountEntity : accountEntities){
            empIds.add(accountEntity.getId());
        }

        Map<Long, EmpSummary> empId2Summary = findEmpSummaryByEmpIds(empIds);

        return EdsUtils.convertToUserBrief(accountEntities, empId2Summary);
    }

    private Map<Long, EmpSummary> findEmpSummaryByEmpIds(List<Long> empIds) {

        List<EmpSummaryEntity> empSummaryEntities = empSummaryEntityMapper.selectByEmpIds(empIds);

        Map<Long, EmpSummary> empId2Summary = new HashMap<>();
        for(Long empId : empIds){
            empId2Summary.put(empId, new EmpSummary());
        }

        if(null != empSummaryEntities && !empSummaryEntities.isEmpty()) {
            for(EmpSummaryEntity empSummaryEntity : empSummaryEntities){
                Long empId = empSummaryEntity.getEmpId();

                EmpSummary empSummary = empId2Summary.get(empId);
                empSummary.setCreditRemain(empSummaryEntity.getCreditRemain());
                empSummary.setCreditTotal(empSummaryEntity.getCreditTotal());
                empSummary.setOrderQuantityDone(empSummaryEntity.getOrderQuantityDone());
                empSummary.setOrderQuantityServing(empSummaryEntity.getOrderQuantityServing());

                empId2Summary.put(empId, empSummary);
            }
        }

        return empId2Summary;
    }

    @Override
    public int updateUserPasswordByIdAndPassword(Long userId, String oldPwd, String password) {

        AccountEntity accountEntity = accountEntityMapper.selectByPrimaryKey(userId);
        if(oldPwd.trim().equals(accountEntity.getPassword())) {
            accountEntity.setPassword(password);
            accountEntity.setUpdateTime(new Date());
            accountEntity.setUpdateBy(userId);
            accountEntityMapper.updateByPrimaryKeySelective(accountEntity);

            return CONST.ERROR_CODE_OK;
        }else {
            return CONST.ERROR_CODE_FAILED;
        }
    }

    @Override
    public void setUserOfferTypeById(Long userId, String offerTypeStar) {
        AccountEntity accountEntity = accountEntityMapper.selectByPrimaryKey(userId);
        if(null == accountEntity || !accountEntity.getType().equals(CONST.ACCOUNT_TYPE_EMPLOYEE)){
            return;
        }

        accountEntity.setOfferType(offerTypeStar);
        accountEntity.setUpdateTime(new Date());

        accountEntityMapper.updateByPrimaryKeySelective(accountEntity);
    }

    @Override
    public User findByWxOpenId(String openId) {
        AccountEntity accountEntity = accountEntityMapper.selectByWxOpenId(openId);
        return EdsUtils.convertToUser(accountEntity);
    }

    @Override
    public Long saveNewOpenId(String openid, String role) {
        AccountEntity accountEntity = accountEntityMapper.selectByWxOpenId(openid);
        if(null == accountEntity){
            accountEntity = new AccountEntity();
            accountEntity.setWechatId(openid);
            accountEntity.setStatus(CONST.STATUS_ENABLE);
            accountEntity.setType(role);
            accountEntity.setCreateTime(new Date());
            accountEntityMapper.insertSelective(accountEntity);
        }
        return accountEntity.getId();
    }

    @Override
    public User login(String method, String username, String password) {

        User user;
        boolean valid = false;
        String phone=username;
        if(method.equals(CONST.LOGIN_METHOD_SYSTEM)) {
            user = findByPhone(phone);
        }else if(method.equals(CONST.LOGIN_METHOD_WECHAT)) {
            String openId = username;
            user = findByWxOpenId(openId);
            valid = true;
        }else if(method.equals(CONST.LOGIN_METHOD_SMS)){
            user = findByPhone(phone);
        } else {
            throw new AuthenticationException("不正确的认证方式");
        }

        if (user == null) {
            //这里返回后会报出对应异常
            throw new AuthenticationException("找不到当前账户");
        } else {
            if(user.getStatus().equals(CONST.STATUS_DISABLE)){
                throw new AuthenticationException("账户被禁用");
            }

            String rpassword = user.getPassword();
            if(valid || password.equals(rpassword)){
                //这里验证authenticationToken和simpleAuthenticationInfo的信息
                if(valid){
                    rpassword = username;
                }
                user.setToken(
                        JWTUtil.sign(user.getId(), phone, rpassword, user.getType())
                );
                return user;
            }else {
                throw new IncorrectCredentialsException();
            }
        }
    }

    @Override
    public List<User> findListOfAdminUsers() {

        List<AccountEntity> accountEntities = accountEntityMapper.selectUsersByRole(CONST.ACCOUNT_TYPE_ADMIN);

        return EdsUtils.convertToUsers(accountEntities);
    }

    @Override
    public void updateUserStatusByIdAndStatus(Long userId, String status) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(userId);
        accountEntity.setStatus(status);
        accountEntity.setUpdateBy(1l);
        accountEntity.setUpdateTime(new Date());
        accountEntityMapper.updateByPrimaryKeySelective(accountEntity);
    }

    @Override
    public Long addUserByNameAndPhoneAndPassword(String name, String phone, String password) {

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setType(CONST.ACCOUNT_TYPE_ADMIN);
        accountEntity.setName(name);
        accountEntity.setPhone(phone);
        accountEntity.setPassword(password);
        accountEntity.setStatus(CONST.STATUS_ENABLE);
        accountEntity.setCreateBy(1l);
        accountEntity.setCreateTime(new Date());
        accountEntity.setUpdateBy(1l);
        accountEntity.setUpdateTime(new Date());

        accountEntityMapper.insertSelective(accountEntity);

        return accountEntity.getId();
    }

    @Override
    public void updateUserPasswordByIdAndStatus(Long userId, String password) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(userId);
        accountEntity.setPassword(password);
        accountEntity.setUpdateBy(1l);
        accountEntity.setUpdateTime(new Date());
        accountEntityMapper.updateByPrimaryKeySelective(accountEntity);
    }

    @Override
    public Long applyOffer(ApplyOffer applyOffer) {

        OfferApplyEntity offerApplyEntity = composeOfferApplyEntity(
//                applyOffer.getIdcardImagesStr(),
//                applyOffer.getCertificateImagesStr(),
//                applyOffer.getIdNumber(),
                applyOffer.getLocationsStr(),
                applyOffer.getOffersStr(),
                applyOffer.getName(),
                applyOffer.getPhone());

        offerApplyEntity.setRemark("");
        offerApplyEntity.setStatus(CONST.STATUS_APPLYING);
        offerApplyEntity.setCreateTime(new Date());
        offerApplyEntity.setCreateBy(applyOffer.getApplierId());

        offerApplyEntityMapper.insertSelective(offerApplyEntity);

        return offerApplyEntity.getId();
    }

    private OfferApplyEntity composeOfferApplyEntity(String locationsStr, String offersStr, String name, String phone) {

        OfferApplyEntity offerApplyEntity = new OfferApplyEntity();

//        offerApplyEntity.setIdcardImages(idcardImagesStr);
//        offerApplyEntity.setCertificateImages(certificateImagesStr);
//        offerApplyEntity.setIdcardImages(idcardImagesStr);
//        offerApplyEntity.setIdNumber(idNumber);
        offerApplyEntity.setLocations(locationsStr);
        offerApplyEntity.setOffers(offersStr);
        offerApplyEntity.setName(name);
        offerApplyEntity.setPhone(phone);

        return offerApplyEntity;
    }

    @Override
    public void changeApplyOffer(ApplyOffer applyOffer) {

        OfferApplyEntity offerApplyEntity = composeOfferApplyEntity(
//                applyOffer.getIdcardImagesStr(),applyOffer.getCertificateImagesStr(),applyOffer.getIdNumber(),
                applyOffer.getLocationsStr(), applyOffer.getOffersStr(), applyOffer.getName(), applyOffer.getPhone()
        );

        offerApplyEntity.setId(applyOffer.getApplyId());
        offerApplyEntity.setUpdateTime(new Date());
        offerApplyEntity.setCreateBy(applyOffer.getApplierId());

        offerApplyEntityMapper.updateByPrimaryKeySelective(offerApplyEntity);
    }

    @Override
    public ApplyOfferPlus findLatestApplyOffedByApplierId(Long userId) {

        PageHelper.startPage(1,1, "create_time desc");
        List<OfferApplyEntity> offerApplyEntities = offerApplyEntityMapper.selectByApplierId(userId);
        if(null == offerApplyEntities || offerApplyEntities.isEmpty())
            return null;

        return EdsUtils.convertToApplyOfferPlus(offerApplyEntities.get(0));
    }

    @Override
    public PageModel<ApplyOfferPlus> findPageOfApplyOffersByStatus(String status, PageModel pageModel) {

        PageHelper.startPage(pageModel.getPageNum(), pageModel.getLimit());
        PageHelper.orderBy(pageModel.getOrderBy());

        List<OfferApplyEntity> offerApplyEntities = offerApplyEntityMapper.selectByStatus(status);

        List<ProdCatalogEntity> prodCatalogs = prodCatalogEntityMapper.selectAll();
        Map<Long, String> offerId2Name = EdsUtils.convertToOfferId2NameByProductCatalogEntities(prodCatalogs);
        PageInfo<ApplyOfferPlus> pageInfo = new PageInfo<>(EdsUtils.convertToApplyOfferPluses(offerApplyEntities, offerId2Name, globalConfigService));
        pageInfo.setTotal(
                PageHelper.count(() -> offerApplyEntityMapper.selectByStatus(status))
        );

        return PageModel.convertToPageModel(pageInfo);

    }

    @Override
    public int auditApplyOfferByAuditorIdAndApplyIdAndStatusAndRemark(Long auditorId, Long applyId, String status, String remark) {


        OfferApplyEntity offerApplyEntity = offerApplyEntityMapper.selectByPrimaryKey(applyId);
        if(null == offerApplyEntity){
            return CONST.ERROR_CODE_NOT_FOUND;
        }

        if(!offerApplyEntity.getStatus().equals(CONST.STATUS_APPLYING)){
            return CONST.ERROR_CODE_STATE;
        }

        /**如果审核状态变为通过，这里需要更新业务员申请的信息
         * 1. 姓名 电话
         * 2. 身份证
         * 3. 资格证
         * 4. 服务的地区
         * 5. 业务范围
         */
        if(status.equals(CONST.STATUS_DONE)){

            /**更改账户的姓名和手机*/
            Long applierId = offerApplyEntity.getCreateBy();
//            AccountEntity accountEntity = new AccountEntity();
//            accountEntity.setId(applierId);
//            accountEntity.setName(offerApplyEntity.getName());
//            accountEntity.setPhone(offerApplyEntity.getPhone());
//            accountEntity.setUpdateTime(new Date());
//            accountEntity.setUpdateBy(auditorId);
//            accountEntityMapper.updateByPrimaryKeySelective(accountEntity);

            /**身份证 资格证*/
//            EmpProfileEntity empProfileEntity = empProfileEntityMapper.selectByPrimaryKey(applierId);
//            if(null == empProfileEntity){
//                empProfileEntity = new EmpProfileEntity();
//                empProfileEntity.setEmpId(applierId);
////                empProfileEntity.setCertificateImages(offerApplyEntity.getCertificateImages());
////                empProfileEntity.setIdcardImages(offerApplyEntity.getIdcardImages());
////                empProfileEntity.setIdcardNumber(offerApplyEntity.getIdNumber());
//                empProfileEntity.setStatus(CONST.STATUS_ENABLE);
//                empProfileEntity.setCreateTime(new Date());
//                empProfileEntity.setCreateBy(auditorId);
//                empProfileEntityMapper.insertSelective(empProfileEntity);
//            }
//            else { //如果之前已经存在的，只更新证书文件
////                empProfileEntity.setCertificateImages(offerApplyEntity.getCertificateImages());
//                empProfileEntity.setUpdateBy(auditorId);
//                empProfileEntity.setUpdateTime(new Date());
//                empProfileEntityMapper.updateByPrimaryKeySelective(empProfileEntity);
//            }

            List<String> locations = Arrays.asList(offerApplyEntity.getLocations().split(","));
            List<String> offers    = Arrays.asList(offerApplyEntity.getOffers().split(","));

            /**刷新服务的地区 业务范围*/
            renewEmployeeOfferByEmpIdAndLocationsAndOffers(applierId, locations, offers, auditorId);

        }

        offerApplyEntity.setStatus(status);
        offerApplyEntity.setRemark(remark);
        offerApplyEntity.setUpdateBy(auditorId);
        offerApplyEntity.setUpdateTime(new Date());

        offerApplyEntityMapper.updateByPrimaryKeySelective(offerApplyEntity);

        return CONST.ERROR_CODE_OK;
    }

    private void renewEmployeeOfferByEmpIdAndLocationsAndOffers(Long applierId, List<String> locations, List<String> offers, Long auditorId) {
        //查找现在有哪些业务和服务地区的对应关系
        List<EmpOfferEntity> oldEmpOfferEntities = empOfferEntityMapper.selectByEmpId(applierId);

        Set<String> newEmpOfferKey = new HashSet<>();
        Set<String> renewEmpOfferKey = new HashSet<>();

        Map<String, String> oldEmpOfferKeys2Status = new HashMap<>();
        if(null != oldEmpOfferEntities && !oldEmpOfferEntities.isEmpty()) {
            for (EmpOfferEntity empOfferEntity : oldEmpOfferEntities) {
                String key = composeEmpOfferKey(empOfferEntity.getEmpId(), empOfferEntity.getLocation(), empOfferEntity.getServiceId());
                oldEmpOfferKeys2Status.put(key, empOfferEntity.getStatus());
            }
        }


        for(String location : locations){
            for(String offer : offers){
                Long offerId = Long.valueOf(offer);
                String key = composeEmpOfferKey(applierId, location, offerId);
                String oldStatus = oldEmpOfferKeys2Status.get(key);

                //获取了之后就移除，剩下的都是需要删除的
                oldEmpOfferKeys2Status.remove(key);

                if(null != oldStatus && oldStatus.equals(CONST.STATUS_ENABLE)) {
                    //这里可以不用新增，直接使用原来配置的就行了
                    continue;
                }
                if(null == oldStatus){
                    newEmpOfferKey.add(key);
                    continue;
                }
                if(oldStatus.equals(CONST.STATUS_DISABLE)){
                    renewEmpOfferKey.add(key);
                    continue;
                }
            }
        }

//        Set<String> disableEmpOfferKey = oldEmpOfferKeys2Status.keySet();

//        if(!disableEmpOfferKey.isEmpty()){
//            List<EmpOfferEntity> empOfferEntities = composeEmpOffersByKeySetAndStatus(disableEmpOfferKey, CONST.STATUS_DISABLE, true, auditorId);
//            batchUpdateEmpOfferByPrimaryKeySelective(empOfferEntities);
//        }

        if(!renewEmpOfferKey.isEmpty()){
            List<EmpOfferEntity> empOfferEntities = composeEmpOffersByKeySetAndStatus(renewEmpOfferKey, CONST.STATUS_ENABLE, true, auditorId);
            batchUpdateEmpOfferByPrimaryKeySelective(empOfferEntities);
        }

        if(!newEmpOfferKey.isEmpty()){
            List<EmpOfferEntity> empOfferEntities = composeEmpOffersByKeySetAndStatus(newEmpOfferKey, CONST.STATUS_ENABLE, false, auditorId);
            for(EmpOfferEntity empOfferEntity : empOfferEntities){
                empOfferEntityMapper.insert(empOfferEntity);
            }
//            empOfferEntityMapper.batchInsert(empOfferEntities);
        }
    }

    private void batchUpdateEmpOfferByPrimaryKeySelective(List<EmpOfferEntity> empOfferEntities) {
        for(EmpOfferEntity empOfferEntity : empOfferEntities){
            empOfferEntityMapper.updateByPrimaryKeySelective(empOfferEntity);
        }
    }

    private List<EmpOfferEntity> composeEmpOffersByKeySetAndStatus(Set<String> empOfferKeys, String status, boolean update, Long auditorId) {
        if(null == empOfferKeys || empOfferKeys.isEmpty())
            return Collections.emptyList();

        List<EmpOfferEntity> empOfferEntities = new ArrayList<>();
        for(String empOfferKey : empOfferKeys){

            String[] empOfferKeyArray = empOfferKey.split("@");

            EmpOfferEntity empOfferEntity = new EmpOfferEntity();
            empOfferEntity.setStatus(status);
            empOfferEntity.setEmpId(Long.valueOf(empOfferKeyArray[0]));
            empOfferEntity.setLocation(empOfferKeyArray[1]);
            empOfferEntity.setServiceId(Long.valueOf(empOfferKeyArray[2]));

            if(update){
                empOfferEntity.setUpdateTime(new Date());
                empOfferEntity.setUpdateBy(auditorId);
            }else {
                empOfferEntity.setCreateBy(auditorId);
                empOfferEntity.setCreateTime(new Date());
            }

            empOfferEntities.add(empOfferEntity);
        }

        return empOfferEntities;
    }

    private String composeEmpOfferKey(Long empId, String location, Long serviceId) {
        return String.format("%s@%s@%s",empId.toString(),location,serviceId.toString());
    }

//    @Override
//    public User findByName(String name) {
//        return null;
//    }
}

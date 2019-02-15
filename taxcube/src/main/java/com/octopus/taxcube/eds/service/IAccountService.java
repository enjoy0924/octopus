package com.octopus.taxcube.eds.service;

import com.octopus.taxcube.common.PageModel;
import com.octopus.taxcube.eds.pojo.*;

import java.util.List;

public interface IAccountService {

    User findByPhone(String phone);

    User findByWxOpenId(String openId);

    Long saveNewOpenId(String openid, String role);

    User login(String method, String username, String password);

    List<User> findListOfAdminUsers();

    void updateUserStatusByIdAndStatus(Long userId, String status);

    Long addUserByNameAndPhoneAndPassword(String name, String phone, String password);

    void updateUserPasswordByIdAndStatus(Long userId, String password);

    Long applyOffer(ApplyOffer applyOffer);

    void changeApplyOffer(ApplyOffer applyOffer);

    ApplyOfferPlus findLatestApplyOffedByApplierId(Long userId);

    PageModel<ApplyOfferPlus> findPageOfApplyOffersByStatus(String status, PageModel pageModel);

    int auditApplyOfferByAuditorIdAndApplyIdAndStatusAndRemark(Long userId, Long applyId, String status, String remark);

    User findByUserId(Long userId);

    List<ServiceOfferRange> findServiceOfferedByEmpId(Long empId);

    void changePhoneByUserIdAndPhoneNumberAndName(Long userId, String phone, String name);

    List<UserBrief> findEmployeesOfServiceOfferedByProdIdAndLocation(Long prodId, String location, boolean withStar);

    int updateUserPasswordByIdAndPassword(Long userId, String oldPwd, String password);

    void setUserOfferTypeById(Long userId, String offerTypeStar);
}

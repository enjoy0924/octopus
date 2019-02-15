package com.octopus.taxcube.eds.service.impl;

import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.dao.EmpCertApplyEntityMapper;
import com.octopus.taxcube.eds.entity.EmpCertApplyEntity;
import com.octopus.taxcube.eds.service.ICertService;
import com.octopus.taxcube.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CertServiceImpl implements ICertService {

    @Autowired
    private EmpCertApplyEntityMapper empCertApplyEntityMapper;

    @Override
    public void applyNewCert(String type, String images, Long userId) {

        EmpCertApplyEntity empCertApplyEntity = empCertApplyEntityMapper.selectByPrimaryKey(userId);
        boolean create = false;
        if(null == empCertApplyEntity){
            create = true;
            empCertApplyEntity = new EmpCertApplyEntity();
            empCertApplyEntity.setEmpId(userId);
            empCertApplyEntity.setCreateBy(userId);
            empCertApplyEntity.setCreateTime(new Date());
        }else {
            empCertApplyEntity.setUpdateBy(userId);
            empCertApplyEntity.setUpdateTime(new Date());
        }

        if(type.equals("idcard")){
            empCertApplyEntity.setIdcardImages(images);
            empCertApplyEntity.setIdcardStatus(CONST.STATUS_PENDING);
        }else if(type.equals("certificate")){
            empCertApplyEntity.setCertificateImages(images);
            empCertApplyEntity.setCetificateStatus(CONST.STATUS_PENDING);
        }else {
            throw new NotFoundException("不支持的认证类型");
        }

        if(create){
            empCertApplyEntityMapper.insertSelective(empCertApplyEntity);
        }else {
            empCertApplyEntityMapper.updateByPrimaryKeySelective(empCertApplyEntity);
        }
    }
}

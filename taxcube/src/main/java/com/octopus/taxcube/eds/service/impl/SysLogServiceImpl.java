package com.octopus.taxcube.eds.service.impl;

import com.octopus.taxcube.eds.dao.SysLogEntityMapper;
import com.octopus.taxcube.eds.entity.SysLogEntity;
import com.octopus.taxcube.eds.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private SysLogEntityMapper sysLogEntityMapper;

    @Override
    public void save(SysLogEntity sysLog) {
        sysLogEntityMapper.insert(sysLog);
    }
}

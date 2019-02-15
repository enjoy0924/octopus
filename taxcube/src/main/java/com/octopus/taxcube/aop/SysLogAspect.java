package com.octopus.taxcube.aop;

import com.alibaba.fastjson.JSON;
import com.octopus.taxcube.annotation.SysLog;
import com.octopus.taxcube.eds.entity.SysLogEntity;
import com.octopus.taxcube.eds.service.ISysLogService;
import com.octopus.taxcube.shiro.JWTUtil;
import com.octopus.taxcube.util.HttpContextUtils;
import com.octopus.taxcube.util.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private ISysLogService sysLogService;

    @Pointcut("@annotation(com.octopus.taxcube.annotation.SysLog)")
    public void logPointCut() {

    }

    @Before("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLogEntity sysLog = new SysLogEntity();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if(syslog != null){
            //注解上的描述
            sysLog.setOperation(syslog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        if(args.length>0){
            String params = JSON.toJSONString(args);
            sysLog.setParam(params);
        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IpUtil.getIpAddr(request));

        //用户名
        String username = JWTUtil.getUsername();

        sysLog.setUsername(username);

        sysLog.setCreateTime(new Date());
        //保存系统日志
        sysLogService.save(sysLog);
    }

}
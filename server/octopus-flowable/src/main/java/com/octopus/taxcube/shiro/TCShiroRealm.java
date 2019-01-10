package com.octopus.taxcube.shiro;

import com.octopus.taxcube.common.CONST;
import com.octopus.taxcube.eds.entity.Permission;
import com.octopus.taxcube.eds.entity.Role;
import com.octopus.taxcube.eds.entity.User;
import com.octopus.taxcube.eds.service.ILoginService;
import com.octopus.taxcube.utils.JsonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class TCShiroRealm extends AuthorizingRealm {

    //用于用户查询
    @Autowired
    private ILoginService loginService;

    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String phone= (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = loginService.findByPhone(phone);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role:user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permission permission:role.getPermissions()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            throw new AuthenticationException("错误的token信息");
        }
        //获取用户信息
        User user;
        boolean valid = false;
        if(authenticationToken instanceof UsernamePasswordToken) {
            String phone = authenticationToken.getPrincipal().toString();
            user = loginService.findByPhone(phone);
        }else if(authenticationToken instanceof WxAuthToken) {
            String openId = authenticationToken.getPrincipal().toString();
            user = loginService.findByWxOpenId(openId);
            valid = true;
        }else {
            throw new AuthenticationException("不正确的认证方式");
        }

        if (user == null) {
            //这里返回后会报出对应异常
            throw new AuthenticationException("找不到当前账户");
        } else {
            if(user.getStatus().equals(CONST.STATUS_DISABLE)){
                throw new AuthenticationException("账户被禁用");
            }

            String cpassword = new String((char[])authenticationToken.getCredentials());
            String rpassword = user.getPassword();
            if(valid || cpassword.equals(rpassword)){
                //这里验证authenticationToken和simpleAuthenticationInfo的信息
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getPhone(), user.getPassword(), getName());

                SecurityUtils.getSubject().getSession().setAttribute(user.getPhone(), JsonUtil.getJsonByObj(user));

                return simpleAuthenticationInfo;
            }else {
                throw new IncorrectCredentialsException();
            }
        }
    }
}

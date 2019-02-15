package com.octopus.taxcube.shiro;


import com.octopus.taxcube.common.constenum.CONST;
import com.octopus.taxcube.eds.pojo.User;
import com.octopus.taxcube.eds.service.IAccountService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/*
 *Author:zhangxin_an，zc
 *Description:
 *Data:Created in 21:25 2018/4/9
 */
public class AuthRealm extends AuthorizingRealm {
    //用于用户查询
    @Autowired
    private IAccountService accountService;

    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String jwtToken= (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        String roleType = JWTUtil.getRoleType(jwtToken);

        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        for (Role role:user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(roleType);
//            for (Permission permission:role.getPermissions()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission("");
//            }
//        }
        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        String token = (String) auth.getCredentials();
        Long userId = JWTUtil.getUserId(token);
        String username = JWTUtil.getUsername(token);
        String roleType = JWTUtil.getRoleType(token);
        if (userId == null || roleType == null) {
            throw new AuthenticationException("token invalid");
        }

        User userBean = accountService.findByUserId(userId);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (CONST.STATUS_DISABLE == userBean.getStatus()) {
            throw new AuthenticationException("当前账户不可用");
        }

        String password = userBean.getPassword();
        if(!username.matches("[0-9]+") && !roleType.equals(CONST.ACCOUNT_TYPE_SYSADMIN)){
            //非电话号码登录，微信登录
            password = username;
        }

        if (!JWTUtil.verify(token, password)) {
            throw new AuthenticationException("token 失效");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token, token, getName());


        return info;//放入shiro.调用CredentialsMatcher检验密码
    }

}
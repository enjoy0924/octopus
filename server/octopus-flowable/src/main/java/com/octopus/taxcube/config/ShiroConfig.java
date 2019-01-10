package com.octopus.taxcube.config;

import com.octopus.taxcube.exception.GlobalExceptionHandler;
import com.octopus.taxcube.shiro.KickoutSessionControlFilter;
import org.apache.shiro.mgt.SecurityManager;
import com.octopus.taxcube.shiro.TCShiroRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

//    @Value("${redis.host}")
//    private String redisHost;
//    @Value("${redis.port}")
//    private Integer redisPort;
//    @Value("${redis.expireTime}")
//    private Integer redisExpireTime;

    //将自己的验证方式加入容器
    @Bean
    public TCShiroRealm myShiroRealm() {
        TCShiroRealm shiroRealm = new TCShiroRealm();
        return shiroRealm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());

        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("192.168.1.253");
        redisManager.setPort(6379);
        redisManager.setExpire(1800);// 配置缓存过期时间
        redisManager.setTimeout(0);
        // redisManager.setPassword(password);
        return redisManager;
    }

    @Bean
    public SimpleCookie cookie(){
        SimpleCookie cookie = new SimpleCookie("TOKEN-SID"); //  cookie的name,对应的默认是 JSESSIONID
        cookie.setHttpOnly(true);
        cookie.setPath("/api");        //  path为 / 用于多个系统共享JSESSIONID
        return cookie;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookie(cookie());
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    @Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter() {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager(cacheManager());
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(1);
        kickoutSessionControlFilter.setKickoutUrl("/auth/kickout");
        return kickoutSessionControlFilter;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterChainMap = new HashMap<String, String>();
        //登出
        filterChainMap.put("/api/*/login", "anon");
        //
        filterChainMap.put("/logout","logout");

        filterChainMap.put("/swagger-ui.html", "anon");
        filterChainMap.put("/swagger-resources/**", "anon");
        filterChainMap.put("/v2/api-docs", "anon");
        filterChainMap.put("/webjars/**", "anon");
        filterChainMap.put("/doc.html", "anon");
        filterChainMap.put("/favicon.ico", "anon");
        filterChainMap.put("/index","anon");
        filterChainMap.put("/csrf", "anon");

        //对所有用户认证
        filterChainMap.put("/**","authc");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题    https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new GlobalExceptionHandler();
    }
}

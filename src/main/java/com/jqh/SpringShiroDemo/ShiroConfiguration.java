package com.jqh.SpringShiroDemo;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager ;

import java.util.LinkedHashMap;
//  配置类
@Configuration
public class ShiroConfiguration {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);

        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/unauthorized");

        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 注意，以下配置可以在数据库中实现
        filterChainDefinitionMap.put("/index","authc"); //访问index，必须先验证
        filterChainDefinitionMap.put("/login","anon"); // 访问login ，不需要验证
        filterChainDefinitionMap.put("/loginUser","anon"); //  访问loginUser ，不需要验证
        filterChainDefinitionMap.put("/admin","roles[admin]") ;// 不同角色对应不同接口，只有admin角色才能访问admin页面,如果不能被访问，返回到unauthorized页面
        filterChainDefinitionMap.put("/edit","perms[edit]") ; //  不同权限对应不同的接口，只有edit权限的人才能访问edit
        filterChainDefinitionMap.put("/druid/**","anon");   // druid监控  http://127.0.0.1:8080/druid/index.html
        filterChainDefinitionMap.put("/**","user");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager ;
    }

    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher){
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return  authRealm ;
    }

    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher(){
        return new CredentialMatcher();
    }

    // 集成spring
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}

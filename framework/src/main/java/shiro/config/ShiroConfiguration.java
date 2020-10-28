package shiro.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import shiro.Realm.LoginRealm;
import shiro.property.ShiroProperty;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/10/27
 * @since 1.0
 */
@Configuration
public class ShiroConfiguration {
    @Bean
    public LoginRealm loginRealm(){
        return new LoginRealm();
    }

    /**
     * 初始化Shiro安全管理器
     */
    @Bean
    public SecurityManager securityManager(LoginRealm loginRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(loginRealm);
        return securityManager;
    }

    /**
     * ShiroFilter配置
     * @param securityManager 安全管理器
     * @return ShiroFilterFactoryBean实例
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, ShiroProperty shiroProperty){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // Shiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 身份认证失败，则跳转到登录页面的配置
        shiroFilterFactoryBean.setLoginUrl(shiroProperty.getLoginUrl());
        // 权限认证失败，则跳转到指定页面
        shiroFilterFactoryBean.setUnauthorizedUrl(shiroProperty.getUnauthorizedUrl());
        //权限认证成功，则跳转到指定页面
        shiroFilterFactoryBean.setSuccessUrl(shiroProperty.getSuccessUrl());
        // Shiro连接约束配置，即过滤链的定义
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //请求白名单即匿名访问过滤链配置
        shiroProperty.configAnonFilterChain(filterChainDefinitionMap);
        // 未匹配到的请求，使用如下配置
        filterChainDefinitionMap.put("/**", "authc");
        // 自定义filters初始化
        Map<String, Filter> filters = new LinkedHashMap<>();
        shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    /**
     *  注册LifecycleBeanPostProcessor实例
     * @return LifecycleBeanPostProcessor实例
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    protected DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }
    /**
     * 配置Shiro授权注解通知器，做细粒度权限控制
     * @see org.apache.shiro.authz.annotation.RequiresPermissions
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

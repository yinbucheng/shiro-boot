package cn.bucheng.shiroboot.config;

import cn.bucheng.shiroboot.constant.ShiroConstant;
import cn.bucheng.shiroboot.core.SimpleAuthorityRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ：yinchong
 * @create ：2019/7/5 8:59
 * @description：shiro集成spring配置的核心类
 * @modified By：
 * @version:
 */
@Configuration
public class ShiroConfig {


    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //这里表示下面不需要授权访问
        loadPublic(filterChainDefinitionMap);
        loadAuthority(filterChainDefinitionMap);
        //这里表示配置授权才能进行访问(这里一定要在最后)
        filterChainDefinitionMap.put("/*", ShiroConstant.AUTHORIZATIOIN);
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        factoryBean.setLoginUrl("/login");
        factoryBean.setSuccessUrl("/index");
        factoryBean.setUnauthorizedUrl("/403");
        factoryBean.setSecurityManager(securityManager);
        return factoryBean;
    }

    @Bean
    DefaultWebSecurityManager securityManager(@Qualifier("simpleAuthorizingRealm") AuthenticatingRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    AuthorizingRealm simpleAuthorizingRealm() {
        return new SimpleAuthorityRealm();
    }



    //加载权限
    public void loadAuthority( Map<String, String> params){
        params.put("/user/test","perms[/user/test]");
    }

    //加载不需要进行认证的地址
    public void loadPublic(Map<String,String> params){
        params.put("/user/login", ShiroConstant.ANONYMITY);
    }


}

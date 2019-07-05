package cn.bucheng.shiroboot.core.config;

import cn.bucheng.shiroboot.core.constant.ShiroConstant;
import cn.bucheng.shiroboot.core.SimpleAuthorityRealm;
import cn.bucheng.shiroboot.mapper.ResourceMapper;
import cn.bucheng.shiroboot.mapper.UserMapper;
import cn.bucheng.shiroboot.model.po.ResourcePO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
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
    @Autowired
    private ResourceMapper resourceMapper;


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
        Wrapper<ResourcePO> wrapper = new EntityWrapper<>();
        List<ResourcePO> resourcePOS = resourceMapper.selectList(wrapper);
        if(null!=resourcePOS){
            for(ResourcePO resourcePO:resourcePOS){
                params.put(resourcePO.getResUrl(),"perms["+resourcePO.getResUrl()+"]");
            }
        }
    }

    //加载不需要进行认证的地址
    public void loadPublic(Map<String,String> params){
        params.put("/user/login", ShiroConstant.ANONYMITY);
    }


}

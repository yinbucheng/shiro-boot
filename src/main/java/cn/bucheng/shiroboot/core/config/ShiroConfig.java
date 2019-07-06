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
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
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

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        redisManager.setExpire(1800);// 配置缓存过期时间
        redisManager.setTimeout(0);
        return redisManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
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
        params.put("/logout", "logout");
        params.put("/css/**",ShiroConstant.ANONYMITY);
        params.put("/js/**",ShiroConstant.ANONYMITY);
        params.put("/img/**",ShiroConstant.ANONYMITY);
        params.put("/font-awesome/**",ShiroConstant.ANONYMITY);
        params.put("/templates/**",ShiroConstant.ANONYMITY);
    }


}

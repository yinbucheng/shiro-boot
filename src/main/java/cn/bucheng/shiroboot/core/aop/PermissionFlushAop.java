package cn.bucheng.shiroboot.core.aop;

import cn.bucheng.shiroboot.mapper.ResourceMapper;
import cn.bucheng.shiroboot.model.po.ResourcePO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author buchengyin
 * @create 2019/7/6 16:21
 * @describe 刷新权限,添加aop监控service方法动态刷新权限
 */
@Aspect
@Component
public class PermissionFlushAop {
    @Autowired
    ShiroFilterFactoryBean filterFactoryBean;

    @Autowired
    private ResourceMapper resourceMapper;

    @After("execution(* cn.bucheng.shiroboot.service.*.*.*(..))")
    public void after() {
        System.out.println("==============>刷新权限<============");
        //加载权限
        Map<String, String> params = filterFactoryBean.getFilterChainDefinitionMap();
        if (params == null) {
            params = new HashMap<>();
        }
        Wrapper<ResourcePO> wrapper = new EntityWrapper<>();
        List<ResourcePO> resourcePOS = resourceMapper.selectList(wrapper);
        if (null != resourcePOS) {
            for (ResourcePO resourcePO : resourcePOS) {
                if(resourcePO.getResUrl()==""||resourcePO.getResUrl().equals(""))
                    continue;
                params.put(resourcePO.getResUrl(), "perms[" + resourcePO.getResUrl() + "]");
            }
        }
        filterFactoryBean.setFilterChainDefinitionMap(params);

    }
}

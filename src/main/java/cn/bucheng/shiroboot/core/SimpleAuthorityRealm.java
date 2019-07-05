package cn.bucheng.shiroboot.core;

import cn.bucheng.shiroboot.mapper.UserMapper;
import cn.bucheng.shiroboot.model.po.ResourcePO;
import cn.bucheng.shiroboot.model.po.UserPO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ：yinchong
 * @create ：2019/7/5 9:04
 * @description：
 * @modified By：
 * @version:
 */
public class SimpleAuthorityRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进行认证 " + authenticationToken.getPrincipal());
        String account = (String) authenticationToken.getPrincipal();
        UserPO user = userMapper.findByName(account);
        if (null == user) {
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
        SimplePrincipalCollection collection = new SimplePrincipalCollection();
        collection.add(account, user.getUserName());
        info.setPrincipals(collection);
        info.setCredentials(user.getPassword());
        return info;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject = SecurityUtils.getSubject();
        System.out.println("进行授权 " + subject.getPrincipal());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<ResourcePO> resourcePOS = userMapper.listResourcesByUserName((String) subject.getPrincipal());
        if (resourcePOS != null) {
            for (ResourcePO resourcePO : resourcePOS) {
                simpleAuthorizationInfo.addStringPermission(resourcePO.getResUrl());
            }
        }
        return simpleAuthorizationInfo;
    }

}

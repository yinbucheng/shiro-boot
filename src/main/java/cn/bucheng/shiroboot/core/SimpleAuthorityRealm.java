package cn.bucheng.shiroboot.core;

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

/**
 * @author ：yinchong
 * @create ：2019/7/5 9:04
 * @description：
 * @modified By：
 * @version:
 */
public class SimpleAuthorityRealm extends AuthorizingRealm {
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进行认证 " + authenticationToken.getPrincipal());
        String account = (String) authenticationToken.getPrincipal();
        if (!"yinchong".equals(account) && !"yinbucheng".equals(account)) {
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
        SimplePrincipalCollection collection = new SimplePrincipalCollection();
        collection.add("yinchong", "尹冲");
        collection.add("yinbucheng", "尹冲");
        info.setPrincipals(collection);
        info.setCredentials("12345");
        return info;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject = SecurityUtils.getSubject();
        System.out.println("进行授权 "+subject.getPrincipal());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission("/user/test");
        return simpleAuthorizationInfo;
    }

}

package com.wp.service.realm;

import com.wp.dao.IUserDao;
import com.wp.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: bos-parent
 * @description: 自定义realm, 并注入给securityManager
 * @author: Pan wu
 * @create: 2018-08-15 23:12
 **/
public class BosRealm extends AuthorizingRealm {

    @Autowired
    private IUserDao iUserDao;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("test whether myReal start  ");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        User user = iUserDao.getUserByName(username);
        if(user == null) {
            return null;
        }
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission("staff-list");
        return simpleAuthorizationInfo;
    }
}

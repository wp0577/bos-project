package com.wp.service.realm;

import com.wp.dao.IFunctionDao;
import com.wp.dao.IUserDao;
import com.wp.domain.Function;
import com.wp.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: bos-parent
 * @description: 自定义realm, 并注入给securityManager
 * @author: Pan wu
 * @create: 2018-08-15 23:12
 **/
public class BosRealm extends AuthorizingRealm {

    @Autowired
    private IUserDao iUserDao;
    @Autowired
    private IFunctionDao iFunctionDao;

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
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String name = user.getUsername();
        List<Function> list = new ArrayList<>();
        if(name.equals("admin")) {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
            list = iFunctionDao.getByCriterial(detachedCriteria);
        }
        else {
            list = iFunctionDao.getFunctionsByUserId(user.getId());
        }
        for (Function f : list) {
            simpleAuthorizationInfo.addStringPermission(f.getCode());
        }
        return simpleAuthorizationInfo;
    }
}

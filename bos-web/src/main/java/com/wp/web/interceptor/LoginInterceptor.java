package com.wp.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wp.domain.User;
import com.wp.utils.BosUtil;

public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        User user = BosUtil.getUserFromSession();
        if(user == null) return "login";
        else
        return actionInvocation.invoke();
    }
}

package com.wp.web.action;

import com.wp.domain.User;
import com.wp.service.IUserService;
import com.wp.web.action.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    private String checkcode;
    @Autowired
    private IUserService userService;

    public String login() throws Exception {
        //get correct checkcode from session
        String rightCheckcode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        if(rightCheckcode.equals(checkcode)) {
            User user = (User) userService.login(model);
            if(user == null) {
                addActionError("username or password wrong");
                return LOGIN;
            }
            ServletActionContext.getRequest().getSession().setAttribute("user", user);
            return SUCCESS;
        }
        else {
            //
            addActionError("the validate code is wrong");
            return LOGIN;
        }
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
}
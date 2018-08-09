package com.wp.web.action;

import com.wp.domain.User;
import com.wp.service.IUserService;
import com.wp.utils.MD5加密.BosUtil;
import com.wp.web.action.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    private String checkcode;
    @Autowired
    private IUserService userService;

    public String editPassword() {
        //用下面的方法会将user整个更新一遍，我们可以只更新其中password字段
        /*User user = BosUtil.getUserFromSession();
        user.setPassword(model.getPassword());*/
        User user = BosUtil.getUserFromSession();
        String res = "1";
        try {
            userService.editPassword(user.getId(), model.getPassword());
        } catch (Exception e){
            res = "0";
        }
        try {
            ServletActionContext.getResponse().getWriter().write(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public String logout() {
        ServletActionContext.getRequest().getSession().invalidate();
        return LOGIN;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
}
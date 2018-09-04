package com.wp.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.wp.domain.User;
import com.wp.service.IUserService;
import com.wp.utils.BosUtil;
import com.wp.utils.MD5加密.MD5Utils;
import com.wp.utils.crm.Customer;
import com.wp.utils.crm.ICustomerService;
import com.wp.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IUserService userService;

    private String checkcode;

    private String[] roleIds;

    public String countNumber() {
        Object number = ServletActionContext.getServletContext().getAttribute("number");
        try {
            ServletActionContext.getResponse().getWriter().write(String.valueOf(number));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("user number = " +  number);
        return NONE;
    }

    public String add() {
        userService.add(model, roleIds);
        return "list";
    }

    public String pageQuery() {
        userService.pageQuery(pageBean);
        String2Json(pageBean, new String[]{"noticebills","roles"});
        return NONE;
    }

    public String login() {
        String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        if(StringUtils.isNotBlank(checkcode) && checkcode.equals(key)) {
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken authenticationToken = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));
            try {
                subject.login(authenticationToken);
                //得到user后放入session
                User user = (User) subject.getPrincipal();
                ServletActionContext.getRequest().getSession().setAttribute("user",user);
            }catch (Exception e) {
                e.printStackTrace();
                return LOGIN;
            }
            return SUCCESS;
        }
        else {
            return LOGIN;
        }
    }
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

    public String login_bak() throws Exception {
        //test cxf service
        /*List<Customer> all = iCustomerService.getAllHasAssociation("123");
        System.out.println(all);*/

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


    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

}
package com.wp.utils;

import com.wp.domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class BosUtil {

    public static HttpSession getSession() {
        return ServletActionContext.getRequest().getSession();
    }

    public static User getUserFromSession() {
        return (User) getSession().getAttribute("user");
    }

}

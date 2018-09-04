package com.wp.web.listener;

import com.wp.dao.ILoginInfoDao;
import com.wp.dao.IUserDao;
import com.wp.domain.LoginInfo;
import com.wp.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;

public class SessionCountListener implements HttpSessionListener {


    //在线用户数
    private int number;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
/*
        ILoginInfoDao iLoginInfoDao=(ILoginInfoDao) SessionUtil.getObjectFromApplication(se.getSession().getServletContext(), "ILoginInfoDaoImpl");
        List<LoginInfo> all = iLoginInfoDao.getAll();
        List<LoginInfo> userList = (List<LoginInfo>) se.getSession().getServletContext().getAttribute("userList");
*/
        //设置session失效时间，为调试用

        se.getSession().setMaxInactiveInterval(60*2);
        ArrayList<LoginInfo> userList = (ArrayList<LoginInfo>) se.getSession().getServletContext().getAttribute("userList");
        for (LoginInfo loginInfo:userList) {
            System.out.println("siez = "+ userList.size());
            System.out.println("id = "+loginInfo.getSessionIdString());
            System.out.println("ip = "+loginInfo.getIpString());
        }
        if(SessionUtil.getUserBySessionId(userList, se.getSession().getId())==null) {
            number++;
        }
        //在线用户的数量存储到域对象ServletContext的number中
        se.getSession().getServletContext().setAttribute("number", number);
        System.out.println("number " +number);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //list是存储在域对象ServletContext中，用于记录用户的的日志信息
        ArrayList<LoginInfo> list=
                (ArrayList<LoginInfo>) se.getSession().getServletContext().getAttribute("userList");
        //根据list去删session中的number
        if(list!=null || list.size()>0) number=(number<=0)?0:number-1;
        se.getSession().getServletContext().setAttribute("number", number);
        //根据sessionid删除将要推出的用户信息
        SessionUtil.remove(list,se.getSession().getId());
        se.getSession().getServletContext().setAttribute("userList", list);
        System.out.println("after = "+ number);
    }
}

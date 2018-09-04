package com.wp.web.listener;

import com.wp.domain.LoginInfo;
import com.wp.service.ILoginInfoService;
import com.wp.utils.SessionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Service
@Transactional(readOnly = false)
public class RequestListener extends RequestContextListener implements ServletRequestListener {


    private ArrayList<LoginInfo> userList=new ArrayList<LoginInfo>();  //在线用户Id

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        //因为session监听器是和spring的监听器是一个级别，启动session监听器还没加载完spring监听器，
        // 所以说注入的对象都为null。可以在监听器使用WebApplicationContextUtils来获取加载在servletContext中装载的spring容器，
        // 而不会创建新的spring容器，而造成ssm项目中存在两个spring容器！
        ILoginInfoService iLoginInfoService=(ILoginInfoService) SessionUtil.getObjectFromApplication(sre.getServletContext(), "ILoginInfoServiceImp");

        //创建用户list在servlectcontext域对象中
        userList=(ArrayList<LoginInfo>) sre.getServletContext().getAttribute("userList");
        if(userList==null){
            userList=new ArrayList<LoginInfo>();
        }
        //获得用户信息之sessionid
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        String id=request.getSession().getId();
        if(SessionUtil.getUserBySessionId(userList, id)==null){
            //获得ip地址
            String remoteAddr = request.getRemoteAddr();
            //0:0:0:0:0:0:0:1应该是shiro安全管理器创建的，当localhost发起request时，0:0:0:0:0:0:0:1也会发起一次请求并算不同的sessionId
            //if(remoteAddr.equals("0:0:0:0:0:0:0:1")) remoteAddr=request.getLocalAddr();
            LoginInfo u=iLoginInfoService.getLoginByIp(remoteAddr);
            if(u==null) {
                u = new LoginInfo();
                u.setIpString(request.getRemoteAddr());
                u.setTotalCount(1);
                u.setFirstTimeString(new Timestamp(new Date().getTime()));
            }
            else {
                u.setTotalCount(u.getTotalCount()+1);
            }
            //无论是否存在都存在sessionId作为判重的要求
            u.setSessionIdString(id);
            //获得登录地址
            u.setLastTimeString(new Timestamp(new Date().getTime()));
            userList.add(u);
            iLoginInfoService.save(u);
        }
        sre.getServletContext().setAttribute("userList", userList);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }



}

package com.wp.utils;
import java.util.ArrayList;

import com.wp.domain.LoginInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

public class SessionUtil {
    /*
     * 根据sessionID查找用户
     */
    public static LoginInfo getUserBySessionId(ArrayList<LoginInfo> list,String id){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getSessionIdString().equals(id)){
                return list.get(i);
            }
        }
        return null;
    }
    /*
     * 根据sessionID删除用户
     */
    public static void remove(ArrayList<LoginInfo> list,String id) {
        for(int i=0;i<list.size();i++){
            if(list.get(i).getSessionIdString().equals(id)){
                list.remove(list.get(i));
            }
        }
    }

    /**
     * 通过WebApplicationContextUtils 得到Spring容器的实例。根据bean的名称返回bean的实例。
     * @param servletContext  ：ServletContext上下文。
     * @param beanName  :要取得的Spring容器中Bean的名称。
     * @return 返回Bean的实例。
     */
    public static Object getObjectFromApplication(ServletContext servletContext, String beanName){
        //通过WebApplicationContextUtils 得到Spring容器的实例。
        ApplicationContext application= WebApplicationContextUtils.getWebApplicationContext(servletContext);
        //返回Bean的实例。
        return application.getBean(beanName);
    }
}

package com.wp.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wp.utils.MD5加密.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.CharsetEncoder;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
    //使用protected是为了让子类也能得到model对象
    protected T model;
    protected PageBean pageBean = new PageBean();
    DetachedCriteria detachedCriteria = null;

    //在构造方法中动态获取实体类型，通过反射创建model对象
    public BaseAction() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class<T> classes = (Class<T>) actualTypeArguments[0];
        detachedCriteria = DetachedCriteria.forClass(classes);
        pageBean.setDetachedCriteria(detachedCriteria);
        try {
            model = classes.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void String2Json(PageBean o, String[] excludes) {
        //将page转化成json格式并传回
        //使用Jsonlib工具类
        //使用json-lib将PageBean对象转为json，通过输出流写回页面中
        //JSONObject---将单一对象转为json
        //JSONArray----将数组或者集合对象转为json
        JsonConfig jsonConfig = new JsonConfig();
        //指定哪些属性不需要转json
        //new String[]{"currentPage","detachedCriteria","pageSize"}
        jsonConfig.setExcludes(excludes);
        String jsonObject = JSONObject.fromObject(o,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void String2Json(List list, String[] excludes) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        String jsonObject = JSONArray.fromObject(list,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPage(int page) {
        pageBean.setCurrentPage(page);
    }

    public void setRows(int row) {
        pageBean.setPageSize(row);
    }


    @Override
    public T getModel() {
        return model;
    }
}

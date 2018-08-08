package com.wp.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
    //使用protected是为了让子类也能得到model对象
    protected T model;

    //在构造方法中动态获取实体类型，通过反射创建model对象
    public BaseAction() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class<T> classes = (Class<T>) actualTypeArguments[0];
        try {
            model = classes.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    @Override
    public T getModel() {
        return model;
    }
}

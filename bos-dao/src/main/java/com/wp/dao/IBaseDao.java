package com.wp.dao;

import com.wp.utils.MD5加密.PageBean;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    public void save(T t);
    public void update(T t);
    public void delete(T t);
    public List<T> getAll();
    public T getById(Serializable id);

    //一定要三个点
    void executeUpdate(String s, Object... objects);

    void getPage(PageBean pageBean);
}

package com.wp.dao;

import com.wp.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

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

    void saveOrUpdate(T t);
    //通过特定条件获得数据
    List<T> getByCriterial(DetachedCriteria detachedCriteria);
}

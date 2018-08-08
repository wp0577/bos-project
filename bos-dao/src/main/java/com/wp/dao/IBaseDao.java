package com.wp.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    public void save(T t);
    public void update(T t);
    public void delete(T t);
    public List<T> getAll();
    public T getById(Serializable id);
}

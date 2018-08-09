package com.wp.dao.imp;

import com.wp.dao.IBaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class IBaseDaoImp<T> extends HibernateDaoSupport implements IBaseDao<T>  {

    private Class entityClass;

    public IBaseDaoImp() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        entityClass = (Class<T>) actualTypeArguments[0];
    }

    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(T t) {
        getHibernateTemplate().save(t);
    }

    @Override
    public void update(T t) {
        getHibernateTemplate().update(t);
    }

    @Override
    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    @Override
    public List<T> getAll() {
        String hql = "from" + entityClass.getSimpleName();;
        return (List<T>) getHibernateTemplate().find(hql);
    }

    @Override
    public T getById(Serializable id) {
        return (T) getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public void executeUpdate(String sql, Object... objects) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.getNamedQuery(sql);
        int i = 0;
        for (Object o : objects) {
            query.setParameter(i++, o);
        }
        query.executeUpdate();
    }
}

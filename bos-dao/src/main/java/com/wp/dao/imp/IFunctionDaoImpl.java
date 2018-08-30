package com.wp.dao.imp;

import com.wp.dao.IFunctionDao;
import com.wp.domain.Function;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: bos-parent
 * @description:
 * @author: Pan wu
 * @create: 2018-08-29 15:58
 **/

@Repository
public class IFunctionDaoImpl extends IBaseDaoImp<Function> implements IFunctionDao{
    @Override
    public List<Function> getAll() {
        String hql = "from Function f where f.parentFunction is null";
        List<Function> list = (List<Function>) getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public List<Function> getFunctionsByUserId(String id) {
        //we should only select function result by using select statement
        //because it will return all includes roles, etc.
        String hql = "select distinct f from Function f left join f.roles r left join r.users u where u.id = ?";
        return (List<Function>) getHibernateTemplate().find(hql, id);
    }

    @Override
    public List<Function> getAllMenu() {
        String hql = "from Function f where f.generatemenu = '1'";
        List<Function> list = (List<Function>) getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public List<Function> getMenuByUserId(String id) {
        String hql = "select distinct f from Function f left join f.roles r left join r.users u where u.id = ? and f.generatemenu = '1'";
        return (List<Function>) getHibernateTemplate().find(hql, id);
    }
}

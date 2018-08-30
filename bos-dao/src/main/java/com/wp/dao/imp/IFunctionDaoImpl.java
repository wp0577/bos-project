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
}

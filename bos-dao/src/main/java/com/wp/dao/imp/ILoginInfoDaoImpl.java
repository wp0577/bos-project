package com.wp.dao.imp;

import com.wp.dao.ILoginInfoDao;
import com.wp.domain.LoginInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: bos-parent
 * @description:
 * @author: Pan wu
 * @create: 2018-09-04 01:23
 **/
@Repository
public class ILoginInfoDaoImpl extends IBaseDaoImp<LoginInfo> implements ILoginInfoDao{

    @Override
    public String getTotalCount() {
        String hql = "select sum(totalCount) from LoginInfo";
        List<Object> objects = (List<Object>) getHibernateTemplate().find(hql);
        if(objects!=null && objects.size()>0) return String.valueOf(objects.get(0));
        else return null;
    }
}

package com.wp.dao.imp;

import com.wp.dao.ISubareaDao;
import com.wp.domain.Subarea;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ISubareaDaoImpl extends IBaseDaoImp<Subarea> implements ISubareaDao {
    @Override
    public List<Object> showHighCharts() {
        String hql = "select r.province, count(*) from Subarea s left join s.region r group by r.province";
        return (List<Object>) getHibernateTemplate().find(hql);
    }
}

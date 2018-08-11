package com.wp.dao.imp;

import com.wp.dao.IBaseDao;
import com.wp.dao.IRegionDao;
import com.wp.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImp extends IBaseDaoImp<Region> implements IRegionDao {
    @Override
    public List<Region> getByQ(String q) {

        String hql = "from Region where city like ?  " +
                "or district like ? or postcode like ? or shortcode like ? or citycode like ?";
        return (List<Region>) getHibernateTemplate().find(hql,"%"+ q +"%", "%"+ q +"%", "%"+ q +"%", "%"+ q +"%", "%"+ q +"%");

    }
}

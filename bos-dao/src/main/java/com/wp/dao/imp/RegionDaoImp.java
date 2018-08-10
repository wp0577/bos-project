package com.wp.dao.imp;

import com.wp.dao.IBaseDao;
import com.wp.dao.IRegionDao;
import com.wp.domain.Region;
import org.springframework.stereotype.Repository;

@Repository
public class RegionDaoImp extends IBaseDaoImp<Region> implements IRegionDao {
}

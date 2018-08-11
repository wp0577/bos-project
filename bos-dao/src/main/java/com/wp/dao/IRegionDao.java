package com.wp.dao;

import com.wp.domain.Region;

import java.util.List;

public interface IRegionDao extends IBaseDao<Region>{
    List<Region> getByQ(String q);
}

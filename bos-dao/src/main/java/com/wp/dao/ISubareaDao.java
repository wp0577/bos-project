package com.wp.dao;

import com.wp.domain.Subarea;

import java.util.List;

public interface  ISubareaDao extends IBaseDao<Subarea>{

    List<Object> showHighCharts();
}

package com.wp.dao;

import com.wp.domain.Function;

import java.util.List;

/**
 * @program: bos-parent
 * @description:
 * @author: Pan wu
 * @create: 2018-08-29 15:58
 **/
public interface IFunctionDao extends IBaseDao<Function>{
    List<Function> getFunctionsByUserId(String id);
}

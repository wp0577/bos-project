package com.wp.service;

import com.wp.domain.Function;
import com.wp.domain.User;
import com.wp.utils.PageBean;

import java.util.List;

/**
 * @program: bos-parent
 * @description:
 * @author: Pan wu
 * @create: 2018-08-29 15:55
 **/
public interface IFunctionService{
    List<Function> getList();

    void save(Function model);

    void pageQuery(PageBean pageBean);

    List<Function> getMenu(User user);
}

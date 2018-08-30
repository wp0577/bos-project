package com.wp.service;

import com.wp.domain.Role;
import com.wp.utils.PageBean;

import java.util.List;

/**
 * @program: bos-parent
 * @description:
 * @author: Pan wu
 * @create: 2018-08-29 23:43
 **/
public interface IRoleService {
    void save(Role model, String functionIds);

    void pageQuery(PageBean pageBean);

    List<Role> getAll();
}

package com.wp.service;

import com.wp.domain.Staff;
import com.wp.utils.MD5加密.PageBean;

public interface IStaffService<Staff> {
    void save(Staff model);

    void getPage(PageBean pageBean);

    void deleteByID(String id);

    Staff getById(String id);
}

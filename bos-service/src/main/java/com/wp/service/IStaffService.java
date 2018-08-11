package com.wp.service;

import com.wp.domain.Staff;
import com.wp.utils.MD5加密.PageBean;

import java.util.List;

public interface IStaffService<Staff> {
    void save(Staff model);

    void getPage(PageBean pageBean);

    void deleteByID(String id);

    Staff getById(String id);

    List<Staff> getAll();

    List<Staff> getAllNotDelete();
}

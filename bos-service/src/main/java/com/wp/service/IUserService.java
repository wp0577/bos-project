package com.wp.service;

import com.wp.domain.User;
import com.wp.utils.PageBean;
import org.springframework.stereotype.Service;

public interface IUserService<User> {
    User login(User model);

    void editPassword(String id, String password);

    void add(com.wp.domain.User model, String[] roleIds);

    void pageQuery(PageBean pageBean);
}

package com.wp.service;

import com.wp.domain.User;
import org.springframework.stereotype.Service;

public interface IUserService<User> {
    User login(User model);

    void editPassword(String id, String password);
}

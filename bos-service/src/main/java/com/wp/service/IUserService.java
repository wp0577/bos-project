package com.wp.service;

import com.wp.domain.User;

public interface IUserService<User> {
    User login(User model);
}

package com.wp.dao;

import com.wp.domain.User;

public interface IUserDao extends IBaseDao<User>{

    public User getUserByNameAndPassword(String username, String password);

}

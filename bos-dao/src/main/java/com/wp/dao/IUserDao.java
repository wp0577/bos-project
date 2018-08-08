package com.wp.dao;

import com.wp.domain.User;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

public interface IUserDao extends IBaseDao<User>{

    public User getUserByNameAndPassword(String username, String password);

}

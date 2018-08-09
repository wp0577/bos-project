package com.wp.service.implement;

import com.wp.dao.IUserDao;
import com.wp.domain.User;
import com.wp.service.IUserService;
import com.wp.utils.MD5加密.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService<User>{

    @Autowired
    private IUserDao userDao;

    @Override
    public User login(User model) {
        String username = model.getUsername();
        String password = MD5Utils.md5(model.getPassword());
        User user = userDao.getUserByNameAndPassword(username, password);
        return user;
    }

    @Override
    public void editPassword(String id, String password) {
        password = MD5Utils.md5(password);
        userDao.executeUpdate("user.editPassword", password, id);
    }
}

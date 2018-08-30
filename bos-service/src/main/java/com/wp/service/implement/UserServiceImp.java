package com.wp.service.implement;

import com.wp.dao.IUserDao;
import com.wp.domain.Role;
import com.wp.domain.User;
import com.wp.service.IUserService;
import com.wp.utils.MD5加密.MD5Utils;
import com.wp.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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

    @Override
    public void add(User model, String[] roleIds) {
        model.setPassword(MD5Utils.md5(model.getPassword()));
        userDao.saveOrUpdate(model);
        if(roleIds!=null && roleIds.length>0) {
            for (String id : roleIds) {
                Role role = new Role();
                role.setId(id);
                model.getRoles().add(role);
            }
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        userDao.getPage(pageBean);
    }
}

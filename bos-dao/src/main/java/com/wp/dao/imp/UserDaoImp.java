package com.wp.dao.imp;

import com.wp.dao.IUserDao;
import com.wp.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp extends IBaseDaoImp<User> implements IUserDao {

    @Override
    public User getUserByNameAndPassword(String username, String password) {
        String hql = "from User u where u.username = ? and u.password = ?";
        List<User> list = (List<User>) getHibernateTemplate().find(hql, username, password);
        if(list!=null && list.size()>0) {
            return list.get(0);
        }
        return null;
    }
}

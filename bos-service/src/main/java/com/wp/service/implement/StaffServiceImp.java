package com.wp.service.implement;

import com.wp.dao.IStaffDao;
import com.wp.domain.Staff;
import com.wp.service.IStaffService;
import com.wp.utils.MD5加密.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffServiceImp implements IStaffService<Staff> {

    @Autowired
    private IStaffDao iStaffDao;

    @Override
    public void save(Staff model) {
        iStaffDao.save(model);
    }

    @Override
    public void deleteByID(String id) {
        String[] split = id.split(",");
        for (String ids:split) {
            iStaffDao.executeUpdate("staff.delete", ids);
        }
    }

    @Override
    public void getPage(PageBean pageBean) {
        iStaffDao.getPage(pageBean);
    }

    @Override
    public List<Staff> getAll() {
        return iStaffDao.getAll();
    }

    @Override
    public List<Staff> getAllNotDelete() {
        DetachedCriteria detachedCriteria  = DetachedCriteria.forClass(Staff.class);
        detachedCriteria.add(Restrictions.eq("deltag", "0"));
        List<Staff> list = iStaffDao.getByCriterial(detachedCriteria);
        return list;
    }

    @Override
    public Staff getById(String id) {
        return iStaffDao.getById(id);
    }
}

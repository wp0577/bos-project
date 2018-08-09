package com.wp.service.implement;

import com.wp.dao.IStaffDao;
import com.wp.domain.Staff;
import com.wp.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StaffServiceImp implements IStaffService<Staff> {

    @Autowired
    private IStaffDao iStaffDao;

    @Override
    public void save(Staff model) {
        iStaffDao.save(model);
    }
}

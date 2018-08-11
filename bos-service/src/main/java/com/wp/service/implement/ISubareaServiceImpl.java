package com.wp.service.implement;

import com.wp.dao.ISubareaDao;
import com.wp.domain.Subarea;
import com.wp.service.ISubareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ISubareaServiceImpl implements ISubareaService<Subarea> {

    @Autowired
    private ISubareaDao iSubareaDao;

    @Override
    public void save(Subarea model) {
        iSubareaDao.save(model);
    }
}

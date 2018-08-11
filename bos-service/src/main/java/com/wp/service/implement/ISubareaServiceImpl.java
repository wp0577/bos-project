package com.wp.service.implement;

import com.wp.dao.ISubareaDao;
import com.wp.domain.Subarea;
import com.wp.service.ISubareaService;
import com.wp.utils.MD5加密.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ISubareaServiceImpl implements ISubareaService<Subarea> {

    @Autowired
    private ISubareaDao iSubareaDao;

    @Override
    public void save(Subarea model) {
        iSubareaDao.save(model);
    }

    @Override
    public void getPage(PageBean pageBean) {
        iSubareaDao.getPage(pageBean);
    }

    @Override
    public List<Subarea> getAllNotAssoc() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
        detachedCriteria.add(Restrictions.isNull("decidedzone"));
        return iSubareaDao.getByCriterial(detachedCriteria);
    }

    @Override
    public List<Subarea> getAll() {
        return iSubareaDao.getAll();
    }
}

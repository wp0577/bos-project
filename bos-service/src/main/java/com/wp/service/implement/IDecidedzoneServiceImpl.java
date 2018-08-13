package com.wp.service.implement;

import com.wp.dao.IDecidedzoneDao;
import com.wp.dao.ISubareaDao;
import com.wp.domain.Decidedzone;
import com.wp.domain.Subarea;
import com.wp.service.IDecidedzoneService;
import com.wp.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IDecidedzoneServiceImpl implements IDecidedzoneService {

    @Autowired
    private IDecidedzoneDao iDecidedzoneDao;
    @Autowired
    private ISubareaDao iSubareaDao;

    @Override
    public void getPage(PageBean pageBean) {
        iDecidedzoneDao.getPage(pageBean);
    }

    @Override
    public List<Subarea> getSubareaByDecidedzone(String decidezone_id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
        //这里不能写decidedzone_id，在hibernate框架下，里面的查询应该用对象的角度出发
        detachedCriteria.add(Restrictions.eq("decidedzone.id",decidezone_id));
        List<Subarea> byCriterial = iSubareaDao.getByCriterial(detachedCriteria);
        return byCriterial;
    }

    @Override
    public void save(Decidedzone model, String[] subareasid) {
        //通过subarea去保存decidezone信息，而不是直接在model中保存subarea
        iDecidedzoneDao.save(model);
        for(String id : subareasid) {
            Subarea subarea = iSubareaDao.getById(id);
            subarea.setDecidedzone(model);
        }
    }
}

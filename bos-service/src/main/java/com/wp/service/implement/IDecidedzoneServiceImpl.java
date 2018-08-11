package com.wp.service.implement;

import com.wp.dao.IDecidedzoneDao;
import com.wp.dao.ISubareaDao;
import com.wp.dao.imp.ISubareaDaoImpl;
import com.wp.domain.Decidedzone;
import com.wp.domain.Subarea;
import com.wp.service.IDecidedzoneService;
import com.wp.utils.MD5加密.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void save(Decidedzone model, String[] subareasid) {
        //通过subarea去保存decidezone信息，而不是直接在model中保存subarea
        iDecidedzoneDao.save(model);
        for(String id : subareasid) {
            Subarea subarea = iSubareaDao.getById(id);
            subarea.setDecidedzone(model);
        }
    }
}

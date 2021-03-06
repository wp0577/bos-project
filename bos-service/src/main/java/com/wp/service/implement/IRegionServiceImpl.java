package com.wp.service.implement;

import com.wp.dao.IRegionDao;
import com.wp.domain.Region;
import com.wp.service.IRegionService;
import com.wp.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class IRegionServiceImpl implements IRegionService {
    @Autowired
    private IRegionDao iRegionDao;

    @Override
    public void uploadFile(ArrayList<Region> regionArray) {

        for(Region region : regionArray){
            iRegionDao.saveOrUpdate(region);
        }

    }

    @Override
    public List<Region> getAll() {
        return  iRegionDao.getAll();
    }

    @Override
    public List<Region> getByQ(String q) {
        return iRegionDao.getByQ(q);
    }

    @Override
    public void getPage(PageBean pageBean) {
        iRegionDao.getPage(pageBean);
    }
}

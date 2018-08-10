package com.wp.service.implement;

import com.wp.dao.IRegionDao;
import com.wp.domain.Region;
import com.wp.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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
}

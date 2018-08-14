package com.wp.service.implement;

import com.wp.dao.IWorkOrderDao;
import com.wp.domain.Workbill;
import com.wp.domain.Workordermanage;
import com.wp.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: bos-parent
 * @description:
 * @author: Pan wu
 * @create: 2018-08-13 22:56
 **/
@Service
@Transactional
public class WorkOrderServiceImpl implements WorkOrderService {

    @Autowired
    private IWorkOrderDao iWorkOrderDao;

    @Override
    public void save(Workordermanage model) {
        iWorkOrderDao.save(model);
    }
}

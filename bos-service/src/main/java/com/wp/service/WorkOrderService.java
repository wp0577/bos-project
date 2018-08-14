package com.wp.service;

import com.wp.domain.Workbill;
import com.wp.domain.Workordermanage;
import org.springframework.stereotype.Service;

/**
 * @program: bos-parent
 * @description:
 * @author: Pan wu
 * @create: 2018-08-13 22:56
 **/
public interface WorkOrderService {
    void save(Workordermanage model);
}

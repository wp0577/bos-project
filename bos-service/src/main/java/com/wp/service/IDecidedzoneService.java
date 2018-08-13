package com.wp.service;

import com.wp.domain.Decidedzone;
import com.wp.utils.PageBean;

public interface IDecidedzoneService {

    void save(Decidedzone model, String[] subareasid);

    void getPage(PageBean pageBean);
}

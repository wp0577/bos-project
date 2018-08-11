package com.wp.service;

import com.wp.domain.Decidedzone;
import com.wp.utils.MD5加密.PageBean;

public interface IDecidedzoneService {

    void save(Decidedzone model, String[] subareasid);

    void getPage(PageBean pageBean);
}

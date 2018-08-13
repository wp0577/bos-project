package com.wp.service;

import com.wp.domain.Decidedzone;
import com.wp.domain.Subarea;
import com.wp.utils.PageBean;

import java.util.List;

public interface IDecidedzoneService {

    void save(Decidedzone model, String[] subareasid);

    void getPage(PageBean pageBean);

    List<Subarea> getSubareaByDecidedzone(String decidezone_id);
}

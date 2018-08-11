package com.wp.web.action;

import com.wp.domain.Region;
import com.wp.domain.Subarea;
import com.wp.service.ISubareaService;
import com.wp.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.swing.*;


@Controller
@Scope("prototype")
public class SubAreaAction extends BaseAction<Subarea> {

    @Autowired
    private ISubareaService iSubareaService;

    public String list() {
        //不用先判断model是否为空
        //只需要判断region和keyword就行
        Region region = model.getRegion();
        String addresskey = model.getAddresskey();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        if(region != null) {
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            detachedCriteria.createAlias("region", "r");
            if(StringUtils.isNotBlank(province)) {
                detachedCriteria.add(Restrictions.like("r.province", "%"+province+"%"));
            }
            if(StringUtils.isNotBlank(city)) {
                detachedCriteria.add(Restrictions.like("r.city", "%"+city+"%"));
            }
            if(StringUtils.isNotBlank(district)) {
                detachedCriteria.add(Restrictions.like("r.district", "%"+district+"%"));
            }
        }
        if(StringUtils.isNotBlank(addresskey)) {
            detachedCriteria.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
        }

        iSubareaService.getPage(pageBean);
        this.String2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
        return NONE;
    }

    public String save() {
        iSubareaService.save(model);
        return "list";
    }

}

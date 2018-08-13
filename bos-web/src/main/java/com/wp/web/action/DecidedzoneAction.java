package com.wp.web.action;

import com.wp.domain.Decidedzone;
import com.wp.domain.Subarea;
import com.wp.service.IDecidedzoneService;
import com.wp.utils.crm.Customer;
import com.wp.utils.crm.ICustomerService;
import com.wp.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

    @Autowired
    private IDecidedzoneService iDecidedzoneService;

    //该属性用来接受页面传来的记录subarea.id的数据，因为和subarea类无关，所以只用String[]即可。
    private String[] subareasid;

    private List<Integer> customerIds;

    @Autowired
    private ICustomerService iCustomerService;

    /*
    * access to cxf service and get customer data
    * */
    public String getAllNotAsso() {
        List<Customer> allNotAssociation = iCustomerService.getAllNotAssociation();
        this.String2Json(allNotAssociation,null);
        return NONE;
    }
    public String getAllHasAsso() {
        List<Customer> allHasAssociation = iCustomerService.getAllHasAssociation(model.getId());
        this.String2Json(allHasAssociation,null);
        return NONE;
    }

    public String saveCustomer() {
        iCustomerService.saveCustomerAndDecidedZone(model.getId(),customerIds);
        return "list";
    }

    /*id: 123
    name: 123
    region.id: 402846ef652574820165257638180004
    id: 8a8080b9652750c7016527523d760000
    id: 8a8080b965276126016527620d0f0000
    以上为前端发过来的参数我们需要将后两个id改成Subarea.id
    region.id也要改成staff.id
*/

    public String save() {
        System.out.println(model);
        System.out.println(subareasid);
        iDecidedzoneService.save(model, subareasid);
        return "list";
    }
    /*
    * 返回json对象包含decidedzeon的信息
     * */

    public String list() {
        /*//不用先判断model是否为空
        //只需要判断region和keyword就行
        Decidedzone decidedzone = model.getRegion();
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
        }*/

        iDecidedzoneService.getPage(pageBean);
        this.String2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","subareas", "decidedzones"});
        return NONE;
    }

    public void setSubareasid(String[] subareasid) {
        this.subareasid = subareasid;
    }

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }
}

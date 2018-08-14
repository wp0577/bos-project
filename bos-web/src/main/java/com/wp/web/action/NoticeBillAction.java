package com.wp.web.action;

import com.wp.domain.Noticebill;
import com.wp.service.IDecidedzoneService;
import com.wp.service.INoticeBillService;
import com.wp.utils.crm.Customer;
import com.wp.utils.crm.ICustomerService;
import com.wp.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import static com.wp.domain.Noticebill.*;

/**
 * @program: bos-parent
 * @description: handle all noticebill page request
 * @author: Pan wu
 * @create: 2018-08-13 13:42
 **/

@Controller
@Scope("prototype")
public class NoticeBillAction extends BaseAction<Noticebill> {

    @Autowired
    private INoticeBillService iNoticeBillService;
    @Autowired
    private ICustomerService iCustomerService;

    public String save() {
        String address = model.getPickaddress();
        //对工单进行保存
        iNoticeBillService.save(model);

        //应该将业务都放在service中执行
        /*//通过地址选择到定区id
        String decidedzoneId = iCustomerService.getDecidedIdbyAddress(address);
        if(decidedzoneId == null) {
            model.setOrdertype(ORDERTYPE_MAN);
        }
        else {
            iDecidedzoneService.getStaffByDecidedzoneId(decidedzoneId);
        }*/
        return "list";
    }



    public String listCustomerByTel() {
        Customer customerByTelephone = iCustomerService.getCustomerByTelephone(model.getTelephone());
        this.String2Json(customerByTelephone, new String[]{""});
        return NONE;
    }


}

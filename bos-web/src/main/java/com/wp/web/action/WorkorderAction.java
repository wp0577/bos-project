package com.wp.web.action;

import com.wp.domain.Workbill;
import com.wp.domain.Workordermanage;
import com.wp.service.WorkOrderService;
import com.wp.web.action.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * @program: bos-parent
 * @description: control all the request about workorder
 * @author: Pan wu
 * @create: 2018-08-13 22:53
 **/
@Controller
@Scope("prototype")
public class WorkorderAction extends BaseAction<Workordermanage>{

    @Autowired
    private WorkOrderService workOrderService;

    public String save() throws IOException {
        String f = "1";
        try {
            workOrderService.save(model);
        }catch(Exception e){
            e.printStackTrace();
            f = "0";
        }
        ServletActionContext.getResponse().getWriter().write(f);
        return NONE;
    }


}

package com.wp.web.action;

import com.wp.domain.Staff;
import com.wp.service.IStaffService;
import com.wp.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

    @Autowired
    private IStaffService iStaffService;

    public String save() {
        iStaffService.save(model);
        return "list";
    }

}

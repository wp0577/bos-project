package com.wp.web.action;

import com.wp.domain.Subarea;
import com.wp.service.ISubareaService;
import com.wp.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller
@Scope("prototype")
public class SubAreaAction extends BaseAction<Subarea> {

    @Autowired
    private ISubareaService iSubareaService;

    public String save() {
        iSubareaService.save(model);
        return "list";
    }

}

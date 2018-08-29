package com.wp.web.action;

import com.wp.domain.Function;
import com.wp.service.IFunctionService;
import com.wp.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @program: bos-parent
 * @description: permission control
 * @author: Pan wu
 * @create: 2018-08-29 15:51
 **/

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

    @Autowired
    private IFunctionService iFunctionService;

    public String listAjax() {

        List<Function> list = iFunctionService.getList();
        String2Json(list, new String[]{"parentFunction", "roles", "children"});
        return NONE;

    }

    public String save() {
        iFunctionService.save(model);
        return "list";
    }

}

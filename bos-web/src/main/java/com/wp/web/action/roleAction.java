package com.wp.web.action;

import com.wp.domain.Role;
import com.wp.service.IRoleService;
import com.wp.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @program: bos-parent
 * @description: role function
 * @author: Pan wu
 * @create: 2018-08-29 23:41
 **/

@Controller
@Scope("prototype")
public class roleAction extends BaseAction<Role> {

    @Autowired
    private IRoleService iRoleService;

    private String functionIds;

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    public String save() {
        iRoleService.save(model, functionIds);
        return "list";
    }

    public String pageQuery() {
        iRoleService.pageQuery(pageBean);
        String2Json(pageBean, new String[]{"functions", "users"});
        return NONE;
    }

    public String getAll() {
        List<Role> list = iRoleService.getAll();
        String2Json(list, new String[]{"functions","users"});
        return NONE;
    }

    // TODO: 8/30/18
    public String edit() {
        return NONE;
    }


}

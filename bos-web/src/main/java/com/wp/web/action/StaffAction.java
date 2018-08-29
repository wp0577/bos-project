package com.wp.web.action;

import com.wp.domain.Staff;
import com.wp.service.IStaffService;
import com.wp.web.action.base.BaseAction;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

    @Autowired
    private IStaffService iStaffService;

    //不能用id，因为staff对象里已经定义了id
    private String ids;

    public String edit() {
        //不能直接通过model对象去更新，因为edit表单传来的字段并不一定覆盖了model所有的字段
        //这样更新的时候数据库原本的model其他的字段会被设置为null
        //先通过id查到staff对象
        Staff staff = (Staff) iStaffService.getById(model.getId());
        //将model对象里赋值的属性赋值给staff
        staff.setTelephone(model.getTelephone());
        staff.setHaspda(model.getHaspda());
        staff.setName(model.getName());
        staff.setStation(model.getStation());
        staff.setHaspda(model.getHaspda());

        iStaffService.save(staff);

        return "list";
    }

    @RequiresPermissions("staff-delete")
    public String deleteById() {
        iStaffService.deleteByID(ids);
        return "list";
    }

    public String save() {
        iStaffService.save(model);
        return "list";
    }

    public String list() throws IOException {

        iStaffService.getPage(pageBean);
        //将page转化成json格式并传回
        //使用Jsonlib工具类
        //使用json-lib将PageBean对象转为json，通过输出流写回页面中
        //JSONObject---将单一对象转为json
        //JSONArray----将数组或者集合对象转为json
        //指定哪些属性不需要转json
        this.String2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","decidedzones"});
        return NONE;
    }

    /*
    * 返回所有未被删除的职员列表
    */
    public String listAjax() {
        List<Staff> list = iStaffService.getAllNotDelete();
        this.String2Json(list, new String[]{"decidedzones"});
        return NONE;
    }


    public void setIds(String ids) {
        this.ids = ids;
    }

}

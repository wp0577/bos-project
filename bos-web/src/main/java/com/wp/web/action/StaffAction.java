package com.wp.web.action;

import com.wp.domain.Staff;
import com.wp.service.IStaffService;
import com.wp.utils.MD5加密.PageBean;
import com.wp.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

    @Autowired
    private IStaffService iStaffService;

    private int page;

    private int rows;

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

    public String deleteById() {
        iStaffService.deleteByID(ids);
        return "list";
    }

    public String save() {
        iStaffService.save(model);
        return "list";
    }

    public String list() throws IOException {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        pageBean.setDetachedCriteria(DetachedCriteria.forClass(Staff.class));
        iStaffService.getPage(pageBean);
        System.out.println(pageBean.getRows().get(0));
        //将page转化成json格式并传回
        //使用Jsonlib工具类
        //使用json-lib将PageBean对象转为json，通过输出流写回页面中
        //JSONObject---将单一对象转为json
        //JSONArray----将数组或者集合对象转为json
        JsonConfig jsonConfig = new JsonConfig();
        //指定哪些属性不需要转json
        jsonConfig.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize"});
        String jsonObject = JSONObject.fromObject(pageBean,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json");
        ServletActionContext.getResponse().getWriter().write(jsonObject);
        return NONE;
    }


    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

}

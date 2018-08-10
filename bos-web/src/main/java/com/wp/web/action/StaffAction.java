package com.wp.web.action;

import com.wp.domain.Staff;
import com.wp.service.IStaffService;
import com.wp.utils.MD5加密.PageBean;
import com.wp.web.action.base.BaseAction;
import net.sf.json.JSONObject;
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
        String jsonObject = JSONObject.fromObject(pageBean).toString();
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

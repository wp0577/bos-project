package com.wp.web.action;

import com.wp.domain.Region;
import com.wp.domain.Subarea;
import com.wp.service.ISubareaService;
import com.wp.utils.FileUtils;
import com.wp.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@Scope("prototype")
public class SubAreaAction extends BaseAction<Subarea> {

    @Autowired
    private ISubareaService iSubareaService;

    /*返回所以未被定区关联过的分区列表*/
    public String listAjax() {
        List<Subarea> list = iSubareaService.getAllNotAssoc();
        this.String2Json(list, new String[]{"decidedzone","region"});
        return NONE;
    }

    public String exportFile() throws IOException {
        //得到所有分区数据
        List<Subarea> list = new ArrayList<>();
        list = iSubareaService.getAll();

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet subarea_info = hssfWorkbook.createSheet("Subarea Info");
        HSSFRow row = subarea_info.createRow(0);
        row.createCell(0).setCellValue("分区编号");
        row.createCell(1).setCellValue("开始编号");
        row.createCell(2).setCellValue("结束编号");
        row.createCell(3).setCellValue("位置信息");
        row.createCell(4).setCellValue("省市区");

        for (Subarea subarea : list) {
            HSSFRow dataRow = subarea_info.createRow(subarea_info.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(subarea.getId());
            dataRow.createCell(1).setCellValue(subarea.getStartnum());
            dataRow.createCell(2).setCellValue(subarea.getEndnum());
            dataRow.createCell(3).setCellValue(subarea.getPosition());
            dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
        }

        //第三步：使用输出流进行文件下载（一个流、两个头）

        String filename = "分区数据.xls";
        String contentType = ServletActionContext.getServletContext().getMimeType(filename);
        ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
        ServletActionContext.getResponse().setContentType(contentType);


        //获取客户端浏览器类型
        String agent = ServletActionContext.getRequest().getHeader("User-Agent");
        filename = FileUtils.encodeDownloadFilename(filename, agent);
        ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
        hssfWorkbook.write(out);

        return NONE;
    }

    public String list() {
        //不用先判断model是否为空
        //只需要判断region和keyword就行
        Region region = model.getRegion();
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
        }

        iSubareaService.getPage(pageBean);
        this.String2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","decidedzone","subareas"});
        return NONE;
    }

    public String save() {
        iSubareaService.save(model);
        return "list";
    }

}

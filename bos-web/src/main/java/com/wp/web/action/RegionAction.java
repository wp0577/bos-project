package com.wp.web.action;

import com.wp.domain.Region;
import com.wp.service.IRegionService;
import com.wp.utils.MD5加密.PageBean;
import com.wp.utils.MD5加密.PinYin4jUtils;
import com.wp.web.action.base.BaseAction;
import org.apache.commons.collections.functors.NonePredicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

    @Autowired
    private IRegionService iRegionService;

    private File myFile;

    private String q;

    public String list() {
        iRegionService.getPage(pageBean);
        this.String2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
        return NONE;
    }

    public String listAjax() {
        List<Region> regions = null;
        if(StringUtils.isNotBlank(q)) {
            regions = iRegionService.getByQ(q);
        }
        else {
            regions = iRegionService.getAll();
        }
        this.String2Json(regions, new String[]{"subareas"});
        return NONE;
    }

    public String upload() throws Exception {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(myFile));
        HSSFSheet sheet1 = hssfWorkbook.getSheet("Sheet1");
        ArrayList<Region> regionArray = new ArrayList();
        for(Row row : sheet1){
            if(row.getRowNum() == 0) continue;
            Region region = new Region();
            region.setId(row.getCell(0).getStringCellValue());
            region.setProvince(row.getCell(1).getStringCellValue());
            region.setCity(row.getCell(2).getStringCellValue());
            region.setDistrict(row.getCell(3).getStringCellValue());
            region.setPostcode(row.getCell(4).getStringCellValue());
            //通过pingyin4j创建shrotcode和citycode
            String province = region.getProvince().substring(0, region.getProvince().length()-1);
            String city = region.getCity().substring(0, region.getCity().length()-1);
            String district = region.getDistrict().substring(0, region.getDistrict().length()-1);
            String[] shortcodeTemp = PinYin4jUtils.getHeadByString(province+city+district);
            String shortcode = StringUtils.join(shortcodeTemp);
            String citycode = PinYin4jUtils.hanziToPinyin(city,"");
            region.setShortcode(shortcode);
            region.setCitycode(citycode);
            regionArray.add(region);
        }
        iRegionService.uploadFile(regionArray);
        return NONE;
    }


    public void setiRegionService(IRegionService iRegionService) {
        this.iRegionService = iRegionService;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

}

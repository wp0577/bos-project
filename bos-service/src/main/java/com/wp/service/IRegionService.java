package com.wp.service;

import com.wp.domain.Region;
import com.wp.utils.MD5加密.PageBean;

import java.util.ArrayList;
import java.util.List;

public interface IRegionService {
    void uploadFile(ArrayList<Region> regionArray);

    void getPage(PageBean pageBean);

    List<Region> getAll();

    List<Region> getByQ(String q);
}

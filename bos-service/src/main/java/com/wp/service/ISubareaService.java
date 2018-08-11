package com.wp.service;

import com.wp.domain.Subarea;
import com.wp.utils.MD5加密.PageBean;

import java.util.List;

public interface ISubareaService<Subarea> {
    void save(Subarea model);

    void getPage(PageBean pageBean);

    List<Subarea> getAll();

    List<Subarea> getAllNotAssoc();
}

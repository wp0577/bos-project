package com.wp.service;

import com.wp.utils.PageBean;

import java.util.List;

public interface ISubareaService<Subarea> {
    void save(Subarea model);

    void getPage(PageBean pageBean);

    List<Subarea> getAll();

    List<Subarea> getAllNotAssoc();
}

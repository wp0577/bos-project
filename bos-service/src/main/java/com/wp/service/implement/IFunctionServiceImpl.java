package com.wp.service.implement;

import com.wp.dao.IFunctionDao;
import com.wp.domain.Function;
import com.wp.service.IFunctionService;
import com.wp.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: bos-parent
 * @description:
 * @author: Pan wu
 * @create: 2018-08-29 15:56
 **/

@Service
@Transactional
public class IFunctionServiceImpl implements IFunctionService {

    @Autowired
    private IFunctionDao iFunctionDao;

    @Override
    public List<Function> getList() {
        return iFunctionDao.getAll();
    }

    @Override
    public void save(Function model) {
        //此处会报错因为当我们不输入parentFunction数据时，parentFunction.id不是为空而是空串，需要将其变为空才能insert
        //而且要考虑到直接在url传参时，parentFunction为空的情况
        //不能用==来判断，因为这是判断reference address
        if(model.getParentFunction() !=null && model.getParentFunction().getId().equals("")) {
            model.setParentFunction(null);
        }
        iFunctionDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        iFunctionDao.getPage(pageBean);
    }
}

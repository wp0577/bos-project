package com.wp.service.implement;

import com.wp.dao.IRoleDao;
import com.wp.domain.Function;
import com.wp.domain.Role;
import com.wp.service.IRoleService;
import com.wp.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: bos-parent
 * @description:
 * @author: Pan wu
 * @create: 2018-08-29 23:44
 **/

@Service
@Transactional
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao iRoleDao;

    @Override
    public void save(Role model, String functionIds) {
        iRoleDao.save(model);
        if(StringUtils.isNotBlank(functionIds)) {
            String[] split = functionIds.split(",");
            for (String splits : split) {
                Function function = new Function();
                function.setId(splits);
                model.getFunctions().add(function);
            }
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        iRoleDao.getPage(pageBean);
    }

    @Override
    public List<Role> getAll() {
        return iRoleDao.getAll();
    }
}

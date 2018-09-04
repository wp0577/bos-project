package com.wp.service.implement;

import com.wp.dao.ILoginInfoDao;
import com.wp.domain.LoginInfo;
import com.wp.service.ILoginInfoService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ILoginInfoServiceImp implements ILoginInfoService {

    @Autowired
    private ILoginInfoDao iLoginInfoDao;

    @Override
    public void save(LoginInfo u) {
        iLoginInfoDao.saveOrUpdate(u);
    }

    @Override
    public LoginInfo getLoginByIp(String ip) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LoginInfo.class);
        detachedCriteria.add(Restrictions.eq("ipString",ip));
        List<LoginInfo> byCriterial = iLoginInfoDao.getByCriterial(detachedCriteria);
        if (byCriterial.size()==0) return null;
        return byCriterial.get(0);
    }
}

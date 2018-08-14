package com.wp.service.implement;

import com.wp.dao.IDecidedzoneDao;
import com.wp.dao.INoticeBillDao;
import com.wp.dao.IWorkBillDao;
import com.wp.domain.*;
import com.wp.service.IDecidedzoneService;
import com.wp.service.INoticeBillService;
import com.wp.utils.BosUtil;
import com.wp.utils.crm.ICustomerService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: bos-parent
 * @description:
 * @author: Pan wu
 * @create: 2018-08-13 14:25
 **/

@Service
@Transactional
public class INoticeBillServiceImpl implements INoticeBillService {

    @Autowired
    private INoticeBillDao iNoticeBillDao;
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private IDecidedzoneDao iDecidedzoneDao;
    @Autowired
    private IWorkBillDao workbillDao;

    @Override
    public void save(Noticebill model) {
        iNoticeBillDao.save(model);
        User user = BosUtil.getUserFromSession();
        model.setUser(user);
        String address = model.getPickaddress();
        String decidedzoneId = iCustomerService.getDecidedIdbyAddress(address);
        if(decidedzoneId == null) {
            model.setOrdertype(Noticebill.ORDERTYPE_MAN);

        }
        else {
            //通过decidedZoneId直接可以调DecidedZoneDao中的getById方法
            Decidedzone byId = iDecidedzoneDao.getById(decidedzoneId);
            Staff staff = byId.getStaff();
            model.setStaff(staff);
            model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
            //为取派员产生一个工单
            Workbill workbill = new Workbill();
            workbill.setAttachbilltimes(0);//追单次数
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//创建时间，当前系统时间
            workbill.setNoticebill(model);//工单关联页面通知单
            workbill.setPickstate(Workbill.PICKSTATE_NO);//取件状态
            workbill.setRemark(model.getRemark());//备注信息
            workbill.setStaff(staff);//工单关联取派员
            workbill.setType(Workbill.TYPE_1);//工单类型
            workbillDao.save(workbill);

            //调用短信平台，发送短信
            //使用邮件
            // TODO: 8/13/18


        }
    }
}

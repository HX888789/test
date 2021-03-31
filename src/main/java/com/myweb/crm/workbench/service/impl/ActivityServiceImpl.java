package com.myweb.crm.workbench.service.impl;

import com.myweb.crm.utils.SqlSessionUtil;
import com.myweb.crm.workbench.dao.ActivityDao;
import com.myweb.crm.workbench.domain.Activity;
import com.myweb.crm.workbench.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao= SqlSessionUtil.getsession().getMapper(ActivityDao.class);

    @Override
    public boolean save(Activity a) {
        boolean flag=true;
        int count=activityDao.save(a);
        System.out.println("==============================="+count);
        if(count!=1){
            flag=false;
        }
        return flag;
    }
}

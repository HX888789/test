package com.myweb.crm.workbench.service.impl;

import com.myweb.crm.utils.SqlSessionUtil;
import com.myweb.crm.vo.PaginationVo;
import com.myweb.crm.workbench.dao.ActivityDao;
import com.myweb.crm.workbench.domain.Activity;
import com.myweb.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

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

    @Override
    public PaginationVo<Activity> pageList(Map<String, Object> map) {
//        取得total
        int total=activityDao.getTotalByCondition(map);
        System.out.println("total======================="+total);
//        取得datalist
        List<Activity> dataList=activityDao.getActivityListByCondition(map);
        for (Activity s:dataList)
        System.out.println("dataList================================"+s);
        PaginationVo<Activity> vo =new PaginationVo<>();
        vo.setTotal(total);
        vo.setDataList(dataList);
        return vo;
    }
}

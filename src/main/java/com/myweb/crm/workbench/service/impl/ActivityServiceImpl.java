package com.myweb.crm.workbench.service.impl;

import com.myweb.crm.settings.dao.UserDao;
import com.myweb.crm.settings.domain.User;
import com.myweb.crm.utils.SqlSessionUtil;
import com.myweb.crm.vo.PaginationVo;
import com.myweb.crm.workbench.dao.ActivityDao;
import com.myweb.crm.workbench.dao.ActivityRemarkDao;
import com.myweb.crm.workbench.domain.Activity;
import com.myweb.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao= SqlSessionUtil.getsession().getMapper(ActivityDao.class);
    private ActivityRemarkDao activityRemarkDao=SqlSessionUtil.getsession().getMapper(ActivityRemarkDao.class);
    private UserDao userDao=SqlSessionUtil.getsession().getMapper(UserDao.class);

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

    @Override
    public boolean delete(String[] ids) {
       boolean flag=true;
//       先查询出需要删除的备注的数量
        int count1=activityRemarkDao.getCountByAids(ids);
//        删除备注 返回收到影响的条数（实际删除的数量）
        int count2=activityRemarkDao.deleteByAids(ids);
        if(count1!=count2){
            flag=false;
        }
//        删除市场活动
        int nums=activityDao.delete(ids);
        if(nums!= ids.length){
            flag=false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getUserListAndActivity(String id) {
//        取ulist
        List<User> uList=userDao.getUserList();
//        取a
        Activity a=activityDao.getById(id);
//        打包到map
        Map<String ,Object> map=new HashMap<>();
        map.put("uList",uList);
        map.put("a",a);
        return map;
    }

    @Override
    public boolean update(Activity a) {
        boolean flag=true;
        int count=activityDao.update(a);
        System.out.println("==============================="+count);
        if(count!=1){
            flag=false;
        }
        return flag;
    }
}

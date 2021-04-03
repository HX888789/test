package com.myweb.crm.workbench.dao;

import com.myweb.crm.workbench.domain.Activity;
import com.myweb.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
     int save(Activity a);


    int getTotalByCondition(Map<String, Object> map);

    List<Activity> getActivityListByCondition(Map<String, Object> map);

    int delete(String[] ids);

    Activity getById(String id);

    int update(Activity a);

    Activity detail(String id);
}

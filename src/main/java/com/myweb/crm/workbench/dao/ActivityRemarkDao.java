package com.myweb.crm.workbench.dao;

import com.myweb.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkDao {
     int saveRemark(ActivityRemark ar);


    int getCountByAids(String[] ids);

    int deleteByAids(String[] ids);

    List<ActivityRemark> getRemarkListByAid(String id);

    int deleteRemark(String id);

    int undateRemark(ActivityRemark ar);
}

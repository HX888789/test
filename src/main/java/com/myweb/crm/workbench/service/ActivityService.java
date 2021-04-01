package com.myweb.crm.workbench.service;

import com.myweb.crm.vo.PaginationVo;
import com.myweb.crm.workbench.domain.Activity;

import java.util.Map;

public interface ActivityService {
    boolean save(Activity a);

    PaginationVo<Activity> pageList(Map<String, Object> map);
}

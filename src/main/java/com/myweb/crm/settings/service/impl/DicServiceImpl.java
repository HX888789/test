package com.myweb.crm.settings.service.impl;

import com.myweb.crm.settings.dao.DicTypeDao;
import com.myweb.crm.settings.dao.DicValueDao;
import com.myweb.crm.settings.domain.DicValue;
import com.myweb.crm.settings.service.DicService;
import com.myweb.crm.utils.SqlSessionUtil;

public class DicServiceImpl implements DicService {
    private DicTypeDao dicTypeDao= SqlSessionUtil.getsession().getMapper(DicTypeDao.class);
    private DicValueDao valueDao= SqlSessionUtil.getsession().getMapper(DicValueDao.class);
}

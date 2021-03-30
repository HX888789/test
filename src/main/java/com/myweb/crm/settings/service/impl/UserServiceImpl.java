package com.myweb.crm.settings.service.impl;

import com.myweb.crm.settings.dao.UserDao;
import com.myweb.crm.settings.service.UserService;
import com.myweb.crm.utils.SqlSessionUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao= SqlSessionUtil.getsession().getMapper(UserDao.class);
}

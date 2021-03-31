package com.myweb.crm.settings.dao;

import com.myweb.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {
    User login(Map<String, Object> map);
}

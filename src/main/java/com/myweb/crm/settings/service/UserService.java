package com.myweb.crm.settings.service;

import com.myweb.crm.exception.LoginException;
import com.myweb.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}

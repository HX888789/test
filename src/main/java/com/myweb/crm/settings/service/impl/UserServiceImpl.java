package com.myweb.crm.settings.service.impl;

import com.myweb.crm.exception.LoginException;
import com.myweb.crm.settings.dao.UserDao;
import com.myweb.crm.settings.domain.User;
import com.myweb.crm.settings.service.UserService;
import com.myweb.crm.utils.DateTimeUtil;
import com.myweb.crm.utils.MD5Util;
import com.myweb.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao= SqlSessionUtil.getsession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
         loginPwd= MD5Util.getMD5(loginPwd);
        Map<String ,Object> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user=userDao.login(map);

        if(user==null){
            throw new LoginException("账号密码错误");
        }
//        如果程序往下执行，说明账号密码正确，需要继续判断其他三项信息
        String expireTime=user.getExpireTime();
        String currentTime= DateTimeUtil.getSysTime();
        if(expireTime.compareTo(currentTime)<0){
            throw new LoginException("账号已失效");
        }
//        锁定状态判断
        if("0".equals(user.getLockState())){
            throw new LoginException("账号已锁定");
        }
//        判断ip地址是否符合 允许ip是否包含登录ip
        if(!user.getAllowIps().contains(ip)){
            throw new LoginException("IP地址受限");
        }
        return user;
    }

    @Override
    public List<User> getUserList() {
        List<User> uList=userDao.getUserList();
        return uList;
    }
}

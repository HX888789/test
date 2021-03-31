package com.myweb.crm.workbench.web.controller;


import com.myweb.crm.settings.domain.User;
import com.myweb.crm.settings.service.UserService;
import com.myweb.crm.settings.service.impl.UserServiceImpl;
import com.myweb.crm.utils.DateTimeUtil;
import com.myweb.crm.utils.PrintJson;
import com.myweb.crm.utils.ServiceFactory;
import com.myweb.crm.utils.UUIDUtil;
import com.myweb.crm.workbench.domain.Activity;
import com.myweb.crm.workbench.service.ActivityService;
import com.myweb.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到市场活动控制器");
        String path=request.getServletPath();
        if("/workbench/activity/getUserList.do".equals(path)){

            getUserList(request,response);

        }else if("/workbench/activity/save.do".equals(path)){
            save(request,response);
        }
    }

    private void getUserList(HttpServletRequest request,HttpServletResponse response){
        System.out.println("取得用户信息列表");
        UserService us= (UserService) ServiceFactory.getservice(new UserServiceImpl());
        List<User> uList=us.getUserList();
        PrintJson.printJsonObj(response,uList);
    }
    private void save(HttpServletRequest request,HttpServletResponse response){
        System.out.println("执行市场活动的添加操作");
        ActivityService as= (ActivityService) ServiceFactory.getservice(new ActivityServiceImpl());
        String id = UUIDUtil.getUUID();
        String owner =request.getParameter("owner");
        String name =request.getParameter("name");
        String startDate =request.getParameter("startDate");
        String endDate  =request.getParameter("endDate");
        String cost =request.getParameter("cost");
        String description =request.getParameter("description");
//        创建时间是当前系统时间
        String createTime = DateTimeUtil.getSysTime();
//        创建人是当前登录的用户
        String createBy = ((User) request.getSession().getAttribute("user")).getName();
        Activity a=new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);
        boolean flag=as.save(a);
        PrintJson.printJsonFlag(response,flag);
    }
}
package com.myweb.crm.workbench.web.controller;


import com.myweb.crm.settings.domain.User;
import com.myweb.crm.settings.service.UserService;
import com.myweb.crm.settings.service.impl.UserServiceImpl;
import com.myweb.crm.utils.DateTimeUtil;
import com.myweb.crm.utils.PrintJson;
import com.myweb.crm.utils.ServiceFactory;
import com.myweb.crm.utils.UUIDUtil;
import com.myweb.crm.vo.PaginationVo;
import com.myweb.crm.workbench.domain.Activity;
import com.myweb.crm.workbench.domain.ActivityRemark;
import com.myweb.crm.workbench.service.ActivityService;
import com.myweb.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        else if ("/workbench/activity/pageList.do".equals(path)){
            pageList(request,response);

        }else if ("/workbench/activity/delete.do".equals(path)){
            delete(request, response);
        }else if("/workbench/activity/getUserListAndActivity.do".equals(path)){
            get(request, response);
        }else if("/workbench/activity/update.do".equals(path)){
            update(request,response);
        }else if("/workbench/activity/detail.do".equals(path)){
            detail(request,response);
        }else if("/workbench/activity/getRemarkListByAid.do".equals(path)){
            getRemarkListByAid(request,response);
        }else if("/workbench/activity/deleteRemark.do".equals(path)){
            deleteRemark(request,response);
        }else if("/workbench/activity/saveRemark.do".equals(path)){
            saveRemark(request,response);
        }else if("/workbench/activity/undateRemark.do".equals(path)){
            undateRemark(request,response);
        }
    }

    private void undateRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行修改备注的操作");
        String id=request.getParameter("id");
        String noteContent=request.getParameter("noteContent");
        String editTime=DateTimeUtil.getSysTime();
        String editBy=((User)request.getSession().getAttribute("user")).getName();
        String editflag="1";
        ActivityRemark ar=new ActivityRemark();
        ar.setId(id);
        ar.setNoteContent(noteContent);
        ar.setEditBy(editBy);
        ar.setEditTime(editTime);
        ar.setEditFlag(editflag);
        ActivityService as= (ActivityService) ServiceFactory.getservice(new ActivityServiceImpl());
        boolean flag=as.undateRemark(ar);
        Map<String ,Object> map=new HashMap<>();
        map.put("success",flag);
        map.put("ar",ar);
        PrintJson.printJsonObj(response,map);
    }

    private void saveRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行添加备注操作");
        String noteContent=request.getParameter("noteContent");
        String activityId=request.getParameter("activityId");
        String id=UUIDUtil.getUUID();
        String createTime=DateTimeUtil.getSysTime();
        String createBy=((User)request.getSession().getAttribute("user")).getName();
        String editflag="0";
        ActivityRemark ar=new ActivityRemark();
        ar.setId(id);
        ar.setNoteContent(noteContent);
        ar.setActivityId(activityId);
        ar.setCreateBy(createBy);
        ar.setCreateTime(createTime);
        ar.setEditFlag(editflag);
        ActivityService as= (ActivityService) ServiceFactory.getservice(new ActivityServiceImpl());
        boolean flag= as.saveRemark(ar);
        Map<String ,Object> map=new HashMap<>();
        map.put("success",flag);
        map.put("ar",ar);
        PrintJson.printJsonObj(response,map);
    }

    private void deleteRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进行备注删除操作");
        String id=request.getParameter("id");
        ActivityService as= (ActivityService) ServiceFactory.getservice(new ActivityServiceImpl());
        boolean flag=as.deleteRemark(id);
        PrintJson.printJsonFlag(response,flag);

    }

    private void getRemarkListByAid(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到备注查询方法了");
        String id=request.getParameter("activityId");
        ActivityService as= (ActivityService) ServiceFactory.getservice(new ActivityServiceImpl());
        List<ActivityRemark> ar=as.getRemarkListByAid(id);
        PrintJson.printJsonObj(response,ar);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到详细信息页面");
        String id =request.getParameter("id");
        System.out.println("id================================="+id);
        ActivityService as= (ActivityService) ServiceFactory.getservice(new ActivityServiceImpl());
        Activity a=as.detail(id);
        request.setAttribute("a",a);
        request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request,response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动修改操作");
        ActivityService as= (ActivityService) ServiceFactory.getservice(new ActivityServiceImpl());
        String id=request.getParameter("id");
        String owner =request.getParameter("owner");
        String name =request.getParameter("name");
        String startDate =request.getParameter("startDate");
        String endDate  =request.getParameter("endDate");
        String cost =request.getParameter("cost");
        String description =request.getParameter("description");
//        修改时间是当前系统时间
        String editTime = DateTimeUtil.getSysTime();
//        修改人是当前登录的用户
        String editBy = ((User) request.getSession().getAttribute("user")).getName();
        Activity a=new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setCreateTime(editTime);
        a.setCreateBy(editBy);
        boolean flag=as.update(a);
        PrintJson.printJsonFlag(response,flag);
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
    private void pageList(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("开始活动查询列表");
        String name=request.getParameter("name");
        String owner=request.getParameter("owner");
        String startDate=request.getParameter("startDate");
        String endDate=request.getParameter("endDate");
        String pageNo=request.getParameter("pageNo");
//        每页展现的记录数
        String pageSize=request.getParameter("pageSize");
//        计算略出的记录数
        int skipCount=((Integer.valueOf(pageNo)-1)*(Integer.valueOf(pageSize)));
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("skipCount",skipCount);
        map.put("pageSize",Integer.valueOf(pageSize));
        ActivityService as= (ActivityService) ServiceFactory.getservice(new ActivityServiceImpl());
//        分页的查询每个都有 所以选用一个通用的vo，操作起来比较方便
        PaginationVo<Activity> vo =as.pageList(map);
        PrintJson.printJsonObj(response,vo);

    }
    private void delete(HttpServletRequest request,HttpServletResponse response){
        System.out.println("执行市场删除操作");
        String ids[] =request.getParameterValues("id");
        for (String s:ids)
        System.out.println("ids======================="+s);
        ActivityService as= (ActivityService) ServiceFactory.getservice(new ActivityServiceImpl());
        boolean flag=as.delete(ids);
        PrintJson.printJsonFlag(response,flag);
    }
    private void get(HttpServletRequest request,HttpServletResponse response){
        System.out.println("进入到查询用户信息列表和根据市场活动id查询单条记录的操作");
        String id=request.getParameter("id");
        ActivityService as= (ActivityService) ServiceFactory.getservice(new ActivityServiceImpl());
        Map<String , Object> map=as.getUserListAndActivity(id);
        PrintJson.printJsonObj(response,map);
    }
}

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


public class ClueController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到市场活动控制器");
        String path=request.getServletPath();
        if("/workbench/clue/getUserList.do".equals(path)){



        }else if("/workbench/clue/save.do".equals(path)){

        }

    }


}

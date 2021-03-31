package com.myweb.crm.settings.web.controller;

import com.myweb.crm.settings.domain.User;
import com.myweb.crm.settings.service.UserService;
import com.myweb.crm.settings.service.impl.UserServiceImpl;
import com.myweb.crm.utils.PrintJson;
import com.myweb.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到用户控制器");
        String path=request.getServletPath();
        if("/settings/user/login.do".equals(path)){
        login(request,response);

        }else if("/settings/user/.do".equals(path)){

        }
    }
    private void login(HttpServletRequest request, HttpServletResponse response){
        String loginAct=request.getParameter("loginAct");
        String loginPwd=request.getParameter("loginPwd");
//        接收浏览器端的IP地址
        String ip =request.getRemoteAddr();
        System.out.println("ip==============================="+ip);
//        未来业务层开发，统一使用代理类形态的接口对象
        UserService us= (UserService) ServiceFactory.getservice(new UserServiceImpl());
        try {
            User user=us.login(loginAct,loginPwd,ip);
//            将用户信息放到session域中
            request.getSession().setAttribute("user",user);
//         如果程序执行到此处，说明业务层没有抛出异常  表示登录成功
            PrintJson.printJsonFlag(response,true);
        }catch (Exception e){
            e.printStackTrace();
//          执行到catch块 说明登录失败
//            获取失败信息
            String msg=e.getMessage();
            Map<String ,Object> map=new HashMap<String,Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }
}

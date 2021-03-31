package com.myweb.crm.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入到过滤器 验证是否登录");
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        HttpServletResponse resq= (HttpServletResponse) servletResponse;
//      拿到当前路径  让登录页面和登录方法放行
        String path=req.getServletPath();

        if("/login.jsp".equals(path)||"/settings/user/login.do".equals(path)){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {
//        验证用户是否登录 就是判断session是否有对象
         if(req.getSession().getAttribute("user")!=null){
             filterChain.doFilter(servletRequest,servletResponse);
         }
         else {
//             重定向到登录页
//             转发：路径会停留到老路径上，而不是跳转到之后的最新路径
//             我们应该在为用户跳转到登录页的通知，将浏览器的地址栏自动设置为当前登录页的路径
//             ${pageContext.request.contextPath} 这是获取项目名
             resq.sendRedirect(req.getContextPath()+"/login.jsp");
         }
    }
    }

    @Override
    public void destroy() {

    }
}

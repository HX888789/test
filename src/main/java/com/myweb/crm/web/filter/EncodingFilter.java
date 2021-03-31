package com.myweb.crm.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

//        过滤post请求的中文参数乱码
        req.setCharacterEncoding("utf-8");
//        过滤响应流相应的中文乱码
        resp.setContentType("text/html;charset=utf-8");
        chain.doFilter(req,resp);
    }
}

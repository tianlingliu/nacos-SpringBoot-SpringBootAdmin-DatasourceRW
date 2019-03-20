package com.aibao.claims.auth.utils.filter;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/druid/*",
        initParams = {
                @WebInitParam(name = "allow", value = ""),// IP白名单 (没有配置或者为空，则允许所有访问)
                @WebInitParam(name = "deny", value = "192.168.0.1"),// IP黑名单 (存在共同时，deny优先于allow)
                @WebInitParam(name = "loginUsername", value = "root"),// 用户名
                @WebInitParam(name = "loginPassword", value = "root"),// 密码
                @WebInitParam(name = "resetEnable", value = "false")// 禁用HTML页面上的“Reset All”功能
        })
@WebFilter(filterName = "myFilter", urlPatterns = "/*", asyncSupported = true)
public class MyFilter extends StatViewServlet implements Filter {


    private static final long serialVersionUID = 1L;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        System.out.println(request.getRequestURI());
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }


}
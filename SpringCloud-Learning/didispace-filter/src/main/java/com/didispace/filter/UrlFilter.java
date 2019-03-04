package com.didispace.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Component
//@component （把普通pojo实例化到spring容器中，相当于配置文件中的
//<bean id="" class=""/>）
// 过滤器
@WebFilter(filterName = "test", urlPatterns = "/success/*")
public class UrlFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----------------------->过滤器被创建");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        if(!requestURI.contains("info")){
            servletRequest.getRequestDispatcher("/failed").forward(servletRequest, servletResponse);
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        System.out.println("----------------------->过滤器被销毁");
    }
}

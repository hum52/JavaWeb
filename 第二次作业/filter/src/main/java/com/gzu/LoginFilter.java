package com.gzu;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter("/*")
public class LoginFilter implements Filter {
    // 定义不需要登录的排除路径
    private static final String[] EXCLUDED_PATHS = {"/login", "/register", "/index.jsp",""};

    // 初始化过滤器
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginFilter initialized...");
    }

    // 过滤请求
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String username = (String)request.getSession().getAttribute("username");
        String requestURI = request.getRequestURI();
        if (isExcluded(requestURI)) {
            filterChain.doFilter(request, response);

        }
        else
        {
            if (username == null || username.isEmpty()) {// 如果用户未登录
                request.getRequestDispatcher("/login.jsp").forward(request, response);// 转发到登录页面

            }
            else {
                filterChain.doFilter(request, response);// 放行

            }
        }
    }

    // 检查当前请求路径是否在排除列表中
    private boolean isExcluded(String uri) {
        for (String excludedPath : EXCLUDED_PATHS) {
            if (uri.endsWith(excludedPath)) {
                return true;
            }
        }
        return false;
    }

    // 销毁过滤器
    @Override
    public void destroy() {
        System.out.println("LoginFilter destroyed...");
    }
}

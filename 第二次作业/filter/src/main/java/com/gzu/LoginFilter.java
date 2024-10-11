package com.gzu;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    // 定义不需要登录的排除路径
    private static final String[] EXCLUDED_PATHS = {"/login", "/register", "/public"};

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
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI(); // 获取请求的 URI

        try {
            // 检查请求是否在排除列表中
            if (isExcluded(uri) || request.getSession().getAttribute("user") != null) {
                filterChain.doFilter(request, httpResponse); // 允许请求继续
            } else {
                // 重定向到登录页面
                httpResponse.sendRedirect(request.getContextPath() + "/login");
            }
        } catch (Exception e) {
            // 处理异常，例如记录日志
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器内部错误");
        }
    }

    // 检查当前请求路径是否在排除列表中
    private boolean isExcluded(String uri) {
        return Arrays.asList(EXCLUDED_PATHS).contains(uri);
    }

    // 销毁过滤器
    @Override
    public void destroy() {
        System.out.println("LoginFilter destroyed...");
    }
}

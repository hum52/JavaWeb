package com.gzu;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.logging.Logger;

@WebListener
public class RequestLoggingListener implements ServletRequestListener {

    private static final Logger logger = Logger.getLogger(RequestLoggingListener.class.getName());//日志记录器

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();//获取请求对象
        request.setAttribute("startTime", System.currentTimeMillis());//添加开始时间

        String message1 = ("请求开始时间: " + new Date() + "\n\nIP: " + request.getRemoteAddr()
                + "\n方法: " + request.getMethod()
                + "\nURI: " + request.getRequestURI()
                + (request.getQueryString() != null ? "\n查询字符串: " + request.getQueryString() : "")
                + "\nUser-Agent: " + request.getHeader("User-Agent"));//将记录信息存入字符串
        logger.info(message1);//向日志中添加记录信息
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();//获取请求对象
        long startTime = (long) request.getAttribute("startTime");//从request对象获取开始时间
        long processingTime = System.currentTimeMillis() - startTime;

        String str=("\n\n请求结束: " + new Date() + "\n处理时间: " + processingTime + "ms");
        logger.info(str);
    }
}

package com.gzu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.awt.SystemColor.window;

@WebServlet("/login")
public class LoginServlet  extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");//解决中文乱码问题

       String username = (String) request.getParameter("username");//获取用户名和密码
       String password = (String) request.getParameter("password");
       if (username.equals("admin") && password.equals("admin")) {//登录成功
           request.getSession().setAttribute("username", username);//写入session
           request.getRequestDispatcher("/index.jsp").forward(request, response);//重定向到首页
       }
       else {//登录失败

           response.getWriter().println("用户名或密码错误！");

       }
    }
}

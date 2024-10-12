# LoginServlet.java 概述

`LoginServlet` 是一个 Java Servlet，用于处理用户登录请求。它主要通过处理 POST 请求来验证用户的凭据并响应结果。

## 实现步骤：

1. **包和库导入**：
    - 定义在 `com.gzu` 包中，导入处理 Servlet 所需的类和接口。

2. **Servlet 映射**：
    - 使用 `@WebServlet("/login")` 注解，将该 Servlet 映射到 `/login` 路径。

3. **处理 POST 请求**：
    - 重写 `doPost` 方法，接收 `HttpServletRequest` 和 `HttpServletResponse` 对象。

4. **设置响应内容**：
    - 设置响应内容类型为 `text/html` 并使用 UTF-8 编码，以确保中文字符正确显示。

5. **获取用户输入**：
    - 从请求中提取 `username` 和 `password` 参数。

6. **验证用户名和密码**：
    - 检查输入的用户名和密码是否匹配预定义的值“admin”。

7. **成功处理**：
    - 将用户名存入会话，并重定向请求到 `index.jsp` 页面。

8. **失败处理**：
    - 如果验证失败，向客户端返回“用户名或密码错误！”的提示。

## 主要功能：
该 Servlet 实现了一个简单的登录功能，通过验证用户提供的凭据并处理成功或失败的情况。

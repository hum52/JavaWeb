### `doFilter` 函数概述

`doFilter` 方法是 `LoginFilter` 类的核心方法，用于处理HTTP请求的过滤逻辑，具体步骤如下：

1. **请求和响应对象转换**：
   - 将 incoming `ServletRequest` 转换为 `HttpServletRequest`，将 `ServletResponse` 转换为 `HttpServletResponse`。

2. **获取请求URI**：
   - 通过`request.getRequestURI()`获取当前请求的URI，以确定用户的请求路径。

3. **请求处理逻辑**：
   - 使用 `isExcluded()` 方法检查当前请求的URI是否在定义的排除列表中，或者判断用户是否已登录（通过检查session中是否存在"user"属性）。
   - 如果请求的URI在排除列表中或者用户已登录，则调用 `filterChain.doFilter(request, httpResponse)`，允许请求继续处理。
   - 如果都不满足，则使用 `httpResponse.sendRedirect()` 方法重定向用户到登录页面 (`/login`)。

4. **异常处理**：
   - 方法中包含一个 `try-catch` 块，捕获可能的异常。如果发生异常，则通过 `httpResponse.sendError()` 返回500内部服务器错误，并提供相关的错误信息。

### 总结

`doFilter` 方法通过检查用户的登录状态和请求路径，实现了对需要授权的资源访问的控制，确保只有经过身份验证的用户可以访问受保护的页面。


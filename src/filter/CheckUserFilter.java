package filter;

// 引入所需的类
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;

// 实现Filter接口的CheckUserFilter类
public class CheckUserFilter implements Filter {
    // 实现Filter接口的doFilter方法
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {
        // 将ServletRequest和ServletResponse转换为HttpServletRequest和HttpServletResponse
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 用户是否登录的验证
        User u = (User) request.getSession().getAttribute("user");
        // 如果用户未登录
        if (u == null) {
            // 跳转到登录页面
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            return;
        }
        // 继续请求的后续访问操作
        chain.doFilter(req, resp);
    }

    // 实现Filter接口的destroy方法，销毁时调用
    @Override
    public void destroy() {
        // 这里没有实现任何销毁逻辑
    }

    // 实现Filter接口的init方法，初始化时调用
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // 这里没有实现任何初始化逻辑
    }
}

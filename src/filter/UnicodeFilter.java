package filter;

// 引入所需的类
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// 实现Filter接口的UnicodeFilter类
public class UnicodeFilter implements Filter {
    // 实现Filter接口的doFilter方法
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {
        // 打印开始信息
        System.out.println("start");
        // 针对POST提交的请求设置字符编码为utf-8
        req.setCharacterEncoding("utf-8");
        // 继续请求的后续访问操作
        chain.doFilter(req, resp);
        // 打印结束信息
        System.out.println("end");
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

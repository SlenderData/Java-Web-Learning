package servlet;

// 引入所需的类
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;

public class UserServlet extends HttpServlet {
    // 处理POST请求的方法
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 设置请求的字符编码为utf-8
        req.setCharacterEncoding("utf-8");
        // 获取请求的路径
        String path = req.getServletPath();
        // 根据请求路径判断是注册还是登录
        if ("/regist.user".equals(path)) {
            // 调用注册方法
            regist(req, resp);
        } else if ("/login.user".equals(path)) {
            // 调用登录方法
            login(req, resp);
        }
    }

    // 处理GET请求的方法
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 获取请求的路径
        String path = req.getServletPath();
        // 根据请求路径判断是注销
        if ("/logout.user".equals(path)) {
            // 调用注销方法
            logout(req, resp);
        }
    }

    // 注销方法
    protected void logout(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 获取session对象
        HttpSession session = req.getSession();
        // 销毁session
        session.invalidate();
        // 重定向到商品列表页面
        resp.sendRedirect(req.getContextPath() + "/list.product");
    }

    // 登录方法
    protected void login(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 获取请求参数中的用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 加载MySQL驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 连接到数据库
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jjxy?useUnicode=true&characterEncoding=utf-8", "root", "root");
            // 准备SQL查询语句
            String sql = "select * from t_user where username=? and password=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            // 如果查询结果存在，表示登录成功
            if (rs.next()) {
                // 获取用户信息
                int id = rs.getInt("id");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                User u = new User();
                u.setId(id);
                u.setAddress(address);
                u.setPassword(password);
                u.setPhone(phone);
                u.setUsername(username);
                // 获取session对象
                HttpSession session = req.getSession();
                System.out.println(session.getId());
                // 将用户信息存入session
                session.setAttribute("user", u);
                // 重定向到商品列表页面
                resp.sendRedirect(req.getContextPath() + "/list.product");
                return;
            }
            // 如果登录失败，设置错误提示信息并转发到登录页面
            req.setAttribute("message", "用户名密码不正确！");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭ResultSet
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 关闭PreparedStatement
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 关闭数据库连接
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 注册方法
    protected void regist(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 获取请求参数中的用户名、密码、电话和地址
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 加载MySQL驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 连接到数据库
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jjxy?useUnicode=true&characterEncoding=utf-8", "root", "root");
            // 准备SQL查询语句，检查用户名是否已存在
            String sql = "select * from t_user where username=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            // 如果查询结果存在，表示用户名已存在
            if (rs.next()) {
                // 设置错误提示信息并转发到注册页面
                req.setAttribute("message", username + "用户名已存在！");
                req.getRequestDispatcher("/jsp/regist.jsp").forward(req, resp);
                return;
            }
            // 开始事务
            con.setAutoCommit(false);
            // 准备插入用户信息的SQL语句
            sql = "insert into t_user (username,password,phone,address) values (?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.executeUpdate();
            // 提交事务
            con.commit();
            // 重定向到登录页面
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 出现异常时回滚事务
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            // 关闭ResultSet
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 关闭PreparedStatement
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 关闭数据库连接
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

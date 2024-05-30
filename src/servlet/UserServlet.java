package servlet;


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
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String path=req.getServletPath();
		if("/regist.user".equals(path)){
			regist(req, resp);
		}else if("/login.user".equals(path)){
			login(req, resp);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path=req.getServletPath();
		if("/logout.user".equals(path)){
			logout(req, resp);
		}
	}
	protected void logout(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		注销：删除session中的用户信息
		HttpSession session=req.getSession();
//		session.removeAttribute("user");
//		销毁session
		session.invalidate();
		resp.sendRedirect(req.getContextPath()+"/list.product");
	}
	
	protected void login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jjxy?useUnicode=true&characterEncoding=utf-8", "root", "root");
			String sql="select * from t_user where username=? and password=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()){
//				登录成功返回商品列表页面
//				考虑将后续需要用到的用户信息保存到session
				int id=rs.getInt("id");
				String phone=rs.getString("phone");
				String address=rs.getString("address");
				User u=new User();
				u.setId(id);
				u.setAddress(address);
				u.setPassword(password);
				u.setPhone(phone);
				u.setUsername(username);
//				获取session
				HttpSession session=req.getSession();
				System.out.println(session.getId());
//				将用户信息存到session中
				session.setAttribute("user", u);
				resp.sendRedirect(req.getContextPath()+"/list.product");
				return;
			}
//			登录失败，返回登录页面进行错误信息的带入提示
			req.setAttribute("message", "用户名密码不正确！");
			req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	protected void regist(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String phone=req.getParameter("phone");
		String address=req.getParameter("address");
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jjxy?useUnicode=true&characterEncoding=utf-8", "root", "root");
			String sql="select * from t_user where username=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next()){
//				注册失败，返回注册页面进行错误信息的带入提示
				req.setAttribute("message", username+"用户名已存在！");
				req.getRequestDispatcher("/jsp/regist.jsp").forward(req, resp);
				return;
			}
			
			con.setAutoCommit(false);
			sql="insert into t_user (username,password,phone,address) values (?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, phone);
			ps.setString(4, address);
			ps.executeUpdate();
			con.commit();
			resp.sendRedirect(req.getContextPath()+"/jsp/login.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cart;
import entity.User;
import mysql.DataBase;


public class CartServlet extends HttpServlet {
    public static List<Cart> cart = new ArrayList<Cart>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        if ("/add.cart".equals(path)) {
            add(req, resp);
        }
        if ("/show.cart".equals(path)) {
            show(req, resp);
        }
        if ("/delete.cart".equals(path)) {
            delete(req, resp);
        }
        if ("/update.cart".equals(path)) {
            update(req, resp);
        }
    }


    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String index = req.getParameter("index");
        User u = (User) req.getSession().getAttribute("user");
        String username = u.getUsername();
        DataBase db = new DataBase();
        ResultSet rs = db.getData("SELECT * FROM goods where goodsid=" + index);
        String goodsname = "";
        Double price = 0.0;
        try {
            if (rs.next()) {
                goodsname = rs.getString(2);
                price = rs.getDouble(3);
            } else {
                System.out.println("获取出错！！！");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        rs = db.getData("SELECT * FROM cart where goodsname='" + goodsname + "' and un='" + username + "'");
        try {
            if (rs.next()) {
                String sql = "UPDATE cart SET number=" + (rs.getInt("number") + 1) + ",price=" + (price * (rs.getInt("number") + 1)) + " where goodsname='" + goodsname + "' and un='" + username + "'";
                db.setData(sql);
                req.setAttribute("msg", "商品" + goodsname + "加入购物成成功！");
                req.getRequestDispatcher("/show.goods").forward(req, resp);
            } else {
                String sql = "insert into cart(goodsname,number,price,un) values('" + goodsname + "',1,'" + price + "','" + username + "')";
                System.out.print(sql);
                db.setData(sql);
                req.setAttribute("msg", "商品" + goodsname + "加入购物成成功！");
                req.getRequestDispatcher("/show.goods").forward(req, resp);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        db.close();

    }

    protected void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        String username = u.getUsername();
        DataBase db = new DataBase();
        ResultSet rs = db.getData("SELECT * FROM cart where un='" + username + "'");

        try {
            while (rs.next()) {
                Cart c = new Cart();
                c.setGoodsname(rs.getString(1));
                c.setNumber(rs.getInt(2));
                c.setPrice(rs.getDouble(3));
                c.setUsername(username);
                cart.add(c);

            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        req.setAttribute("cart", cart);
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
        cart.clear();
        db.close();
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBase db = new DataBase();
        User u = (User) req.getSession().getAttribute("user");
        String username = u.getUsername();
        String type = req.getParameter("type");
        //清空
        if ("All".equals(type)) {
            String sql = "DELETE FROM cart WHERE un = '" + username + "' ";
            db.setData(sql);
            resp.sendRedirect(req.getContextPath() + "/show.cart");
        }
        //删除某个
        else {
            String goodsname = req.getParameter("goodsname");
            byte[] b = goodsname.getBytes("ISO8859-1");
            goodsname = new String(b, "utf-8");
            String sql = "DELETE FROM cart WHERE goodsname = '" + goodsname + "' AND un = '" + username + "'";
            db.setData(sql);
            resp.sendRedirect(req.getContextPath() + "/show.cart");
        }
        db.close();
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String goodsname = req.getParameter("goodsname");
        byte[] b = goodsname.getBytes("ISO8859-1");
        goodsname = new String(b, "utf-8");
        System.out.println(goodsname);
        int newNumber = Integer.parseInt(req.getParameter("number"));
        System.out.println(newNumber);
        User u = (User) req.getSession().getAttribute("user");
        String username = u.getUsername();
        DataBase db = new DataBase();

        // 获取商品单价
        ResultSet rs = db.getData("SELECT * FROM goods where goodsname='" + goodsname + "'");
        double price = 0.0;
        try {
            if (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 更新购物车中的商品数量和价格
        String sql = "UPDATE cart SET number=" + newNumber + ", price=" + (price * newNumber) +
                " WHERE goodsname='" + goodsname + "' AND un='" + username + "'";
        db.setData(sql);

        db.close();
        resp.sendRedirect(req.getContextPath() + "/show.cart");
    }


}

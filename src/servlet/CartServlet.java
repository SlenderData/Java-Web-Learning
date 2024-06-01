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
import java.util.List;
import java.util.ArrayList;
import entity.Cart;
import entity.Product;
import entity.User;

public class CartServlet extends HttpServlet {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/jjxy?useUnicode=true&characterEncoding=utf-8", "root", "root");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/add.cart":
                addToCart(request, response);
                break;
            case "/list.cart":
                listCart(request, response);
                break;
            // 根据需要添加其他操作
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            return;
        }

        int productId = Integer.parseInt(request.getParameter("product_id"));
        Product product = getProductById(productId);
        if (product == null) {
            response.sendRedirect(request.getContextPath() + "/list.product");
            return;
        }

        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM t_cart WHERE product_id = ? AND user_id = ?");
            ps.setInt(1, productId);
            ps.setInt(2, user.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int cartId = rs.getInt("id");
                int num = rs.getInt("num") + 1;
                double price = num * product.getPrice();
                PreparedStatement updatePs = con.prepareStatement("UPDATE t_cart SET num = ?, price = ? WHERE id = ?");
                updatePs.setInt(1, num);
                updatePs.setDouble(2, price);
                updatePs.setInt(3, cartId);
                updatePs.executeUpdate();
            } else {
                PreparedStatement insertPs = con.prepareStatement("INSERT INTO t_cart (product_id, num, price, user_id) VALUES (?, ?, ?, ?)");
                insertPs.setInt(1, productId);
                insertPs.setInt(2, 1);
                insertPs.setDouble(3, product.getPrice());
                insertPs.setInt(4, user.getId());
                insertPs.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/list.cart");
    }

    private Product getProductById(int productId) {
        Product product = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM t_product WHERE id = ?")) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    private void listCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            return;
        }

        List<Cart> cartList = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT c.*, p.name, p.price AS unit_price FROM t_cart c JOIN t_product p ON c.product_id = p.id WHERE c.user_id = ?")) {
            ps.setInt(1, user.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cart cart = new Cart();
                    cart.setId(rs.getInt("id"));
                    cart.setNum(rs.getInt("num"));
                    cart.setPrice(rs.getDouble("price"));
                    Product product = new Product();
                    product.setId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("unit_price"));
                    cart.setProduct(product);
                    cart.setUser(user);
                    cartList.add(cart);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("carts", cartList);
        request.getRequestDispatcher("/jsp/cart.jsp").forward(request, response);
    }
}

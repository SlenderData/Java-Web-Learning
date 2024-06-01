package servlet;

// 引入所需的类
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Product;

// 商品表的所有业务操作的Servlet
public class ProductServlet extends HttpServlet {
    // 注释掉init方法
    // @Override
    // public void init() throws ServletException {
    //     System.out.println("ProductServlet.init()");
    // }

    // 注释掉destroy方法
    // @Override
    // public void destroy() {
    //     System.out.println("ProductServlet.destroy()");
    // }

    // 处理GET请求的方法
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 输出对应浏览器的sessionID
        System.out.println(req.getSession().getId());

        // 获取请求路径
        String path = req.getServletPath();
        // 根据请求路径判断是查询商品列表
        if ("/list.product".equals(path)) {
            // 调用查询商品列表方法
            list(req, resp);
        }
    }

    // 查询商品中所有商品信息的方法
    protected void list(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 连接数据库，查询商品表中所有商品信息，将查到的所有数据返回到list.jsp进行展示
        System.out.println("查询商品列表");
        // 数据库连接信息
        String url = "jdbc:mysql://localhost:3306/jjxy?useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String password = "root";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        // 用于保存多个商品信息的商品集合
        List<Product> products = new ArrayList<Product>();
        try {
            // 加载MySQL驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 连接到数据库
            con = DriverManager.getConnection(url, user, password);
            // 准备SQL查询语句
            String sql = "select * from t_product";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            // 处理查询结果
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                // 将查询出的每一条商品信息保存到商品对象中
                Product p = new Product();
                p.setId(id);
                p.setName(name);
                p.setPrice(price);
                products.add(p);
            }
            // 将保存于商品集合中的所有信息传递到list.jsp页面显示
            req.setAttribute("products", products);
            req.getRequestDispatcher("/jsp/list.jsp").forward(req, resp);
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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/jjxy?useUnicode=true&characterEncoding=utf-8", "root", "root");
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> products = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM t_product");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher("/jsp/list.jsp").forward(request, response);
    }
}

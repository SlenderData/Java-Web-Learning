package entity;

public class Cart {
    // 私有成员变量
    private Integer id;        // 购物车ID
    private Integer num;       // 商品数量
    private Double price;      // 商品总价格
    private Product product;   // 商品对象
    private User user;         // 用户对象

    // 无参构造方法
    public Cart() {
    }

    // 获取购物车ID的方法
    public Integer getId() {
        return id;
    }

    // 设置购物车ID的方法
    public void setId(Integer id) {
        this.id = id;
    }

    // 获取商品数量的方法
    public Integer getNum() {
        return num;
    }

    // 设置商品数量的方法
    public void setNum(Integer num) {
        this.num = num;
    }

    // 获取商品总价格的方法
    public Double getPrice() {
        return price;
    }

    // 设置商品总价格的方法
    public void setPrice(Double price) {
        this.price = price;
    }

    // 获取商品对象的方法
    public Product getProduct() {
        return product;
    }

    // 设置商品对象的方法
    public void setProduct(Product product) {
        this.product = product;
    }

    // 获取用户对象的方法
    public User getUser() {
        return user;
    }

    // 设置用户对象的方法
    public void setUser(User user) {
        this.user = user;
    }
}

package entity;

// 用于存储商品信息的实体类
public class Product {
    // 私有成员变量
    private Integer id;    // 商品ID
    private String name;   // 商品名称
    private Double price;  // 商品价格

    // 无参构造方法
    public Product() {
    }

    // 获取商品ID的方法
    public Integer getId() {
        return id;
    }

    // 设置商品ID的方法
    public void setId(Integer id) {
        this.id = id;
    }

    // 获取商品名称的方法
    public String getName() {
        return name;
    }

    // 设置商品名称的方法
    public void setName(String name) {
        this.name = name;
    }

    // 获取商品价格的方法
    public Double getPrice() {
        return price;
    }

    // 设置商品价格的方法
    public void setPrice(Double price) {
        this.price = price;
    }
}

package entity;

// User类表示用户实体
public class User {
    // 私有成员变量
    private Integer id;        // 用户ID
    private String username;   // 用户名
    private String password;   // 密码
    private String phone;      // 电话
    private String address;    // 地址

    // 无参构造方法
    public User() {
    }

    // 获取用户ID的方法
    public Integer getId() {
        return id;
    }

    // 设置用户ID的方法
    public void setId(Integer id) {
        this.id = id;
    }

    // 获取用户名的方法
    public String getUsername() {
        return username;
    }

    // 设置用户名的方法
    public void setUsername(String username) {
        this.username = username;
    }

    // 获取密码的方法
    public String getPassword() {
        return password;
    }

    // 设置密码的方法
    public void setPassword(String password) {
        this.password = password;
    }

    // 获取电话的方法
    public String getPhone() {
        return phone;
    }

    // 设置电话的方法
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // 获取地址的方法
    public String getAddress() {
        return address;
    }

    // 设置地址的方法
    public void setAddress(String address) {
        this.address = address;
    }

    // 重写toString方法，返回用户信息的字符串表示
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password="
                + password + ", phone=" + phone + ", address=" + address + "]";
    }
}

set names utf8;
create database jjxy charset=utf8;
use jjxy;
-- 商品表
create table t_product(
id int primary key auto_increment,
name varchar(50),
price double
);

insert into t_product (name,price) values ('娃哈哈矿泉水',1.5);
insert into t_product (name,price) values ('营养快线',4);
insert into t_product (name,price) values ('AD钙奶',3);

-- 用户表
create table t_user(
id int primary key auto_increment,
username varchar(50),
password varchar(50),
phone varchar(50),
address varchar(50)
);

-- 购物车表
create table t_cart(
id int primary key auto_increment,
product_id int, --与商品id关联外键
num int, -- 数量
price double, --商品单价*数量
user_id int --与用户id关联的外键
);



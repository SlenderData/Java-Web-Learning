set names utf8;
create database jjxy default charset = utf8;
use jjxy;

create table goods
(
    goodsid   int primary key auto_increment,
    goodsname varchar(50) not null unique,
    price     double
) engine = innodb
  default charset = utf8;

insert into goods(goodsid, goodsname, price)
values (1, '娃哈哈矿泉水', 1.5);
insert into goods(goodsid, goodsname, price)
values (2, '营养快线', 4);
insert into goods(goodsid, goodsname, price)
values (3, 'AD钙奶', 3);
insert into goods(goodsid, goodsname, price)
values (4, '脉动', 6);
insert into goods(goodsid, goodsname, price)
values (5, '红牛', 5);
insert into goods(goodsid, goodsname, price)
values (6, '可乐', 3.5);
insert into goods(goodsid, goodsname, price)
values (7, '雪碧', 3.5);
insert into goods(goodsid, goodsname, price)
values (8, '芬达', 3.5);
insert into goods(goodsid, goodsname, price)
values (9, '果粒橙', 4);
insert into goods(goodsid, goodsname, price)
values (10, '冰红茶', 3);
insert into goods(goodsid, goodsname, price)
values (11, '绿茶', 3);

create table user
(
    un    varchar(50) not null unique,
    pwd   varchar(50) not null,
    phone varchar(30),
    addr  varchar(50)
) engine = innodb
  default charset = utf8;

create table cart
(
    goodsname varchar(50) not null,
    number    int         not null,
    price     double,
    un        varchar(50)
) engine = innodb
  default charset = utf8;

create table order_
(
    id        varchar(80) not null,
    un        varchar(50) not null,
    goodsname varchar(50) not null,
    number    int         not null,
    price     double      not null
) engine = innodb
  default charset = utf8;
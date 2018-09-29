# Doublez
提供一些基础的API供Doublez安卓端调用

登陆注册接口文档：
URL: http://123.207.163.104/Doublez/user.do
1.登录：
参数：
	method[必选]  {“login”}  
	email[必选]   
	password[必选]

返回数据：
{“code”: 0/1/2/3/4 int类型，0代表成功，其他都为失败
 “msg”: String 类型，信息}
2.注册
	method[必选]  {“register”}
	email[必选]   
	password[必选]
	avartar[必选] 头像图片的byte64字符串
返回数据：
{“code”: 0/1/2/3/4 int类型，0代表成功，其他都为失败
 “msg”: String 类型，信息}
 
 数据库建表：
 分为必需信息和以后也许会用到的详细信息
 create table user(
	id int primary key auto_increment,
    email varchar(255),
    password varchar(255),
    avatar blob
    );
 create table user_info(
    id int primary key auto_increment,
    user_id int,
	  nickname varchar(255),
    sex varchar(20),
    age int,
    school varchar(255),
    major varchar(255),
    grade varchar(255)  
    )


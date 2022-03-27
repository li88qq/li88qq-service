-- User:用户表(后台)
create table `AmUser`(
  id bigint not null auto_increment comment '自增长主键',
  username varchar(30) comment '用户名',
  `password` varchar(50) comment '密码',
  `name` varchar(20) comment '昵称',
  mobile varchar(20) comment '手机号码',
  email varchar(50) comment '邮箱',
  head varchar(30) comment '头像地址',
  `state` int not null default 0 comment '账号状态',
  loginDate bigint not null default 0 comment '本次登录时间,时间戳',
  loginIp varchar(50) comment '本地登录ip',
  lastLoginDate bigint not null default 0 comment '上次登录时间,时间戳',
  lastLoginIp varchar(50) comment '上次登录ip',
  createDate bigint not null default 0 comment '注册时间',
  salt varchar(50) comment '盐',
  primary key(id),
  unique key(username)
) comment '注册用户';

-- LoginLog:登录记录(后台)
create table AmLoginLog(
  id bigint not null auto_increment comment '自增长主键',
  uid bigint not null default 0 comment '用户id',
  `state` int not null default 0 comment '登录状态',
  errorCode int not null default 0 comment '异常状态码',
  createDate bigint not null default 0 comment '登录时间',
  loginIp varchar(50) comment '登录ip',
  logoutDate bigint not null default 0 comment '登出时间',
  primary key(id),
  index UID_INDEX(uid)
) comment '登录记录';



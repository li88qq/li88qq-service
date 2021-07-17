-- 用户表
create table User(
    id bigint not null auto_increment comment '主键',
    username varchar(32) comment '用户名',
    nickname varchar(32) comment '昵称',
    email varchar(32) comment '邮箱',
    mobile varchar(11) comment '手机号码',
    photo varchar(64) comment '头像地址',
    password varchar(64) comment '密码',
    state int not null default 0 comment '状态,0-未激活,1-正常',
    salt varchar(32) comment '加密盐',
    createDate bigint not null default 0 comment '注册时间',
    updateDate bigint not null default 0 comment '更新时间',
    lastLoginDate bigint not null default 0 comment '最后登录时间',
    lastLoginIp varchar(32) comment '最后登录ip',
    primary key(id),
    unique key(username)
) comment '用户表';

-- 用户登录记录
create table LoginLog(
    id bigint not null auto_increment comment '主键',
    uid bigint not null default 0 comment '用户id',
    state int not null default 0 comment '登录状态,1-正常,2-失败',
    errorType int not null default 0 comment '失败类型',
    createDate bigint not null default 0 comment '登录时间',
    loginIp varchar(32) comment '登录ip',
    primary key(id)
) comment '用户登录记录';


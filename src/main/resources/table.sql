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
)   comment '用户表';

-- 用户登录记录
create table LoginLog(
    id bigint not null auto_increment comment '主键',
    uid bigint not null default 0 comment '用户id',
    state int not null default 0 comment '登录状态,1-正常,2-失败',
    errorType int not null default 0 comment '失败类型',
    createDate bigint not null default 0 comment '登录时间',
    loginIp varchar(32) comment '登录ip',
    primary key(id)
)   comment '用户登录记录';

-- 文章表
create table Article(
    id bigint not null auto_increment comment '主键',
    uid bigint not null default 0 comment '用户id',
    title varchar(100) comment '标题',
    state int not null default 0 comment '状态',
    open int not null default 0 comment '是否公开,1-公开',
    original int not null default 0 comment '是否原创,1-原创',
    quote varchar(255) comment '引用地址',
    createDate bigint not null default 0 comment '创建时间',
    updateDate bigint not null default 0 comment '更新时间',
    ctUpdateDate bigint not null default 0 comment '内容更新时间',
    sn varchar(32) comment '随机码',
    words int not null default 0 comment '字数',
    readCount int not null default 0 comment '阅读数',
    primary key(id)
)   comment '文章表';

-- 文章内容
create table ArticleContent(
    id bigint not null comment '主键,文章id',
    content text comment '内容',
    primary key(id)
)   comment '文章内容';

-- 文章标签
create table ArticleLabel(
    id bigint not null auto_increment comment '主键',
    name varchar(10) comment '标签名称',
    createUid bigint not null default 0 comment '创建用户',
    createDate bigint not null default 0 comment '创建时间',
    primary key(id),
    unique key(name)
)   comment '文章标签';

-- 文章标签关联表
create table Article_Label(
    articleId bigint not null comment '文章id',
    labelId bigint not null comment '标签id',
    primary key(articleId,labelId)
)   comment '文章标签关联表';

-- 文章阅读记录
create table ArticleReadLog(
    id bigint not null auto_increment comment '主键',
    articleId bigint not null default 0 comment '文章id',
    uid bigint not null default 0 comment '用户id',
    createDate bigint not null default 0 comment '创建时间',
    ip varchar(32) comment 'ip',
    primary key(id)
)   comment '文章阅读记录';

-- 操作记录
create table ActionLog(
    id bigint not null auto_increment comment '主键',
    uid bigint not null default 0 comment '用户id',
    acType int not null default 0 comment '操作类型',
    title varchar(50) comment '标题',
    detail varchar(255) comment '详情',
    createDate bigint not null default 0 comment '操作时间',
    ip varchar(32) comment 'ip',
    primary key(id)
)   comment '用户操作记录';
)






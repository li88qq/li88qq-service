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
    lastLoginDate bigint not null default 0 comment '上一次登录时间',
    lastLoginIp varchar(32) comment '上一次登录ip',
    loginDate bigint not null default 0 comment '本次登录时间',
    loginIp varchar(32) comment '本次登录ip',
    primary key(id),
    unique key(username)
)   comment '用户表';

-- 用户登录记录
create table LoginLog(
    id bigint not null auto_increment comment '主键',
    uid bigint not null default 0 comment '用户id',
    state int not null default 0 comment '登录状态,1-正常',
    remark varchar(255) comment '备注',
    loginType int not null default 0 comment '登录方式',
    createDate bigint not null default 0 comment '登录时间',
    loginIp varchar(32) comment '登录ip',
    updateDate bigint not null default 0 comment '登出时间',
    primary key(id)
)   comment '用户登录记录';

-- 文章表
create table Article(
    id bigint not null auto_increment comment '主键',
    uid bigint not null default 0 comment '用户id',
    title varchar(100) comment '标题',
    state int not null default 0 comment '状态',
    delState int not null default 0 comment '删除状态,0-不删除,1-删除',
    open int not null default 0 comment '是否公开,1-公开',
    original int not null default 0 comment '是否原创,1-原创',
    quote varchar(255) comment '引用地址',
    labels varchar(255) comment '标签列表',
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
    content text CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' comment '内容',
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

-- 异常记录
create table ErrorLog(
    id bigint not null auto_increment comment '主键',
    requestUri varchar(100) comment '请求接口',
    exName varchar(100) comment '异常类型',
    msg varchar(255) comment '错误信息',
    uid bigint not null default 0 comment '用户id',
    ip varchar(32) comment 'ip',
    createDate bigint not null default 0 comment '创建时间',
    primary key(id)
)   comment '异常记录';

-- 导航
create table Navigation(
    id bigint not null auto_increment comment '主键',
    uid bigint not null default 0 comment '用户id',
    typeId bigint not null default 0 comment '分类id',
    name varchar(20) comment '名称',
    url varchar(255) comment 'url',
    remark varchar(255) comment '备注',
    logo varchar(100) comment '图标',
    createDate bigint not null default 0 comment '操作时间',
    clickCount int not null default 0 comment '点击次数',
    primary key(id)
)   comment '导航';

-- 导航分类
create table NavigationType(
    id bigint not null auto_increment comment '主键',
    uid bigint not null default 0 comment '用户id',
    name varchar(20) comment '名称',
    sort int not null default 0 comment '排序',
    createDate bigint not null default 0 comment '操作时间',
    primary key(id)
)   comment '导航分类';

-- todo标签
create table ToDoLabel(
    id bigint not null auto_increment comment '主键',
    uid bigint not null default 0 comment '用户id',
    name varchar(20) comment '名称',
    sort int not null default 0 comment '排序',
    deleteState int not null default 0 comment '删除标志,0-正常,1-删除',
    createDate bigint not null default 0 comment '创建时间',
    updateDate bigint not null default 0 comment '更新时间',
    primary key(id)
)   comment 'todo标签';

-- todo表
create table ToDo(
    id bigint not null auto_increment comment '主键',
    uid bigint not null default 0 comment '用户id',
    labelId bigint not null default 0 comment '标签id',
    content varchar(255) comment '内容',
    state int not null default 0 comment '状态,0-新建,1-已完成',
    sort int not null default 0 comment '排序,重要级别,倒序,',
    beginDate bigint not null default 0 comment '创建时间',
    endDate bigint not null default 0 comment '计划结束时间',
    finishDate bigint not null default 0 comment '完成时间',
    createDate bigint not null default 0 comment '创建时间',
    updateDate bigint not null default 0 comment '更新时间',
    primary key(id)
)   comment 'todo表';

-- menu表
create table Menu(
    id bigint not null auto_increment comment '主键',
    name varchar(20) comment '名称',
    parentId bigint not null default 0 comment '上级id',
    href varchar(100) comment '路径',
    icon varchar(32) comment '图标',
    grade int not null default 0 comment '级别',
    sort int not null default 0 comment '排序,升序',
    state int not null default 0 comment '状态,0-新建,1-启用,2-关闭',
    createDate bigint not null default 0 comment '创建时间',
    updateDate bigint not null default 0 comment '更新时间',
    primary key(id)
)   comment '菜单表';




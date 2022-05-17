-- User:用户表
create table `User`(
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

-- LoginLog:登录记录
create table LoginLog(
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

-- ActionLog:操作记录
create table ActionLog(
  id bigint not null auto_increment comment '自增长主键',
  uid bigint not null default 0 comment '用户id',
  actionType int not null default 0 comment '操作类型',
  title varchar(50) comment '标题',
  remark varchar(255) comment '描述',
  createDate bigint not null default 0 comment '操作时间',
  ip varchar(50) comment 'ip',
  primary key(id),
  index UID_INDEX(uid)
) comment '操作记录';

-- ErrorLog:异常记录
create table ErrorLog(
  id bigint not null auto_increment comment '自增长主键',
  uid bigint not null default 0 comment '用户id',
  errorType varchar(50) comment '异常类名称',
  message varchar(255) comment '异常信息',
  api varchar(255) comment '请求接口',
  createDate bigint not null default 0 comment '发生时间',
  ip varchar(50) comment 'ip',
  primary key(id),
  index UID_INDEX(uid)
) comment '异常记录';

-- Menu:菜单
create table Menu(
  id int not null auto_increment comment '自增长主键',
  `name` varchar(10) comment '名称',
  parentId int not null default 0 comment '父节点id',
  parentIds varchar(50) comment '父节点id列表,多个使用,隔开',
  icon varchar(25) comment '图标',
  url varchar(30) comment '路径',
  router varchar(30) comment '路由',
  sort int not null default 0 comment '排序',
  `state` int not null default 0 comment '状态',
  createDate bigint not null default 0 comment '创建时间',
  updateDate bigint not null default 0 comment '更新时间',
  primary key(id)
) comment '菜单';

-- Article:文章
create table Article(
  id bigint not null auto_increment comment '自增长主键',
  uid bigint not null default 0 comment '用户id',
  title varchar(100) comment '标题',
  img varchar(100) comment '背景图',
  `open` int not null default 0 comment '是否公开',
  original int not null default 0 comment '是否原创',
  transport varchar(255) comment '转载地址',
  createDate bigint not null default 0 comment '创建时间',
  updateDate bigint not null default 0 comment '更新时间',
  articleNo varchar(32) comment '文章编号,格式:年月日+id截取后4位',
  rno varchar(8) comment '随机码,8位',
  wordCount int not null default 0 comment '文章字数',
  viewCount int not null default 0 comment '阅读次数',
  primary key(id)
) comment '文章';

-- ArticleDoc:文章内容
create table ArticleDoc(
  id bigint not null comment '文章id',
  doc text comment '文章内容',
  originalDoc text comment '原内容',
  updateDate bigint not null default 0 comment '更新时间',
  primary key(id)
) comment '文章内容';

-- ArticleLabel:文章标签
create table ArticleLabel(
  id bigint not null comment '文章id',
  `name` varchar(20) comment '标签名称',
  createDate bigint not null default 0 comment '创建时间',
  createUid bigint not null default 0 comment '创建人',
  primary key(id)
) comment '文章标签';

-- Article_Label:文章和标签关联表
create table Article_Label(
  labelId bigint not null comment '标签id',
  articleId bigint not null comment '文章id',
  primary key(labelId,articleId)
) comment '文章和标签关联表';

-- Navigation:导航
create table Navigation(
  id bigint not null auto_increment comment '自增长主键',
  uid bigint not null default 0 comment '用户id',
  `name` varchar(20) comment '名称',
  url varchar(255) comment '链接',
  icon varchar(100) comment '图标',
  remark varchar(255) comment '备注',
  sort int not null default 0 comment '排序',
  createDate bigint not null default 0 comment '创建时间',
  updateDate bigint not null default 0 comment '更新时间',
  clickCount int not null default 0 comment '点击次数',
  primary key(id)
) comment '导航';

-- NavigationType:导航类型
create table NavigationType(
  id bigint not null auto_increment comment '自增长主键',
  uid bigint not null default 0 comment '用户id',
  `name` varchar(20) comment '名称',
  icon varchar(100) comment '图标',
  remark varchar(255) comment '备注',
  sort int not null default 0 comment '排序',
  createDate bigint not null default 0 comment '创建时间',
  updateDate bigint not null default 0 comment '更新时间',
  primary key(id)
) comment '导航类型';

-- Article_Label:导航分类关系
create table Navigation_Type(
  typeId bigint not null comment '分类id',
  navId bigint not null comment '导航id',
  primary key(typeId,navId)
) comment '导航分类关系';

-- Image:图片
create table Image(
  id bigint not null auto_increment comment '自增长主键',
  uid bigint not null default 0 comment '用户id',
  `path` varchar(32) comment '保存路径',
  imageType int not null default 0 comment '类型',
  `name` varchar(32) comment '名称',
  remark varchar(100) comment '备注',
  createDate bigint not null default 0 comment '创建时间',
  primary key(id)
) comment '图片表';




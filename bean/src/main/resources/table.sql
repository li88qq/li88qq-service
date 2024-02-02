create table `User`
(
    id         int         not null auto_increment comment '自增主键',
    username   varchar(32) not null comment '用户名,唯一',
    nickname   varchar(32) comment '昵称',
    mobile     varchar(11) comment '手机号码',
    email      varchar(32) comment '邮箱地址',
    avatar     varchar(100) comment '头像地址',
    roleId     int         not null default 0 comment '角色id',
    state      int         not null default 0 comment '状态',
    `password` varchar(64) comment '密码',
    createDate bigint      not null default 0 comment '创建时间',
    createIp   varchar(32) comment '创建ip',
    updateDate bigint      not null default 0 comment '更新时间',
    updateIp   varchar(32) comment '更新ip',
    primary key (id)
) comment '用户表';

create table `Role`
(
    id        int not null auto_increment comment '自增主键',
    `name`    varchar(32) comment '名称',
    isAllMenu int not null default 0 comment '是否授权全部菜单,1-是',
    isRoot    int not null default 0 comment '是否root',
    primary key (id)
) comment '角色表';

create table `Menu`
(
    id         int    not null auto_increment comment '自增主键',
    parentId   int    not null default 0 comment '上级id',
    parentIds  varchar(64) comment '上级节点id列表,逗号隔开',
    `name`     varchar(32) comment '菜单名称',
    href       varchar(255) comment '路径',
    icon       varchar(32) comment '图标',
    openType   int    not null default 0 comment '打开方式,0-直接打开,1-新窗口打开',
    keepalive  int    not null default 0 comment '是否保持页面,0-否,1-是',
    isDefault  int    not null default 0 comment '是否默认菜单,0-否,1-是',
    sort       int    not null default 0 comment '排序,升序',
    createDate bigint not null default 0 comment '创建时间',
    updateDate bigint not null default 0 comment '更新时间',
    primary key (id)
) comment '菜单表';

create table `Role_Menu`
(
    roleId int not null comment '角色id',
    menuId int not null comment '菜单id',
    primary key (roleId, menuId)
) comment '角色授权菜单表';

create table `Action`
(
    id     int not null auto_increment comment '自增主键',
    `name` varchar(32) comment '菜单名称',
    `code` varchar(64) comment '编码,格式:类前缀:动作,如:User:Save',
    `type` int not null default 0 comment '类型',
    primary key (id)
) comment '动作表';

create table `Menu_Action`
(
    menuId   int not null comment '菜单id',
    actionId int not null comment '动作id',
    primary key (menuId, actionId)
) comment '菜单动作关联表';

create table `AppConfig`
(
    id         int    not null auto_increment comment '自增主键',
    appCode    varchar(32) comment '应用编码,唯一值',
    appName    varchar(64) comment '应用名称',
    domain     varchar(100) comment '域名',
    ico        varchar(100) comment '小图标',
    logo       varchar(100) comment 'logo',
    beiAnIco   varchar(100) comment '备案图标',
    beiAn      varchar(255) comment '备案信息',
    beiAnLink  varchar(255) comment '备案链接',
    icp        varchar(32) comment 'icp',
    icpLink    varchar(100) comment 'icp链接',
    copyRight  varchar(64) comment '版权声明',
    createDate bigint not null default 0 comment '创建时间',
    updateDate bigint not null default 0 comment '更新时间',
    primary key (id)
) comment '系统配置';

create table `ActionLog`
(
    id         bigint not null auto_increment comment '自增主键',
    uid        int    not null default 0 comment '用户id',
    `type`     int    not null default 0 comment '操作类型',
    title      varchar(100) comment '标题',
    detail     varchar(1000) comment '详情',
    coreData   varchar(1000) comment '关键数据',
    createDate bigint not null default 0 comment '创建时间',
    ip         varchar(32) comment 'ip',
    primary key (id)
) comment '操作记录';

create table `LoginLog`
(
    id         bigint not null auto_increment comment '自增主键',
    uid        int    not null default 0 comment '用户id',
    state      int    not null default 0 comment '登录状态',
    errorState int    not null default 0 comment '异常状态',
    createDate bigint not null default 0 comment '登录时间',
    ip         varchar(32) comment '登录ip',
    logoutDate bigint not null default 0 comment '登出时间',
    primary key (id)
) comment '登录记录';
create table Menu(
    id int not null auto_increment comment '自增主键',
    parentId int not null default 0 comment '上级id',
    parentIds varchar(255) comment '上级节点id列表,逗号隔开',
    name varchar(32) comment '菜单名称',
    href varchar(255) comment '路径',
    icon varchar(32) comment '图标',
    openType int not null default 0 comment '打开方式,0-直接打开,1-新窗口打开',
    sort int not null default 0 comment '排序,升序',
    createDate bigint not null default 0 comment '创建时间',
    updateDate bigint not null default 0 comment '更新时间',
    primary key(id)
) comment '菜单表';

create table AppConfig(
    id int not null auto_increment comment '自增主键',
    domain varchar(32) comment '域名',
    appName varchar(32) comment '应用名称',
    ico varchar(100) comment '小图标',
    logo varchar(100) comment 'logo',
    beiAnIco varchar(100) comment '备案图标',
    beiAn varchar(100) comment '备案',
    beiAnLink varchar(100) comment '备案链接',
    icp varchar(100) comment 'icp',
    icpLink varchar(100) comment 'icp链接',
    copyRight varchar(100) comment '版权声明',
    createDate bigint not null default 0 comment '创建时间',
    updateDate bigint not null default 0 comment '更新时间',
    primary key(id),
    unique (domain)
) comment '应用配置表';
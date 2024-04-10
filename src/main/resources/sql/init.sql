-- 添加注册账号
insert into `User` (id, username, nickname, `password`, createDate)
values (1, 'root', 'root', '$2b$10$OyNgFr3cNEXsoN.TOkRMZ.F01x6cRqQBBWWgyLt5gG6XsgBFzDJZu', unix_timestamp());

-- 添加系统超级用户
insert into ROLE (id, `name`, isAllMenu, isRoot)
values (1, '系统超级用户', 1, 1);

-- 修改注册账号角色id
update `User`
set roleId = 1
where id = 1;

-- 添加菜单
INSERT INTO `Menu` (id, parentId, parentIds, `name`, href, icon, openType, isDefault, sort, createDate)
VALUES (1, 0, '', '我的', '', 'fa-cog', 0, 1, 1, unix_timestamp()),
       (2, 1, '1', '主页', '/my/home', '', 0, 1, 1, unix_timestamp()),
       (3, 1, '1', '工作台', '/my/workbench', '', 1, 0, 2, unix_timestamp()),
       (4, 1, '1', '看板', '/my/dashboard', '', 0, 1, 3, unix_timestamp()),
       (5, 1, '1', '个人设置', '/my/setting', '', 0, 1, 4, unix_timestamp()),
       (6, 0, '1', '系统管理', '', 'fa-cog', 0, 0, 2, UNIX_TIMESTAMP()),
       (7, 6, '6', '角色管理', '/system/role', '', 0, 0, 1, UNIX_TIMESTAMP()),
       (8, 6, '6', '用户管理', '/system/user', '', 0, 0, 2, UNIX_TIMESTAMP()),
       (9, 6, '6', '菜单管理', '/system/menu', '', 0, 0, 3, UNIX_TIMESTAMP());
-- 授权
INSERT INTO Role_Menu
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9);


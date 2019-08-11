CREATE TABLE `user` (
   `id` varchar(32) NOT NULL COMMENT '用户编号',
   `create_time` datetime NOT NULL COMMENT '新增时间',
   `update_time` datetime NOT NULL COMMENT '修改时间',
   `version` int(11) NOT NULL COMMENT '版本号',
   `name` varchar(32) NOT NULL COMMENT '用戶姓名',
   `mail` varchar(32) DEFAULT NULL COMMENT '邮箱',
   `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
   `password` varchar(32) NOT NULL COMMENT '密码',
   `salt` varchar(32) NOT NULL COMMENT '盐',
   `department_id` int(11) NOT NULL COMMENT '部门编号',
   `state` smallint(6) DEFAULT NULL COMMENT '状态 1:正常 2:禁用',
   `delete_state` smallint(6) NOT NULL COMMENT '是否删除 0:正常 1:删除',
   PRIMARY KEY (`id`),
   UNIQUE INDEX (`id`),
   UNIQUE INDEX (`name`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `role` (
    `id` varchar(32) NOT NULL COMMENT '角色编号',
    `create_time` datetime NOT NULL COMMENT '新增时间',
    `update_time` datetime NOT NULL COMMENT '修改时间',
    `version` int(11) NOT NULL COMMENT '版本号',
    `name` varchar(32) NOT NULL COMMENT '角色名称',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    UNIQUE INDEX (`id`),
    UNIQUE INDEX (`name`)
);

CREATE TABLE `menu` (
    `id` varchar(32) NOT NULL COMMENT '菜单编号',
    `create_time` datetime NOT NULL COMMENT '新增时间',
    `update_time` datetime NOT NULL COMMENT '修改时间',
    `version` int(11) NOT NULL COMMENT '版本号',
    `name` varchar(32) NOT NULL COMMENT '菜单名称',
    `url` varchar(512) DEFAULT NULL COMMENT '路径',
    `perms` varchar(512) DEFAULT NULL COMMENT '权限',
    `type` smallint(6) NOT NULL COMMENT '菜单类型 1:菜单 2:按钮',
    `icon` varchar(255) DEFAULT NULL COMMENT '图标',
    PRIMARY KEY (`id`),
    UNIQUE INDEX (`id`),
    UNIQUE INDEX (`name`)
);

CREATE TABLE `department` (
    `id` varchar(32) NOT NULL COMMENT '部门编号',
    `create_time` datetime NOT NULL COMMENT '新增时间',
    `update_time` datetime NOT NULL COMMENT '修改时间',
    `version` int(11) NOT NULL COMMENT '版本号',
    `name` varchar(32) NOT NULL COMMENT '部门名称',
    `parent_id` varchar(32) DEFAULT NULL COMMENT '父部门编号',
    PRIMARY KEY (`id`),
    UNIQUE INDEX (`id`),
    UNIQUE INDEX (`name`)
);

CREATE TABLE `user_role_relate` (
    `id` varchar(32) NOT NULL COMMENT '用户角色编号',
    `create_time` datetime NOT NULL COMMENT '新增时间',
    `update_time` datetime NOT NULL COMMENT '修改时间',
    `version` int(11) NOT NULL COMMENT '版本号',
    `role_id` varchar(32) NOT NULL COMMENT '角色编号',
    `user_id` varchar(32) NOT NULL COMMENT '用户编号',
    PRIMARY KEY (`id`),
    UNIQUE INDEX (`role_id`),
    UNIQUE INDEX (`user_id`)
);

CREATE TABLE `role_menu_relate` (
    `id` varchar(32) NOT NULL COMMENT '角色菜单关联编号',
    `create_time` datetime NOT NULL COMMENT '新增时间',
    `update_time` datetime NOT NULL COMMENT '修改时间',
    `version` int(11) NOT NULL COMMENT '版本号',
    `role_id` varchar(32) NOT NULL COMMENT '角色编号',
    `menu_id` varchar(32) NOT NULL COMMENT '菜单编号',
    PRIMARY KEY (`id`),
    UNIQUE INDEX (`role_id`),
    UNIQUE INDEX (`menu_id`)
);
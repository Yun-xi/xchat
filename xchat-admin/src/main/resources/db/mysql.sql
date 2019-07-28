CREATE TABLE `user` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `create_time` datetime NOT NULL COMMENT '新增时间',
   `update_time` datetime NOT NULL COMMENT '修改时间',
   `department_id` int(11) NOT NULL COMMENT '部门编号',
   `mail` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
   `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用戶姓名',
   `password` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
   `phone` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
   `salt` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '盐',
   `state` smallint(6) DEFAULT NULL COMMENT '状态 1:正常 2:禁用',
   `time` datetime DEFAULT NULL,
   PRIMARY KEY (`id`),
   UNIQUE INDEX (`name`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
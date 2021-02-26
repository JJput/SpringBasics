/*
 Navicat Premium Data Transfer

 Source Server         : localhost MySQL
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : youde

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 21/02/2021 13:46:06
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`
(
    `id`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '编号',
    `created_by`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '创建人',
    `created_time` datetime                                               NOT NULL COMMENT '创建时间',
    `update_by`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin           DEFAULT NULL COMMENT '更新人',
    `update_time`  datetime                                                        DEFAULT NULL COMMENT '更新时间',
    `dele`         varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   NOT NULL DEFAULT '1' COMMENT '删除',
    `remake`       varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '备注',
    `spare1`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin           DEFAULT NULL COMMENT '备用1',
    `value`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '数据值',
    `label`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '标签名',
    `type`         varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型',
    `description`  varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '描述',
    `sort`         int                                                    NOT NULL COMMENT '排序（升序）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='字典表 ';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT 'id',
                        `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
                        `created_time` datetime NOT NULL COMMENT '创建时间',
                        `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        `dele` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '删除',
                        `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
                        `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '手机号',
                        `pwd` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户';


SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_resource
-- ----------------------------
DROP TABLE IF EXISTS `user_resource`;
CREATE TABLE `user_resource`
(
    `id`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL DEFAULT '' COMMENT 'id',
    `created_by`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '创建人',
    `created_time` datetime                                               NOT NULL COMMENT '创建时间',
    `update_by`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin           DEFAULT NULL COMMENT '更新人',
    `update_time`  datetime                                                        DEFAULT NULL COMMENT '更新时间',
    `dele`         varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin            DEFAULT NULL COMMENT '删除',
    `name`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称|菜单或按钮',
    `page`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin           DEFAULT NULL COMMENT '页面|路由',
    `request`      varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '请求|接口',
    `parent`       char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin               DEFAULT NULL COMMENT '父id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='资源';

-- ----------------------------
-- Records of user_resource
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL DEFAULT '' COMMENT 'id',
    `created_by`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '创建人',
    `created_time` datetime                                               NOT NULL COMMENT '创建时间',
    `update_by`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin           DEFAULT NULL COMMENT '更新人',
    `update_time`  datetime                                                        DEFAULT NULL COMMENT '更新时间',
    `dele`         varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin            DEFAULT NULL COMMENT '删除',
    `name`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '角色',
    `desc`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '描述',
    `company_id`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '公司自定义角色',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色';

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `user_role_resource`;
CREATE TABLE `user_role_resource`
(
    `id`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT 'id',
    `created_by`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
    `created_time` datetime                                              NOT NULL COMMENT '创建时间',
    `update_by`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '更新人',
    `update_time`  datetime                                                       DEFAULT NULL COMMENT '更新时间',
    `dele`         varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin           DEFAULT NULL COMMENT '删除',
    `role_id`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色|id',
    `resource_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '资源|id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色资源关联';

-- ----------------------------
-- Records of user_role_resource
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_role_user
-- ----------------------------
DROP TABLE IF EXISTS `user_role_user`;
CREATE TABLE `user_role_user`
(
    `id`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT 'id',
    `created_by`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
    `created_time` datetime                                              NOT NULL COMMENT '创建时间',
    `update_by`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '更新人',
    `update_time`  datetime                                                       DEFAULT NULL COMMENT '更新时间',
    `dele`         varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin           DEFAULT NULL COMMENT '删除',
    `role_id`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色|id',
    `user_id`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户|id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色用户关联';

-- ----------------------------
-- Records of user_role_user
-- ----------------------------
BEGIN;
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;

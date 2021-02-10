/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云MySQL-财务
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 118.24.29.122:3306
 Source Schema         : finance

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 02/11/2020 15:33:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for 字典
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `created_by` varchar(32) NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `dele` varchar(1) NOT NULL DEFAULT '1' COMMENT '删除',
  `remake` varchar(512) DEFAULT NULL COMMENT '备注',
  `spare1` varchar(64) DEFAULT NULL COMMENT '备用1',
  `id` varchar(32) NOT NULL COMMENT '编号',
  `value` varchar(128) NOT NULL COMMENT '数据值',
  `label` varchar(128) NOT NULL COMMENT '标签名',
  `type` varchar(128) NOT NULL COMMENT '类型',
  `description` varchar(128) NOT NULL COMMENT '描述',
  `sort` int NOT NULL COMMENT '排序（升序）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表 ';
-- ----------------------------
-- Table structure for user_resource
-- ----------------------------
DROP TABLE IF EXISTS `user_resource`;
CREATE TABLE `user_resource` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT 'id',
  `name` varchar(100) NOT NULL COMMENT '名称|菜单或按钮',
  `page` varchar(50) DEFAULT NULL COMMENT '页面|路由',
  `request` varchar(200) DEFAULT NULL COMMENT '请求|接口',
  `parent` char(6) DEFAULT NULL COMMENT '父id',
  `created_by` varchar(32) NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `dele` varchar(1) DEFAULT NULL COMMENT '删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源';

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '角色',
  `desc` varchar(100) NOT NULL COMMENT '描述',
  `company_id` varchar(32) NOT NULL COMMENT '公司自定义角色',
  `created_by` varchar(32) NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `dele` varchar(1) DEFAULT NULL COMMENT '删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Table structure for user_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `user_role_resource`;
CREATE TABLE `user_role_resource` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT 'id',
  `role_id` varchar(32) NOT NULL COMMENT '角色|id',
  `resource_id` varchar(32) NOT NULL COMMENT '资源|id',
  `created_by` varchar(32) NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `dele` varchar(1) DEFAULT NULL COMMENT '删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色资源关联';

-- ----------------------------
-- Table structure for user_role_user
-- ----------------------------
DROP TABLE IF EXISTS `user_role_user`;
CREATE TABLE `user_role_user` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT 'id',
  `role_id` varchar(32) NOT NULL COMMENT '角色|id',
  `user_id` varchar(32) NOT NULL COMMENT '用户|id',
  `created_by` varchar(32) NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `dele` varchar(1) DEFAULT NULL COMMENT '删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色用户关联';

SET FOREIGN_KEY_CHECKS = 1;

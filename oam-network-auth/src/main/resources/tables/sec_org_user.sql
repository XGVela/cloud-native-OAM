/*
 Navicat Premium Data Transfer

 Source Server         : 111.111.63.136
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 111.111.63.136:30036
 Source Schema         : omc

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 17/08/2022 11:39:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sec_org_user
-- ----------------------------
DROP TABLE IF EXISTS `sec_org_user`;
CREATE TABLE `sec_org_user`  (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '组织ID',
  `org_code` bigint(64) NULL DEFAULT NULL COMMENT '组织编码',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组织名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '组织-人员-关联' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

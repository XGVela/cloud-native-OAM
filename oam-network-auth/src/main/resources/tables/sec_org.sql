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

 Date: 17/08/2022 11:39:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sec_org
-- ----------------------------
DROP TABLE IF EXISTS `sec_org`;
CREATE TABLE `sec_org`  (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` bigint(64) NULL DEFAULT NULL COMMENT '编码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型：ROOT、PORV、CITY、DEPT',
  `is_leaf` tinyint(1) NULL DEFAULT NULL COMMENT '是否叶子节点',
  `pid` int(64) NULL DEFAULT NULL COMMENT '父ID',
  `pcode` int(64) NULL DEFAULT NULL COMMENT '父编码',
  `pname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '父名称',
  `order_by` int(11) NULL DEFAULT 999 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '组织' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

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

 Date: 17/08/2022 11:46:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for omc_strategy_conf
-- ----------------------------
DROP TABLE IF EXISTS `omc_strategy_conf`;
CREATE TABLE `omc_strategy_conf`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '策略类型',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '策略名称',
  `value` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '策略值',
  `flag` tinyint(4) NULL DEFAULT 0 COMMENT '标识 | 1 开启',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '策略配置表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

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

 Date: 17/08/2022 11:47:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mepm_user_validity
-- ----------------------------
DROP TABLE IF EXISTS `mepm_user_validity`;
CREATE TABLE `mepm_user_validity`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `user_validity` datetime NULL DEFAULT NULL COMMENT '用户有效期',
  `user_ps_validity` datetime NULL DEFAULT NULL COMMENT '用户密码有效期',
  `user_ps_update` datetime NULL DEFAULT NULL COMMENT '用户密码创建或更新日期',
  `login_time` datetime NULL DEFAULT NULL COMMENT '登录时间',
  `login_error_count` int(11) NULL DEFAULT NULL COMMENT '登录错误次数',
  `login_error_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '登录错误时间',
  `unit` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
  `time_range` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '时间范围',
  `access_error_count` int(11) NULL DEFAULT NULL COMMENT '连续错误登录次数',
  `access_error_time` datetime NULL DEFAULT NULL COMMENT '延迟登录时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

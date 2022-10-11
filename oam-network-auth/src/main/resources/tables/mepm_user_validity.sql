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
  `user_id` bigint(20) NOT NULL COMMENT 'user_id',
  `user_validity` datetime NULL DEFAULT NULL COMMENT 'user_validity',
  `user_ps_validity` datetime NULL DEFAULT NULL COMMENT 'user_ps_validity',
  `user_ps_update` datetime NULL DEFAULT NULL COMMENT 'user_ps_update',
  `login_time` datetime NULL DEFAULT NULL COMMENT 'login_time',
  `login_error_count` int(11) NULL DEFAULT NULL COMMENT 'login_error_count',
  `login_error_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'login_error_time',
  `unit` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'unit',
  `time_range` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'time_range',
  `access_error_count` int(11) NULL DEFAULT NULL COMMENT 'access_error_count',
  `access_error_time` datetime NULL DEFAULT NULL COMMENT 'access_error_time',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

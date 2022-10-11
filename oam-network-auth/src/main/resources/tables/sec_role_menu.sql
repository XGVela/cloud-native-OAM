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

 Date: 17/08/2022 11:40:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sec_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sec_role_menu`;
CREATE TABLE `sec_role_menu`  (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

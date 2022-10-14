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

 Date: 17/08/2022 11:40:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sec_users
-- ----------------------------
DROP TABLE IF EXISTS `sec_users`;
CREATE TABLE `sec_users`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_login` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `user_pass` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `user_nicename` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `user_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `user_phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户所关联的projectid',
  `user_registered` datetime NULL DEFAULT NULL,
  `user_activation_key` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `user_status` int(11) NOT NULL DEFAULT 0,
  `user_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_first_login` tinyint(1) NULL DEFAULT NULL COMMENT '首次登陆 |默认为true',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_login_key`(`user_login`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Triggers structure for table sec_users
-- ----------------------------
DROP TRIGGER IF EXISTS `insert_users_copy1_copy1`;
delimiter ;;
CREATE TRIGGER `insert_users_copy1_copy1` AFTER INSERT ON `sec_users` FOR EACH ROW insert into sec_user_role(user_id,role_id) values(NEW.id,'2')
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sec_users
-- ----------------------------
DROP TRIGGER IF EXISTS `delete_users_copy1_copy1`;
delimiter ;;
CREATE TRIGGER `delete_users_copy1_copy1` AFTER DELETE ON `sec_users` FOR EACH ROW delete from sec_user_role where user_id=old.id
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;

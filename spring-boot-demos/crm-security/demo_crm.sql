/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_192.168.200.66
 Source Server Type    : MySQL
 Source Server Version : 50634
 Source Host           : 192.168.200.66:3306
 Source Schema         : demo_crm

 Target Server Type    : MySQL
 Target Server Version : 50634
 File Encoding         : 65001

 Date: 27/03/2019 14:10:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES ('1', 'system', NULL, 'glyphicon glyphicon-cog', 'system manage', NULL, 10);
INSERT INTO `system_menu` VALUES ('308de144762f4320a8a80263756d9441', 'menu', '/system/menu/list/1', NULL, 'menu', '1', 13);
INSERT INTO `system_menu` VALUES ('cbebe6d8e97f4974abf42e7a46c03f3e', 'user', '/system/user/list/1', NULL, 'user', '1', 11);
INSERT INTO `system_menu` VALUES ('d337f43f1faa490387742f62c4629702', 'role', '/system/role/list/1', NULL, 'role', '1', 12);
INSERT INTO `system_menu` VALUES ('f4534218c5ae496385d0cf5e003225ce', 'operate', '/system/operate/list/1', NULL, 'operate', '1', 14);

-- ----------------------------
-- Table structure for system_operate
-- ----------------------------
DROP TABLE IF EXISTS `system_operate`;
CREATE TABLE `system_operate`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_operate
-- ----------------------------
INSERT INTO `system_operate` VALUES ('096fa19ff95c4f97b5e4cbbe9084e0a4', '/system/operate/save', NULL, 'save', '4', 135);
INSERT INTO `system_operate` VALUES ('0b0b6f34846f4674a68138166cbe1b05', '/system/role/update', NULL, 'update', '2', 116);
INSERT INTO `system_operate` VALUES ('1', NULL, NULL, 'user power', NULL, 100);
INSERT INTO `system_operate` VALUES ('17d6d8f8dedb48c5ac8c1262c940b800', '/system/role/eidt', NULL, 'edit', '2', 113);
INSERT INTO `system_operate` VALUES ('1d84024ea3334d5cb8deab97fb8d2531', '/system/menu/save', NULL, 'save', '3', 125);
INSERT INTO `system_operate` VALUES ('2', NULL, NULL, 'role power', NULL, 110);
INSERT INTO `system_operate` VALUES ('2add365b783d430a965ba815b2da26b3', '/system/menu/update', NULL, 'update', '3', 126);
INSERT INTO `system_operate` VALUES ('3', NULL, NULL, 'menu power', NULL, 120);
INSERT INTO `system_operate` VALUES ('3d94fb1b7d824477b4e284ec38e49fd1', '/system/menu/eidt', NULL, 'edit', '3', 123);
INSERT INTO `system_operate` VALUES ('3fc79001dd12478da12380f9978014af', '/system/operate/add', NULL, 'add', '4', 131);
INSERT INTO `system_operate` VALUES ('4', NULL, NULL, 'operate power', NULL, 130);
INSERT INTO `system_operate` VALUES ('440ecbba9e1e4e98a474908679e00e57', '/system/user/delete', NULL, 'delete', '1', 102);
INSERT INTO `system_operate` VALUES ('4f75e19690934509b0c2ca922106e4f5', '/system/operate/update', NULL, 'update', '4', 136);
INSERT INTO `system_operate` VALUES ('53f872fb358947da99ab9b2479976dea', '/system/role/delete', NULL, 'delete', '2', 112);
INSERT INTO `system_operate` VALUES ('569d731a4e994f4696e42d6f167156ce', '/system/role/add', NULL, 'add', '2', 111);
INSERT INTO `system_operate` VALUES ('778b6038de0d4fd08e9c9a188afa0c53', '/system/user/update', NULL, 'update', '1', 106);
INSERT INTO `system_operate` VALUES ('7cc316a92c764f968184d923d9759cfc', '/system/user/list', NULL, 'list', '1', 104);
INSERT INTO `system_operate` VALUES ('8fedb5e4de6c47ff945d6ff4602a0adc', '/system/menu/list', NULL, 'list', '3', 124);
INSERT INTO `system_operate` VALUES ('a78c69892e14471b8bf7602c1342752d', '/system/menu/delete', NULL, 'delete', '3', 122);
INSERT INTO `system_operate` VALUES ('ab7bdfee1df34982be6edf45b0d2bb8c', '/system/operate/list', NULL, 'list', '4', 134);
INSERT INTO `system_operate` VALUES ('b4ee65828c9a4666b188b8517503582a', '/system/user/eidt', NULL, 'edit', '1', 103);
INSERT INTO `system_operate` VALUES ('bd29f9f5c05f4c198dff3fe468a1d14e', '/system/user/save', NULL, 'save', '1', 105);
INSERT INTO `system_operate` VALUES ('c8b4ac9319204d5ca36aa95cadfc6d93', '/system/menu/add', NULL, 'add', '3', 121);
INSERT INTO `system_operate` VALUES ('cd665b15397945ef90eb86eecd9663b8', '/system/operate/eidt', NULL, 'edit', '4', 133);
INSERT INTO `system_operate` VALUES ('d303e9f86e4144acbc9d61aba6f3365f', '/system/role/save', NULL, 'save', '2', 115);
INSERT INTO `system_operate` VALUES ('dee44655097d411d9c6ac2f50a2a0796', '/system/role/list', NULL, 'list', '2', 114);
INSERT INTO `system_operate` VALUES ('e72850117a3946d8adecc8addeaadf43', '/system/operate/delete', NULL, 'delete', '4', 132);
INSERT INTO `system_operate` VALUES ('ede6d3e474b3461c894b7464b56f3319', '/system/user/add', NULL, 'add', '1', 101);

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('86d95ae060014fc6956f6c4006daf713', 'SUPER', '超级管理员');
INSERT INTO `system_role` VALUES ('f9aa40d58b074d2ab21788006143e23b', 'TEST', '测试员');

-- ----------------------------
-- Table structure for system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu`;
CREATE TABLE `system_role_menu`  (
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`menu_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_role_menu
-- ----------------------------
INSERT INTO `system_role_menu` VALUES ('1', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_menu` VALUES ('1', 'f9aa40d58b074d2ab21788006143e23b');
INSERT INTO `system_role_menu` VALUES ('308de144762f4320a8a80263756d9441', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_menu` VALUES ('cbebe6d8e97f4974abf42e7a46c03f3e', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_menu` VALUES ('cbebe6d8e97f4974abf42e7a46c03f3e', 'f9aa40d58b074d2ab21788006143e23b');
INSERT INTO `system_role_menu` VALUES ('d337f43f1faa490387742f62c4629702', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_menu` VALUES ('f4534218c5ae496385d0cf5e003225ce', '86d95ae060014fc6956f6c4006daf713');

-- ----------------------------
-- Table structure for system_role_operate
-- ----------------------------
DROP TABLE IF EXISTS `system_role_operate`;
CREATE TABLE `system_role_operate`  (
  `operate_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`operate_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_role_operate
-- ----------------------------
INSERT INTO `system_role_operate` VALUES ('096fa19ff95c4f97b5e4cbbe9084e0a4', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('0b0b6f34846f4674a68138166cbe1b05', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('17d6d8f8dedb48c5ac8c1262c940b800', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('1d84024ea3334d5cb8deab97fb8d2531', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('2add365b783d430a965ba815b2da26b3', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('328888148db2491ab9a9f2041d25ba18', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('3d94fb1b7d824477b4e284ec38e49fd1', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('3fc79001dd12478da12380f9978014af', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('440ecbba9e1e4e98a474908679e00e57', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('440ecbba9e1e4e98a474908679e00e57', 'f9aa40d58b074d2ab21788006143e23b');
INSERT INTO `system_role_operate` VALUES ('4f75e19690934509b0c2ca922106e4f5', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('53f872fb358947da99ab9b2479976dea', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('569d731a4e994f4696e42d6f167156ce', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('778b6038de0d4fd08e9c9a188afa0c53', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('778b6038de0d4fd08e9c9a188afa0c53', 'f9aa40d58b074d2ab21788006143e23b');
INSERT INTO `system_role_operate` VALUES ('7cc316a92c764f968184d923d9759cfc', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('7cc316a92c764f968184d923d9759cfc', 'f9aa40d58b074d2ab21788006143e23b');
INSERT INTO `system_role_operate` VALUES ('8fedb5e4de6c47ff945d6ff4602a0adc', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('a08a9c51da414cdcb727425fdc0a4949', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('a78c69892e14471b8bf7602c1342752d', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('ab7bdfee1df34982be6edf45b0d2bb8c', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('b4ee65828c9a4666b188b8517503582a', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('b4ee65828c9a4666b188b8517503582a', 'f9aa40d58b074d2ab21788006143e23b');
INSERT INTO `system_role_operate` VALUES ('bd29f9f5c05f4c198dff3fe468a1d14e', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('bd29f9f5c05f4c198dff3fe468a1d14e', 'f9aa40d58b074d2ab21788006143e23b');
INSERT INTO `system_role_operate` VALUES ('c7fab1befdfe45a995fb4c43b81c74bb', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('c8b4ac9319204d5ca36aa95cadfc6d93', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('cd665b15397945ef90eb86eecd9663b8', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('d303e9f86e4144acbc9d61aba6f3365f', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('d8f354d1c7f34a6fbab88d981fb77406', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('dee44655097d411d9c6ac2f50a2a0796', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('e72850117a3946d8adecc8addeaadf43', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('ede6d3e474b3461c894b7464b56f3319', '86d95ae060014fc6956f6c4006daf713');
INSERT INTO `system_role_operate` VALUES ('ede6d3e474b3461c894b7464b56f3319', 'f9aa40d58b074d2ab21788006143e23b');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('6f32b5c37b9b4cf3ae2e6f4beff337dd', 'test@qq.com', 'test', '$2a$10$TV0IXtBUfNmlOnacPT.pA.RQk/kmqgk6.CruSdNocX7f2kth2BNDu', 'https://zk-dconsole-bucket-1-1257511866.cos.ap-guangzhou.myqcloud.com/1548496072195timg.jpg', 'f9aa40d58b074d2ab21788006143e23b');
INSERT INTO `system_user` VALUES ('88736aa63c4d4a4c8da92cffd60e6f08', 'super@bsp.com', 'super', '$2a$10$ad66BP3NEFaEBAlYEaCq1eYEKxEzMevNGGUquQ/ifuHfZQ9qOCag.', 'https://zk-dconsole-bucket-1-1257511866.cos.ap-guangzhou.myqcloud.com/1548496081782timg.jpg', '86d95ae060014fc6956f6c4006daf713');

SET FOREIGN_KEY_CHECKS = 1;

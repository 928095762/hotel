/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : hotel

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 13/04/2020 10:01:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hotel_guest
-- ----------------------------
DROP TABLE IF EXISTS `hotel_guest`;
CREATE TABLE `hotel_guest`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宾客姓名',
  `phone_num` bigint(20) NOT NULL COMMENT '电话号码',
  `ID_num` bigint(20) NOT NULL COMMENT '身份证号码',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `nation` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '宾客表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel_guest
-- ----------------------------
INSERT INTO `hotel_guest` VALUES (18, '张三', 13592241245, 130627199411031877, '928164895@qq.com', '北京市xx区', '汉', '贵宾', '2020-04-13 09:38:41', '超级管理员');
INSERT INTO `hotel_guest` VALUES (19, '李四', 17720158890, 130627199411031866, '924954168@qq.com', '天津市xx区', '汉', '无', '2020-04-13 09:39:22', '超级管理员');
INSERT INTO `hotel_guest` VALUES (20, '王小五', 13266948754, 0, NULL, NULL, NULL, NULL, '2020-04-13 09:40:01', 'admin');

-- ----------------------------
-- Table structure for hotel_room
-- ----------------------------
DROP TABLE IF EXISTS `hotel_room`;
CREATE TABLE `hotel_room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房间号',
  `type` int(11) NOT NULL COMMENT '房间类型。1：单人间；2：标准双人间；3：三人间；4：大床房；',
  `status` int(11) NOT NULL COMMENT '状态。0：禁用；1：空闲；2：有预定；3：有人；',
  `price` decimal(32, 2) NOT NULL COMMENT '价格',
  `remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '房间表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel_room
-- ----------------------------
INSERT INTO `hotel_room` VALUES (7, '1-110', 1, 1, 100.00, '无', '2020-04-13 09:36:47', '超级管理员');
INSERT INTO `hotel_room` VALUES (8, '1-120', 2, 1, 210.00, '', '2020-04-13 09:56:47', '超级管理员');
INSERT INTO `hotel_room` VALUES (9, '1-130', 3, 3, 160.00, '0', '2020-04-13 09:37:19', '超级管理员');
INSERT INTO `hotel_room` VALUES (10, '1-140', 4, 2, 500.00, '无', '2020-04-13 09:48:15', '超级管理员');

-- ----------------------------
-- Table structure for reserve_room
-- ----------------------------
DROP TABLE IF EXISTS `reserve_room`;
CREATE TABLE `reserve_room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_id` int(11) NULL DEFAULT NULL COMMENT '房间id',
  `room_type` int(11) NOT NULL COMMENT '房间类型',
  `room_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间号',
  `guest_id` int(11) NOT NULL COMMENT '宾客id',
  `guest_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宾客姓名',
  `phonenum` bigint(20) NOT NULL COMMENT '宾客电话',
  `start_time` datetime(0) NOT NULL COMMENT '预订住房时间段',
  `end_time` datetime(0) NOT NULL COMMENT '预订住房时间段',
  `pay` decimal(32, 2) NOT NULL DEFAULT 0.00 COMMENT '预付款',
  `status` int(11) NOT NULL COMMENT '状态。0：未入住；2：已入住；3：退订；',
  `remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客房预订表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reserve_room
-- ----------------------------
INSERT INTO `reserve_room` VALUES (16, 8, 2, '1-120', 20, '王小五', 13266948754, '2020-04-13 09:39:46', '2020-04-24 15:05:05', 0.00, 2, '无', '超级管理员', '2020-04-13 09:40:46');
INSERT INTO `reserve_room` VALUES (17, 10, 4, '1-140', 18, '张三', 13592241245, '2020-04-15 00:00:00', '2020-04-21 04:05:00', 100.00, 0, '无', '超级管理员', '2020-04-13 09:48:15');

-- ----------------------------
-- Table structure for room_logs
-- ----------------------------
DROP TABLE IF EXISTS `room_logs`;
CREATE TABLE `room_logs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房间id',
  `room_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间号',
  `room_type` int(11) NULL DEFAULT NULL COMMENT '房间类型',
  `guest_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宾客id',
  `guest_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宾客姓名',
  `phonenum` bigint(20) NULL DEFAULT NULL COMMENT '宾客电话',
  `start_time` datetime(0) NOT NULL COMMENT '入住时间',
  `end_time` datetime(0) NOT NULL COMMENT '退房时间',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态。1：住房中；2：已退房；',
  `pay` decimal(32, 2) NULL DEFAULT 0.00 COMMENT '预付款',
  `price` decimal(32, 2) NULL DEFAULT NULL COMMENT '应付款',
  `price_status` int(11) NULL DEFAULT NULL COMMENT '付款状态。0：未付款；1：已结清；',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '入住登记表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_logs
-- ----------------------------
INSERT INTO `room_logs` VALUES (5, '8', '1-120', 2, '20', '王小五', 13266948754, '2020-04-13 09:39:46', '2020-04-24 15:05:05', 2, 0.00, 2310.00, 1, '2020-04-13 09:56:47', '超级管理员');
INSERT INTO `room_logs` VALUES (6, '9', '1-130', 3, '19', '李四', 17720158890, '2020-04-22 00:00:00', '2020-04-30 00:00:00', 1, 0.00, 1280.00, 0, '2020-04-13 09:56:24', '超级管理员');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role_flag` int(11) NOT NULL COMMENT '角色标记。1：管理员；2：员工；',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '超级管理员', 'admin', '123456', 0, '2020-04-05 09:08:49', 'admin');
INSERT INTO `sys_user` VALUES (5, '小红 ', 'xiaohong', '123456', 1, '2020-04-13 09:32:38', '超级管理员');
INSERT INTO `sys_user` VALUES (6, '小明', 'xiaoming', '123456', 2, '2020-04-13 09:35:06', '超级管理员');
INSERT INTO `sys_user` VALUES (7, '小丁', 'xiaoding', '123456', 2, '2020-04-13 09:35:56', '超级管理员');

SET FOREIGN_KEY_CHECKS = 1;

/*
Navicat MySQL Data Transfer

Source Server         : MYsql
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : epidemic

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2022-05-01 10:55:16
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `class_info`
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级编号',
  `class_name` varchar(255) NOT NULL COMMENT '班级名',
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of class_info
-- ----------------------------

-- ----------------------------
-- Table structure for `health_info`
-- ----------------------------
DROP TABLE IF EXISTS `health_info`;
CREATE TABLE `health_info` (
  `health_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `user_id` int(11) NOT NULL COMMENT '填写人id',
  `health_state` tinyint(4) NOT NULL COMMENT '0健康1发烧咳嗽2其他',
  `vaccine_num` tinyint(4) NOT NULL COMMENT '接种疫苗数',
  `temperature` float NOT NULL COMMENT '体温',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `location` varchar(255) NOT NULL COMMENT '当前地址',
  `route` text NOT NULL COMMENT '今日行程',
  PRIMARY KEY (`health_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of health_info
-- ----------------------------

-- ----------------------------
-- Table structure for `inform_info`
-- ----------------------------
DROP TABLE IF EXISTS `inform_info`;
CREATE TABLE `inform_info` (
  `inform_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NOT NULL COMMENT '班级编号',
  `user_id` int(11) NOT NULL COMMENT '发布者',
  `inform_title` varchar(255) NOT NULL COMMENT '通知标题',
  `inform_content` text NOT NULL COMMENT '通知内容',
  `release_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`inform_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of inform_info
-- ----------------------------

-- ----------------------------
-- Table structure for `leave_info`
-- ----------------------------
DROP TABLE IF EXISTS `leave_info`;
CREATE TABLE `leave_info` (
  `leave_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '请假人的id',
  `leave_reason` varchar(255) NOT NULL COMMENT '请假理由',
  `leave_type` varchar(255) NOT NULL COMMENT '请假类型(外出 / 病假)',
  `start_time` datetime NOT NULL COMMENT '请假开始时间',
  `end_time` datetime NOT NULL COMMENT '请假结束时间',
  `leave_state` tinyint(4) NOT NULL COMMENT '审批状态( 0未审批1审批不通过2审批通过 )',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `class_id` int(11) NOT NULL COMMENT '所在的班级',
  PRIMARY KEY (`leave_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of leave_info
-- ----------------------------

-- ----------------------------
-- Table structure for `report_info`
-- ----------------------------
DROP TABLE IF EXISTS `report_info`;
CREATE TABLE `report_info` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `user_id` int(11) NOT NULL COMMENT '人员id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '进出校时间',
  `report_state` tinyint(4) NOT NULL COMMENT '1进校0出校',
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of report_info
-- ----------------------------

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` varchar(20) NOT NULL COMMENT '学号或工号',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `user_pwd` varchar(255) NOT NULL COMMENT '密码',
  `user_sex` tinyint(4) NOT NULL DEFAULT '1' COMMENT '性别1男0女',
  `user_email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `user_address` varchar(255) DEFAULT NULL COMMENT '住址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `class_id` int(11) NOT NULL COMMENT '班级编号',
  `identity` tinyint(4) NOT NULL COMMENT '身份0管理1学生2教师',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_info
-- ----------------------------

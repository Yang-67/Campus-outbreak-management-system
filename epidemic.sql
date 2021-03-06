/*
Navicat MySQL Data Transfer

Source Server         : MYsql
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : epidemic

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2022-05-22 22:28:10
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `class_info`
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级编号',
  `class_name` varchar(255) NOT NULL COMMENT '班级名',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO class_info VALUES ('1', '信管1901', '1');
INSERT INTO class_info VALUES ('2', '信管1902', '1');

-- ----------------------------
-- Table structure for `health_info`
-- ----------------------------
DROP TABLE IF EXISTS `health_info`;
CREATE TABLE `health_info` (
  `health_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `user_id` varchar(20) NOT NULL COMMENT '填写人id',
  `health_state` tinyint(4) NOT NULL COMMENT '0健康1发烧咳嗽2其他',
  `vaccine_num` tinyint(4) NOT NULL COMMENT '接种疫苗数',
  `temperature` float NOT NULL COMMENT '体温',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `location` varchar(255) NOT NULL COMMENT '当前地址',
  `route` text NOT NULL COMMENT '今日行程',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`health_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of health_info
-- ----------------------------
INSERT INTO health_info VALUES ('1', '3191905130', '0', '3', '37.3', '2022-04-27 14:49:50', '福州', '学校', '1');
INSERT INTO health_info VALUES ('2', '3191905130', '0', '3', '37', '2022-04-28 15:08:45', '福州', '学校', '1');
INSERT INTO health_info VALUES ('3', '3191905130', '2', '2', '36', '2022-04-29 15:10:53', '闽侯', '在学校', '1');
INSERT INTO health_info VALUES ('4', '3191905130', '1', '1', '35.5', '2022-04-30 15:12:43', '上街', '福建', '1');
INSERT INTO health_info VALUES ('5', '3191905130', '2', '2', '33', '2022-05-01 15:59:27', '使得', '是', '1');
INSERT INTO health_info VALUES ('6', '3191905130', '0', '3', '35', '2022-05-02 20:19:46', '福建', '哈哈哈', '1');
INSERT INTO health_info VALUES ('7', '3191905130', '2', '0', '34', '2022-05-02 20:21:59', '闽侯', '上街', '1');
INSERT INTO health_info VALUES ('8', '3191905130', '0', '1', '34', '2022-05-04 14:20:26', '福建', '上街', '1');
INSERT INTO health_info VALUES ('9', '3191905124', '0', '3', '37.5', '2022-05-04 10:36:20', '福建工程学院', '学校', '1');

-- ----------------------------
-- Table structure for `inform_info`
-- ----------------------------
DROP TABLE IF EXISTS `inform_info`;
CREATE TABLE `inform_info` (
  `inform_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` varchar(11) DEFAULT NULL COMMENT '班级编号',
  `user_id` varchar(20) NOT NULL COMMENT '发布者',
  `inform_title` varchar(255) NOT NULL COMMENT '通知标题',
  `inform_content` text NOT NULL COMMENT '通知内容',
  `release_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`inform_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of inform_info
-- ----------------------------
INSERT INTO inform_info VALUES ('1', '1,', '1001', '这是个标题', '这是内容', '2022-05-01 20:54:20', '1');
INSERT INTO inform_info VALUES ('2', '1,', '1001', '这也是一个标题', '这也是一个内容', '2022-05-01 20:54:53', '1');
INSERT INTO inform_info VALUES ('3', '0,', '1001', '放假信息', '五一放假', '2022-05-02 23:00:32', '1');
INSERT INTO inform_info VALUES ('4', '2,', '1001', '核酸检测', '今天下午进行核酸检测', '2022-05-04 10:37:18', '1');
INSERT INTO inform_info VALUES ('5', '0,1,2,', '1001', '今天', '很好', '2022-05-04 11:50:34', '1');

-- ----------------------------
-- Table structure for `leave_info`
-- ----------------------------
DROP TABLE IF EXISTS `leave_info`;
CREATE TABLE `leave_info` (
  `leave_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL COMMENT '请假人的id',
  `leave_reason` varchar(255) NOT NULL COMMENT '请假理由',
  `leave_type` varchar(255) NOT NULL COMMENT '请假类型(外出 / 病假)',
  `start_time` datetime NOT NULL COMMENT '请假开始时间',
  `end_time` datetime NOT NULL COMMENT '请假结束时间',
  `leave_state` tinyint(4) NOT NULL COMMENT '审批状态( 0未审批1审批不通过2审批通过 )',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `class_id` int(11) NOT NULL COMMENT '所在的班级',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '1',
  `leave_no` varchar(255) DEFAULT NULL COMMENT '审核不通过理由',
  PRIMARY KEY (`leave_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of leave_info
-- ----------------------------
INSERT INTO leave_info VALUES ('1', '3191905130', '玩', '其它', '2022-05-03 20:35:20', '2022-05-04 00:00:00', '0', '2022-05-03 20:39:07', '1', '1', '');
INSERT INTO leave_info VALUES ('2', '3191905130', '吃饭', '外出', '2022-05-03 20:53:13', '2022-05-04 00:01:01', '1', '2022-05-03 20:53:29', '2', '1', null);
INSERT INTO leave_info VALUES ('3', '3191905130', '你猜', '其它', '2022-05-03 21:18:01', '2022-05-03 21:18:05', '2', '2022-05-03 21:18:10', '1', '1', null);
INSERT INTO leave_info VALUES ('4', '3191905124', '玩', '外出', '2022-05-07 10:45:17', '2022-05-07 10:46:27', '0', '2022-05-07 10:45:38', '2', '1', '');

-- ----------------------------
-- Table structure for `report_info`
-- ----------------------------
DROP TABLE IF EXISTS `report_info`;
CREATE TABLE `report_info` (
  `report_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `user_id` varchar(11) NOT NULL COMMENT '人员id',
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
  `class_id` varchar(11) DEFAULT NULL COMMENT '班级编号',
  `identity` tinyint(4) NOT NULL COMMENT '身份0管理1学生2教师',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO user_info VALUES ('1001', '老师', '$2a$10$UrSyZ/AGHYqBd6qFbmBF/.GuLUBVQo8h0G/Epknudo/mXi.GD20DW', '0', 'aaa@163.com', '福建省', '2022-05-01 20:53:31', 'http://localhost:8081/user/file/9ec94ed47e76486e927f8274803a4ddd.png', '1,2,', '2', '1');
INSERT INTO user_info VALUES ('3191905124', '同桌', '$2a$10$1juZDy.PGPUbNbpfpXg6.edV1IBasPhq4LADJ/uKbjEaWIMVtXuQO', '1', null, null, '2022-05-04 10:33:21', null, '2', '1', '1');
INSERT INTO user_info VALUES ('3191905130', '杨鹏飞', '$2a$10$8GGS7x4AawQa1UM/ipRJL.Z99AA/BoIsRd9cFq3C8yDaa8F5hAOui', '1', 'aaa@163.com', '宁夏', '2022-05-01 15:32:42', 'http://localhost:8081/user/file/61901b47512348df8d77f114670f9e74.png', '1', '1', '1');

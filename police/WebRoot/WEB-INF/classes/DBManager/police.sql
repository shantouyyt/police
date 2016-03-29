/*
Navicat MySQL Data Transfer

Source Server         : 3306
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : police

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2016-03-29 17:39:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accident
-- ----------------------------
DROP TABLE IF EXISTS `accident`;
CREATE TABLE `accident` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TrafficMode` varchar(100) DEFAULT NULL,
  `AccidentSite` varchar(200) DEFAULT NULL,
  `CreateDate` varchar(50) DEFAULT NULL,
  `Content` varchar(600) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `Tel` varchar(20) DEFAULT NULL,
  `Sex` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `AccidentNo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accident
-- ----------------------------

-- ----------------------------
-- Table structure for accidentapproval
-- ----------------------------
DROP TABLE IF EXISTS `accidentapproval`;
CREATE TABLE `accidentapproval` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) DEFAULT NULL,
  `AccidentNo` varchar(50) DEFAULT NULL,
  `Remark` varchar(400) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL,
  `LeaderRemark` varchar(400) DEFAULT NULL,
  `CreateDate` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accidentapproval
-- ----------------------------

-- ----------------------------
-- Table structure for accidentresponse
-- ----------------------------
DROP TABLE IF EXISTS `accidentresponse`;
CREATE TABLE `accidentresponse` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) DEFAULT NULL,
  `AccidentNo` varchar(50) DEFAULT NULL,
  `Remark` varchar(400) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL,
  `CreateDate` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accidentresponse
-- ----------------------------

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) DEFAULT NULL,
  `Sex` int(11) DEFAULT NULL,
  `Tel` varchar(20) DEFAULT NULL,
  `License` varchar(50) DEFAULT NULL,
  `LicenseExpire` varchar(50) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `CreateDate` varchar(50) DEFAULT NULL,
  `Remark` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of driver
-- ----------------------------
INSERT INTO `driver` VALUES ('1', '姓名', '1', '电话', '驾驶证', '2016-04-29 10:03:36', '地址', '2016-03-26 10:04:31', '备注');
INSERT INTO `driver` VALUES ('2', '11', '1', '222', '22', '2016-03-31 10:30:14', '2222', '2016-03-26 10:30:20', '222');
INSERT INTO `driver` VALUES ('3', 'sd', '1', 'sdf', 'fsdf', '2016-03-23 10:30:33', 'sdf', '2016-03-26 10:30:37', 'sdfsdf');
INSERT INTO `driver` VALUES ('4', 'sdf', '1', 'sdf', 'sdfsdf', '2016-03-30 10:30:41', 'sdf', '2016-03-26 10:30:45', 'dsfsdf');
INSERT INTO `driver` VALUES ('5', '修改姓名1', '1', '修改电话1', '修改驾驶证1', '2016-03-31 10:30:50', '修改地址1', '2016-03-26 10:30:55', '修改备注1');
INSERT INTO `driver` VALUES ('6', 'sdfs', '0', 'sdfsdf', 'sdfsdf', '2016-03-24 10:31:00', 'sdfsdf', '2016-03-26 10:31:07', 'sdfsdfsdf');
INSERT INTO `driver` VALUES ('7', 'sdfs', '1', 'sdf', 'dfsdf', '2016-03-23 10:31:11', 'sdf', '2016-03-26 10:31:16', 'sdfsdfsdf');

-- ----------------------------
-- Table structure for inpolice
-- ----------------------------
DROP TABLE IF EXISTS `inpolice`;
CREATE TABLE `inpolice` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Sex` int(11) DEFAULT NULL,
  `CreateDate` varchar(50) DEFAULT NULL,
  `Remark` varchar(400) DEFAULT NULL,
  `Tel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inpolice
-- ----------------------------
INSERT INTO `inpolice` VALUES ('2', '小李', '1', '2016-03-20 09:38:14', '喝酒', '1111111');
INSERT INTO `inpolice` VALUES ('3', '中文', '1', '1', '1收到', '1');
INSERT INTO `inpolice` VALUES ('5', '名字', '0', '2016-03-20 04:58:05', '备注vvv', '电话');
INSERT INTO `inpolice` VALUES ('6', 'fff', '0', '2016-03-20 05:00:01', 'eeeeeeeeee', 'eeeeee');
INSERT INTO `inpolice` VALUES ('7', 'sdf', '1', '2016-03-20 05:05:38', 'sdfds', 'sdf');
INSERT INTO `inpolice` VALUES ('8', 'aasd', '1', '2016-03-20 05:05:44', 'dasdasd', 'asdas');
INSERT INTO `inpolice` VALUES ('9', 'sdfs', '1', '2016-03-20 05:05:49', 'sdfsdfds', 'dfdsf');
INSERT INTO `inpolice` VALUES ('10', 'sdfsdf', '1', '2016-03-20 05:05:54', 'sdfsd', 'fsdfsdfsd');
INSERT INTO `inpolice` VALUES ('11', 'sfsdfs', '1', '2016-03-20 05:06:01', 'sdfsdf', 'dfsdf');
INSERT INTO `inpolice` VALUES ('30', 'f', '1', '2016-03-20 05:38:41', 'sdfsdf', 'sdf');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(30) DEFAULT NULL,
  `PassWord` varchar(100) DEFAULT NULL,
  `Sex` int(11) DEFAULT NULL,
  `UserType` int(11) DEFAULT NULL,
  `CreateDate` varchar(50) DEFAULT NULL,
  `No` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', '123123', '1', '3', '2016-03-17 18:40:28', null);
INSERT INTO `users` VALUES ('2', 'jy001', '123123', '1', '1', '2016-03-21 09:29:28', '1000002');
INSERT INTO `users` VALUES ('3', 'ld123', '123123', '1', '2', '2016-03-22 08:58:02', '2000003');

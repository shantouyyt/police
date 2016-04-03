/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : police

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2016-04-03 15:11:40
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accident
-- ----------------------------
INSERT INTO `accident` VALUES ('2', '摩托', '汕头', '2016-03-30 09:24:32', '事故内容', '3', '张三', '18600', '1', '2', null);
INSERT INTO `accident` VALUES ('3', '小汽车', '汕头', '2016-04-03 12:45:14', '汕头', '2', '张三', '186', '1', '2', null);
INSERT INTO `accident` VALUES ('4', '摩托', '潮州', '2016-04-03 12:46:00', '事故内容', '2', '张三', '176', '1', '2', null);

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
INSERT INTO `accidentapproval` VALUES ('1', '2', '2', '审批内容', '3', null, '2016-03-30 09:25:22');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accidentresponse
-- ----------------------------
INSERT INTO `accidentresponse` VALUES ('1', '2', '2', '定责内容xxx', '2', '2016-03-30 09:44:32');

-- ----------------------------
-- Table structure for analysis
-- ----------------------------
DROP TABLE IF EXISTS `analysis`;
CREATE TABLE `analysis` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Remark` varchar(400) DEFAULT NULL,
  `CreateDate` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of analysis
-- ----------------------------
INSERT INTO `analysis` VALUES ('2', '事故地点', '2016-04-03 03:03:49');

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
-- Table structure for evidence
-- ----------------------------
DROP TABLE IF EXISTS `evidence`;
CREATE TABLE `evidence` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AccidentNO` varchar(20) DEFAULT NULL,
  `SGDD` varchar(100) DEFAULT NULL,
  `TQ` varchar(50) DEFAULT NULL,
  `QSRS` varchar(20) DEFAULT NULL,
  `CreateDate` varchar(50) DEFAULT NULL,
  `SZRS` varchar(10) DEFAULT NULL,
  `ZJJJSS` varchar(50) DEFAULT NULL,
  `YJ1` varchar(400) DEFAULT NULL,
  `YJ2` varchar(400) DEFAULT NULL,
  `SWRS` varchar(10) DEFAULT NULL,
  `DLKD` varchar(20) DEFAULT NULL,
  `ZSRS` varchar(10) DEFAULT NULL,
  `JTFS` varchar(20) DEFAULT NULL,
  `BZ` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of evidence
-- ----------------------------
INSERT INTO `evidence` VALUES ('1', '31', '事故地点', '天气', '轻伤人数', '2016-04-02 16:50:42', '失踪人数', '直接经济损失', '事故原因1', '事故原因2', '死亡人数', '道路宽度', '重伤人数', '交通方式', '备注');

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
  `SGDD` varchar(100) DEFAULT NULL,
  `SWQK` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inpolice
-- ----------------------------
INSERT INTO `inpolice` VALUES ('2', '小李', '1', '2016-03-20 09:38:14', '喝酒', '1111111', null, null);
INSERT INTO `inpolice` VALUES ('3', '中文', '1', '1', '1收到', '1', null, null);
INSERT INTO `inpolice` VALUES ('5', '名字', '0', '2016-03-20 04:58:05', '备注vvv', '电话', null, null);
INSERT INTO `inpolice` VALUES ('6', 'fff', '0', '2016-03-20 05:00:01', 'eeeeeeeeee', 'eeeeee', null, null);
INSERT INTO `inpolice` VALUES ('7', 'sdf', '1', '2016-03-20 05:05:38', 'sdfds', 'sdf', null, null);
INSERT INTO `inpolice` VALUES ('8', 'aasd', '1', '2016-03-20 05:05:44', 'dasdasd', 'asdas', null, null);
INSERT INTO `inpolice` VALUES ('9', 'sdfs', '1', '2016-03-20 05:05:49', 'sdfsdfds', 'dfdsf', null, null);
INSERT INTO `inpolice` VALUES ('10', 'sdfsdf', '1', '2016-03-20 05:05:54', 'sdfsd', 'fsdfsdfsd', null, null);
INSERT INTO `inpolice` VALUES ('11', 'sfsdfs', '1', '2016-03-20 05:06:01', 'sdfsdf', 'dfsdf', null, null);
INSERT INTO `inpolice` VALUES ('30', 'f', '1', '2016-03-20 05:38:41', 'sdfsdf', 'sdf', null, null);
INSERT INTO `inpolice` VALUES ('31', '报警人姓名', '1', '2016-03-31 03:48:40', '备注xxxx', '报警人电话', '事故地点', '死亡情况');

-- ----------------------------
-- Table structure for outpolice
-- ----------------------------
DROP TABLE IF EXISTS `outpolice`;
CREATE TABLE `outpolice` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `InPoliceID` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `CreateDate` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of outpolice
-- ----------------------------
INSERT INTO `outpolice` VALUES ('5', '31', '4', '2016-04-02 08:15:40');
INSERT INTO `outpolice` VALUES ('6', '31', '2', '2016-04-02 08:15:40');

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
  `WHCD` varchar(50) DEFAULT NULL,
  `CSRQ` varchar(50) DEFAULT NULL,
  `JX` varchar(50) DEFAULT NULL,
  `ZW` varchar(400) DEFAULT NULL,
  `ZGZH` varchar(50) DEFAULT NULL,
  `CJNX` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', '123123', '1', '3', '2016-03-17 18:40:28', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('2', 'jy001', '123123', '1', '1', '2016-03-21 09:29:28', '1000002', '小学', '2016-03-31 17:28:38', '警员', '职务', '资格证号', '10');
INSERT INTO `users` VALUES ('3', 'ld123', '123123', '1', '2', '2016-03-22 08:58:02', '2000003', null, null, null, null, null, null);
INSERT INTO `users` VALUES ('4', 'jy002', '123123', '1', '1', '2016-04-02 08:15:15', '1000004', null, null, null, null, null, null);

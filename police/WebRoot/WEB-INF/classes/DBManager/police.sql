/*
Navicat MySQL Data Transfer

Source Server         : 3306
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : police

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2016-03-25 17:32:49
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

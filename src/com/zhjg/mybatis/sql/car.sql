/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2016-12-20 11:31:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `car`
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `brand` varchar(20) DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  `price` double(20,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES ('1', 'bmw', 'bmw523Li', '200000');
INSERT INTO `car` VALUES ('2', 'dddd', 'dddd', '5151515');
INSERT INTO `car` VALUES ('4', 'daa', 'dad', '78913');
INSERT INTO `car` VALUES ('8', 'asa', 'adf', '145');
INSERT INTO `car` VALUES ('9', 'add', 'dfa', '4454');

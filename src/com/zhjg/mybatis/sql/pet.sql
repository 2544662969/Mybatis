/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2016-12-20 11:31:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pet`
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `pid` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pet_pid` (`pid`),
  CONSTRAINT `fk_pet_pid` FOREIGN KEY (`pid`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES ('1', 'mimi', '1');
INSERT INTO `pet` VALUES ('2', 'qq', '1');
INSERT INTO `pet` VALUES ('3', 'lucky', '2');

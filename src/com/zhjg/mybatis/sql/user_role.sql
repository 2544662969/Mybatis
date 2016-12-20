/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2016-12-20 11:30:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userId` int(20) DEFAULT NULL,
  `roleId` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role_userid` (`userId`),
  KEY `fk_user_role_roleid` (`roleId`),
  CONSTRAINT `fk_user_role_roleid` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_userid` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');
INSERT INTO `user_role` VALUES ('3', '3', '3');
INSERT INTO `user_role` VALUES ('4', '1', '2');
INSERT INTO `user_role` VALUES ('5', '1', '3');
INSERT INTO `user_role` VALUES ('6', '2', '3');

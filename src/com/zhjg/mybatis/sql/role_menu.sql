/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2016-12-20 11:31:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `roleId` int(20) DEFAULT NULL,
  `menuId` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_menu_roleid` (`roleId`),
  KEY `fk_role_menu_menuid` (`menuId`),
  CONSTRAINT `fk_role_menu_menuid` FOREIGN KEY (`menuId`) REFERENCES `sys_menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_role_menu_roleid` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1', '1');
INSERT INTO `role_menu` VALUES ('2', '1', '2');
INSERT INTO `role_menu` VALUES ('3', '1', '3');
INSERT INTO `role_menu` VALUES ('4', '1', '4');
INSERT INTO `role_menu` VALUES ('5', '2', '1');
INSERT INTO `role_menu` VALUES ('6', '2', '3');
INSERT INTO `role_menu` VALUES ('7', '2', '4');
INSERT INTO `role_menu` VALUES ('8', '3', '1');
INSERT INTO `role_menu` VALUES ('9', '3', '4');

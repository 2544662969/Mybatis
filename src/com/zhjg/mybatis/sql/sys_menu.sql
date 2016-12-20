/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2016-12-20 11:30:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `pid` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sys_menu_pid` (`pid`),
  CONSTRAINT `fk_sys_menu_pid` FOREIGN KEY (`pid`) REFERENCES `sys_menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统菜单', null, null);
INSERT INTO `sys_menu` VALUES ('2', '权限管理', 'authManage', '1');
INSERT INTO `sys_menu` VALUES ('3', '用户管理', 'userManage', '1');
INSERT INTO `sys_menu` VALUES ('4', '系统设置', 'sysSetting', '1');

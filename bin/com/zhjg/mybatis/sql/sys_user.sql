/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 40021
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 40021
File Encoding         : 65001

Date: 2016-12-12 17:34:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(20) NOT NULL auto_increment,
  `name` varchar(20) NOT NULL default '',
  `age` int(10) NOT NULL default '0',
  `address` varchar(40) NOT NULL default '',
  PRIMARY KEY  (`id`)
) TYPE=MyISAM;

-- ----------------------------
-- Records of sys_user
-- ----------------------------

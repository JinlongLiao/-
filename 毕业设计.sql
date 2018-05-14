/*
Navicat MySQL Data Transfer

Source Server         : mysql7
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-05-14 19:34:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for navigation
-- ----------------------------
DROP TABLE IF EXISTS `navigation`;
CREATE TABLE `navigation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order` int(255) unsigned NOT NULL,
  `title` varchar(255) DEFAULT '',
  `context` varchar(255) DEFAULT '',
  `url` varchar(255) DEFAULT '#',
  `desc` varchar(255) DEFAULT '',
  `target` varchar(255) DEFAULT '_blank',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of navigation
-- ----------------------------
INSERT INTO `navigation` VALUES ('0', '0', '导航栏 ', '导航栏', '#', '导航栏不能修改', '');
INSERT INTO `navigation` VALUES ('1', '3', '微服务 ', '微服务', 'http://127.0.0', '微服务', '_self');
INSERT INTO `navigation` VALUES ('2', '2', '首页', '', 'http://127.0.0.1', '', '_self');
INSERT INTO `navigation` VALUES ('3', '4', '微服务介绍', '', 'http://127.0.0.1/infoService', '微服务介绍', '_self');
INSERT INTO `navigation` VALUES ('4', '5', '总结', '总结', 'http://127.0.0.1/last', null, '_self');
INSERT INTO `navigation` VALUES ('5', '6', '页面设置', '设置', 'http://127.0.0.1/GetAllNavs', '页面设置', '_self');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(40) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'account1', '张三', '20', '100.00');
INSERT INTO `user` VALUES ('2', 'account2', '李四', '28', '180.00');
INSERT INTO `user` VALUES ('3', 'account3', '王五', '32', '280.00');

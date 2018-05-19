/*
Navicat MySQL Data Transfer

Source Server         : mysql7
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-05-19 17:39:40
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
-- Table structure for tb_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_content`;
CREATE TABLE `tb_content` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '文章标题',
  `content` longtext,
  `html` longtext NOT NULL COMMENT 'html 内容',
  `addTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '添加时间',
  `updateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_main
-- ----------------------------
DROP TABLE IF EXISTS `tb_main`;
CREATE TABLE `tb_main` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `m_title` varchar(255) DEFAULT NULL COMMENT '巨幕标题',
  `m_content` varchar(255) DEFAULT NULL COMMENT '巨幕内容',
  `m_bg_img` varchar(255) DEFAULT NULL COMMENT '巨幕bg',
  `m_bt_title` varchar(255) DEFAULT NULL COMMENT '按钮名称',
  `m_bt_url` varchar(255) DEFAULT '' COMMENT '按钮地址',
  `m_bt_desc` varchar(255) DEFAULT '' COMMENT '按钮描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

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

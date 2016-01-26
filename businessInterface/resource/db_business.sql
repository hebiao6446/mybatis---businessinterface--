/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50615
 Source Host           : localhost
 Source Database       : db_business

 Target Server Type    : MySQL
 Target Server Version : 50615
 File Encoding         : utf-8

 Date: 01/26/2016 17:40:50 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tb_attend_descript`
-- ----------------------------
DROP TABLE IF EXISTS `tb_attend_descript`;
CREATE TABLE `tb_attend_descript` (
  `content_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(32) NOT NULL,
  `day_time` date DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` int(11) NOT NULL,
  PRIMARY KEY (`content_id`),
  KEY `fk_content_user_id` (`user_id`) USING BTREE,
  CONSTRAINT `tb_attend_descript_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_attendance`
-- ----------------------------
DROP TABLE IF EXISTS `tb_attendance`;
CREATE TABLE `tb_attendance` (
  `attendance_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(32) DEFAULT NULL,
  `in_sign_type` varchar(1) DEFAULT NULL,
  `in_time` time DEFAULT NULL,
  `out_sign_type` varchar(1) DEFAULT NULL,
  `out_time` time DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  `in_address` varchar(200) DEFAULT NULL,
  `out_address` varchar(200) DEFAULT NULL,
  `day_time` date DEFAULT NULL,
  `delete_flag` int(5) DEFAULT NULL,
  `in_lng` decimal(11,8) DEFAULT NULL,
  `in_lat` decimal(11,8) DEFAULT NULL,
  `out_lng` decimal(11,8) DEFAULT NULL,
  `out_lat` decimal(11,8) DEFAULT NULL,
  PRIMARY KEY (`attendance_id`),
  KEY `fk_attendance_userid` (`user_id`) USING BTREE,
  CONSTRAINT `tb_attendance_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_blessings`
-- ----------------------------
DROP TABLE IF EXISTS `tb_blessings`;
CREATE TABLE `tb_blessings` (
  `blessings_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `blessings_type_id` bigint(32) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`blessings_id`),
  KEY `fk_blessings_typeid` (`blessings_type_id`) USING BTREE,
  CONSTRAINT `tb_blessings_ibfk_1` FOREIGN KEY (`blessings_type_id`) REFERENCES `tb_blessings_type` (`blessings_type_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_blessings_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_blessings_type`;
CREATE TABLE `tb_blessings_type` (
  `blessings_type_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`blessings_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_company`
-- ----------------------------
DROP TABLE IF EXISTS `tb_company`;
CREATE TABLE `tb_company` (
  `company_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `introduce` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `logo` varchar(100) DEFAULT NULL,
  `report_work` time DEFAULT NULL COMMENT '上班时间',
  `out_work` time DEFAULT NULL COMMENT '下班时间',
  `update_time` datetime DEFAULT NULL,
  `delete_flag` int(11) NOT NULL,
  `status` int(10) DEFAULT NULL,
  `enable_time` date DEFAULT NULL,
  `disable_time` date DEFAULT NULL,
  `linkman` varchar(20) DEFAULT NULL,
  `is_location` int(1) DEFAULT '1' COMMENT '1是不用定位员工，2是必须定位员工',
  `is_gps` int(1) DEFAULT '2' COMMENT '1签到签退必须打开GPS，2是不需要打开GPS',
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_company_module`
-- ----------------------------
DROP TABLE IF EXISTS `tb_company_module`;
CREATE TABLE `tb_company_module` (
  `company_id` bigint(32) NOT NULL,
  `module_id` bigint(32) NOT NULL,
  `usable` varchar(1) DEFAULT NULL,
  `useful_time` date DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` int(11) NOT NULL,
  PRIMARY KEY (`company_id`,`module_id`),
  KEY `fk_company_module_module_id` (`module_id`) USING BTREE,
  CONSTRAINT `tb_company_module_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `tb_company` (`company_id`),
  CONSTRAINT `tb_company_module_ibfk_2` FOREIGN KEY (`module_id`) REFERENCES `tb_module` (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_contacts`
-- ----------------------------
DROP TABLE IF EXISTS `tb_contacts`;
CREATE TABLE `tb_contacts` (
  `contacts_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(32) DEFAULT NULL,
  `company` varchar(200) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `first_letter` varchar(5) DEFAULT NULL,
  `job` varchar(100) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `business` varchar(50) DEFAULT NULL,
  `qq` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` int(5) DEFAULT NULL,
  PRIMARY KEY (`contacts_id`),
  KEY `fk_contacts_gorupid` (`group_id`) USING BTREE,
  CONSTRAINT `tb_contacts_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `tb_group` (`group_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_group`
-- ----------------------------
DROP TABLE IF EXISTS `tb_group`;
CREATE TABLE `tb_group` (
  `group_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `user_id` bigint(32) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` int(11) NOT NULL,
  `is_default` varchar(1) DEFAULT NULL,
  `order_field` int(10) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  KEY `fk_group_user_id` (`user_id`) USING BTREE,
  CONSTRAINT `tb_group_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_module`
-- ----------------------------
DROP TABLE IF EXISTS `tb_module`;
CREATE TABLE `tb_module` (
  `module_id` bigint(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  `usable` varchar(1) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` int(11) NOT NULL,
  `order_list` int(11) DEFAULT NULL COMMENT '从小到大排序  默认为1 ',
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `tb_monitor`;
CREATE TABLE `tb_monitor` (
  `monitor_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `attendance_id` bigint(32) NOT NULL,
  `time` time DEFAULT NULL COMMENT 'HH:mm:ss',
  `lat` decimal(11,8) DEFAULT NULL,
  `lng` decimal(11,8) DEFAULT NULL,
  `day_time` date DEFAULT NULL,
  `delete_flag` int(11) NOT NULL,
  PRIMARY KEY (`monitor_id`),
  KEY `fk_monitor_attendancde_id` (`attendance_id`) USING BTREE,
  CONSTRAINT `tb_monitor_ibfk_1` FOREIGN KEY (`attendance_id`) REFERENCES `tb_attendance` (`attendance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_order`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `order_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(32) DEFAULT NULL,
  `user_id` bigint(32) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `product_name` varchar(200) DEFAULT NULL,
  `order_count` int(6) DEFAULT NULL,
  `total_price` decimal(18,2) DEFAULT NULL,
  `price` decimal(18,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `order_beizhu` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_order_productid` (`product_id`) USING BTREE,
  KEY `fk_order_userid` (`user_id`) USING BTREE,
  CONSTRAINT `tb_order_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`) ON DELETE CASCADE,
  CONSTRAINT `tb_order_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_product`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `product_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `product_category_id` bigint(32) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `price` decimal(18,2) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `delete_flag` int(5) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `fk_product_catid` (`product_category_id`) USING BTREE,
  CONSTRAINT `tb_product_ibfk_1` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category` (`product_category_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_product_category`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_category`;
CREATE TABLE `tb_product_category` (
  `product_category_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `parent_id` bigint(32) DEFAULT NULL,
  `company_id` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`product_category_id`),
  KEY `fk_product_companyid` (`company_id`) USING BTREE,
  CONSTRAINT `tb_product_category_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `tb_company` (`company_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_product_img`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_img`;
CREATE TABLE `tb_product_img` (
  `product_img_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(32) DEFAULT NULL,
  `file_path` varchar(600) DEFAULT NULL,
  `file_type` int(5) DEFAULT NULL,
  PRIMARY KEY (`product_img_id`),
  KEY `fk_product_imgid` (`product_id`) USING BTREE,
  CONSTRAINT `tb_product_img_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_project`
-- ----------------------------
DROP TABLE IF EXISTS `tb_project`;
CREATE TABLE `tb_project` (
  `project_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `contacts_id` bigint(32) DEFAULT NULL,
  `user_id` bigint(32) DEFAULT NULL,
  `source` varchar(100) DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  `total_price` decimal(18,2) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` int(5) DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  KEY `fk_project_userid` (`user_id`) USING BTREE,
  KEY `fk_project_conactid` (`contacts_id`) USING BTREE,
  CONSTRAINT `tb_project_ibfk_1` FOREIGN KEY (`contacts_id`) REFERENCES `tb_contacts` (`contacts_id`) ON DELETE CASCADE,
  CONSTRAINT `tb_project_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_project_visit`
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_visit`;
CREATE TABLE `tb_project_visit` (
  `project_visit_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `project_id` bigint(32) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `delete_flag` int(5) DEFAULT NULL,
  PRIMARY KEY (`project_visit_id`),
  KEY `fk_project_visit` (`project_id`) USING BTREE,
  CONSTRAINT `tb_project_visit_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `tb_project` (`project_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `role_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `parent_id` bigint(32) NOT NULL,
  `company_id` bigint(32) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` int(11) NOT NULL,
  PRIMARY KEY (`role_id`),
  KEY `fk_role_company_id` (`company_id`) USING BTREE,
  CONSTRAINT `tb_role_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `tb_company` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_sell`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sell`;
CREATE TABLE `tb_sell` (
  `sell_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `contacts_id` bigint(32) DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  `sell_method` varchar(100) DEFAULT NULL,
  `total_price` decimal(18,2) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` int(5) DEFAULT NULL,
  `user_id` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`sell_id`),
  KEY `fk_sell_contactsid` (`contacts_id`) USING BTREE,
  KEY `fk_sell_userid` (`user_id`) USING BTREE,
  CONSTRAINT `tb_sell_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `tb_sell_ibfk_2` FOREIGN KEY (`contacts_id`) REFERENCES `tb_contacts` (`contacts_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_sell_product`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sell_product`;
CREATE TABLE `tb_sell_product` (
  `sell_id` bigint(32) NOT NULL,
  `product_id` bigint(32) NOT NULL,
  `product_num` int(5) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` int(5) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`sell_id`,`product_id`),
  KEY `fk_sell_productid` (`product_id`) USING BTREE,
  CONSTRAINT `tb_sell_product_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`) ON DELETE CASCADE,
  CONSTRAINT `tb_sell_product_ibfk_2` FOREIGN KEY (`sell_id`) REFERENCES `tb_sell` (`sell_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_sell_visit`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sell_visit`;
CREATE TABLE `tb_sell_visit` (
  `sell_visit_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `sell_id` bigint(32) DEFAULT NULL,
  `visit_content` varchar(2000) DEFAULT NULL,
  `update_time` varchar(30) DEFAULT NULL,
  `delete_flag` int(5) DEFAULT NULL,
  PRIMARY KEY (`sell_visit_id`),
  KEY `fk_visit_sellid` (`sell_id`) USING BTREE,
  CONSTRAINT `tb_sell_visit_ibfk_1` FOREIGN KEY (`sell_id`) REFERENCES `tb_sell` (`sell_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_suggest`
-- ----------------------------
DROP TABLE IF EXISTS `tb_suggest`;
CREATE TABLE `tb_suggest` (
  `suggest_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `content` varchar(500) NOT NULL,
  `update_time` datetime NOT NULL,
  `delete_flag` int(11) NOT NULL,
  PRIMARY KEY (`suggest_id`),
  KEY `fk_suggest_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_things`
-- ----------------------------
DROP TABLE IF EXISTS `tb_things`;
CREATE TABLE `tb_things` (
  `things_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `is_emergency` varchar(1) NOT NULL,
  `period_type` int(11) NOT NULL,
  `create_user_id` bigint(20) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `delete_flag` int(11) NOT NULL,
  PRIMARY KEY (`things_id`),
  KEY `fk_things_user_id` (`create_user_id`) USING BTREE,
  CONSTRAINT `tb_things_ibfk_1` FOREIGN KEY (`create_user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_things_file`
-- ----------------------------
DROP TABLE IF EXISTS `tb_things_file`;
CREATE TABLE `tb_things_file` (
  `file_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `things_id` bigint(20) NOT NULL,
  `file_path` varchar(200) NOT NULL,
  `file_type` int(11) NOT NULL DEFAULT '1' COMMENT '1图片\r\n2音频\r\n3视频',
  PRIMARY KEY (`file_id`),
  KEY `fk_things_file_things_id` (`things_id`) USING BTREE,
  CONSTRAINT `tb_things_file_ibfk_1` FOREIGN KEY (`things_id`) REFERENCES `tb_things` (`things_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_things_finish`
-- ----------------------------
DROP TABLE IF EXISTS `tb_things_finish`;
CREATE TABLE `tb_things_finish` (
  `things_id` bigint(20) NOT NULL,
  `finish_user_id` bigint(20) NOT NULL,
  `is_finish` varchar(1) NOT NULL,
  `finish_time` datetime NOT NULL,
  `delete_flag` int(11) NOT NULL,
  PRIMARY KEY (`things_id`,`finish_user_id`),
  KEY `fk_things_finish_user_id` (`finish_user_id`) USING BTREE,
  CONSTRAINT `tb_things_finish_ibfk_1` FOREIGN KEY (`things_id`) REFERENCES `tb_things` (`things_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_things_finish_ibfk_2` FOREIGN KEY (`finish_user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account` varchar(20) DEFAULT NULL COMMENT '用户帐号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `name` varchar(20) NOT NULL COMMENT '真实姓名',
  `first_letter` varchar(10) NOT NULL COMMENT '真实姓名首字母',
  `phone` varchar(11) NOT NULL COMMENT '联系方式',
  `role_id` bigint(32) NOT NULL COMMENT '角色ID',
  `attendance` varchar(1) DEFAULT NULL COMMENT '是否考勤',
  `update_time` datetime DEFAULT NULL,
  `delete_flag` int(11) NOT NULL,
  `head_image` varchar(600) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uni_phone` (`phone`) USING BTREE,
  KEY `fk_user_role_id` (`role_id`) USING BTREE,
  CONSTRAINT `tb_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

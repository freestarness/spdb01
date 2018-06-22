/*
Date: 2018-06-22 09:44:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_code` varchar(30) NOT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `log_id` int(11) DEFAULT NULL,
  `open_date` int(11) DEFAULT NULL,
  `deleted` char(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of customer_info
-- ----------------------------
INSERT INTO `customer_info` VALUES ('1', '123', 'MA测试1', '4', '1', '3', '0', '2018-06-14 10:59:56', 'admin', '2018-06-14 10:59:58', 'admin');
INSERT INTO `customer_info` VALUES ('3', '789d d d', 'MA测试3', '3', '2', '3', '1', '2018-06-21 15:33:36', 'admin', '2018-06-22 09:33:39', 'mzh');
INSERT INTO `customer_info` VALUES ('4', '101', 'MA测试4', '2', '1', '0', '0', '2018-06-21 16:12:36', 'admin', '2018-06-21 16:12:36', 'admin');
INSERT INTO `customer_info` VALUES ('5', '120', 'MA测试5', '1', '1', '0', '0', '2018-06-21 16:13:49', 'admin', '2018-06-21 16:13:49', 'admin');
INSERT INTO `customer_info` VALUES ('6', '12345678', 'MA测试2', '3', '1', '3', '0', '2018-06-21 15:33:36', 'admin', '2018-06-21 18:18:34', 'admin');
INSERT INTO `customer_info` VALUES ('7', '213123', 'MA测试7', '1', '1', '0', '1', '2018-06-21 18:21:14', 'admin', '2018-06-21 18:21:14', 'admin');
INSERT INTO `customer_info` VALUES ('10', '1232141234', 'MA测试8', '1', '1', '0', '0', '2018-06-21 18:25:51', 'admin', '2018-06-21 18:25:51', 'admin');
INSERT INTO `customer_info` VALUES ('11', 'dfsdefe', 'MA测试9', '2', '1', '0', '0', '2018-06-21 18:27:56', 'admin', '2018-06-21 18:27:56', 'admin');

-- ----------------------------
-- Table structure for sys_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_token`;
CREATE TABLE `sys_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `access_token` varchar(200) DEFAULT NULL,
  `expiration` bigint(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `deleted` char(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_token
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `login_password` varchar(255) DEFAULT NULL,
  `deleted` char(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '0', '2018-06-20 10:35:58', '1', '2018-06-20 10:36:00', '1');
INSERT INTO `user_info` VALUES ('2', 'mzh', 'e10adc3949ba59abbe56e057f20f883e', '0', '2018-06-20 10:35:58', '1', '2018-06-20 10:35:58', '1');

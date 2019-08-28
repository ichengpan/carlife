/*
Navicat MySQL Data Transfer

Source Server         : 本地Cheng
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : cheng

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-07-05 14:39:47
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('4');
INSERT INTO `hibernate_sequence` VALUES ('4');
INSERT INTO `hibernate_sequence` VALUES ('4');

/*
Navicat MySQL Data Transfer

Source Server         : 本地Cheng
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : cheng

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-07-05 14:39:58
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `people`
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `id` bigint(8) NOT NULL DEFAULT '0',
  `name` varchar(8) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people
-- ----------------------------
/*
Navicat MySQL Data Transfer

Source Server         : 本地Cheng
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : cheng

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-07-05 14:40:10
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `available` bit(1) DEFAULT NULL COMMENT '是否可用',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父编号',
  `parent_ids` varchar(255) DEFAULT NULL COMMENT '父编号列表',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限字符串,menu例子：role:*，button例子：role:create,role:update;',
  `resource_type` enum('menu','button') DEFAULT NULL COMMENT '资源类型 枚举类[menu|button]',
  `url` varchar(255) DEFAULT NULL COMMENT '资源路径',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('2', '', '用户管理', '1', '0/1', 'userInfo:info', 'menu', 'userInfo/userInfo');
INSERT INTO `sys_permission` VALUES ('3', '', '用户添加', '2', '0/1/2', 'userInfo:add', 'button', 'userInfo/userAdd');
INSERT INTO `sys_permission` VALUES ('4', '', '用户删除', '2', '0/1/2', 'userInfo:del', 'button', 'userInfo/userDel');
INSERT INTO `sys_permission` VALUES ('6', '', '用户查询', '2', '0/1/2', 'userInfo:query', 'button', 'userInfo/userList');
INSERT INTO `sys_permission` VALUES ('1', '', '权限设置', '0', '0', 'permission:main', 'menu', null);
INSERT INTO `sys_permission` VALUES ('5', '', '用户修改', '2', '0/1/2', 'userInfo:edit', 'button', 'userInfo/userUpdate');
INSERT INTO `sys_permission` VALUES ('7', '', '角色管理', '1', '0/1', 'role:info', 'menu', 'role/roleInfo');
INSERT INTO `sys_permission` VALUES ('8', '', '角色添加', '7', '0/1/7', 'role:add', 'button', 'role/roleAdd');
INSERT INTO `sys_permission` VALUES ('9', '', '角色删除', '7', '0/1/7', 'role:del', 'button', 'role/roleDel');
INSERT INTO `sys_permission` VALUES ('10', '', '角色修改', '7', '0/1/7', 'role:edit', 'button', 'role/roleUpdate');
INSERT INTO `sys_permission` VALUES ('11', '', '角色查询', '7', '0/1/7', 'role:query', 'button', 'role/roleList');
INSERT INTO `sys_permission` VALUES ('12', '', '支付设置', 0, '0', 'pay:payinfo', 'menu', 'pay/payinfo');
INSERT INTO `sys_permission` VALUES ('13', '', '微信支付', 12, '0/12', 'pay:wechatpay', 'button', 'pay/wechatPay');
/*
Navicat MySQL Data Transfer

Source Server         : 本地Cheng
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : cheng

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-07-05 14:40:27
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `available` bit(1) DEFAULT NULL COMMENT '是否可用',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述,UI界面显示使用',
  `role` varchar(255) DEFAULT NULL COMMENT '角色标识程序中判断使用,如"admin",这个是唯一的:',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '', '管理员', 'admin');
INSERT INTO `sys_role` VALUES ('2', '', 'VIP会员', 'vip');

/*
Navicat MySQL Data Transfer

Source Server         : 本地Cheng
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : cheng

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-07-05 14:40:36
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  KEY `FKomxrs8a388bknvhjokh440waq` (`permission_id`),
  KEY `FK9q28ewrhntqeipl1t04kh1be7` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '5');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('1', '9');
INSERT INTO `sys_role_permission` VALUES ('1', '10');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '13');

/*
Navicat MySQL Data Transfer

Source Server         : 本地Cheng
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : cheng

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-07-05 14:40:44
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  KEY `FKgkmyslkrfeyn9ukmolvek8b8f` (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('3', '2');
/*
Navicat MySQL Data Transfer

Source Server         : 本地Cheng
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : cheng

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-07-05 14:40:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '加密密码的盐',
  `state` tinyint(4) NOT NULL COMMENT '用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `UK_f2ksd6h8hsjtd57ipfq9myr64` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '0', 'admin');
INSERT INTO `user_info` VALUES ('3', '测试用户', '63cfe12fdd9c3037df5a44665afd71c6', '02f053d477e244928c35f915b6204166', '1', 'cheng');


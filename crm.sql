/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-09-12 21:28:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_dict
-- ----------------------------
DROP TABLE IF EXISTS `base_dict`;
CREATE TABLE `base_dict` (
  `dict_id` varchar(32) NOT NULL COMMENT '数据字典id(主键)',
  `dict_type_code` varchar(10) NOT NULL COMMENT '数据字典类别代码',
  `dict_type_name` varchar(64) NOT NULL COMMENT '数据字典类别名称',
  `dict_item_name` varchar(64) NOT NULL COMMENT '数据字典项目名称',
  `dict_item_code` varchar(10) DEFAULT NULL COMMENT '数据字典项目(可为空)',
  `dict_sort` int(10) DEFAULT NULL COMMENT '排序字段',
  `dict_enable` char(1) NOT NULL COMMENT '1:使用 0:停用',
  `dict_memo` varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_dict
-- ----------------------------
INSERT INTO `base_dict` VALUES ('1', '001', '客户行业', '教育培训 ', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('10', '003', '公司性质', '民企', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('12', '004', '年营业额', '1-10万', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('13', '004', '年营业额', '10-20万', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('14', '004', '年营业额', '20-50万', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('15', '004', '年营业额', '50-100万', null, '4', '1', null);
INSERT INTO `base_dict` VALUES ('16', '004', '年营业额', '100-500万', null, '5', '1', null);
INSERT INTO `base_dict` VALUES ('17', '004', '年营业额', '500-1000万', null, '6', '1', null);
INSERT INTO `base_dict` VALUES ('18', '005', '客户状态', '基础客户', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('19', '005', '客户状态', '潜在客户', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('2', '001', '客户行业', '电子商务', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('20', '005', '客户状态', '成功客户', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('21', '005', '客户状态', '无效客户', null, '4', '1', null);
INSERT INTO `base_dict` VALUES ('22', '006', '客户级别', '普通客户', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('23', '006', '客户级别', 'VIP客户', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('24', '007', '商机状态', '意向客户', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('25', '007', '商机状态', '初步沟通', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('26', '007', '商机状态', '深度沟通', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('27', '007', '商机状态', '签订合同', null, '4', '1', null);
INSERT INTO `base_dict` VALUES ('3', '001', '客户行业', '对外贸易', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('30', '008', '商机类型', '新业务', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('31', '008', '商机类型', '现有业务', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('32', '009', '商机来源', '电话营销', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('33', '009', '商机来源', '网络营销', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('34', '009', '商机来源', '推广活动', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('4', '001', '客户行业', '酒店旅游', null, '4', '1', null);
INSERT INTO `base_dict` VALUES ('5', '001', '客户行业', '房地产', null, '5', '1', null);
INSERT INTO `base_dict` VALUES ('6', '002', '客户信息来源', '电话营销', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('7', '002', '客户信息来源', '网络营销', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('8', '003', '公司性质', '合资', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('9', '003', '公司性质', '国企', null, '2', '1', null);

-- ----------------------------
-- Table structure for cst_customer
-- ----------------------------
DROP TABLE IF EXISTS `cst_customer`;
CREATE TABLE `cst_customer` (
  `cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
  `cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
  `cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源',
  `cust_industry` varchar(32) DEFAULT NULL COMMENT '客户所属行业',
  `cust_level` varchar(32) DEFAULT NULL COMMENT '客户级别',
  `cust_linkman` varchar(64) DEFAULT NULL COMMENT '联系人',
  `cust_phone` varchar(64) DEFAULT NULL COMMENT '固定电话',
  `cust_mobile` varchar(16) DEFAULT NULL COMMENT '移动电话',
  PRIMARY KEY (`cust_id`),
  KEY `FK_dmms37dag8mnbawig0pf3q15y` (`cust_source`),
  KEY `FK_h2ealrc67sjqcjsbu0wylnhjw` (`cust_industry`),
  KEY `FK_opb53wlyo9k87n2xtuxkq2gt2` (`cust_level`),
  CONSTRAINT `FK_dmms37dag8mnbawig0pf3q15y` FOREIGN KEY (`cust_source`) REFERENCES `base_dict` (`dict_id`),
  CONSTRAINT `FK_h2ealrc67sjqcjsbu0wylnhjw` FOREIGN KEY (`cust_industry`) REFERENCES `base_dict` (`dict_id`),
  CONSTRAINT `FK_opb53wlyo9k87n2xtuxkq2gt2` FOREIGN KEY (`cust_level`) REFERENCES `base_dict` (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cst_customer
-- ----------------------------
INSERT INTO `cst_customer` VALUES ('8', '万达集团', '6', '5', '22', null, '12345678', '34567890');
INSERT INTO `cst_customer` VALUES ('12', '腾讯', '7', '2', '23', null, '13123212', '12312312');
INSERT INTO `cst_customer` VALUES ('17', 'alibaba', '7', '2', '23', null, '234234', '23423423');
INSERT INTO `cst_customer` VALUES ('19', '饿了么', '7', '2', '22', null, '234234', '2342342');
INSERT INTO `cst_customer` VALUES ('20', '碧桂园', '6', '5', '23', null, '2342323', '78976767');
INSERT INTO `cst_customer` VALUES ('21', '猿搜题', '6', '1', '22', null, '234234', '3453453');
INSERT INTO `cst_customer` VALUES ('22', '携程旅行', '7', '4', '23', null, '2353546', '68645434');

-- ----------------------------
-- Table structure for cst_linkman
-- ----------------------------
DROP TABLE IF EXISTS `cst_linkman`;
CREATE TABLE `cst_linkman` (
  `lkm_id` bigint(255) NOT NULL AUTO_INCREMENT,
  `lkm_gender` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lkm_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lkm_phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lkm_email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lkm_qq` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lkm_mobile` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lkm_memo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lkm_position` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lkm_cust_id` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`lkm_id`),
  KEY `FKh9yp1nql5227xxcopuxqx2e7q` (`lkm_cust_id`),
  CONSTRAINT `FKh9yp1nql5227xxcopuxqx2e7q` FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of cst_linkman
-- ----------------------------
INSERT INTO `cst_linkman` VALUES ('13', '男', 'Jack Ma', '2342342', null, null, '234234222', null, null, '17');
INSERT INTO `cst_linkman` VALUES ('15', '女', '马总', '123123', null, null, '1231231', null, null, '12');
INSERT INTO `cst_linkman` VALUES ('16', '女', '唐总', '34342342', null, null, '343223', null, null, '20');

-- ----------------------------
-- Table structure for sale_visit
-- ----------------------------
DROP TABLE IF EXISTS `sale_visit`;
CREATE TABLE `sale_visit` (
  `visit_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `visit_addr` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `visit_detail` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `visit_interviewee` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `visit_time` datetime DEFAULT NULL,
  `visit_nexttime` datetime DEFAULT NULL,
  `visit_cust_id` bigint(20) DEFAULT NULL,
  `visit_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`visit_id`),
  KEY `FK_l8sexnpf6jbqtdq2117595vgk` (`visit_cust_id`),
  KEY `FK_dqpiyhuo2gjkw2ej30yn55wss` (`visit_user_id`),
  CONSTRAINT `FK_dqpiyhuo2gjkw2ej30yn55wss` FOREIGN KEY (`visit_user_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `FK_l8sexnpf6jbqtdq2117595vgk` FOREIGN KEY (`visit_cust_id`) REFERENCES `cst_customer` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sale_visit
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_code` varchar(32) NOT NULL COMMENT '用户账号',
  `user_name` varchar(64) NOT NULL COMMENT '用户名称',
  `user_password` varchar(32) NOT NULL COMMENT '用户密码',
  `user_state` char(1) DEFAULT NULL COMMENT '1:正常,0:暂停',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('21', '1', '业务员1号', 'c4ca4238a0b923820dcc509a6f75849b', null);

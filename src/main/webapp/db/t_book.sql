/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50138
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50138
File Encoding         : 65001

Date: 2011-09-21 15:52:43
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `t_book`
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(255) NOT NULL,
  `bookauthor` varchar(255) NOT NULL,
  `bookprice` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO t_book VALUES ('1', '老人与海', '海明威', '66');
INSERT INTO t_book VALUES ('2', '三国演义', '罗贯中', '23.1');
INSERT INTO t_book VALUES ('3', '围城', '钱钟书', '81');
INSERT INTO t_book VALUES ('4', '红楼梦', '曹雪芹', '50');
INSERT INTO t_book VALUES ('5', '西游记', '吴承恩', '40');
INSERT INTO t_book VALUES ('6', '水浒传', '施耐庵', '56.7');
INSERT INTO t_book VALUES ('7', '基督山伯爵', '大仲马', '100');
INSERT INTO t_book VALUES ('8', '苏菲的世界', '乔斯坦·贾德 ', '40');
INSERT INTO t_book VALUES ('9', '麦田里的守望者', '塞林格', '56.7');
INSERT INTO t_book VALUES ('10', '呼啸山庄', '艾米莉·勃朗特 ', '43');
INSERT INTO t_book VALUES ('11', '巴黎圣母院', '维克多.雨果 ', '56.7');
INSERT INTO t_book VALUES ('12', '雷雨', '曹禺', '68');
INSERT INTO t_book VALUES ('13', '大海', '天蚕土豆', '99.9');
INSERT INTO t_book VALUES ('14', 'qq', 'qq', '12');
INSERT INTO t_book VALUES ('15', '明朝那些事', '当年明月', '358.2');
INSERT INTO t_book VALUES ('16', 'Think in Java', 'dreamsunday', '124');

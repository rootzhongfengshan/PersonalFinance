-- --------------------------------------------------------
-- 主机:                           47.104.108.36
-- 服务器版本:                        5.5.56-MariaDB - MariaDB Server
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 baby 的数据库结构
CREATE DATABASE IF NOT EXISTS `baby` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `baby`;


-- 导出  表 baby.bb_baby 结构
CREATE TABLE IF NOT EXISTS `bb_baby` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `brithday` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  baby.bb_baby 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `bb_baby` DISABLE KEYS */;
INSERT INTO `bb_baby` (`id`, `name`, `brithday`) VALUES
	(1, '最代码牛哥', '2018-03-16'),
	(4, '钟歘', '2018-10-23');
/*!40000 ALTER TABLE `bb_baby` ENABLE KEYS */;

-- 导出  表 baby.bb_blog 结构
CREATE TABLE IF NOT EXISTS `bb_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日记表主键',
  `first` varchar(200) DEFAULT NULL COMMENT '宝宝的第一次',
  `language` varchar(200) DEFAULT NULL COMMENT '宝宝学会的语言',
  `cognitive` varchar(200) DEFAULT NULL COMMENT '宝宝的认知',
  `blog` text NOT NULL COMMENT '日记正文',
  `create_time` datetime NOT NULL COMMENT '创建记录时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新修改时间，默认为创建记录时间',
  `baby_id` tinyint(4) NOT NULL COMMENT '记录所属宝贝',
  `user_id` tinyint(4) NOT NULL COMMENT '记录者',
  PRIMARY KEY (`id`),
  KEY `bb_blog_bb_baby_id_fk` (`baby_id`),
  KEY `bb_blog_bb_user_id_fk` (`user_id`),
  CONSTRAINT `bb_blog_bb_baby_id_fk` FOREIGN KEY (`baby_id`) REFERENCES `bb_baby` (`id`),
  CONSTRAINT `bb_blog_bb_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `bb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 正在导出表  baby.bb_blog 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `bb_blog` DISABLE KEYS */;
INSERT INTO `bb_blog` (`id`, `first`, `language`, `cognitive`, `blog`, `create_time`, `update_time`, `baby_id`, `user_id`) VALUES
	(1, 'zuidaima.com', 'java', 'java', 'zuidaima.com', '2018-04-03 11:25:31', '2018-04-03 11:25:31', 1, 1),
	(2, NULL, NULL, NULL, 'baby2017 插入记录测试！', '2018-10-18 11:26:22', '2018-10-18 11:26:22', 1, 1),
	(3, NULL, NULL, NULL, 'baby2017 插入记录测试！', '2018-10-18 11:33:55', '2018-10-18 11:33:55', 1, 1),
	(4, '洗鞋', '粤语', '开心认识了小乌龟', '我很开心', '2018-10-23 15:08:32', '2018-10-23 15:08:32', 1, 1),
	(5, '创意鹅服', 'aaaaa', 'aaaa', 'aaaaaaa', '2018-10-23 20:14:06', '2018-10-23 20:14:06', 1, 1);
/*!40000 ALTER TABLE `bb_blog` ENABLE KEYS */;

-- 导出  表 baby.bb_healthy 结构
CREATE TABLE IF NOT EXISTS `bb_healthy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `height` int(11) NOT NULL COMMENT '身高',
  `weight` float NOT NULL COMMENT '体重',
  `baby_id` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bb_healthy_bb_baby_id_fk` (`baby_id`),
  CONSTRAINT `bb_healthy_bb_baby_id_fk` FOREIGN KEY (`baby_id`) REFERENCES `bb_baby` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  baby.bb_healthy 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `bb_healthy` DISABLE KEYS */;
INSERT INTO `bb_healthy` (`id`, `height`, `weight`, `baby_id`, `create_time`) VALUES
	(1, 150, 60, 1, '2018-04-03 11:26:17');
/*!40000 ALTER TABLE `bb_healthy` ENABLE KEYS */;

-- 导出  表 baby.bb_user 结构
CREATE TABLE IF NOT EXISTS `bb_user` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `gm` tinyint(4) NOT NULL COMMENT '简单管理权限，级别为1-5',
  `amilymembers` varchar(30) NOT NULL COMMENT '所属家庭成员类别 比如爸爸或妈妈',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bb_user_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  baby.bb_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `bb_user` DISABLE KEYS */;
INSERT INTO `bb_user` (`id`, `name`, `password`, `gm`, `amilymembers`) VALUES
	(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 5, 'baba'),
	(4, 'zhongfs', '886a6afc15c99e2fbd21ec22a3330358', 70, '爸爸');
/*!40000 ALTER TABLE `bb_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

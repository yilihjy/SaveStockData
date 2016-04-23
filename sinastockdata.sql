SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for 15mline
-- ----------------------------
DROP TABLE IF EXISTS `15mline`;
CREATE TABLE `15mline` (
  `stock_code` varchar(255) NOT NULL,
  `stock_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `open` double(255,4) DEFAULT NULL,
  `close` double(255,4) DEFAULT NULL,
  `low` double(255,4) DEFAULT NULL,
  `high` double(255,4) DEFAULT NULL,
  `volume` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`stock_code`,`stock_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for 1hline
-- ----------------------------
DROP TABLE IF EXISTS `1hline`;
CREATE TABLE `1hline` (
  `stock_code` varchar(255) NOT NULL,
  `stock_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `open` double(255,4) DEFAULT NULL,
  `close` double(255,4) DEFAULT NULL,
  `low` double(255,4) DEFAULT NULL,
  `high` double(255,4) DEFAULT NULL,
  `volume` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`stock_code`,`stock_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for 30mline
-- ----------------------------
DROP TABLE IF EXISTS `30mline`;
CREATE TABLE `30mline` (
  `stock_code` varchar(255) NOT NULL,
  `stock_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `open` double(255,4) DEFAULT NULL,
  `close` double(255,4) DEFAULT NULL,
  `low` double(255,4) DEFAULT NULL,
  `high` double(255,4) DEFAULT NULL,
  `volume` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`stock_code`,`stock_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for 5mline
-- ----------------------------
DROP TABLE IF EXISTS `5mline`;
CREATE TABLE `5mline` (
  `stock_code` varchar(255) NOT NULL,
  `stock_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `open` double(255,4) DEFAULT NULL,
  `close` double(255,4) DEFAULT NULL,
  `low` double(255,4) DEFAULT NULL,
  `high` double(255,4) DEFAULT NULL,
  `volume` int(255) DEFAULT NULL,
  PRIMARY KEY (`stock_code`,`stock_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dailyline
-- ----------------------------
DROP TABLE IF EXISTS `dailyline`;
CREATE TABLE `dailyline` (
  `stock_code` varchar(255) NOT NULL,
  `stock_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `open` double(255,4) DEFAULT NULL,
  `close` double(255,4) DEFAULT NULL,
  `low` double(255,4) DEFAULT NULL,
  `high` double(255,4) DEFAULT NULL,
  `volume` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`stock_code`,`stock_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stock_code_name
-- ----------------------------
DROP TABLE IF EXISTS `stock_code_name`;
CREATE TABLE `stock_code_name` (
  `stock_code` varchar(255) NOT NULL,
  `stock_name` varchar(255) NOT NULL,
  PRIMARY KEY (`stock_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for weekline
-- ----------------------------
DROP TABLE IF EXISTS `weekline`;
CREATE TABLE `weekline` (
  `stock_code` varchar(255) NOT NULL,
  `stock_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `open` double(255,4) DEFAULT NULL,
  `close` double(255,4) DEFAULT NULL,
  `low` double(255,4) DEFAULT NULL,
  `high` double(255,4) DEFAULT NULL,
  `volume` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`stock_code`,`stock_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

CREATE DATABASE IF NOT EXISTS `PaloAlto` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `PaloAlto`;

CREATE TABLE IF NOT EXISTS `blogPosts` (
  `postId` int(11) NOT NULL AUTO_INCREMENT,
  `dateSubmitted` varchar(20) NOT NULL,
  `startDate` varchar(20) DEFAULT NULL,
  `endDate` varchar(20) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `postBody` text NOT NULL,
  `userIdFK` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `postType` varchar(20) NOT NULL,
  `titleNumber` varchar(105) NOT NULL,
  PRIMARY KEY (`postId`),
  UNIQUE KEY `readableUrl` (`titleNumber`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=808 ;

INSERT INTO `blogPosts` (`postId`, `dateSubmitted`, `startDate`, `endDate`, `title`, `postBody`, `userIdFK`, `status`, `postType`, `titleNumber`) VALUES
(807, '0019-07-09 00:00:00', '0019-07-09 00:00:00', '0019-07-09 00:00:00', 'News', 'Q', 11, 'Published', 'International', 'News');

CREATE TABLE IF NOT EXISTS `categories` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(50) NOT NULL,
  PRIMARY KEY (`categoryId`),
  UNIQUE KEY `categoryName` (`categoryName`),
  KEY `categoryName_2` (`categoryName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=270 ;

INSERT INTO `categories` (`categoryId`, `categoryName`) VALUES
(268, 'Cheese'),
(266, 'News'),
(267, 'Sales'),
(269, 'Unique');

CREATE TABLE IF NOT EXISTS `categoriesPostsBridge` (
  `categoriesPostsBridgeId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryIdFK` int(11) NOT NULL,
  `blogPostIdFK` int(11) NOT NULL,
  PRIMARY KEY (`categoriesPostsBridgeId`),
  KEY `categoryIdFK` (`categoryIdFK`),
  KEY `blogPostIdFK` (`blogPostIdFK`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

INSERT INTO `categoriesPostsBridge` (`categoriesPostsBridgeId`, `categoryIdFK`, `blogPostIdFK`) VALUES
(26, 269, 807);

CREATE TABLE IF NOT EXISTS `hashTags` (
  `hashTagId` int(11) NOT NULL AUTO_INCREMENT,
  `hashTagName` varchar(50) NOT NULL,
  PRIMARY KEY (`hashTagId`),
  UNIQUE KEY `hashTagName_UNIQUE` (`hashTagName`),
  UNIQUE KEY `hashTagId_UNIQUE` (`hashTagId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

CREATE TABLE IF NOT EXISTS `postHashTagBridge` (
  `postHashTagId` int(11) NOT NULL AUTO_INCREMENT,
  `postIdFK` int(11) DEFAULT NULL,
  `HashTagIdFK` int(11) DEFAULT NULL,
  PRIMARY KEY (`postHashTagId`),
  KEY `postIdFK` (`postIdFK`),
  KEY `HashTagIdFK` (`HashTagIdFK`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

CREATE TABLE IF NOT EXISTS `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `displayName` varchar(100) NOT NULL,
  `passwordSalt` char(64) NOT NULL,
  `passwordHash` char(64) NOT NULL,
  `siteRole` enum('user','admin','owner','developer') NOT NULL,
  `username` varchar(25) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


ALTER TABLE `categoriesPostsBridge`
  ADD CONSTRAINT `categoriesPostsBridge_ibfk_1` FOREIGN KEY (`categoryIdFK`) REFERENCES `categories` (`categoryId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `categoriesPostsBridge_ibfk_2` FOREIGN KEY (`blogPostIdFK`) REFERENCES `blogPosts` (`postId`) ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE `postHashTagBridge`
  ADD CONSTRAINT `postHashTagBridge_ibfk_1` FOREIGN KEY (`postIdFK`) REFERENCES `blogPosts` (`postId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `postHashTagBridge_ibfk_2` FOREIGN KEY (`HashTagIdFK`) REFERENCES `hashTags` (`hashTagId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

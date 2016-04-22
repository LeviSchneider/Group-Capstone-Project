-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 22, 2016 at 12:38 AM
-- Server version: 5.5.47-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `PaloAlto_Test`
--
CREATE DATABASE IF NOT EXISTS `PaloAlto_Test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `PaloAlto_Test`;

-- --------------------------------------------------------

--
-- Table structure for table `blogPosts`
--

DROP TABLE IF EXISTS `blogPosts`;
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
  PRIMARY KEY (`postId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=113 ;

--
-- Dumping data for table `blogPosts`
--

INSERT INTO `blogPosts` (`postId`, `dateSubmitted`, `startDate`, `endDate`, `title`, `postBody`, `userIdFK`, `status`, `postType`) VALUES
(110, '0019-07-09 00:00:00', '0019-07-09 00:00:00', '0019-07-09 00:00:00', 'News', 'Q', 11, 'Published', 'International'),
(111, '0019-07-09 00:00:00', '0019-07-09 00:00:00', '0019-07-09 00:00:00', 'Ads', 'B', 22, 'PendingForApproval', 'Local'),
(112, '0019-07-09 00:00:00', '0019-07-09 00:00:00', '0019-07-09 00:00:00', 'News', 'F', 33, 'Draft', 'Local');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(50) NOT NULL,
  PRIMARY KEY (`categoryId`),
  UNIQUE KEY `categoryName` (`categoryName`),
  KEY `categoryName_2` (`categoryName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=169 ;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`categoryId`, `categoryName`) VALUES
(167, 'Cheese'),
(165, 'News'),
(166, 'Sales'),
(168, 'Unique');

-- --------------------------------------------------------

--
-- Table structure for table `categoriesPostsBridge`
--

DROP TABLE IF EXISTS `categoriesPostsBridge`;
CREATE TABLE IF NOT EXISTS `categoriesPostsBridge` (
  `categoriesPostsBridgeId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryIdFK` int(11) NOT NULL,
  `blogPostIdFK` int(11) NOT NULL,
  PRIMARY KEY (`categoriesPostsBridgeId`),
  KEY `categoryIdFK` (`categoryIdFK`),
  KEY `blogPostIdFK` (`blogPostIdFK`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=40 ;

-- --------------------------------------------------------

--
-- Table structure for table `hashTags`
--

DROP TABLE IF EXISTS `hashTags`;
CREATE TABLE IF NOT EXISTS `hashTags` (
  `hashTagId` int(11) NOT NULL AUTO_INCREMENT,
  `hashTagName` varchar(50) NOT NULL,
  PRIMARY KEY (`hashTagId`),
  UNIQUE KEY `hashTagName_UNIQUE` (`hashTagName`),
  UNIQUE KEY `hashTagId_UNIQUE` (`hashTagId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=31 ;

-- --------------------------------------------------------

--
-- Table structure for table `postHashTagBridge`
--

DROP TABLE IF EXISTS `postHashTagBridge`;
CREATE TABLE IF NOT EXISTS `postHashTagBridge` (
  `postHashTagId` int(11) NOT NULL AUTO_INCREMENT,
  `postIdFK` int(11) DEFAULT NULL,
  `HashTagIdFK` int(11) DEFAULT NULL,
  PRIMARY KEY (`postHashTagId`),
  KEY `postIdFK` (`postIdFK`),
  KEY `HashTagIdFK` (`HashTagIdFK`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=31 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `displayName` varchar(100) NOT NULL,
  `passwordSalt` char(64) NOT NULL,
  `passwordHash` char(64) NOT NULL,
  `siteRole` enum('user','admin','owner','developer') NOT NULL,
  `username` varchar(25) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `categoriesPostsBridge`
--
ALTER TABLE `categoriesPostsBridge`
  ADD CONSTRAINT `categoriesPostsBridge_ibfk_2` FOREIGN KEY (`blogPostIdFK`) REFERENCES `blogPosts` (`postId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `categoriesPostsBridge_ibfk_1` FOREIGN KEY (`categoryIdFK`) REFERENCES `categories` (`categoryId`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `postHashTagBridge`
--
ALTER TABLE `postHashTagBridge`
  ADD CONSTRAINT `postHashTagBridge_ibfk_2` FOREIGN KEY (`HashTagIdFK`) REFERENCES `hashTags` (`hashTagId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `postHashTagBridge_ibfk_1` FOREIGN KEY (`postIdFK`) REFERENCES `blogPosts` (`postId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

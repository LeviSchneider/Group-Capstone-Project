-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 24, 2016 at 07:31 AM
-- Server version: 5.5.49-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `PaloAlto`
--
CREATE DATABASE IF NOT EXISTS `PaloAlto` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `PaloAlto`;

-- --------------------------------------------------------

--
-- Table structure for table `blogPosts`
--

CREATE TABLE IF NOT EXISTS `blogPosts` (
  `postId` int(11) NOT NULL AUTO_INCREMENT,
  `dateSubmitted` varchar(20) NOT NULL,
  `startDate` varchar(20) DEFAULT NULL,
  `endDate` varchar(20) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `postBody` text NOT NULL,
  `userIdFK` int(11) NOT NULL,
  `postType` varchar(20) NOT NULL,
  `titleNumber` varchar(105) NOT NULL,
  PRIMARY KEY (`postId`),
  UNIQUE KEY `readableUrl` (`titleNumber`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=853 ;

--
-- Dumping data for table `blogPosts`
--

INSERT INTO `blogPosts` (`postId`, `dateSubmitted`, `startDate`, `endDate`, `title`, `postBody`, `userIdFK`, `postType`, `titleNumber`) VALUES
(807, '0019-07-09 00:00:00', '0019-07-09 00:00:00', '0019-07-09 00:00:00', 'News', 'Q', 11, 'International', 'News'),
(808, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '<p>fdasfsd</p>', 999, 'blogPost', 'This is a title'),
(809, '2016-12-26 19:00:00', '2016-12-26 19:00:00', '2016-12-26 19:00:00', 'This is a title', '<p><strong>Post rich content here.a</strong></p>', 999, 'blogPost', 'This is a title2'),
(810, '2016-12-26 19:00:00', '2016-12-26 19:00:00', '2016-12-26 19:00:00', 'This is a title', '<p><strong>Post rich content #aaaaeee here.a</strong></p>', 999, 'blogPost', 'This is a title3'),
(811, '2016-12-26 19:00:00', '2016-12-26 19:00:00', '2016-12-26 19:00:00', 'This is a title', '<p><strong>Post rich content #aaaaeee here.a</strong></p>', 999, 'blogPost', 'This is a title4'),
(812, '2016-12-26 19:00:00', '2016-12-26 19:00:00', '2016-12-26 19:00:00', 'This is a title', '<p><strong>Post rich content #aaaaeee here.a</strong></p>', 999, 'blogPost', 'This is a title5'),
(813, '2016-12-26 19:00:00', '2016-12-26 19:00:00', '2016-12-26 19:00:00', 'This is a title', '<p>#more<strong>Post rich content #aaaaeee here.a</strong></p>', 999, 'blogPost', 'This is a title6'),
(821, '2016-12-25 19:00:00', '2016-12-25 19:00:00', '2016-12-25 19:00:00', 'hey', '<p>#more<strong>Post rich content #aaaaeee here.a</strong></p>', 999, 'blogPost', 'hey'),
(822, '2016-12-25 19:00:00', '2016-12-25 19:00:00', '2016-12-25 19:00:00', 'he21y', '<p>#more<strong>Post rich content #aaaaeee here.a</strong></p>', 999, 'blogPost', 'he21y2'),
(824, '2016-12-27 19:00:00', NULL, NULL, 'asdfasd', '<p>asdfasdf</p>', 999, 'blogPost', 'asdfasd'),
(825, '2016-12-27 19:00:00', NULL, NULL, 'asdf', '<p>asdf</p>', 999, 'blogPost', 'asdf'),
(826, '2016-12-27 19:00:00', NULL, NULL, 'aaaaaaaaaaaaaaaaa', '<p>aaaaaaaaaaaaaaaaaaaaaaaaaaa</p>', 999, 'blogPost', 'aaaaaaaaaaaaaaaaa'),
(827, '2016-12-27 19:00:00', NULL, NULL, 'ssssssssssssssssssssss', '<p>sssssssssssssssssssssssssssssssssssssss</p>', 999, 'blogPost', 'ssssssssssssssssssssss'),
(828, '2016-12-27 19:00:00', NULL, NULL, 'ddddddddddddddddddddddddddddd', '<p>ssssssssssssssssssssssssssssssss</p>', 999, 'blogPost', 'ddddddddddddddddddddddddddddd'),
(831, '2016-12-27 19:00:00', '2016-04-18 20:00:00', '2016-04-29 20:00:00', 'dddddddddddddddddddd', '<p>dddddddddddddddddddddddddddddddddddddddddddd</p>', 999, 'blogPost', 'dddddddddddddddddddd'),
(832, '2016-12-27 19:00:00', NULL, NULL, 'sadf', '<p>asdf</p>', 999, 'blogPost', 'sadf'),
(833, '2016-12-27 19:00:00', NULL, NULL, 'sadf', '<p>asdf</p>', 999, 'blogPost', 'sadf2'),
(834, '2016-12-27 19:00:00', NULL, NULL, 'sadf', '<p>asdf</p>', 999, 'blogPost', 'sadf3'),
(835, '2016-12-27 19:00:00', '2016-04-26 20:00:00', '2016-04-28 20:00:00', 'sadffsdfsdf', '<p>asdfsdfdsf</p>', 999, 'blogPost', 'sadffsdfsdf'),
(836, '2016-12-27 19:00:00', NULL, NULL, 'asdfasdfasdf', '<p>asdfasdfasdf</p>', 999, 'blogPost', 'asdfasdfasdf'),
(837, '2016-12-27 19:00:00', NULL, NULL, 'asdf', '<p>asdfadsf</p>', 999, 'blogPost', 'asdf2'),
(839, '2016-12-27 19:00:00', NULL, NULL, 'asdfa', '<p>asdfasdf</p>', 999, 'blogPost', 'asdfa'),
(840, '2016-12-27 19:00:00', NULL, NULL, 'asdfas', '<p>asdfasdf</p>', 999, 'blogPost', 'asdfas'),
(842, '2016-12-27 19:00:00', NULL, NULL, 'vvvvvvvvvvvvvv', '<p>vvvvvvvvvvvvvvvv</p>', 999, 'blogPost', 'vvvvvvvvvvvvvv'),
(844, '2016-12-27 19:00:00', NULL, NULL, 'testing timeout', '<p>test</p>', 999, 'blogPost', 'testing timeout'),
(845, '2016-12-27 19:00:00', NULL, NULL, '', '', 999, 'blogPost', ''),
(846, '2016-12-27 19:00:00', NULL, NULL, '', '', 999, 'blogPost', '2'),
(847, '2016-12-27 19:00:00', NULL, NULL, '', '', 999, 'blogPost', '3'),
(848, '2016-12-27 19:00:00', NULL, NULL, '', '', 999, 'blogPost', '4'),
(849, '2016-12-27 19:00:00', NULL, NULL, '', '', 999, 'blogPost', '5'),
(850, '2016-12-27 19:00:00', NULL, NULL, '', '', 999, 'blogPost', '6'),
(851, '2016-12-27 19:00:00', NULL, NULL, '', '', 999, 'blogPost', '7'),
(852, '2016-12-27 19:00:00', NULL, NULL, '', '', 999, 'blogPost', '8');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(50) NOT NULL,
  PRIMARY KEY (`categoryId`),
  UNIQUE KEY `categoryName` (`categoryName`),
  KEY `categoryName_2` (`categoryName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=275 ;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`categoryId`, `categoryName`) VALUES
(270, 'aaaaa'),
(271, 'asdf'),
(272, 'asdfadsf'),
(268, 'Cheese'),
(273, 'dsfsdfsdf'),
(274, 'natesucks'),
(266, 'News'),
(267, 'Sales'),
(269, 'Unique');

-- --------------------------------------------------------

--
-- Table structure for table `categoriesPostsBridge`
--

CREATE TABLE IF NOT EXISTS `categoriesPostsBridge` (
  `categoriesPostsBridgeId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryIdFK` int(11) NOT NULL,
  `blogPostIdFK` int(11) NOT NULL,
  PRIMARY KEY (`categoriesPostsBridgeId`),
  KEY `categoryIdFK` (`categoryIdFK`),
  KEY `blogPostIdFK` (`blogPostIdFK`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `categoriesPostsBridge`
--

INSERT INTO `categoriesPostsBridge` (`categoriesPostsBridgeId`, `categoryIdFK`, `blogPostIdFK`) VALUES
(26, 269, 807),
(27, 270, 808),
(28, 272, 831),
(29, 271, 833),
(30, 273, 834),
(31, 273, 835),
(32, 268, 844);

-- --------------------------------------------------------

--
-- Table structure for table `hashTags`
--

CREATE TABLE IF NOT EXISTS `hashTags` (
  `hashTagId` int(11) NOT NULL AUTO_INCREMENT,
  `hashTagName` varchar(50) NOT NULL,
  PRIMARY KEY (`hashTagId`),
  UNIQUE KEY `hashTagName_UNIQUE` (`hashTagName`),
  UNIQUE KEY `hashTagId_UNIQUE` (`hashTagId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Dumping data for table `hashTags`
--

INSERT INTO `hashTags` (`hashTagId`, `hashTagName`) VALUES
(24, '#aaaaeee'),
(26, '#more'),
(23, '#test');

-- --------------------------------------------------------

--
-- Table structure for table `postHashTagBridge`
--

CREATE TABLE IF NOT EXISTS `postHashTagBridge` (
  `postHashTagId` int(11) NOT NULL AUTO_INCREMENT,
  `postIdFK` int(11) DEFAULT NULL,
  `HashTagIdFK` int(11) DEFAULT NULL,
  PRIMARY KEY (`postHashTagId`),
  KEY `postIdFK` (`postIdFK`),
  KEY `HashTagIdFK` (`HashTagIdFK`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Dumping data for table `postHashTagBridge`
--

INSERT INTO `postHashTagBridge` (`postHashTagId`, `postIdFK`, `HashTagIdFK`) VALUES
(23, 808, 23),
(24, 811, 24),
(25, 812, 24),
(26, 813, 26),
(27, 813, 24),
(28, 821, 26),
(29, 821, 24),
(30, 822, 26),
(31, 822, 24);

-- --------------------------------------------------------

--
-- Table structure for table `postStatus`
--

CREATE TABLE IF NOT EXISTS `postStatus` (
  `postStatusId` int(11) NOT NULL AUTO_INCREMENT,
  `postStatusName` varchar(50) NOT NULL,
  PRIMARY KEY (`postStatusId`),
  UNIQUE KEY `postStatusName` (`postStatusName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `postStatus`
--

INSERT INTO `postStatus` (`postStatusId`, `postStatusName`) VALUES
(3, 'Draft'),
(2, 'Published'),
(4, 'Ready For Approval'),
(1, 'Unpublished');

-- --------------------------------------------------------

--
-- Table structure for table `postStatusBlogPostBridge`
--

CREATE TABLE IF NOT EXISTS `postStatusBlogPostBridge` (
  `postStatusBlogPostBridgeId` int(11) NOT NULL AUTO_INCREMENT,
  `postStatusIdFK` int(11) NOT NULL,
  `blogPostIdFK` int(11) NOT NULL,
  PRIMARY KEY (`postStatusBlogPostBridgeId`),
  UNIQUE KEY `blogPostIdFK_2` (`blogPostIdFK`),
  KEY `postStatusIdFK` (`postStatusIdFK`),
  KEY `blogPostIdFK` (`blogPostIdFK`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Dumping data for table `postStatusBlogPostBridge`
--

INSERT INTO `postStatusBlogPostBridge` (`postStatusBlogPostBridgeId`, `postStatusIdFK`, `blogPostIdFK`) VALUES
(1, 4, 813),
(3, 3, 812),
(4, 4, 821),
(5, 3, 822),
(6, 1, 824),
(7, 3, 825),
(8, 3, 826),
(9, 3, 827),
(10, 2, 828),
(11, 2, 831),
(12, 3, 832),
(13, 3, 833),
(14, 3, 834),
(15, 3, 835),
(16, 3, 836),
(17, 1, 837),
(18, 2, 839),
(19, 3, 840),
(20, 3, 842),
(21, 3, 844),
(22, 3, 845),
(23, 3, 846),
(24, 3, 847),
(25, 3, 848),
(26, 3, 849),
(27, 3, 850),
(28, 3, 851),
(29, 3, 852);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

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
  ADD CONSTRAINT `categoriesPostsBridge_ibfk_1` FOREIGN KEY (`categoryIdFK`) REFERENCES `categories` (`categoryId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `categoriesPostsBridge_ibfk_2` FOREIGN KEY (`blogPostIdFK`) REFERENCES `blogPosts` (`postId`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `postHashTagBridge`
--
ALTER TABLE `postHashTagBridge`
  ADD CONSTRAINT `postHashTagBridge_ibfk_1` FOREIGN KEY (`postIdFK`) REFERENCES `blogPosts` (`postId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `postHashTagBridge_ibfk_2` FOREIGN KEY (`HashTagIdFK`) REFERENCES `hashTags` (`hashTagId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `postStatusBlogPostBridge`
--
ALTER TABLE `postStatusBlogPostBridge`
  ADD CONSTRAINT `postStatusBlogPostBridge_ibfk_2` FOREIGN KEY (`blogPostIdFK`) REFERENCES `blogPosts` (`postId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `postStatusBlogPostBridge_ibfk_1` FOREIGN KEY (`postStatusIdFK`) REFERENCES `postStatus` (`postStatusId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 21, 2016 at 11:02 AM
-- Server version: 5.5.47-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.14

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
  `dateSubmitted` datetime NOT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `postBody` text NOT NULL,
  `userIdFK` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `postType` varchar(20) NOT NULL,
  PRIMARY KEY (`postId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `blogPosts`
--

INSERT INTO `blogPosts` (`postId`, `dateSubmitted`, `startDate`, `endDate`, `title`, `postBody`, `userIdFK`, `status`, `postType`) VALUES
(1, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '''asdfasdf', 999, 'draft', 'blogPost'),
(2, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '<b>Post rich content here.</b>', 999, 'draft', 'blogPost'),
(3, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '', 999, 'draft', 'blogPost'),
(4, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '<b>Post rich content here.</b>', 999, 'draft', 'blogPost'),
(5, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '', 999, 'draft', 'blogPost'),
(6, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '<b>Post rich content here.</b>', 999, 'draft', 'blogPost'),
(7, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '', 999, 'draft', 'blogPost'),
(8, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '<b>Post rich content here.</b>', 999, 'draft', 'blogPost'),
(9, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '', 999, 'draft', 'blogPost'),
(10, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '', 999, 'draft', 'blogPost'),
(11, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '<b>Post rich content here.</b>', 999, 'draft', 'blogPost'),
(12, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '<b>Post rich content here.</b>', 999, 'draft', 'blogPost'),
(13, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '<p><strong>aaaaaaaaaaaa</strong></p>', 999, 'draft', 'blogPost'),
(14, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '<p><strong>wtertwert</strong></p>', 999, 'draft', 'blogPost'),
(15, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '<p><strong>ayyyyyyyyyyyy</strong></p>', 0, 'draft', 'blogPost'),
(16, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '<p><strong>asdfasdf</strong></p>', 0, 'draft', 'blogPost'),
(17, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '<p>End of the first sprint</p>', 0, 'draft', 'blogPost'),
(18, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '<p><strong><img src="http://www.walnutcreekfoods.com/v/vspfiles/photos/145121-2.jpg" alt="" width="500" height="329" />Post rich content here.</strong></p>', 0, 'draft', 'blogPost'),
(19, '2016-12-27 19:00:00', NULL, NULL, 'This is a title', '<p><strong>Post rich content here.test</strong></p>', 999, 'draft', 'blogPost');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(50) NOT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `hashTags`
--

CREATE TABLE IF NOT EXISTS `hashTags` (
  `hashTagId` int(11) NOT NULL AUTO_INCREMENT,
  `hashTagName` int(11) NOT NULL,
  PRIMARY KEY (`hashTagId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
  ADD CONSTRAINT `categoriesPostsBridge_ibfk_2` FOREIGN KEY (`blogPostIdFK`) REFERENCES `blogPosts` (`postId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `categoriesPostsBridge_ibfk_1` FOREIGN KEY (`categoryIdFK`) REFERENCES `categories` (`categoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `postHashTagBridge`
--
ALTER TABLE `postHashTagBridge`
  ADD CONSTRAINT `postHashTagBridge_ibfk_2` FOREIGN KEY (`HashTagIdFK`) REFERENCES `hashTags` (`hashTagId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `postHashTagBridge_ibfk_1` FOREIGN KEY (`postIdFK`) REFERENCES `blogPosts` (`postId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

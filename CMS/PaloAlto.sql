-- phpMyAdmin SQL Dump
-- version 4.0.10deb1status
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 20, 2016 at 06:18 PM
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
CREATE DATABASE IF NOT EXISTS `PaloAlto` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `PaloAlto_Test`;

-- --------------------------------------------------------

--
-- Table structure for table `blogPosts`
--

DROP TABLE IF EXISTS `blogPosts`;
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=115 ;

--
-- Dumping data for table `blogPosts`
--

INSERT INTO `blogPosts` (`postId`, `dateSubmitted`, `startDate`, `endDate`, `title`, `postBody`, `userIdFK`, `status`, `postType`) VALUES
(114, '0019-07-09 00:00:00', '0019-07-09 00:00:00', '0019-07-09 00:00:00', 'News', 'Q', 11, 'Published', 'International');

-- --------------------------------------------------------

--
-- Table structure for table `hashTags`
--

DROP TABLE IF EXISTS `hashTags`;
CREATE TABLE IF NOT EXISTS `hashTags` (
  `hashTagId` int(11) NOT NULL AUTO_INCREMENT,
  `hashTagName` int(11) NOT NULL,
  PRIMARY KEY (`hashTagId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `postHashTagBridge`
--

DROP TABLE IF EXISTS `postHashTagBridge`;
CREATE TABLE IF NOT EXISTS `postHashTagBridge` (
  `postHashTagId` int(11) NOT NULL AUTO_INCREMENT,
  `postIdFK` int(11) DEFAULT NULL,
  `HashTagIdFK` int(11) DEFAULT NULL,
  PRIMARY KEY (`postHashTagId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

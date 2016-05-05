-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 05, 2016 at 08:30 AM
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

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(20) NOT NULL,
  `authority` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('editor', 'ROLE_EDITOR'),
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_EDITOR');

-- --------------------------------------------------------

--
-- Table structure for table `blogPosts`
--

CREATE TABLE IF NOT EXISTS `blogPosts` (
  `postId` int(11) NOT NULL AUTO_INCREMENT,
  `timeCreated` datetime NOT NULL,
  `timeEdited` datetime NOT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `postBody` text NOT NULL,
  `userIdFK` int(11) NOT NULL,
  `titleNumber` varchar(105) NOT NULL,
  `categoryIdFK` int(11) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`postId`),
  UNIQUE KEY `readableUrl` (`titleNumber`),
  KEY `fk_blogPosts_1_idx` (`categoryIdFK`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1233 ;

--
-- Dumping data for table `blogPosts`
--

INSERT INTO `blogPosts` (`postId`, `timeCreated`, `timeEdited`, `startDate`, `endDate`, `title`, `postBody`, `userIdFK`, `titleNumber`, `categoryIdFK`, `status`) VALUES
(1229, '2016-04-26 20:00:00', '2016-05-04 08:48:14', '2016-05-01 20:00:00', '9999-12-09 19:00:00', 'Red leicester', '<h2><span id="History" class="mw-headline"><img class="img-responsive" style="display: block; margin-left: auto; margin-right: auto;" src="https://upload.wikimedia.org/wikipedia/commons/1/16/Red_Leicester.jpg" width="349" height="262" /></span></h2>\n<p>&nbsp;</p>\n<p><strong>Red Leicester</strong> (also known simply as <strong>Leicester</strong> or <strong>Leicestershire Cheese</strong>)&nbsp;is an English cheese, made in a similar manner to Cheddar cheese, although it is crumblier. Since the 18th century, it has been coloured orange by adding annattoextract during manufacture.&nbsp;It is a cow''s milk cheese, originally from Leicestershire, England, and is named after the city ofLeicester. It has a firm texture, and a slightly nutty taste. Versions sold in supermarkets are typically coloured with annatto, although it is possible to obtain Red Leicester without it.</p>\n<p>Although Red Leicester can be young or "old", aged anywhere from four to nine months, the young Leicesters at the start of that range will be very mild: they often require at least six months to develop a tang. Farmhouse versions are also available. Farmhouse makers will mature it in cloth, the old way, to allow better flavour development.</p>\n<h2><span id="History" class="mw-headline">History</span></h2>\n<p>The cheese was originally made on farms in Leicestershire with milk that was surplus once all the Stilton desired was made. It was originally coloured with carrot or beetroot juice.</p>\n<p>It used to be called Leicestershire Cheese, after the county in which it was originally made, but is now called Red Leicester to distinguish it from the White Leicester, which was made to a national recipe under wartime controls during the 1940s.&nbsp;The rind is reddish-orange with a powdery mould on it.</p>', 999, 'Red_leicester', NULL, 'PUBLISHED'),
(1230, '2016-05-04 08:51:03', '2016-05-04 08:51:03', '2016-05-04 08:51:03', '9999-12-12 00:00:00', 'On stilton...', '<p><img class="img-responsive" style="display: block; margin-left: auto; margin-right: auto;" src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Stilton_Cheese_02.png/220px-Stilton_Cheese_02.png" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Stilton_Cheese_02.png/330px-Stilton_Cheese_02.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Stilton_Cheese_02.png/440px-Stilton_Cheese_02.png 2x" alt="Stilton Cheese 02.png" width="220" height="204" data-file-width="3184" data-file-height="2956" /></p>\n<p>According to the #Stilton Cheesemaker''s Association, the first Englishman to market Blue Stilton cheese was Cooper Thornhill, owner of the <em>Bell Inn</em> on the Great North Road, in the village of Stilton, Huntingdonshire.&nbsp;Traditional legend has it that in 1730, Thornhill discovered a distinctive blue cheese while visiting a small farm near Melton Mowbray in rural #Leicestershire &ndash; possibly in Wymondham.&nbsp;He fell in love with the cheese and made a business arrangement that granted the Bell Inn exclusive marketing rights to Blue Stilton. Soon thereafter, wagon loads of cheese were being delivered to the inn. Since the mainstagecoach routes from London to Northern England passed through the village of Stilton he was able to promote the sale of this #cheese and the fame of Stilton rapidly spread.</p>\n<p>However, the first known written reference to Stilton cheese actually predates this and was in William Stukeley''s <em>Itinerarium Curiosum</em>, Letter V, dated October 1722. Daniel Defoe in his 1724 work <em>A tour thro'' the whole island of Great Britain</em> notes, "We pass''d Stilton, a town famous for cheese, which is call''d our English Parmesan, and is brought to table with the #mites, or maggots round it, so thick, that they bring a spoon with them for you to eat the mites with, as you do the cheese."</p>\n<p>Frances Pawlett (or Paulet), a "skilled cheese maker" of Wymondham, has traditionally been credited as the person who set modern Stilton cheese''s shape and style characteristics in the 1720s,&nbsp;but others have also been named.<font size="1">&nbsp;</font>The recipe for a Stilton cheese was published by Richard Bradley, first Professor of Botany at Cambridge University in his 1726 book <em>A General Treatise of Husbandry and Gardening</em>. Bradley records a letter from a correspondent, John Warner, which states the cheese is made in Stilton and that the Bell Inn produced "the best cheese in town".</p>\n<p>In 1936 the Stilton Cheesemakers'' Association (SCMA) was formed to lobby for regulation to protect the quality and origin of the cheese, and in 1966 Stilton was granted legal protection via a certification trademark, the only British cheese to have received this status.</p>', 999, 'On_stilton', NULL, 'PUBLISHED'),
(1231, '2016-05-05 08:23:15', '2016-05-05 08:23:15', '2016-05-05 08:23:15', '9999-12-12 00:00:00', 'Raclette, fondue; a practical taxonomy of Swiss melting cheese', '<p><img class="img-responsive" style="-webkit-user-select: none;" src="https://upload.wikimedia.org/wikipedia/commons/8/86/Full_cheese_fondue_set_-_in_Switzerland.JPG" /></p>\n<p>The earliest known recipe for cheese fondue as we know it today comes from a 1699 book published in Zurich, under the name "K&auml;ss mit Wein zu kochen", "to cook cheese with wine".<sup id="cite_ref-5" class="reference">[5]</sup> It calls for grated or cut-up cheese to be melted with wine, and for bread to be dipped in it.</p>\n<p>However, the <em>name</em> "cheese fondue", until the late 19th century, referred to a preparation including eggs and cheese, as in la Chapelle''s 1735 <em>Fondu&euml; de Fromage, aux Truffes Fraiches</em><sup id="cite_ref-la_chapelle_3-1" class="reference">[3]</sup> it was something between scrambled eggs with cheese and a cheese souffl&eacute;.<sup id="cite_ref-6" class="reference">[6]</sup> Brillat-Savarin wrote in 1834 that it is "nothing other than scrambled eggs with cheese".<sup id="cite_ref-brillat-savarin_7-0" class="reference">[7]</sup> Variations included cream ("&agrave; la genevoise") and truffles ("&agrave; la pi&eacute;montaise") in addition to eggs, as well as what is now called "raclette" ("fondue valaisanne").<sup id="cite_ref-8" class="reference">[8]</sup></p>\n<p>The first known recipe for the modern cheese fondue under that name, with cheese and wine but no eggs, was published in 1875, and was already presented as a Swissnational dish.<sup id="cite_ref-9" class="reference">[9]</sup> Despite its modern associations with rustic mountain life, it was a town-dweller''s dish from the lowlands of western, French-speaking, Switzerland: rich cheese like Gruy&egrave;re was a valuable export item which peasants could not afford to eat.<sup id="cite_ref-muhieddine_10-0" class="reference">[10]</sup><sup id="cite_ref-11" class="reference">[11]</sup></p>\n<p>The introduction of cornstarch ("Ma&iuml;zena") to Switzerland in 1905 made it easier to make a smooth and stable emulsion of the wine and cheese, and probably contributed to the success of fondue.<sup id="cite_ref-12" class="reference">[12]</sup></p>\n<p>Fondue was popularized as a Swiss national dish by the Swiss Cheese Union (Schweizerische K&auml;seunion) in the 1930s as a way of increasing cheese consumption. The Swiss Cheese Union also created pseudo-regional recipes as part of the "spiritual defense of Switzerland".<sup id="cite_ref-raboud_13-0" class="reference">[13]</sup><sup id="cite_ref-14" class="reference">[14]</sup> After World War II rationing ended, the Swiss Cheese Union continued its marketing campaign, sending fondue sets to military regiments and event organizers across Switzerland. Fondue is now a symbol of Swiss unity.<sup id="cite_ref-raboud_13-1" class="reference">[13]</sup></p>\n<p>In the meantime, fondue continued to be promoted aggressively in Switzerland, with slogans like "La fondue cr&eacute;e la bonne humeur" "fondue creates a good mood" and (1981) "Fondue isch guet und git e gueti Luune" "fondue is good and creates a good mood" &ndash; abbreviated as "figugegl".<sup id="cite_ref-15" class="reference">[15]</sup></p>\n<p>Fondue was promoted to Americans at the Swiss Pavilion''s Alpine restaurant at the 1964 New York World''s Fair.<sup id="cite_ref-16" class="reference">[16]</sup></p>\n<p>The extension of the name "fondue" to other dishes served in a communal hot pot dates to 1950s New York. Konrad Egli, a Swiss restaurateur, introduced <em>fondue bourguignonne</em> at his Chalet Suisse restaurant in 1956. In the mid 1960s, he invented chocolate fondue as part of a promotion for Toblerone chocolate.<sup id="cite_ref-lovegren_17-0" class="reference">[17]</sup> A sort of&nbsp;chocolate mousse or chocolate cake had also sometimes been called "chocolate fondue" starting in the 1930s.<sup id="cite_ref-18" class="reference">[18]</sup></p>\n<h2>&nbsp;</h2>\n<div class="thumb tright">&nbsp;</div>\n<p>Cheese fondue consists of a blend of cheeses, wine and seasoning. To prepare the <em>caquelon</em> it is first rubbed with a cut garlicclove. White wine is slightly heated with cornstarch, and then grated cheese is added and stirred until melted. It is often topped off with a bit of kirsch. The cornstarch or other starch is added to prevent separation. The mixture is stirred continuously as it heats in the <em>caquelon</em>.</p>\n<p>When it is ready, diners dip cubes of bread speared on a fondue fork into the mixture.</p>\n<h3><span id="Temperature_and_la_religieuse" class="mw-headline">Temperature and <em>la religieuse</em></span><span class="mw-editsection"><span class="mw-editsection-bracket">[</span>edit<span class="mw-editsection-bracket">]</span></span></h3>\n<p>A cheese fondue mixture should be kept warm enough to keep the fondue smooth and liquid but not so hot that it burns. If this temperature is held until the fondue is finished there will be a thin crust of toasted (not burnt) cheese at the bottom of the <em>caquelon</em>. This is called <em>la religieuse</em> (French for <em>the nun</em>). It has the texture of a cracker and is almost always lifted out and eaten.</p>\n<p><img class="img-responsive" src="https://upload.wikimedia.org/wikipedia/commons/7/7f/Raclette2.jpg" alt="" width="240" height="300" /></p>', 999, 'Raclette_fondue_a_practical_taxonomy_of_Swiss_melting_cheese', NULL, 'PUBLISHED'),
(1232, '2016-05-05 08:27:23', '2016-05-05 08:27:23', '2016-05-05 08:27:23', '9999-12-12 00:00:00', 'New in stock: Vitebsk Chichillini.', '<p><img class="img-responsive" src="http://www.by.all.biz/img/by/catalog/more/208837_syr_chichillini_kosichka_naturalnogo_kopcheniya.jpeg" alt="Guaranteed free of radiation." width="985" height="600" /></p>', 999, 'New_in_stock_Vitebsk_Chichillini', NULL, 'PUBLISHED');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`categoryId`, `categoryName`) VALUES
(6, 'Sheep / Goat Cheeses'),
(7, 'who knows');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `hashTags`
--

INSERT INTO `hashTags` (`hashTagId`, `hashTagName`) VALUES
(6, 'cheese'),
(5, 'Leicestershire'),
(7, 'mites'),
(4, 'Stilton');

-- --------------------------------------------------------

--
-- Table structure for table `postHashTagBridge`
--

CREATE TABLE IF NOT EXISTS `postHashTagBridge` (
  `postIdFK` int(11) DEFAULT NULL,
  `HashTagIdFK` int(11) DEFAULT NULL,
  UNIQUE KEY `postHashTagPK` (`postIdFK`,`HashTagIdFK`),
  KEY `HashTagIdFK` (`HashTagIdFK`),
  KEY `postHashTagBridge_ibfk_1_idx` (`postIdFK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `postHashTagBridge`
--

INSERT INTO `postHashTagBridge` (`postIdFK`, `HashTagIdFK`) VALUES
(1230, 4),
(1230, 5),
(1230, 6),
(1230, 7);

-- --------------------------------------------------------

--
-- Table structure for table `staticPages`
--

CREATE TABLE IF NOT EXISTS `staticPages` (
  `pageId` int(11) NOT NULL AUTO_INCREMENT,
  `timeCreated` datetime NOT NULL,
  `timeEdited` datetime NOT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `pageBody` text NOT NULL,
  `userIdFK` int(11) NOT NULL,
  `categoryIdFK` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `titleNumber` varchar(150) DEFAULT NULL,
  `sideBarPosition` int(11) NOT NULL,
  PRIMARY KEY (`pageId`),
  UNIQUE KEY `pageHrid_UNIQUE` (`titleNumber`),
  KEY `fk_staticPages_1_idx` (`categoryIdFK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `enabled`, `password`) VALUES
(1, 'editor', 1, 'password'),
(2, 'admin', 1, 'password');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `blogPosts`
--
ALTER TABLE `blogPosts`
  ADD CONSTRAINT `fk_blogPosts_1` FOREIGN KEY (`categoryIdFK`) REFERENCES `categories` (`categoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `postHashTagBridge`
--
ALTER TABLE `postHashTagBridge`
  ADD CONSTRAINT `postHashTagBridge_ibfk_1` FOREIGN KEY (`postIdFK`) REFERENCES `blogPosts` (`postId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `postHashTagBridge_ibfk_2` FOREIGN KEY (`HashTagIdFK`) REFERENCES `hashTags` (`hashTagId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `staticPages`
--
ALTER TABLE `staticPages`
  ADD CONSTRAINT `fk_staticPages_1` FOREIGN KEY (`categoryIdFK`) REFERENCES `categories` (`categoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

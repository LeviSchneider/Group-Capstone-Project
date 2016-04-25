-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 25, 2016 at 08:59 AM
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
  `postType` varchar(20) NOT NULL,
  `titleNumber` varchar(105) NOT NULL,
  PRIMARY KEY (`postId`),
  UNIQUE KEY `readableUrl` (`titleNumber`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1186 ;

--
-- Dumping data for table `blogPosts`
--

INSERT INTO `blogPosts` (`postId`, `dateSubmitted`, `startDate`, `endDate`, `title`, `postBody`, `userIdFK`, `postType`, `titleNumber`) VALUES
(1183, '2016-12-27 19:00:00', NULL, NULL, '‘Tis the Season for these One-of-a-Kind Holiday Cheeses', '<p><a href="http://www.murrayscheese.com/blog/wp-content/uploads/2015/12/rush-creek.jpg"><img class="aligncenter size-large wp-image-3518" style="display: block; margin-left: auto; margin-right: auto;" src="http://www.murrayscheese.com/blog/wp-content/uploads/2015/12/rush-creek-1024x1024.jpg" alt="rush creek" width="584" height="584" /></a></p>\n<p>Twinkly lights. Christmas music. Cozy sweaters&hellip;CHEESE!! Nothing says holiday spirit&ndash;and flavor&ndash;like these super seasonal cheese favorites. These gorgeous wheels are at their peak now, and many are only available for a<strong> precariously short amount of time</strong>. The clock is ticking.</p>\n<p>Plus, a spot-on holiday cheese plate is a guaranteed way to spread love and joy. Happy shopping, feasting, and celebrating to you and yours!</p>\n<p><strong>Rush Creek Reserve</strong></p>\n<p>Uplands Cheese Co., one of the most beloved&nbsp;cheesemakers in the United States makes this&nbsp;Vacherin Mont d&rsquo;Or-inspired beauty, possibly the most sought-after cheese in the world (pictured above). This is big news. And it makes total sense &ndash; the raw winter milk from their pastured herd of cows is less plentiful and of a quality that&rsquo;s better suited to a younger, softer cheese, so they&rsquo;ve done exactly what the French &amp; Swiss have been doing for centuries: binding small wheels in spruce bark and washing them for sixty days to produce <strong>an astoundingly unctuous, resiny, bacony delight</strong>. Best served warm, with a bottle of oxidized white wine, crusty bread, and potatoes.</p>\n<p>This cheese has a very limited availability. (Last year, it wasn&rsquo;t available at all!).<strong> Get it while you can, or spend 2016 in a cheesy shroud of regret.&nbsp;</strong></p>\n<p><strong><a href="http://www.murrayscheese.com/blog/wp-content/uploads/2014/06/comteap31.jpg"><img class="alignright size-medium wp-image-2480" src="http://www.murrayscheese.com/blog/wp-content/uploads/2014/06/comteap31-225x300.jpg" alt="comteap3" width="225" height="300" /></a></strong></p>\n<p><strong>2 Year Comte</strong></p>\n<p>Frankly, a fantastic cheese. Here at murray&rsquo;s, we can&rsquo;t get enough. Aged in the Fort Saint Antoine in Jura, this Comte is produced by one of 13 high altitude cooperatives (&ldquo;Fruitiers&rdquo;) approved by affineur Marcel Petite. <strong>This Comte is aged for 2 years, which is the longest the affineur will age any cheese.</strong> The enormous wheels of raw cows&rsquo; milk have a firm texture, leaving flavors that can range from dense, with hints of smoke and onions, to sweeter, with notes of chocolate and hazelnuts. A holiday cheese plate must, and major crowd-delighter.</p>\n<p><strong><a href="http://www.murrayscheese.com/blog/wp-content/uploads/2015/12/stilton.jpg"><img class="alignleft size-medium wp-image-3520" src="http://www.murrayscheese.com/blog/wp-content/uploads/2015/12/stilton-300x300.jpg" alt="stilton" width="300" height="300" /></a></strong></p>\n<p><strong>Colston Bassett Stilton&nbsp;</strong></p>\n<p><strong>How did&nbsp;Stilton&nbsp;become a&nbsp;Christmastime tradition?</strong>&nbsp;The most sky-high&nbsp;quality milk comes from cows grazing at the end of the summer, and Stilton is at its best after about three months of aging. Hence, the&nbsp;cream of the crop Stilton is ready&nbsp;just in time for the holidays! Plus, it&rsquo;s fantastic after Christmas dinner, with some tawny port and shards of chocolate.</p>\n<p>The term &rdquo;Royal Blue&rdquo; must have come from the creation of <a href="http://www.murrayscheese.com/stilton-colston-bassett.html">Stilton</a>. Invented by Elizabeth Scarbrow and first served in 1720 at the Bell Inn in Stilton, England fame was not far behind. Made with pasteurized cows&rsquo; milk, it is ripened 3-4 months under carefully controlled cool, humid conditions. These farmstead, rustic looking cylinders are made by Colston-Basset Dairy, for Neal&rsquo;s Yard Dairy. What makes them unique is the use of traditional animal rennet, not to be found from any other Stilton maker. Each bite is exceptionally buttery in texture with a clean, mineral tang that you&rsquo;ll never forget.</p>\n<p>&nbsp;</p>\n<p><a href="http://www.murrayscheese.com/blog/wp-content/uploads/2015/12/vacherin.jpg"><img class="alignright size-medium wp-image-3530" src="http://www.murrayscheese.com/blog/wp-content/uploads/2015/12/vacherin-300x300.jpg" alt="vacherin" width="300" height="300" /></a></p>\n<p><strong>Vacherin Mont D&rsquo;Or</strong> inspired mania and devotion, and rightly so. A thermalized cow&rsquo;s milk cheese wrapped in spruce to contain the woodsy liquid interior that, with one taste, commands spontaneous exuberance. It tastes like the holidays.</p>\n<p>Extremely rare and highly seasonal, Vacherin Mont d&rsquo;Or hails from Switzerland on the border of France near the mountain D&rsquo;Or. Traditionally made with the winter milk of the same cows that produce Gruyere in the summer, this cheese is <strong>only available from October until April, making it all the more precious</strong>. The cheese must be made from cows munching on straw and fodder; once outside to graze at pasture, their milk is used for larger alpine cheeses. Swiss regulations also dictate the cheese must be produced at elevations of 2,297 feet or higher. Not a dictate, but we highly recommend you enjoy this delectable cheese with a bottle of Gewurtztraminer. #christmas #stilton</p>', 0, 'blogPost', '‘Tis the Season for these One-of-a-Kind Holiday Cheeses2'),
(1184, '2016-12-27 19:00:00', NULL, NULL, 'Fondue! We DO! Stay Warm & Satisfied with the Perfect Winter Comfort Food', '<p><a href="http://www.murrayscheese.com/blog/wp-content/uploads/2012/12/gooeyfondue.jpg"><img class="aligncenter size-full wp-image-770" style="display: block; margin-left: auto; margin-right: auto;" src="http://www.murrayscheese.com/blog/wp-content/uploads/2012/12/gooeyfondue.jpg" alt="gooeyfondue" width="849" height="565" /></a>What&rsquo;s better than cheese? Trick question&hellip;nothing! But a big bowl of melty cheese (read: fondue) for dipping your heart out ranks really high up there for the best inventions of humankind. The days are getting shorter, and colder, and darker. Fondue to the the stomach and soul-warming rescue! &nbsp;#christmas #brie #fondue</p>\n<p>A quick fondue history lesson: The Swiss have been enjoying this goodness for a very long time.&nbsp;Homer&rsquo;s Iliad&ndash;dated from about 800 to 725 BC&ndash;even mentions chowing down on a glorious mixture of goat&rsquo;s cheese, wine and flour. Sounds like fondue to me! The custom&nbsp;makes a lot of sense. The Swiss have a storied cheese tradition&ndash;and cheese odds and ends could easily be warmed up in a big pot atop a fire and savored during frosty Swiss winters.</p>\n<p>In&nbsp;1930, the Swiss Cheese Union appointed&nbsp;fondue the country&rsquo;s national dish &ndash;&nbsp;and the world has been gaga over it&nbsp;ever since. <strong>Here&rsquo;s a can&rsquo;t-fail recipe for classic fondue&hellip;but experiment away!</strong> Fondue is a perfect blank canvas for playing around. Alpine cheeses are classic choices and stellar melters, so they&rsquo;re a great place to start. (Think nutty, savory <a href="http://www.murrayscheese.com/appenzeller-kaserei-tufertsch.html">Appenzeller</a>, pictured below). Go ahead, add some kick&nbsp;with a little of blue, perhaps some <a href="http://www.murrayscheese.com/gorgonzola-cremificato.html">creamy gorgonzola</a>. Or introduce a funky note with <a href="http://www.murrayscheese.com/spring-brook-reading.html">Spring Brook Farm Reading</a>.</p>\n<p><strong><a href="http://www.murrayscheese.com/blog/wp-content/uploads/2014/02/swiss_and_nutty_appenzeller_1.jpg"><img class="alignleft size-medium wp-image-2132" src="http://www.murrayscheese.com/blog/wp-content/uploads/2014/02/swiss_and_nutty_appenzeller_1-300x300.jpg" alt="swiss_and_nutty_appenzeller_1" width="300" height="300" /></a></strong></p>\n<p><strong>Murray&rsquo;s Classic Fondue&nbsp;</strong></p>\n<p><strong>Ingredients:</strong><br />1 teaspoon salt<br />1 garlic clove<br />150 g (approx. 3/4cup) white wine<br />4 oz (approx. 1 cup) Gruy&egrave;re, grated<br />4 oz (approx. 1 cup) Comt&eacute;, grated<br />3 oz Emmental, grated<br />1 tablespoon + 1 tsp cornstarch<br />1 teaspoon lemon juice</p>\n<p><strong>Directions:</strong><br />1.Sprinkle one teaspoon of salt in the bottom of a small saucepan.<br />2.Cut garlic clove in half and rub the inside of the pan, starting at the salt.<br />3.Heat the wine on medium-high just until boiling. While the wine is heating, combine the cheeses and toss with cornstarch until evenly distributed.<br />4.Gradually add the cheese a half a cup at a time, whisking constantly until melted and smooth.<br />5.Add lemon juice and whisk until incorporated.</p>\n<p><strong>The Great Dipping Debate</strong></p>\n<p>Dipping&hellip;so fun! Here are some of our favorite fondue vehicle. What&rsquo;s yours?</p>\n<ol>\n<li>Cubes of your favorite bread</li>\n<li>Crackers! <a href="http://www.murrayscheese.com/raincoast-crisps.html">Raincoast Crisps</a> do the trick brilliantly.</li>\n<li>Croutons</li>\n<li>Apples and pears!</li>\n<li>Veggies like broccoli, cauliflower, fennel spears, and baby carrots</li>\n<li>Pickles and pickled veggies, like <a href="http://www.murrayscheese.com/pickles-rick-s-picks-mean-beans.html">Rick&rsquo;s Pick&rsquo;s dilly Mean Beans</a></li>\n<li>Fingerling potatoes</li>\n<li>Salumi and cured meat. Yes to chunks of <a href="http://www.murrayscheese.com/chorizo-palacios-spanish-hot.html">chorizo</a>!</li>\n</ol>', 0, 'blogPost', 'Fondue! We DO! Stay Warm & Satisfied with the Perfect Winter Comfort Food2'),
(1185, '2016-12-27 19:00:00', NULL, NULL, '', '<p><img src="http://www.murrayscheese.com/blog/wp-content/uploads/2015/11/american-artisans.png" /></p>\n<p>#christmas&nbsp;</p>', 0, 'blogPost', '2');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=286 ;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`categoryId`, `categoryName`) VALUES
(285, 'Holidays');

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
  UNIQUE KEY `blogPostIdFK_2` (`blogPostIdFK`),
  KEY `categoryIdFK` (`categoryIdFK`),
  KEY `blogPostIdFK` (`blogPostIdFK`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=53 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=67 ;

--
-- Dumping data for table `hashTags`
--

INSERT INTO `hashTags` (`hashTagId`, `hashTagName`) VALUES
(62, '#brie'),
(59, '#christmas'),
(63, '#fondue'),
(60, '#stilton');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=46 ;

--
-- Dumping data for table `postHashTagBridge`
--

INSERT INTO `postHashTagBridge` (`postHashTagId`, `postIdFK`, `HashTagIdFK`) VALUES
(40, 1183, 59),
(41, 1183, 60),
(42, 1184, 59),
(43, 1184, 62),
(44, 1184, 63),
(45, 1185, 59);

-- --------------------------------------------------------

--
-- Table structure for table `postStatus`
--

DROP TABLE IF EXISTS `postStatus`;
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
(4, 'Pending'),
(2, 'Published'),
(1, 'Unpublished');

-- --------------------------------------------------------

--
-- Table structure for table `postStatusBlogPostBridge`
--

DROP TABLE IF EXISTS `postStatusBlogPostBridge`;
CREATE TABLE IF NOT EXISTS `postStatusBlogPostBridge` (
  `postStatusBlogPostBridgeId` int(11) NOT NULL AUTO_INCREMENT,
  `postStatusIdFK` int(11) NOT NULL,
  `blogPostIdFK` int(11) NOT NULL,
  PRIMARY KEY (`postStatusBlogPostBridgeId`),
  UNIQUE KEY `blogPostIdFK_2` (`blogPostIdFK`),
  KEY `postStatusIdFK` (`postStatusIdFK`),
  KEY `blogPostIdFK` (`blogPostIdFK`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=335 ;

--
-- Dumping data for table `postStatusBlogPostBridge`
--

INSERT INTO `postStatusBlogPostBridge` (`postStatusBlogPostBridgeId`, `postStatusIdFK`, `blogPostIdFK`) VALUES
(332, 2, 1183),
(333, 2, 1184),
(334, 2, 1185);

-- --------------------------------------------------------

--
-- Table structure for table `staticPageCategoryBridge`
--

DROP TABLE IF EXISTS `staticPageCategoryBridge`;
CREATE TABLE IF NOT EXISTS `staticPageCategoryBridge` (
  `staticPageCategoryBridgeId` int(11) NOT NULL AUTO_INCREMENT,
  `staticPageIdFK` int(11) NOT NULL,
  `categoryIdFK` int(11) NOT NULL,
  PRIMARY KEY (`staticPageCategoryBridgeId`),
  KEY `fk_staticPageCategoryBridge_1_idx` (`staticPageIdFK`),
  KEY `fk_staticPageCategoryBridge_2_idx` (`categoryIdFK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `static_pages`
--

DROP TABLE IF EXISTS `static_pages`;
CREATE TABLE IF NOT EXISTS `static_pages` (
  `static_pagesId` int(11) NOT NULL AUTO_INCREMENT,
  `staticPageName` varchar(100) NOT NULL,
  `url` varchar(1000) NOT NULL,
  `jspPage` varchar(45) DEFAULT NULL,
  `javaScriptPage` varchar(45) DEFAULT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`static_pagesId`),
  UNIQUE KEY `staticPageName_UNIQUE` (`staticPageName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `static_pages`
--

INSERT INTO `static_pages` (`static_pagesId`, `staticPageName`, `url`, `jspPage`, `javaScriptPage`, `position`) VALUES
(1, 'Home', '/blog', 'blog', 'blog.js', 0),
(2, 'CategoryAdmin', '/categoryAdmin', 'categoryAdmin', 'categoryAdmin.js', 1),
(3, 'Create_Post', '/tinymce', 'tinymce', 'tinymce.js', 2),
(4, 'About_Us', '/about_us', 'aboutUs', 'aboutUs.js', 3),
(5, 'Contact_Us', '/contact_us', 'contactUs', 'contactUs.js', 4);

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

--
-- Constraints for table `staticPageCategoryBridge`
--
ALTER TABLE `staticPageCategoryBridge`
  ADD CONSTRAINT `fk_staticPageCategoryBridge_1` FOREIGN KEY (`staticPageIdFK`) REFERENCES `blogPosts` (`postId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_staticPageCategoryBridge_2` FOREIGN KEY (`categoryIdFK`) REFERENCES `categories` (`categoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

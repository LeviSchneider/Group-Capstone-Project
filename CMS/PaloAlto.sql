SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

/*
DROP SCHEMA IF EXISTS `PaloAlto_Test` ;
CREATE SCHEMA IF NOT EXISTS `PaloAlto_Test` DEFAULT CHARACTER SET utf8 ;
USE `PaloAlto_Test` ;
*/

/*
DROP SCHEMA IF EXISTS `PaloAlto` ;
CREATE SCHEMA IF NOT EXISTS `PaloAlto` DEFAULT CHARACTER SET utf8 ;
USE `PaloAlto` ;
*/


-- -----------------------------------------------------
-- Table `categories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `categories` ;

CREATE TABLE IF NOT EXISTS `categories` (
  `categoryId` INT(11) NOT NULL AUTO_INCREMENT,
  `categoryName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`categoryId`),
  UNIQUE INDEX `categoryName` (`categoryName` ASC),
  INDEX `categoryName_2` (`categoryName` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 276;


-- -----------------------------------------------------
-- Table `status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `status` ;

CREATE TABLE IF NOT EXISTS `status` (
  `statusId` INT(11) NOT NULL AUTO_INCREMENT,
  `statusName` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`statusId`),
  UNIQUE INDEX `categoryName` (`statusName` ASC),
  INDEX `categoryName_2` (`statusName` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 276;


-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `users` ;

CREATE TABLE IF NOT EXISTS `users` (
  `userId` INT(11) NOT NULL AUTO_INCREMENT,
  `displayName` VARCHAR(100) NOT NULL,
  `passwordSalt` CHAR(64) NOT NULL,
  `passwordHash` CHAR(64) NOT NULL,
  `siteRole` ENUM('user','admin','owner','developer') NOT NULL,
  `username` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `blogPosts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `blogPosts` ;

CREATE TABLE IF NOT EXISTS `blogPosts` (
  `postId` INT(11) NOT NULL AUTO_INCREMENT,
  `dateEdited` VARCHAR(20) NOT NULL,
  `startDate` VARCHAR(20) NULL DEFAULT NULL,
  `endDate` VARCHAR(20) NULL DEFAULT NULL,
  `title` VARCHAR(100) NOT NULL,
  `postBody` TEXT NOT NULL,
  `userIdFK` INT(11) NOT NULL,
  `titleNumber` VARCHAR(105) NOT NULL,
  `categoryIdFK` INT(11) NULL,
  `statusIdFK` INT(11) NULL,
  `postHrid` VARCHAR(150) NULL,
  PRIMARY KEY (`postId`),
  UNIQUE INDEX `readableUrl` (`titleNumber` ASC),
  INDEX `fk_blogPosts_1_idx` (`categoryIdFK` ASC),
  INDEX `fk_blogPosts_2_idx` (`statusIdFK` ASC),
  UNIQUE INDEX `postHrid_UNIQUE` (`postHrid` ASC),
  INDEX `fk_blogPosts_3_idx` (`userIdFK` ASC),
  CONSTRAINT `fk_blogPosts_1`
    FOREIGN KEY (`categoryIdFK`)
    REFERENCES `categories` (`categoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_blogPosts_2`
    FOREIGN KEY (`statusIdFK`)
    REFERENCES `status` (`statusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_blogPosts_3`
    FOREIGN KEY (`userIdFK`)
    REFERENCES `users` (`userId`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1082;


-- -----------------------------------------------------
-- Table `hashTags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hashTags` ;

CREATE TABLE IF NOT EXISTS `hashTags` (
  `hashTagId` INT(11) NOT NULL AUTO_INCREMENT,
  `hashTagName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`hashTagId`),
  UNIQUE INDEX `hashTagName_UNIQUE` (`hashTagName` ASC),
  UNIQUE INDEX `hashTagId_UNIQUE` (`hashTagId` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 38;


-- -----------------------------------------------------
-- Table `postHashTagBridge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `postHashTagBridge` ;

CREATE TABLE IF NOT EXISTS `postHashTagBridge` (
  `postIdFK` INT(11) NULL DEFAULT NULL,
  `HashTagIdFK` INT(11) NULL DEFAULT NULL,
  INDEX `HashTagIdFK` (`HashTagIdFK` ASC),
  INDEX `postHashTagBridge_ibfk_1_idx` (`postIdFK` ASC),
  CONSTRAINT `postHashTagBridge_ibfk_1`
    FOREIGN KEY (`postIdFK`)
    REFERENCES `blogPosts` (`postId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `postHashTagBridge_ibfk_2`
    FOREIGN KEY (`HashTagIdFK`)
    REFERENCES `hashTags` (`hashTagId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 39;


-- -----------------------------------------------------
-- Table `sideBarLinks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sideBarLinks` ;

CREATE TABLE IF NOT EXISTS `sideBarLinks` (
  `sideBarLinksId` INT(11) NOT NULL AUTO_INCREMENT,
  `staticPageName` VARCHAR(100) NOT NULL,
  `url` VARCHAR(150) NOT NULL,
  `jspPage` VARCHAR(45) NULL DEFAULT NULL,
  `javaScriptPage` VARCHAR(45) NULL DEFAULT NULL,
  `position` INT(11) NOT NULL,
  PRIMARY KEY (`sideBarLinksId`),
  UNIQUE INDEX `staticPageName_UNIQUE` (`staticPageName` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6;


-- -----------------------------------------------------
-- Table `staticPages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `staticPages` ;

CREATE TABLE IF NOT EXISTS `staticPages` (
  `pageId` INT(11) NOT NULL AUTO_INCREMENT,
  `dateEdited` VARCHAR(20) NOT NULL,
  `startDate` VARCHAR(20) NULL DEFAULT NULL,
  `endDate` VARCHAR(20) NULL DEFAULT NULL,
  `title` VARCHAR(100) NOT NULL,
  `pageBody` TEXT NOT NULL,
  `userIdFK` INT(11) NOT NULL,
  `titleNumber` VARCHAR(105) NOT NULL,
  `categoryIdFK` INT(11) NULL,
  `statusIdFK` INT(11) NULL,
  `pageHrid` VARCHAR(150) NULL,
  PRIMARY KEY (`pageId`),
  UNIQUE INDEX `readableUrl` (`titleNumber` ASC),
  INDEX `fk_staticPages_1_idx` (`categoryIdFK` ASC),
  INDEX `fk_staticPages_2_idx` (`statusIdFK` ASC),
  UNIQUE INDEX `pageHrid_UNIQUE` (`pageHrid` ASC),
  INDEX `fk_staticPages_3_idx` (`userIdFK` ASC),
  CONSTRAINT `fk_staticPages_1`
    FOREIGN KEY (`categoryIdFK`)
    REFERENCES `categories` (`categoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_staticPages_2`
    FOREIGN KEY (`statusIdFK`)
    REFERENCES `status` (`statusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_staticPages_3`
    FOREIGN KEY (`userIdFK`)
    REFERENCES `users` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1082;


-- -----------------------------------------------------
-- Table `pageHashTagBridge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pageHashTagBridge` ;

CREATE TABLE IF NOT EXISTS `pageHashTagBridge` (
  `pageIdFK` INT(11) NULL DEFAULT NULL,
  `HashTagIdFK` INT(11) NULL DEFAULT NULL,
  INDEX `HashTagIdFK` (`HashTagIdFK` ASC),
  INDEX `postHashTagBridge_ibfk_10_idx` (`pageIdFK` ASC),
  CONSTRAINT `pageHashTagBridge_ibfk_1`
    FOREIGN KEY (`pageIdFK`)
    REFERENCES `staticPages` (`pageId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `pageHashTagBridge_ibfk_20`
    FOREIGN KEY (`HashTagIdFK`)
    REFERENCES `hashTags` (`hashTagId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 39;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


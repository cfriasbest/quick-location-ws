-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema quick-location
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `quick-location` ;

-- -----------------------------------------------------
-- Schema quick-location
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quick-location` DEFAULT CHARACTER SET latin1 ;
USE `quick-location` ;

-- -----------------------------------------------------
-- Table `quick-location`.`place`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quick-location`.`place` ;

CREATE TABLE IF NOT EXISTS `quick-location`.`place` (
  `place_id` VARCHAR(255) NOT NULL,
  `lat` FLOAT(10,6) NULL DEFAULT NULL,
  `lng` FLOAT(10,6) NULL DEFAULT NULL,
  `name` VARCHAR(60) NULL DEFAULT NULL,
  `rating` FLOAT(10,6) NULL DEFAULT NULL,
  `vicinity` VARCHAR(80) NULL DEFAULT NULL,
  `formattedAddress` VARCHAR(255) NULL DEFAULT NULL,
  `formattedPhoneNumber` VARCHAR(255) NULL DEFAULT NULL,
  `reviewsCount` INT(11) NULL DEFAULT NULL,
  `updatesCount` INT(11) NULL DEFAULT NULL,
  `url` VARCHAR(255) NULL DEFAULT NULL,
  `category` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`place_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `quick-location`.`openinghours`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quick-location`.`openinghours` ;

CREATE TABLE IF NOT EXISTS `quick-location`.`openinghours` (
  `weekday_text` LONGTEXT NULL DEFAULT NULL,
  `idopenHours` INT(11) NOT NULL AUTO_INCREMENT,
  `place_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idopenHours`),
  INDEX `fk_openinghours_place1_idx` (`place_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `quick-location`.`report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quick-location`.`report` ;

CREATE TABLE IF NOT EXISTS `quick-location`.`report` (
  `id_report` INT(11) NOT NULL AUTO_INCREMENT,
  `field` VARCHAR(255) NULL DEFAULT NULL,
  `author` VARCHAR(255) NULL DEFAULT NULL,
  `field_human` VARCHAR(255) NULL DEFAULT NULL,
  `value` LONGTEXT NULL DEFAULT NULL,
  `done` INT(11) NULL DEFAULT NULL,
  `date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `place_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_report`),
  INDEX `fk_report_place1_idx1` (`place_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 39
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `quick-location`.`review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quick-location`.`review` ;

CREATE TABLE IF NOT EXISTS `quick-location`.`review` (
  `idreviews` INT(11) NOT NULL AUTO_INCREMENT,
  `author_name` VARCHAR(255) NULL DEFAULT NULL,
  `rating` VARCHAR(255) NULL DEFAULT NULL,
  `text` VARCHAR(1000) NULL DEFAULT NULL,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `done` INT(11) NULL DEFAULT '0',
  `place_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idreviews`),
  INDEX `fk_review_place_idx` (`place_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `quick-location`.`statistics`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quick-location`.`statistics` ;

CREATE TABLE IF NOT EXISTS `quick-location`.`statistics` (
  `idStatistics` INT(11) NOT NULL,
  `fecha` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `nickname` VARCHAR(100) NULL DEFAULT NULL,
  `placeId` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`idStatistics`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

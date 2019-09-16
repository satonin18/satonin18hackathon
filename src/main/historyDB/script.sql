-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hackathon
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hackathon
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hackathon` DEFAULT CHARACTER SET utf8 ;
USE `hackathon` ;

-- -----------------------------------------------------
-- Table `hackathon`.`cars`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hackathon`.`cars` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `vendor` VARCHAR(50) NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  `horsepower` INT UNSIGNED NOT NULL,
  `ownerId` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE INDEX `vendor_ind` ON `hackathon`.`cars` (`vendor` ASC) VISIBLE;

CREATE INDEX `ownerId_ind` ON `hackathon`.`cars` (`ownerId` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `hackathon`.`persons`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hackathon`.`persons` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `birthdate` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

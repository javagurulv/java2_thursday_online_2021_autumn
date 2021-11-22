SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `oddJobs` DEFAULT CHARACTER SET utf8 ;
USE `oddJobs` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE TABLE IF NOT EXISTS `Clients` (
  `clientId` BIGINT NOT NULL AUTO_INCREMENT,
  `clientName` VARCHAR(200) NOT NULL,
  `clientSurname` VARCHAR(100) NOT NULL,
  `personalCode` VARCHAR(15) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`clientId`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 000;

CREATE TABLE IF NOT EXISTS `Specialists` (
  `specialistId` BIGINT NOT NULL AUTO_INCREMENT,
  `specialistName` VARCHAR(200) NOT NULL,
  `specialistSurname` VARCHAR(100) NOT NULL,
  `specialistProfession` VARCHAR(100) NOT NULL,
  `personalCode` VARCHAR(15) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`specialistId`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 000;

CREATE TABLE IF NOT EXISTS `Advertisements` (
  `advId` BIGINT NOT NULL AUTO_INCREMENT,
  `advTitle` VARCHAR(200) NOT NULL,
  `advDescription` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`advId`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 000;

CREATE TABLE IF NOT EXISTS `specialists_advertisements` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `specialist_id` BIGINT NOT NULL,
  `advertisement_id` BIGINT NOT NULL,
  `work_out_date` DATETIME NOT NULL,
  `work_finish_date` DATETIME,
  PRIMARY KEY (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

ALTER TABLE `specialists_advertisements`
ADD FOREIGN KEY (`advertisement_id`) REFERENCES `advertisements`(`advId`);

ALTER TABLE `specialists_advertisements`
ADD FOREIGN KEY (`specialist_id`) REFERENCES `specialists`(`specialistId`);

CREATE INDEX ix_specialists_advertisements_advertisement_id
ON specialists_advertisements (advertisement_id);

CREATE INDEX ix_specialists_advertisements_specialists_id
ON specialists_advertisements (specialist_id);

CREATE UNIQUE INDEX clients_unique_index
on Clients (clientName,clientSurname,personalCode);

CREATE UNIQUE INDEX specialists_unique_index
on Specialists (specialistName,specialistSurname,personalCode);
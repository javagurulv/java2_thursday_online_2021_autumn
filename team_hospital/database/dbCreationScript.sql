SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `hospital` DEFAULT CHARACTER SET utf8mb4 ;
USE `hospital` ;

CREATE TABLE IF NOT EXISTS `doctors` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `surname` VARCHAR(100) NOT NULL,
  `speciality` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1001;

CREATE TABLE IF NOT EXISTS `patients` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `surname` VARCHAR(100) NOT NULL,
  `personal_code` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1001;

CREATE TABLE IF NOT EXISTS `visits` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `doctor_id` BIGINT NOT NULL,
  `patient_id` BIGINT NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `description` VARCHAR(200),
  PRIMARY KEY (id),
  FOREIGN KEY (`doctor_id`) REFERENCES `doctors`(`id`),
  FOREIGN KEY (`patient_id`) REFERENCES `patients`(`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1001;

CREATE TABLE IF NOT EXISTS `prescriptions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `doctor_id` BIGINT NOT NULL,
  `patient_id` BIGINT NOT NULL,
  `medication_name` VARCHAR(150) NOT NULL,
  `quantity` INTEGER NOT NULL,
  `date` DATE NOT NULL,
  `valid_till` DATE NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (`doctor_id`) REFERENCES `doctors`(`id`),
  FOREIGN KEY (`patient_id`) REFERENCES `patients`(`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1001;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
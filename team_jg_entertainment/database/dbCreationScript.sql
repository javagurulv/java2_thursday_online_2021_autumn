SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `jg_entertainment` DEFAULT CHARACTER SET utf8 ;
USE `jg_entertainment` ;

CREATE TABLE IF NOT EXISTS `visitors` (
  `visitor_id` BIGINT NOT NULL AUTO_INCREMENT,
  `visitor_name` VARCHAR(100) NOT NULL,
  `visitor_surname` VARCHAR(100) NOT NULL,
  `visitor_telephone_number` VARCHAR (50) NOT NULL,
  PRIMARY KEY (`visitor_id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1001;

ALTER TABLE `visitors`
  ADD `page_count` INT;

ALTER TABLE `visitors`
  ADD `description` VARCHAR(1000);


CREATE TABLE IF NOT EXISTS `tables` (
  `table_id` BIGINT NOT NULL AUTO_INCREMENT,
  `table_title` VARCHAR(100) NOT NULL,
  `table_capacity` BIGINT(50) NOT NULL,
  `table_price` DOUBLE (100,00) NOT NULL,
  PRIMARY KEY (`table_id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1001;


CREATE TABLE IF NOT EXISTS `menu` (
  `menu_id` BIGINT NOT NULL AUTO_INCREMENT,
  `menu_title` VARCHAR(100) NOT NULL,
  `menu_description` VARCHAR(200) NOT NULL,
  `menu_price` DOUBLE (100,00) NOT NULL,
   PRIMARY KEY (`menu_id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1001;


CREATE TABLE IF NOT EXISTS `reservation` (
    `reservation_id` BIGINT NOT NULL AUTO_INCREMENT,
    `id_visitor` BIGINT NOT NULL,
    `id_table` BIGINT NOT NULL,
    `id_menu` BIGINT NOT NULL,
    `reservation_date` TIMESTAMP NOT NULL,
    PRIMARY KEY (reservation_id),
    FOREIGN KEY (`id_visitor`) REFERENCES `visitors`(`visitor_id`),
    FOREIGN KEY (`id_table`) REFERENCES `tables`(`table_id`),
    FOREIGN KEY (`id_menu`) REFERENCES `menus`(`menu_id`)
  )
ENGINE = InnoDB
AUTO_INCREMENT = 1001;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


ALTER TABLE tables
DROP table_capacity;

ALTER TABLE tables
ADD column table_capacity INTEGER(50) NOT NULL;

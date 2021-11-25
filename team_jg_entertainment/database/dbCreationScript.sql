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
    FOREIGN KEY (`id_menu`) REFERENCES `menu`(`menu_id`)
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

UPDATE `jg_entertainment`.`tables` SET `table_capacity` = '4' WHERE (`table_id` = '1001');
UPDATE `jg_entertainment`.`tables` SET `table_capacity` = '4' WHERE (`table_id` = '1002');
UPDATE `jg_entertainment`.`tables` SET `table_capacity` = '4' WHERE (`table_id` = '1003');
UPDATE `jg_entertainment`.`tables` SET `table_capacity` = '6' WHERE (`table_id` = '1004');

ALTER TABLE visitors
DROP visitor_telephone_number;

ALTER TABLE visitors
ADD column visitor_telephone_number VARCHAR(50) NOT NULL;

UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3711' WHERE (`visitor_id` = '1001');
UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3712' WHERE (`visitor_id` = '1002');
UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3713' WHERE (`visitor_id` = '1003');
UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3714' WHERE (`visitor_id` = '1004');
UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3715' WHERE (`visitor_id` = '1005');
UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3716' WHERE (`visitor_id` = '1006');
UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3717' WHERE (`visitor_id` = '1007');
UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3718' WHERE (`visitor_id` = '1008');
UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3719' WHERE (`visitor_id` = '1009');
UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3720' WHERE (`visitor_id` = '1010');
UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3721' WHERE (`visitor_id` = '1011');
UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3722' WHERE (`visitor_id` = '1012');
UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3723' WHERE (`visitor_id` = '1013');
UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3724' WHERE (`visitor_id` = '1014');
UPDATE `jg_entertainment`.`visitors` SET `visitor_telephone_number` = '3725' WHERE (`visitor_id` = '1015');

ALTER TABLE visitors
ADD column access_level INTEGER NOT NULL;


select * from visitors;
select * from tables;
select * from menu;
select * from reservation;
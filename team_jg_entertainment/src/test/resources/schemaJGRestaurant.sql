DROP TABLE IF EXISTS visitors CASCADE;
DROP TABLE IF EXISTS tables CASCADE;
DROP TABLE IF EXISTS menu CASCADE;
DROP TABLE IF EXISTS reservation CASCADE;

CREATE TABLE IF NOT EXISTS `visitors` (
  `visitor_id` BIGINT NOT NULL AUTO_INCREMENT,
  `visitor_name` VARCHAR(100) NOT NULL,
  `visitor_surname` VARCHAR(100) NOT NULL,
  `visitor_telephone_number` VARCHAR (50) NOT NULL,
  PRIMARY KEY (`visitor_id`)
);

ALTER TABLE `visitors` ADD `page_count` INT;
ALTER TABLE `visitors` ADD `description` VARCHAR(1000);

CREATE TABLE IF NOT EXISTS `tables` (
  `table_id` BIGINT NOT NULL AUTO_INCREMENT,
  `table_title` VARCHAR(100) NOT NULL,
  `table_capacity` BIGINT(50) NOT NULL,
  `table_price` DOUBLE (100,00) NOT NULL,
  PRIMARY KEY (`table_id`)
);

CREATE TABLE IF NOT EXISTS `menu` (
  `menu_id` BIGINT NOT NULL AUTO_INCREMENT,
  `menu_title` VARCHAR(100) NOT NULL,
  `menu_description` VARCHAR(200) NOT NULL,
  `menu_price` DOUBLE (100,00) NOT NULL,
   PRIMARY KEY (`menu_id`)
);

CREATE TABLE IF NOT EXISTS `reservation` (
    `reservation_id` BIGINT NOT NULL AUTO_INCREMENT,
    `id_visitor` BIGINT NOT NULL,
    `id_table` BIGINT NOT NULL,
    `id_menu` BIGINT NOT NULL,
    `reservation_date` DATETIME NOT NULL,
    PRIMARY KEY (reservation_id),
    FOREIGN KEY (`id_visitor`) REFERENCES `visitors`(`visitor_id`),
    FOREIGN KEY (`id_table`) REFERENCES `tables`(`table_id`),
    FOREIGN KEY (`id_menu`) REFERENCES `menu`(`menu_id`)
);
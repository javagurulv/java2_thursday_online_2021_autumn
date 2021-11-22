DROP TABLE IF EXISTS Clients CASCADE;
DROP TABLE IF EXISTS Specialists CASCADE;
DROP TABLE IF EXISTS Advertisements CASCADE;
DROP TABLE IF EXISTS specialists_advertisements CASCADE;

CREATE TABLE IF NOT EXISTS `Clients` (
  `clientId` BIGINT NOT NULL AUTO_INCREMENT,
  `clientName` VARCHAR(200) NOT NULL,
  `clientSurname` VARCHAR(100) NOT NULL,
  `personalCode` VARCHAR(15) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`clientId`)
);


CREATE TABLE IF NOT EXISTS `Specialists` (
  `specialistId` BIGINT NOT NULL AUTO_INCREMENT,
  `specialistName` VARCHAR(200) NOT NULL,
  `specialistSurname` VARCHAR(100) NOT NULL,
  `specialistProfession` VARCHAR(100) NOT NULL,
  `personalCode` VARCHAR(15) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`specialistId`)
);


CREATE TABLE IF NOT EXISTS `Advertisements` (
  `advId` BIGINT NOT NULL AUTO_INCREMENT,
  `advTitle` VARCHAR(200) NOT NULL,
  `advDescription` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`advId`)
);

CREATE TABLE IF NOT EXISTS `specialists_advertisements` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `specialist_id` BIGINT NOT NULL,
  `advertisement_id` BIGINT NOT NULL,
  `work_out_date` DATETIME NOT NULL,
  `work_finish_date` DATETIME,
  PRIMARY KEY (id)
);

ALTER TABLE `specialists_advertisements`
ADD FOREIGN KEY (`advertisement_id`) REFERENCES `advertisements`(`advId`);

ALTER TABLE `specialists_advertisements`
ADD FOREIGN KEY (`specialist_id`) REFERENCES `specialists`(`specialistId`);

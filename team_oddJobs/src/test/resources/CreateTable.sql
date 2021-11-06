DROP TABLE IF EXISTS Clients CASCADE;
DROP TABLE IF EXISTS Specialists CASCADE;
DROP TABLE IF EXISTS Advertisements CASCADE;

CREATE TABLE IF NOT EXISTS `Clients` (
  `clientId` BIGINT NOT NULL AUTO_INCREMENT,
  `clientName` VARCHAR(200) NOT NULL,
  `clientSurname` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`clientId`)
);

CREATE TABLE IF NOT EXISTS `Specialists` (
  `specialistId` BIGINT NOT NULL AUTO_INCREMENT,
  `specialistName` VARCHAR(200) NOT NULL,
  `specialistSurname` VARCHAR(100) NOT NULL,
  `specialistProfession` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`specialistId`)
);

CREATE TABLE IF NOT EXISTS `Advertisements` (
  `advId` BIGINT NOT NULL AUTO_INCREMENT,
  `advTitle` VARCHAR(200) NOT NULL,
  `advDescription` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`advId`)
);

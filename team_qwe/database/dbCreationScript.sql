SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


CREATE SCHEMA IF NOT EXISTS `qwe` DEFAULT CHARACTER SET utf8 ;
USE `qwe` ;

CREATE TABLE IF NOT EXISTS `stocks` (
  `ticker` VARCHAR(10) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `industry` VARCHAR(50) NOT NULL,
  `currency` VARCHAR(3) NOT NULL,
  `market_price` DOUBLE(8,2) NOT NULL,
  `dividend_yield` DOUBLE(4,2) NOT NULL,
  `risk_weight` DOUBLE(5,4) NOT NULL,
  PRIMARY KEY (`ticker`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `bonds` (
  `ticker` VARCHAR(10) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `industry` VARCHAR(50) NOT NULL,
  `currency` VARCHAR(3) NOT NULL,
  `market_price` DOUBLE(8,2) NOT NULL,
  `coupon` DOUBLE(4,2) NOT NULL,
  `rating` VARCHAR(4),
  `nominal` INTEGER NOT NULL,
  `maturity` DATE NOT NULL,
  PRIMARY KEY (`ticker`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `age` INTEGER NOT NULL,
  `type` INTEGER NOT NULL,
  `initial_investment` DOUBLE(11,2),
  `cash` DOUBLE(11,2),
  `portfolio_generation_date` DATE,
  `risk_tolerance` INTEGER,
  PRIMARY KEY(`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `users_positions` (
  `position_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `security_ticker` VARCHAR(10) NOT NULL,
  `amount` DOUBLE(11, 2) NOT NULL,
  `purchase_price` DOUBLE(8,2) NOT NULL,
  PRIMARY KEY (`position_id`),
  FOREIGN KEY(`user_id`) REFERENCES `users`(`id`),
  FOREIGN KEY(`security_ticker`) REFERENCES `stocks`(`ticker`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `trades` (
  `trade_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `position_id` BIGINT NOT NULL,
  `trade_date` DATETIME NOT NULL,
  PRIMARY KEY (`trade_id`),
    FOREIGN KEY(`user_id`) REFERENCES `users`(`id`),
    FOREIGN KEY(`security_ticker`) REFERENCES `stocks`(`ticker`),
    FOREIGN KEY(`position_id`) REFERENCES `users_positions`(position_id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1000;

delimiter |

CREATE TRIGGER ins_sec AFTER INSERT ON users_positions
  FOR EACH ROW
  BEGIN
    UPDATE users, users_positions SET cash = cash - NEW.amount * NEW.purchase_price
    WHERE users.id = NEW.user_id;
  END;

|


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
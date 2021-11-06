DROP TABLE IF EXISTS stocks, bonds, users, users_positions CASCADE;
CREATE TABLE IF NOT EXISTS `stocks` (
  `ticker` VARCHAR(10) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `industry` VARCHAR(50) NOT NULL,
  `currency` CHAR(3) NOT NULL,
  `market_price` DECIMAL(8,2) NOT NULL,
  `dividend_yield` DECIMAL(4,2) NOT NULL,
  `risk_weight` DECIMAL(5,4) NOT NULL,
  PRIMARY KEY (`ticker`)
);
CREATE TABLE IF NOT EXISTS `bonds` (
  `ticker` VARCHAR(10) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `industry` VARCHAR(50) NOT NULL,
  `currency` CHAR(3) NOT NULL,
  `market_price` DECIMAL(8,2) NOT NULL,
  `coupon` DECIMAL(4,2) NOT NULL,
  `rating` CHAR(4),
  `nominal` DECIMAL(10,2) NOT NULL,
  `maturity` DATE NOT NULL,
  PRIMARY KEY (`ticker`)
);
CREATE TABLE IF NOT EXISTS `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `age` INTEGER NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `initial_investment` DECIMAL(11,2),
  `cash` DECIMAL(11,2),
  `portfolio_generation_date` DATE,
  `risk_tolerance` INTEGER,
  PRIMARY KEY(`id`)
);
CREATE TABLE IF NOT EXISTS `users_positions` (
  `user_id` BIGINT NOT NULL,
  `security_ticker` VARCHAR(10) NOT NULL,
  `amount` INTEGER NOT NULL,
  `purchase_price` DECIMAL(8,2) NOT NULL,
  FOREIGN KEY(`user_id`) REFERENCES `users`(`id`),
  FOREIGN KEY(`security_ticker`) REFERENCES `stocks`(`ticker`)
);
CREATE TRIGGER IF NOT EXISTS ins_sec
  AFTER INSERT ON users_positions
  FOR EACH ROW CALL "lv.javaguru.java2.qwe.acceptance_test.AcceptanceTestForUserPortfolio$MyTrigger";
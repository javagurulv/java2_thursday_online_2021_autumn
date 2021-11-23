DROP TABLE IF EXISTS stocks, bonds, users, users_positions, trades CASCADE;
CREATE TABLE IF NOT EXISTS `stocks` (
  `ticker` VARCHAR(10) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `industry` VARCHAR(50) NOT NULL,
  `currency` VARCHAR(3) NOT NULL,
  `market_price` DOUBLE NOT NULL,
  `dividend_yield` DOUBLE NOT NULL,
  `risk_weight` DOUBLE NOT NULL,
  PRIMARY KEY (`ticker`)
);
CREATE TABLE IF NOT EXISTS `bonds` (
  `ticker` VARCHAR(10) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `industry` VARCHAR(50) NOT NULL,
  `currency` VARCHAR(3) NOT NULL,
  `market_price` DOUBLE NOT NULL,
  `coupon` DOUBLE NOT NULL,
  `rating` VARCHAR(4),
  `nominal` INTEGER NOT NULL,
  `maturity` DATE NOT NULL,
  PRIMARY KEY (`ticker`)
);
CREATE TABLE IF NOT EXISTS `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `age` INTEGER NOT NULL,
  `type` INTEGER NOT NULL,
  `initial_investment` DOUBLE,
  `cash` DOUBLE,
  `portfolio_generation_date` DATE,
  `risk_tolerance` INTEGER,
  PRIMARY KEY(`id`)
);
CREATE TABLE IF NOT EXISTS `users_positions` (
  `position_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `security_ticker` VARCHAR(10) NOT NULL,
  `amount` DOUBLE NOT NULL,
  `purchase_price` DOUBLE NOT NULL,
  `is_trade` BOOLEAN DEFAULT TRUE,
  PRIMARY KEY (`position_id`),
  FOREIGN KEY(`user_id`) REFERENCES `users`(`id`),
  FOREIGN KEY(`security_ticker`) REFERENCES `stocks`(`ticker`)
);
CREATE TABLE IF NOT EXISTS `trades` (
  `trade_id` BIGINT NOT NULL AUTO_INCREMENT (1000),
  `user_id` BIGINT NOT NULL,
  `security_ticker` VARCHAR(10) NOT NULL,
  `trade_type` INTEGER NOT NULL,
  `quantity` DOUBLE NOT NULL,
  `trade_price` DOUBLE NOT NULL,
  `trade_date` DATETIME NOT NULL,
  PRIMARY KEY (`trade_id`),
  FOREIGN KEY(`user_id`) REFERENCES `users`(`id`),
  FOREIGN KEY(`security_ticker`) REFERENCES `stocks`(`ticker`)
);
CREATE TRIGGER IF NOT EXISTS ins_sec
  AFTER INSERT ON users_positions
  FOR EACH ROW CALL "lv.javaguru.java2.qwe.acceptance_test.AcceptanceTestForUserPortfolio$MyTrigger";

CREATE TRIGGER IF NOT EXISTS del_pos
  AFTER DELETE ON users_positions
  FOR EACH ROW CALL "lv.javaguru.java2.qwe.acceptance_test.AcceptanceTestForStockMarketOrder$MyTrigger2";
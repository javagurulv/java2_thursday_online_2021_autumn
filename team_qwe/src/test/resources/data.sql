INSERT INTO stocks VALUES
  ('AAPL US','Apple Inc.','Technology','USD',148.19,0.59,1),
  ('MSFT US','Microsoft Corporation','Technology','USD',304.36,0.74,0.88),
  ('AMZN US','Amazon.com Inc.','Consumer Discretionary','USD',3199.95,0,0.69),
  ('TSLA US','Tesla Inc','Consumer Discretionary','USD',680.26,0,1.63),
  ('JPM US','JPMorgan Chase & Co.','Financials','USD',154.72,2.33,1.15);
INSERT INTO users (name, age, type, initial_investment, cash, portfolio_generation_date, risk_tolerance) VALUES
  ('Alexander', 25, 4, 1000000.00, 1000000.00, NULL, 5),
  ('Tatyana', 32, 2, 125000.00, 125000.00, NULL, 4),
  ('Vladimir', 78, 0, 30000.00, 30000.00, NULL, 1),
  ('John', 21, 4, 500000.00, 500000.00, NULL, 3);
INSERT INTO users_positions (user_id, security_ticker, amount, purchase_price) VALUES
  (4, 'MSFT US', 1000, 304.36);
--  (4, 'TSLA US', 50, 680.26);
package lv.javaguru.java2.qwe.acceptance_test;

import lv.javaguru.java2.qwe.config.AppConfiguration;
import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.requests.user_requests.GenerateUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GenerateUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.user_services.GenerateUserPortfolioService;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserPortfolioService;
import org.h2.api.Trigger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
public class AcceptanceTestForUserPortfolio {

    @Autowired private ApplicationContext appContext;
    @Autowired private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        jdbcTemplate.update("DROP TABLE IF EXISTS stocks, bonds, users, users_positions CASCADE");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS `stocks` (\n" +
                "  `ticker` VARCHAR(10) NOT NULL,\n" +
                "  `name` VARCHAR(100) NOT NULL,\n" +
                "  `industry` VARCHAR(50) NOT NULL,\n" +
                "  `currency` CHAR(3) NOT NULL,\n" +
                "  `market_price` DECIMAL(8,2) NOT NULL,\n" +
                "  `dividend_yield` DECIMAL(4,2) NOT NULL,\n" +
                "  `risk_weight` DECIMAL(5,4) NOT NULL,\n" +
                "  PRIMARY KEY (`ticker`)\n" +
                ")");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS `bonds` (\n" +
                "  `ticker` VARCHAR(10) NOT NULL,\n" +
                "  `name` VARCHAR(100) NOT NULL,\n" +
                "  `industry` VARCHAR(50) NOT NULL,\n" +
                "  `currency` CHAR(3) NOT NULL,\n" +
                "  `market_price` DECIMAL(8,2) NOT NULL,\n" +
                "  `coupon` DECIMAL(4,2) NOT NULL,\n" +
                "  `rating` CHAR(4),\n" +
                "  `nominal` DECIMAL(10,2) NOT NULL,\n" +
                "  `maturity` DATE NOT NULL,\n" +
                "  PRIMARY KEY (`ticker`)\n" +
                ")");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS `users` (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(100) NOT NULL,\n" +
                "  `age` INTEGER NOT NULL,\n" +
                "  `type` VARCHAR(50) NOT NULL,\n" +
                "  `initial_investment` DECIMAL(11,2),\n" +
                "  `cash` DECIMAL(11,2),\n" +
                "  `portfolio_generation_date` DATE,\n" +
                "  `risk_tolerance` INTEGER,\n" +
                "  PRIMARY KEY(`id`)\n" +
                ")");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS `users_positions` (\n" +
                "  `user_id` BIGINT NOT NULL,\n" +
                "  `security_ticker` VARCHAR(10) NOT NULL,\n" +
                "  `amount` INTEGER NOT NULL,\n" +
                "  `purchase_price` DECIMAL(8,2) NOT NULL,\n" +
                "  FOREIGN KEY(`user_id`) REFERENCES `users`(`id`),\n" +
                "  FOREIGN KEY(`security_ticker`) REFERENCES `stocks`(`ticker`)\n" +
                ")");
        jdbcTemplate.update("CREATE TRIGGER ins_sec AFTER INSERT ON users_positions\n" +
                "  FOR EACH ROW CALL \"lv.javaguru.java2.qwe.acceptance_test.AcceptanceTestForUserPortfolio$MyTrigger\" ");
        jdbcTemplate.update("INSERT INTO stocks VALUES\n" +
                "  ('AAPL US','Apple Inc.','Technology','USD',148.19,0.59,1),\n" +
                "  ('MSFT US','Microsoft Corporation','Technology','USD',304.36,0.74,0.88),\n" +
                "  ('AMZN US','Amazon.com Inc.','Consumer Discretionary','USD',3199.95,0,0.69),\n" +
                "  ('FB US','Facebook Inc. Class A','Communications','USD',359.37,0,0.99),\n" +
                "  ('GOOGL US','Alphabet Inc. Class A','Communications','USD',2748.59,0,0.91),\n" +
                "  ('GOOG US','Alphabet Inc. Class C','Communications','USD',2768.74,0,0.91),\n" +
                "  ('BRK/B US','Berkshire Hathaway Inc. Class B','Financials','USD',285.27,0,0.87),\n" +
                "  ('TSLA US','Tesla Inc','Consumer Discretionary','USD',680.26,0,1.63),\n" +
                "  ('NVDA US','NVIDIA Corporation','Technology','USD',208.16,0.08,1.12),\n" +
                "  ('JNJ US','Johnson & Johnson','Health Care','USD',179.44,2.36,0.71),\n" +
                "  ('JPM US','JPMorgan Chase & Co.','Financials','USD',154.72,2.33,1.15),\n" +
                "  ('UNH US','UnitedHealth Group Incorporated','Health Care','USD',429.71,1.35,1.18),\n" +
                "  ('V US','Visa Inc. Class A','Technology','USD',231.36,0.55,1.03),\n" +
                "  ('PG US','Procter & Gamble Company','Consumer Staples','USD',145.09,2.4,0.72),\n" +
                "  ('HD US','Home Depot Inc.','Consumer Discretionary','USD',329.24,2,1.2),\n" +
                "  ('PYPL US','PayPal Holdings Inc','Technology','USD',272.96,0,1.09),\n" +
                "  ('DIS US','Walt Disney Company','Communications','USD',175.12,0,1.06),\n" +
                "  ('MA US','Mastercard Incorporated Class A','Technology','USD',354.99,0.5,1.18),\n" +
                "  ('ADBE US','Adobe Inc.','Technology','USD',647.34,0,0.84),\n" +
                "  ('BAC US','Bank of America Corp','Financials','USD',40.37,2.08,1.23),\n" +
                "  ('PFE US','Pfizer Inc.','Health Care','USD',48.72,3.2,0.72),\n" +
                "  ('CMCSA US','Comcast Corporation Class A','Communications','USD',59.41,1.68,0.88),\n" +
                "  ('CRM US','salesforce.com inc.','Technology','USD',256.13,0,0.97),\n" +
                "  ('CSCO US','Cisco Systems Inc.','Technology','USD',58.22,2.54,0.82),\n" +
                "  ('NFLX US','Netflix Inc.','Communications','USD',546.88,0,0.69),\n" +
                "  ('VZ US','Verizon Communications Inc.','Communications','USD',55.52,4.52,0.6),\n" +
                "  ('ABT US','Abbott Laboratories','Health Care','USD',126.32,1.42,0.84),\n" +
                "  ('XOM US','Exxon Mobil Corporation','Energy','USD',52.74,6.6,1.1),\n" +
                "  ('KO US','Coca-Cola Company','Consumer Staples','USD',56.64,2.97,1),\n" +
                "  ('PEP US','PepsiCo Inc.','Consumer Staples','USD',158.35,2.72,0.88),\n" +
                "  ('TMO US','Thermo Fisher Scientific Inc.','Health Care','USD',555,0.19,0.79),\n" +
                "  ('LLY US','Eli Lilly and Company','Health Care','USD',270.91,1.26,0.74),\n" +
                "  ('INTC US','Intel Corporation','Technology','USD',52.01,2.67,0.95),\n" +
                "  ('NKE US','NIKE Inc. Class B','Consumer Discretionary','USD',167.79,0.66,1.1),\n" +
                "  ('ACN US','Accenture Plc Class A','Technology','USD',332.65,1.06,0.98),\n" +
                "  ('ABBV US','AbbVie Inc.','Health Care','USD',118.82,4.38,0.86),\n" +
                "  ('WMT US','Walmart Inc.','Consumer Staples','USD',151.45,1.45,0.55),\n" +
                "  ('DHR US','Danaher Corporation','Health Care','USD',321.54,0.26,0.83),\n" +
                "  ('COST US','Costco Wholesale Corporation','Consumer Staples','USD',458.99,0.69,0.69),\n" +
                "  ('MRK US','Merck & Co. Inc.','Health Care','USD',78.68,3.3,0.66),\n" +
                "  ('T US','AT&T Inc.','Communications','USD',27.57,7.54,0.88),\n" +
                "  ('WFC US','Wells Fargo & Company','Financials','USD',47.42,1.69,1.26),\n" +
                "  ('AVGO US','Broadcom Inc.','Technology','USD',475.17,3.03,1.08),\n" +
                "  ('CVX US','Chevron Corporation','Energy','USD',94.3,5.68,1.2),\n" +
                "  ('MCD US','McDonald''s Corporation','Consumer Discretionary','USD',238.49,2.16,0.93),\n" +
                "  ('MDT US','Medtronic Plc','Health Care','USD',129.9,1.94,1.08),\n" +
                "  ('TXN US','Texas Instruments Incorporated','Technology','USD',184.97,2.21,0.89),\n" +
                "  ('NEE US','NextEra Energy Inc.','Utilities','USD',86.28,1.78,0.99),\n" +
                "  ('LIN US','Linde plc','Materials','USD',313.85,1.35,1.1),\n" +
                "  ('ORCL US','Oracle Corporation','Technology','USD',88.94,1.44,0.71),\n" +
                "  ('QCOM US','Qualcomm Inc','Technology','USD',142.09,1.91,1.06),\n" +
                "  ('HON US','Honeywell International Inc.','Industrials','USD',227.7,1.63,1.2),\n" +
                "  ('PM US','Philip Morris International Inc.','Consumer Staples','USD',101.42,4.73,0.92),\n" +
                "  ('BMY US','Bristol-Myers Squibb Company','Health Care','USD',69.2,2.83,0.8),\n" +
                "  ('UNP US','Union Pacific Corporation','Industrials','USD',223.71,1.91,1.07),\n" +
                "  ('INTU US','Intuit Inc.','Technology','USD',545.3,0.43,1.12),\n" +
                "  ('MS US','Morgan Stanley','Financials','USD',100.66,2.78,1.29),\n" +
                "  ('C US','Citigroup Inc.','Financials','USD',70.25,2.9,1.46),\n" +
                "  ('LOW US','Lowe''s Companies Inc.','Consumer Discretionary','USD',208.21,1.54,1.35),\n" +
                "  ('UPS US','United Parcel Service Inc. Class B','Industrials','USD',193.24,2.11,0.84),\n" +
                "  ('SBUX US','Starbucks Corporation','Consumer Discretionary','USD',114.63,1.57,1.09),\n" +
                "  ('GS US','Goldman Sachs Group Inc.','Financials','USD',395.87,2.02,1.29),\n" +
                "  ('AMT US','American Tower Corporation','Real Estate','USD',290.82,1.75,0.94),\n" +
                "  ('AMGN US','Amgen Inc.','Health Care','USD',223.53,3.15,0.74),\n" +
                "  ('BLK US','BlackRock Inc.','Financials','USD',917.17,1.8,1.14),\n" +
                "  ('RTX US','Raytheon Technologies Corporation','Industrials','USD',84.05,2.43,1.4),\n" +
                "  ('AMD US','Advanced Micro Devices Inc.','Technology','USD',104.65,0,1.14),\n" +
                "  ('ISRG US','Intuitive Surgical Inc.','Health Care','USD',1043.82,0,1.18),\n" +
                "  ('IBM US','International Business Machines Corporation','Technology','USD',139.11,4.72,0.99),\n" +
                "  ('TGT US','Target Corporation','Consumer Staples','USD',253.4,1.42,0.74),\n" +
                "  ('AMAT US','Applied Materials Inc.','Technology','USD',127.2,0.75,1.38),\n" +
                "  ('BA US','Boeing Company','Industrials','USD',212.67,0,2.18),\n" +
                "  ('NOW US','ServiceNow Inc.','Technology','USD',600.52,0,0.86),\n" +
                "  ('MRNA US','Moderna Inc.','Health Care','USD',382.98,0,0.11),\n" +
                "  ('MMM US','3M Company','Industrials','USD',194.16,3.05,0.85),\n" +
                "  ('DE US','Deere & Company','Industrials','USD',351.43,1.02,1.16),\n" +
                "  ('CAT US','Caterpillar Inc.','Industrials','USD',204.94,2.17,0.92),\n" +
                "  ('CVS US','CVS Health Corporation','Health Care','USD',84.85,2.36,0.98),\n" +
                "  ('GE US','General Electric Company','Industrials','USD',100.05,0.32,1.15),\n" +
                "  ('CHTR US','Charter Communications Inc. Class A','Communications','USD',794.09,0,0.92),\n" +
                "  ('SCHW US','Charles Schwab Corporation','Financials','USD',71.97,1,0.96),\n" +
                "  ('SPGI US','S&P Global Inc.','Technology','USD',437.52,0.7,1.01),\n" +
                "  ('AXP US','American Express Company','Financials','USD',159.75,1.08,1.44),\n" +
                "  ('PLD US','Prologis Inc.','Real Estate','USD',134.25,1.88,1.19),\n" +
                "  ('ZTS US','Zoetis Inc. Class A','Health Care','USD',207.05,0.48,0.93),\n" +
                "  ('ANTM US','Anthem Inc.','Health Care','USD',377.99,1.2,1.26),\n" +
                "  ('ADP US','Automatic Data Processing Inc.','Technology','USD',211.58,1.76,1.06),\n" +
                "  ('GILD US','Gilead Sciences Inc.','Health Care','USD',72.44,3.92,0.46),\n" +
                "  ('MDLZ US','Mondelez International Inc. Class A','Consumer Staples','USD',63.49,2.21,0.82),\n" +
                "  ('MO US','Altria Group Inc','Consumer Staples','USD',48.47,7.1,0.89),\n" +
                "  ('TJX US','TJX Companies Inc','Consumer Discretionary','USD',74.04,1.4,1.33),\n" +
                "  ('LMT US','Lockheed Martin Corporation','Industrials','USD',357.17,2.91,0.96),\n" +
                "  ('SYK US','Stryker Corporation','Health Care','USD',265.28,0.95,1.2),\n" +
                "  ('CCI US','Crown Castle International Corp','Real Estate','USD',198.05,2.69,0.88),\n" +
                "  ('BKNG US','Booking Holdings Inc.','Communications','USD',2074.74,0,1.21),\n" +
                "  ('CB US','Chubb Limited','Financials','USD',186.21,1.72,1.13),\n" +
                "  ('TMUS US','T-Mobile US Inc.','Communications','USD',141.81,0,0.83),\n" +
                "  ('LRCX US','Lam Research Corporation','Technology','USD',565.97,0.92,1.5),\n" +
                "  ('DUK US','Duke Energy Corporation','Utilities','USD',107.21,3.68,0.98),\n" +
                "  ('FIS US','Fidelity National Information Services Inc.','Technology','USD',129.98,1.2,1.05),\n" +
                "  ('MU US','Micron Technology Inc.','Technology','USD',70.23,0.57,1.18),\n" +
                "  ('MMC US','Marsh & McLennan Companies Inc.','Financials','USD',155.13,1.38,1.03),\n" +
                "  ('PNC US','PNC Financial Services Group Inc.','Financials','USD',185.82,2.69,1.21),\n" +
                "  ('CSX US','CSX Corporation','Industrials','USD',33.73,1.11,1.02),\n" +
                "  ('EL US','Estee Lauder Companies Inc. Class A','Consumer Staples','USD',330.18,0.64,1.01),\n" +
                "  ('COF US','Capital One Financial Corporation','Financials','USD',168.22,1.43,1.65),\n" +
                "  ('USB US','U.S. Bancorp','Financials','USD',55.68,3.02,1.15),\n" +
                "  ('EQIX US','Equinix Inc.','Real Estate','USD',835.63,1.37,0.9),\n" +
                "  ('TFC US','Truist Financial Corporation','Financials','USD',55.58,3.45,1.31),\n" +
                "  ('EW US','Edwards Lifesciences Corporation','Health Care','USD',118.95,0,1.06),\n" +
                "  ('ADSK US','Autodesk Inc.','Technology','USD',334.38,0,0.94),\n" +
                "  ('SHW US','Sherwin-Williams Company','Materials','USD',307.06,0.72,0.99),\n" +
                "  ('BDX US','Becton Dickinson and Company','Health Care','USD',249.3,1.33,0.57),\n" +
                "  ('CME US','CME Group Inc. Class A','Financials','USD',198.36,1.81,0.86),\n" +
                "  ('COP US','ConocoPhillips','Energy','USD',52.77,3.26,1.18),\n" +
                "  ('CI US','Cigna Corporation','Health Care','USD',207.53,1.93,1.17),\n" +
                "  ('SO US','Southern Company','Utilities','USD',67.32,3.92,1.11),\n" +
                "  ('FISV US','Fiserv Inc.','Technology','USD',116.09,0,1.06),\n" +
                "  ('REGN US','Regeneron Pharmaceuticals Inc.','Health Care','USD',663.27,0,0.6),\n" +
                "  ('ILMN US','Illumina Inc.','Health Care','USD',486.71,0,0.81),\n" +
                "  ('CL US','Colgate-Palmolive Company','Consumer Staples','USD',78.8,2.28,0.69),\n" +
                "  ('ETN US','Eaton Corp. Plc','Industrials','USD',167.46,1.82,1.2),\n" +
                "  ('ITW US','Illinois Tool Works Inc.','Industrials','USD',230.57,2.12,1.06),\n" +
                "  ('NSC US','Norfolk Southern Corporation','Industrials','USD',262.27,1.66,1.07),\n" +
                "  ('FDX US','FedEx Corporation','Industrials','USD',266.55,1.13,1),\n" +
                "  ('ICE US','Intercontinental Exchange Inc.','Financials','USD',117.15,1.13,0.84),\n" +
                "  ('HCA US','HCA Healthcare Inc','Health Care','USD',249.34,0.77,1.46),\n" +
                "  ('ATVI US','Activision Blizzard Inc.','Communications','USD',82.63,0.57,0.77),\n" +
                "  ('D US','Dominion Energy Inc','Utilities','USD',80.05,3.15,0.88),\n" +
                "  ('BSX US','Boston Scientific Corporation','Health Care','USD',44.44,0,1.16),\n" +
                "  ('GM US','General Motors Company','Consumer Discretionary','USD',48.8,0,1.48),\n" +
                "  ('AON US','Aon Plc Class A','Financials','USD',279.37,0.73,1.11),\n" +
                "  ('ADI US','Analog Devices Inc.','Technology','USD',166.64,1.66,1.02),\n" +
                "  ('EMR US','Emerson Electric Co.','Industrials','USD',101.7,1.99,1.06),\n" +
                "  ('MCO US','Moody''s Corporation','Technology','USD',376.42,0.66,1.12),\n" +
                "  ('APD US','Air Products and Chemicals Inc.','Materials','USD',267.44,2.24,0.9),\n" +
                "  ('WM US','Waste Management Inc.','Industrials','USD',152.94,1.5,0.82),\n" +
                "  ('IDXX US','IDEXX Laboratories Inc.','Health Care','USD',682.14,0,1.06),\n" +
                "  ('PGR US','Progressive Corporation','Financials','USD',97.57,0.41,0.78),\n" +
                "  ('NXPI US','NXP Semiconductors NV','Technology','USD',205,1.1,1.33),\n" +
                "  ('DG US','Dollar General Corporation','Consumer Staples','USD',234.78,0.72,0.68),\n" +
                "  ('ECL US','Ecolab Inc.','Materials','USD',222.06,0.86,1.17),\n" +
                "  ('NOC US','Northrop Grumman Corporation','Industrials','USD',363,1.73,0.79),\n" +
                "  ('HUM US','Humana Inc.','Health Care','USD',413.61,0.68,1.27),\n" +
                "  ('CMG US','Chipotle Mexican Grill Inc.','Consumer Discretionary','USD',1891.54,0,1.12),\n" +
                "  ('JCI US','Johnson Controls International plc','Industrials','USD',73.4,1.47,1.15),\n" +
                "  ('A US','Agilent Technologies Inc.','Health Care','USD',168.13,0.46,0.86),\n" +
                "  ('BIIB US','Biogen Inc.','Health Care','USD',341.74,0,0.84),\n" +
                "  ('VRTX US','Vertex Pharmaceuticals Incorporated','Health Care','USD',195.85,0,0.65),\n" +
                "  ('MSCI US','MSCI Inc. Class A','Technology','USD',618.86,0.67,0.92),\n" +
                "  ('ROP US','Roper Technologies Inc.','Technology','USD',479.94,0.47,0.87),\n" +
                "  ('KLAC US','KLA Corporation','Technology','USD',319.97,1.31,1.28),\n" +
                "  ('TWTR US','Twitter Inc.','Communications','USD',62.52,0,1.2),\n" +
                "  ('F US','Ford Motor Company','Consumer Discretionary','USD',12.57,0,1.38),\n" +
                "  ('IQV US','IQVIA Holdings Inc','Health Care','USD',254.7,0,1.25),\n" +
                "  ('PSA US','Public Storage','Real Estate','USD',324.28,2.47,0.85),\n" +
                "  ('ALGN US','Align Technology Inc.','Health Care','USD',681.57,0,1.46),\n" +
                "  ('TEL US','TE Connectivity Ltd.','Industrials','USD',148.06,1.35,1.26),\n" +
                "  ('DXCM US','DexCom Inc.','Health Care','USD',518.99,0,1.05),\n" +
                "  ('TROW US','T. Rowe Price Group','Financials','USD',215.47,2,1.05),\n" +
                "  ('SNPS US','Synopsys Inc.','Technology','USD',316.88,0,1.02),\n" +
                "  ('GPN US','Global Payments Inc.','Technology','USD',163.06,0.61,1.33),\n" +
                "  ('EXC US','Exelon Corporation','Utilities','USD',49.36,3.1,1.17),\n" +
                "  ('LHX US','L3Harris Technologies Inc','Industrials','USD',231.14,1.77,1),\n" +
                "  ('FCX US','Freeport-McMoRan Inc.','Materials','USD',32.8,0.91,1.59),\n" +
                "  ('EBAY US','eBay Inc.','Consumer Discretionary','USD',73.36,0.98,1.08),\n" +
                "  ('KMB US','Kimberly-Clark Corporation','Consumer Staples','USD',138.23,3.3,0.71),\n" +
                "  ('TT US','Trane Technologies plc','Industrials','USD',193.41,1.22,1.17),\n" +
                "  ('AIG US','American International Group Inc.','Financials','USD',53.57,2.39,1.63),\n" +
                "  ('DLR US','Digital Realty Trust Inc.','Real Estate','USD',162.77,2.85,0.74),\n" +
                "  ('NEM US','Newmont Corporation','Materials','USD',56.35,3.9,0.77),\n" +
                "  ('CARR US','Carrier Global Corp.','Industrials','USD',55.37,0.87,0.75),\n" +
                "  ('DOW US','Dow Inc.','Materials','USD',60.89,4.6,1.13),\n" +
                "  ('ROST US','Ross Stores Inc.','Consumer Discretionary','USD',123.12,0.93,1.45),\n" +
                "  ('AEP US','American Electric Power Company Inc.','Utilities','USD',91.08,3.25,1),\n" +
                "  ('MET US','MetLife Inc.','Financials','USD',60.51,3.17,1.46),\n" +
                "  ('GD US','General Dynamics Corporation','Industrials','USD',197.3,2.41,1.16),\n" +
                "  ('APH US','Amphenol Corporation Class A','Industrials','USD',74.47,0.78,1.1),\n" +
                "  ('INFO US','IHS Markit Ltd.','Technology','USD',118.1,0.68,1.03),\n" +
                "  ('CDNS US','Cadence Design Systems Inc.','Technology','USD',156.67,0,0.97),\n" +
                "  ('BK US','Bank of New York Mellon Corporation','Financials','USD',53.32,2.55,1.07),\n" +
                "  ('ORLY US','O''Reilly Automotive Inc.','Consumer Discretionary','USD',606.41,0,1.03),\n" +
                "  ('SPG US','Simon Property Group Inc.','Real Estate','USD',128.77,4.66,1.86),\n" +
                "  ('SRE US','Sempra Energy','Utilities','USD',133.36,3.3,1.1),\n" +
                "  ('APTV US','Aptiv PLC','Consumer Discretionary','USD',154.65,0,1.59),\n" +
                "  ('PRU US','Prudential Financial Inc.','Financials','USD',104.34,4.41,1.44),\n" +
                "  ('RMD US','ResMed Inc.','Health Care','USD',285.69,0.59,0.95),\n" +
                "  ('EA US','Electronic Arts Inc.','Communications','USD',140.17,0.49,0.72),\n" +
                "  ('TRV US','Travelers Companies Inc.','Financials','USD',160.84,2.19,1.13),\n" +
                "  ('CTSH US','Cognizant Technology Solutions Corporation Class A','Technology','USD',76.7,1.25,1.07),\n" +
                "  ('YUM US','Yum! Brands Inc.','Consumer Discretionary','USD',134.46,1.49,1.25),\n" +
                "  ('ALL US','Allstate Corporation','Financials','USD',134.56,2.41,1.12),\n" +
                "  ('MSI US','Motorola Solutions Inc.','Technology','USD',240,1.18,0.96),\n" +
                "  ('FTNT US','Fortinet Inc.','Technology','USD',297.94,0,0.92),\n" +
                "  ('MCHP US','Microchip Technology Incorporated','Technology','USD',144.84,1.21,1.38),\n" +
                "  ('SBAC US','SBA Communications Corp. Class A','Real Estate','USD',362.16,0.64,0.9),\n" +
                "  ('DD US','DuPont de Nemours Inc.','Materials','USD',72.85,1.65,1.14),\n" +
                "  ('DFS US','Discover Financial Services','Financials','USD',127.26,1.57,2.03),\n" +
                "  ('SYY US','Sysco Corporation','Consumer Staples','USD',76.79,2.45,1.66),\n" +
                "  ('PPG US','PPG Industries Inc.','Materials','USD',161.12,1.46,1.08),\n" +
                "  ('BAX US','Baxter International Inc.','Health Care','USD',75.19,1.49,0.69),\n" +
                "  ('XEL US','Xcel Energy Inc.','Utilities','USD',70.61,2.59,0.99),\n" +
                "  ('EOG US','EOG Resources Inc.','Energy','USD',64.47,2.56,1.14),\n" +
                "  ('CNC US','Centene Corporation','Health Care','USD',64.31,0,1.14),\n" +
                "  ('IFF US','International Flavors & Fragrances Inc.','Materials','USD',149.76,2.11,1.06),\n" +
                "  ('PH US','Parker-Hannifin Corporation','Industrials','USD',287.85,1.43,1.4),\n" +
                "  ('PAYX US','Paychex Inc.','Technology','USD',115.42,2.29,1.14),\n" +
                "  ('SLB US','Schlumberger NV','Energy','USD',26.46,1.89,1.25),\n" +
                "  ('GIS US','General Mills Inc.','Consumer Staples','USD',60.3,3.38,0.47),\n" +
                "  ('OTIS US','Otis Worldwide Corporation','Industrials','USD',90.02,1.07,0.63),\n" +
                "  ('ROK US','Rockwell Automation Inc.','Industrials','USD',315.05,1.36,1.13),\n" +
                "  ('STZ US','Constellation Brands Inc. Class A','Consumer Staples','USD',213.83,1.42,1.34),\n" +
                "  ('MTD US','Mettler-Toledo International Inc.','Health Care','USD',1530.51,0,0.9),\n" +
                "  ('MNST US','Monster Beverage Corporation','Consumer Staples','USD',96.74,0,0.97),\n" +
                "  ('AFL US','Aflac Incorporated','Financials','USD',56.36,2.34,1.35),\n" +
                "  ('AZO US','AutoZone Inc.','Consumer Discretionary','USD',1640.76,0,1.08),\n" +
                "  ('WELL US','Welltower Inc.','Real Estate','USD',84.22,2.9,1.55),\n" +
                "  ('XLNX US','Xilinx Inc.','Technology','USD',144.27,0,0.86),\n" +
                "  ('NUE US','Nucor Corporation','Materials','USD',116.42,1.39,1.04),\n" +
                "  ('KR US','Kroger Co.','Consumer Staples','USD',46.94,1.79,0.34),\n" +
                "  ('MAR US','Marriott International Inc. Class A','Consumer Discretionary','USD',131.15,0,1.49),\n" +
                "  ('MPC US','Marathon Petroleum Corporation','Energy','USD',54.77,4.24,1.63),\n" +
                "  ('CTAS US','Cintas Corporation','Industrials','USD',391.81,0.97,1.2),\n" +
                "  ('CMI US','Cummins Inc.','Industrials','USD',231.67,2.5,0.97),\n" +
                "  ('WBA US','Walgreens Boots Alliance Inc','Consumer Staples','USD',48.3,3.95,0.79),\n" +
                "  ('FRC US','First Republic Bank','Financials','USD',194.6,0.45,1.07),\n" +
                "  ('HPQ US','HP Inc.','Technology','USD',28.22,2.75,1.22),\n" +
                "  ('ADM US','Archer-Daniels-Midland Company','Consumer Staples','USD',58.94,2.51,0.97),\n" +
                "  ('HLT US','Hilton Worldwide Holdings Inc','Consumer Discretionary','USD',121.77,0,1.3),\n" +
                "  ('WST US','West Pharmaceutical Services Inc.','Health Care','USD',449.51,0.15,0.74),\n" +
                "  ('AWK US','American Water Works Company Inc.','Utilities','USD',184.1,1.31,1.05),\n" +
                "  ('PXD US','Pioneer Natural Resources Company','Energy','USD',141.32,1.59,1.13),\n" +
                "  ('PEG US','Public Service Enterprise Group Inc','Utilities','USD',64.95,3.14,1.09),\n" +
                "  ('TDG US','TransDigm Group Incorporated','Industrials','USD',595.4,0,1.46),\n" +
                "  ('SIVB US','SVB Financial Group','Financials','USD',550.95,0,1.24),\n" +
                "  ('VRSK US','Verisk Analytics Inc','Technology','USD',196.39,0.59,0.9),\n" +
                "  ('MCK US','McKesson Corporation','Health Care','USD',200.41,0.94,0.93),\n" +
                "  ('CTVA US','Corteva Inc','Materials','USD',41.94,1.34,0.99),\n" +
                "  ('FAST US','Fastenal Company','Industrials','USD',55.47,2.02,0.94),\n" +
                "  ('EFX US','Equifax Inc.','Technology','USD',258.51,0.6,1.15),\n" +
                "  ('GLW US','Corning Inc','Technology','USD',40.17,2.39,1.19),\n" +
                "  ('ES US','Eversource Energy','Utilities','USD',92.11,2.62,1.06),\n" +
                "  ('AVB US','AvalonBay Communities Inc.','Real Estate','USD',224.63,2.83,1.34),\n" +
                "  ('KEYS US','Keysight Technologies Inc','Industrials','USD',168.79,0,0.77),\n" +
                "  ('AME US','AMETEK Inc.','Industrials','USD',134.77,0.59,1.11),\n" +
                "  ('KMI US','Kinder Morgan Inc Class P','Energy','USD',15.94,6.78,1.1),\n" +
                "  ('ANSS US','ANSYS Inc.','Technology','USD',362.22,0,0.96),\n" +
                "  ('DHI US','D.R. Horton Inc.','Consumer Discretionary','USD',94.9,0.84,1.32),\n" +
                "  ('CBRE US','CBRE Group Inc. Class A','Real Estate','USD',92.66,0,1.37),\n" +
                "  ('WEC US','WEC Energy Group Inc','Utilities','USD',97.59,2.78,0.98),\n" +
                "  ('SWK US','Stanley Black & Decker Inc.','Industrials','USD',190.39,1.66,1.37),\n" +
                "  ('AMP US','Ameriprise Financial Inc.','Financials','USD',261.6,1.73,1.38),\n" +
                "  ('BLL US','Ball Corporation','Materials','USD',94.63,0.85,1.03),\n" +
                "  ('ZBRA US','Zebra Technologies Corporation Class A','Technology','USD',571.49,0,0.98),\n" +
                "  ('STT US','State Street Corporation','Financials','USD',86.09,2.65,1.15),\n" +
                "  ('ZBH US','Zimmer Biomet Holdings Inc.','Health Care','USD',145.46,0.66,1.19),\n" +
                "  ('LH US','Laboratory Corporation of America Holdings','Health Care','USD',306.26,0,1.23),\n" +
                "  ('SWKS US','Skyworks Solutions Inc.','Technology','USD',178.88,1.25,1.14),\n" +
                "  ('AJG US','Arthur J. Gallagher & Co.','Financials','USD',143,1.34,1.02),\n" +
                "  ('WMB US','Williams Companies Inc.','Energy','USD',24.04,6.82,1.21),\n" +
                "  ('PSX US','Phillips 66','Energy','USD',65.95,5.46,1.17),\n" +
                "  ('CPRT US','Copart Inc.','Consumer Discretionary','USD',139.98,0,1.18),\n" +
                "  ('LEN US','Lennar Corporation Class A','Consumer Discretionary','USD',105.32,0.95,1.46),\n" +
                "  ('EQR US','Equity Residential','Real Estate','USD',82.51,2.92,1.26),\n" +
                "  ('ARE US','Alexandria Real Estate Equities Inc.','Real Estate','USD',207.06,2.16,1.02),\n" +
                "  ('LUV US','Southwest Airlines Co.','Industrials','USD',47.36,0,1.3),\n" +
                "  ('PCAR US','PACCAR Inc','Industrials','USD',80.27,1.69,1),\n" +
                "  ('WLTW US','Willis Towers Watson Public Limited Company','Financials','USD',218.75,1.46,1.08),\n" +
                "  ('CDW US','CDW Corp.','Technology','USD',195.77,0.82,1.33),\n" +
                "  ('MXIM US','Maxim Integrated Products Inc.','Technology','USD',101.65,0,0.95),\n" +
                "  ('ODFL US','Old Dominion Freight Line Inc.','Industrials','USD',282.51,0.28,0.98),\n" +
                "  ('O US','Realty Income Corporation','Real Estate','USD',71.91,3.93,1.34),\n" +
                "  ('SYF US','Synchrony Financial','Financials','USD',49.2,1.79,1.74),\n" +
                "  ('FITB US','Fifth Third Bancorp','Financials','USD',37.62,2.87,1.61),\n" +
                "  ('KSU US','Kansas City Southern','Industrials','USD',290.26,0.74,1.08),\n" +
                "  ('HSY US','Hershey Company','Consumer Staples','USD',180.03,2,0.85),\n" +
                "  ('ED US','Consolidated Edison Inc.','Utilities','USD',76.95,4.03,0.79),\n" +
                "  ('IT US','Gartner Inc.','Technology','USD',302.4,0,1.37),\n" +
                "  ('RSG US','Republic Services Inc.','Industrials','USD',122.1,1.51,0.94),\n" +
                "  ('WY US','Weyerhaeuser Company','Real Estate','USD',34.16,1.99,1.62),\n" +
                "  ('WAT US','Waters Corporation','Health Care','USD',402.25,0,0.85),\n" +
                "  ('LYB US','LyondellBasell Industries NV','Materials','USD',98.21,4.6,1.4),\n" +
                "  ('GRMN US','Garmin Ltd.','Technology','USD',170.43,1.57,1.01),\n" +
                "  ('ALB US','Albemarle Corporation','Materials','USD',219.68,0.71,1.31),\n" +
                "  ('FTV US','Fortive Corp.','Industrials','USD',73.43,0.38,1.1),\n" +
                "  ('DOV US','Dover Corporation','Industrials','USD',172.7,1.16,1.28),\n" +
                "  ('GNRC US','Generac Holdings Inc.','Industrials','USD',400.32,0,1.1),\n" +
                "  ('VLO US','Valero Energy Corporation','Energy','USD',60.2,6.51,1.45),\n" +
                "  ('BBY US','Best Buy Co. Inc.','Consumer Discretionary','USD',112.63,2.49,1.25),\n" +
                "  ('ETSY US','Etsy Inc.','Consumer Discretionary','USD',199.27,0,1.47),\n" +
                "  ('VFC US','V.F. Corporation','Consumer Discretionary','USD',76.43,2.56,1.25),\n" +
                "  ('CERN US','Cerner Corporation','Technology','USD',78.96,1.11,0.89),\n" +
                "  ('VMC US','Vulcan Materials Company','Materials','USD',183.39,0.81,1.12),\n" +
                "  ('DAL US','Delta Air Lines Inc.','Industrials','USD',38.12,0,1.76),\n" +
                "  ('VIAC US','ViacomCBS Inc. Class B','Communications','USD',39.71,2.42,1.35),\n" +
                "  ('URI US','United Rentals Inc.','Industrials','USD',330.13,0,1.49),\n" +
                "  ('KHC US','Kraft Heinz Company','Consumer Staples','USD',36.6,4.37,0.82),\n" +
                "  ('DLTR US','Dollar Tree Inc.','Consumer Staples','USD',102.67,0,0.76),\n" +
                "  ('NTRS US','Northern Trust Corporation','Financials','USD',114.6,2.44,1.08),\n" +
                "  ('TSN US','Tyson Foods Inc. Class A','Consumer Staples','USD',79.94,2.23,0.85),\n" +
                "  ('EXR US','Extra Space Storage Inc.','Real Estate','USD',177.56,2.25,0.95),\n" +
                "  ('HIG US','Hartford Financial Services Group Inc.','Financials','USD',66.24,2.11,1.35),\n" +
                "  ('XYL US','Xylem Inc.','Industrials','USD',131.57,0.85,1.07),\n" +
                "  ('DTE US','DTE Energy Company','Utilities','USD',121.48,2.72,1.14),\n" +
                "  ('MLM US','Martin Marietta Materials Inc.','Materials','USD',375,0.65,1.24),\n" +
                "  ('AEE US','Ameren Corporation','Utilities','USD',89.59,2.46,0.95),\n" +
                "  ('IP US','International Paper Company','Materials','USD',58.76,3.49,0.97),\n" +
                "  ('TSCO US','Tractor Supply Company','Consumer Discretionary','USD',198.11,1.05,0.86),\n" +
                "  ('PPL US','PPL Corporation','Utilities','USD',29.74,5.58,1.32),\n" +
                "  ('PAYC US','Paycom Software Inc.','Technology','USD',469.42,0,1.26),\n" +
                "  ('ETR US','Entergy Corporation','Utilities','USD',114.6,3.32,1.19),\n" +
                "  ('TRMB US','Trimble Inc.','Industrials','USD',90.59,0,1.28),\n" +
                "  ('ENPH US','Enphase Energy Inc.','Energy','USD',165.99,0,1.67),\n" +
                "  ('EIX US','Edison International','Utilities','USD',59.53,4.45,1.1),\n" +
                "  ('OKE US','ONEOK Inc.','Energy','USD',50.43,7.42,1.62),\n" +
                "  ('MKC US','McCormick & Company Incorporated','Consumer Staples','USD',87.56,1.55,0.7),\n" +
                "  ('COO US','Cooper Companies Inc.','Health Care','USD',444.18,0.01,0.93),\n" +
                "  ('NDAQ US','Nasdaq Inc.','Financials','USD',188.67,1.14,0.99),\n" +
                "  ('HBAN US','Huntington Bancshares Incorporated','Financials','USD',14.76,4.07,1.29),\n" +
                "  ('MAA US','Mid-America Apartment Communities Inc.','Real Estate','USD',190.28,2.16,1.28),\n" +
                "  ('CLX US','Clorox Company','Consumer Staples','USD',170.06,2.73,0.36),\n" +
                "  ('FLT US','FLEETCOR Technologies Inc.','Technology','USD',257.83,0,1.18),\n" +
                "  ('CHD US','Church & Dwight Co. Inc.','Consumer Staples','USD',86.02,1.17,0.49),\n" +
                "  ('FE US','FirstEnergy Corp.','Utilities','USD',39.09,3.99,1.03),\n" +
                "  ('VRSN US','VeriSign Inc.','Communications','USD',213.04,0,0.77),\n" +
                "  ('STE US','STERIS Plc','Health Care','USD',214.9,0.8,0.91),\n" +
                "  ('CRL US','Charles River Laboratories International Inc.','Health Care','USD',424.75,0,1.17),\n" +
                "  ('ESS US','Essex Property Trust Inc.','Real Estate','USD',319.49,2.62,1.32),\n" +
                "  ('TDY US','Teledyne Technologies Incorporated','Industrials','USD',450.46,0,1.19),\n" +
                "  ('EXPD US','Expeditors International of Washington Inc.','Industrials','USD',123.03,0.94,0.91),\n" +
                "  ('CTLT US','Catalent Inc','Health Care','USD',123.42,0,1.09),\n" +
                "  ('VTR US','Ventas Inc.','Real Estate','USD',54.59,3.3,1.85),\n" +
                "  ('QRVO US','Qorvo Inc.','Technology','USD',181.56,0,1.08),\n" +
                "  ('PKI US','PerkinElmer Inc.','Health Care','USD',181.1,0.15,0.8),\n" +
                "  ('KMX US','CarMax Inc.','Consumer Discretionary','USD',124.76,0,1.64),\n" +
                "  ('DPZ US','Domino''s Pizza Inc.','Consumer Discretionary','USD',509.52,0.74,0.68),\n" +
                "  ('BR US','Broadridge Financial Solutions Inc.','Technology','USD',174.54,1.47,0.83),\n" +
                "  ('ANET US','Arista Networks Inc.','Technology','USD',369.86,0,0.97),\n" +
                "  ('MPWR US','Monolithic Power Systems Inc.','Technology','USD',476.17,0.5,1.15),\n" +
                "  ('DGX US','Quest Diagnostics Incorporated','Health Care','USD',152,1.63,0.96),\n" +
                "  ('ULTA US','Ulta Beauty Inc','Consumer Discretionary','USD',366.77,0,1.46),\n" +
                "  ('HOLX US','Hologic Inc.','Health Care','USD',77.09,0,1.06),\n" +
                "  ('EXPE US','Expedia Group Inc.','Communications','USD',137.79,0,1.64),\n" +
                "  ('POOL US','Pool Corporation','Consumer Discretionary','USD',486.43,0.66,0.95),\n" +
                "  ('AMCR US','Amcor PLC','Materials','USD',12.79,3.67,0.91),\n" +
                "  ('KEY US','KeyCorp','Financials','USD',19.91,3.72,1.52),\n" +
                "  ('TYL US','Tyler Technologies Inc.','Technology','USD',472.68,0,0.76),\n" +
                "  ('TER US','Teradyne Inc.','Technology','USD',113.56,0.35,1.36),\n" +
                "  ('PEAK US','Healthpeak Properties Inc.','Real Estate','USD',35.16,3.41,1.42),\n" +
                "  ('DRE US','Duke Realty Corporation','Real Estate','USD',50.82,2.01,1.1),\n" +
                "  ('GWW US','W.W. Grainger Inc.','Industrials','USD',430.99,1.5,1.1),\n" +
                "  ('RF US','Regions Financial Corporation','Financials','USD',20.03,3.39,1.44),\n" +
                "  ('TTWO US','Take-Two Interactive Software Inc.','Communications','USD',160.96,0,0.74),\n" +
                "  ('HPE US','Hewlett Packard Enterprise Co.','Technology','USD',14.58,3.29,1.12),\n" +
                "  ('IR US','Ingersoll Rand Inc.','Industrials','USD',50.08,0,1.17),\n" +
                "  ('OXY US','Occidental Petroleum Corporation','Energy','USD',21.95,0.18,1.69),\n" +
                "  ('CMS US','CMS Energy Corporation','Utilities','USD',64.81,2.68,0.98),\n" +
                "  ('AKAM US','Akamai Technologies Inc.','Technology','USD',114.49,0,0.72),\n" +
                "  ('CINF US','Cincinnati Financial Corporation','Financials','USD',123.31,2.04,1.1),\n" +
                "  ('WDC US','Western Digital Corporation','Technology','USD',60.04,0,1.48),\n" +
                "  ('MKTX US','MarketAxess Holdings Inc.','Technology','USD',475.5,0.56,0.67),\n" +
                "  ('AVY US','Avery Dennison Corporation','Materials','USD',218.28,1.25,1.15),\n" +
                "  ('DRI US','Darden Restaurants Inc.','Consumer Discretionary','USD',139.31,3.16,1.85),\n" +
                "  ('CFG US','Citizens Financial Group Inc.','Financials','USD',42.34,3.68,1.45),\n" +
                "  ('VTRS US','Viatris Inc.','Health Care','USD',14.69,3,0.88),\n" +
                "  ('STX US','Seagate Technology Holdings PLC','Technology','USD',89.44,3,1.02),\n" +
                "  ('GPC US','Genuine Parts Company','Consumer Discretionary','USD',123.82,2.63,1.26),\n" +
                "  ('NTAP US','NetApp Inc.','Technology','USD',80.67,2.48,1.06),\n" +
                "  ('ABC US','AmerisourceBergen Corporation','Health Care','USD',119.54,1.47,0.8),\n" +
                "  ('TFX US','Teleflex Incorporated','Health Care','USD',377.07,0.36,1.12),\n" +
                "  ('NVR US','NVR Inc.','Consumer Discretionary','USD',5160.73,0,1.32),\n" +
                "  ('CE US','Celanese Corporation','Materials','USD',153.81,1.77,1.23),\n" +
                "  ('HES US','Hess Corporation','Energy','USD',64.32,1.55,1.22),\n" +
                "  ('BBWI US','Bath & Body Works Inc.','Consumer Discretionary','USD',66.26,0.91,1.77),\n" +
                "  ('MTB US','M&T Bank Corporation','Financials','USD',135.7,3.24,1.14),\n" +
                "  ('CCL US','Carnival Corporation','Consumer Discretionary','USD',21.96,0,1.92),\n" +
                "  ('CZR US','Caesars Entertainment Inc','Consumer Discretionary','USD',85.64,0,2.93),\n" +
                "  ('J US','Jacobs Engineering Group Inc.','Industrials','USD',132.75,0.63,1.08),\n" +
                "  ('K US','Kellogg Company','Consumer Staples','USD',66.16,3.51,0.64),\n" +
                "  ('RCL US','Royal Caribbean Group','Consumer Discretionary','USD',78.21,0,2.27),\n" +
                "  ('IEX US','IDEX Corporation','Industrials','USD',221.29,0.98,1),\n" +
                "  ('BIO US','Bio-Rad Laboratories Inc. Class A','Health Care','USD',780.03,0,0.7),\n" +
                "  ('CAG US','Conagra Brands Inc.','Consumer Staples','USD',33.73,3.7,0.63),\n" +
                "  ('PFG US','Principal Financial Group Inc.','Financials','USD',65.03,3.87,1.56),\n" +
                "  ('RJF US','Raymond James Financial Inc.','Financials','USD',135.83,1.15,1.08),\n" +
                "  ('HAL US','Halliburton Company','Energy','USD',18.21,0.99,1.59),\n" +
                "  ('BXP US','Boston Properties Inc.','Real Estate','USD',113.12,3.47,1.26),\n" +
                "  ('TXT US','Textron Inc.','Industrials','USD',70.96,0.11,1.43),\n" +
                "  ('AES US','AES Corporation','Utilities','USD',24.32,2.48,1.22),\n" +
                "  ('UDR US','UDR Inc.','Real Estate','USD',53.09,2.73,1.33),\n" +
                "  ('EVRG US','Evergy Inc.','Utilities','USD',69.26,3.09,1.07),\n" +
                "  ('MGM US','MGM Resorts International','Consumer Discretionary','USD',38.4,0.03,2.02),\n" +
                "  ('MAS US','Masco Corporation','Consumer Discretionary','USD',60.73,1.55,1.16),\n" +
                "  ('LNT US','Alliant Energy Corp','Utilities','USD',62.18,2.59,1.03),\n" +
                "  ('OMC US','Omnicom Group Inc','Communications','USD',71.64,3.91,1.03),\n" +
                "  ('WAB US','Westinghouse Air Brake Technologies Corporation','Industrials','USD',85.74,0.56,1.37),\n" +
                "  ('ABMD US','ABIOMED Inc.','Health Care','USD',348.11,0,0.96),\n" +
                "  ('CAH US','Cardinal Health Inc.','Health Care','USD',51.39,3.82,0.92),\n" +
                "  ('CNP US','CenterPoint Energy Inc.','Utilities','USD',26.34,2.43,1.41),\n" +
                "  ('NLOK US','NortonLifeLock Inc.','Technology','USD',25.75,1.94,0.76),\n" +
                "  ('DVN US','Devon Energy Corporation','Energy','USD',25.7,1.71,1.53),\n" +
                "  ('EMN US','Eastman Chemical Company','Materials','USD',109.9,2.51,1.3),\n" +
                "  ('BKR US','Baker Hughes Company Class A','Energy','USD',20.43,3.52,1.39),\n" +
                "  ('SJM US','J.M. Smucker Company','Consumer Staples','USD',131.28,3.02,0.5),\n" +
                "  ('JBHT US','J.B. Hunt Transport Services Inc.','Industrials','USD',174.49,0.69,0.95),\n" +
                "  ('LKQ US','LKQ Corporation','Consumer Discretionary','USD',50.96,0,1.54),\n" +
                "  ('IPG US','Interpublic Group of Companies Inc.','Communications','USD',36.45,2.96,1.36),\n" +
                "  ('UAL US','United Airlines Holdings Inc.','Industrials','USD',44.03,0,1.98),\n" +
                "  ('INCY US','Incyte Corporation','Health Care','USD',74.9,0,0.7),\n" +
                "  ('WHR US','Whirlpool Corporation','Consumer Discretionary','USD',221.2,2.53,1.34),\n" +
                "  ('PKG US','Packaging Corporation of America','Materials','USD',148.3,2.7,0.79),\n" +
                "  ('PHM US','PulteGroup Inc.','Consumer Discretionary','USD',52.71,1.06,1.68),\n" +
                "  ('CBOE US','Cboe Global Markets Inc','Financials','USD',129.76,1.48,0.83),\n" +
                "  ('AAP US','Advance Auto Parts Inc.','Consumer Discretionary','USD',209.68,1.91,1.37),\n" +
                "  ('FBHS US','Fortune Brands Home & Security Inc.','Consumer Discretionary','USD',98.46,1.06,1.5),\n" +
                "  ('JKHY US','Jack Henry & Associates Inc.','Technology','USD',177.13,1.04,0.89),\n" +
                "  ('PTC US','PTC Inc.','Technology','USD',128.29,0,1.29),\n" +
                "  ('PWR US','Quanta Services Inc.','Industrials','USD',96.3,0.25,1.16),\n" +
                "  ('IRM US','Iron Mountain Inc.','Real Estate','USD',45.67,5.42,1.05),\n" +
                "  ('XRAY US','DENTSPLY SIRONA Inc.','Health Care','USD',59.86,0.74,1.14),\n" +
                "  ('HRL US','Hormel Foods Corporation','Consumer Staples','USD',46.33,2.12,0.39),\n" +
                "  ('PNR US','Pentair plc','Industrials','USD',78.83,1.02,1.3),\n" +
                "  ('ATO US','Atmos Energy Corporation','Utilities','USD',98.77,2.53,0.93),\n" +
                "  ('LDOS US','Leidos Holdings Inc.','Technology','USD',96.31,1.5,1.18),\n" +
                "  ('WRK US','WestRock Company','Materials','USD',49.43,1.94,1.12),\n" +
                "  ('ALLE US','Allegion PLC','Industrials','USD',138.58,1.04,1.2),\n" +
                "  ('KIM US','Kimco Realty Corporation','Real Estate','USD',21.32,3.19,1.36),\n" +
                "  ('CTXS US','Citrix Systems Inc.','Technology','USD',103.22,1.43,0.62),\n" +
                "  ('L US','Loews Corporation','Financials','USD',55.02,0.45,1.18),\n" +
                "  ('HWM US','Howmet Aerospace Inc.','Industrials','USD',31.81,0.25,1.49),\n" +
                "  ('FANG US','Diamondback Energy Inc.','Energy','USD',68.18,2.64,1.59),\n" +
                "  ('HAS US','Hasbro Inc.','Consumer Discretionary','USD',95.84,2.84,1.31),\n" +
                "  ('FOXA US','Fox Corporation Class A','Communications','USD',36.76,1.31,1),\n" +
                "  ('LVS US','Las Vegas Sands Corp.','Consumer Discretionary','USD',37.55,0,1.12),\n" +
                "  ('AAL US','American Airlines Group Inc.','Industrials','USD',18.5,0,1.86),\n" +
                "  ('FFIV US','F5 Networks Inc.','Technology','USD',199.86,0,0.89),\n" +
                "  ('LNC US','Lincoln National Corporation','Financials','USD',65.81,2.55,2.17),\n" +
                "  ('CHRW US','C.H. Robinson Worldwide Inc.','Industrials','USD',88.69,2.3,0.67),\n" +
                "  ('UHS US','Universal Health Services Inc. Class B','Health Care','USD',153.39,0.52,1.35),\n" +
                "  ('SNA US','Snap-on Incorporated','Industrials','USD',219.53,2.24,1.16),\n" +
                "  ('MHK US','Mohawk Industries Inc.','Consumer Discretionary','USD',200.78,0,1.73),\n" +
                "  ('RHI US','Robert Half International Inc.','Industrials','USD',102.07,1.49,1.17),\n" +
                "  ('FMC US','FMC Corporation','Materials','USD',87.77,2.19,1.24),\n" +
                "  ('LYV US','Live Nation Entertainment Inc.','Consumer Discretionary','USD',80.2,0,1.58),\n" +
                "  ('LUMN US','Lumen Technologies Inc.','Communications','USD',11.83,8.45,0.89),\n" +
                "  ('TPR US','Tapestry Inc.','Consumer Discretionary','USD',40.58,2.46,1.69),\n" +
                "  ('RE US','Everest Re Group Ltd.','Financials','USD',271.94,2.28,1.05),\n" +
                "  ('HSIC US','Henry Schein Inc.','Health Care','USD',75.63,0,1.06),\n" +
                "  ('WRB US','W. R. Berkley Corporation','Financials','USD',76.38,0.68,1.14),\n" +
                "  ('HST US','Host Hotels & Resorts Inc.','Real Estate','USD',15.16,0,1.22),\n" +
                "  ('NRG US','NRG Energy Inc.','Utilities','USD',43.41,2.99,1.26),\n" +
                "  ('DISH US','DISH Network Corporation Class A','Communications','USD',42.88,0,1.22),\n" +
                "  ('MOS US','Mosaic Company','Materials','USD',30.72,0.98,1.25),\n" +
                "  ('REG US','Regency Centers Corporation','Real Estate','USD',64.87,3.67,1.38),\n" +
                "  ('BWA US','BorgWarner Inc.','Consumer Discretionary','USD',42.48,1.6,1.27),\n" +
                "  ('AIZ US','Assurant Inc.','Financials','USD',165.13,1.6,1.01),\n" +
                "  ('CMA US','Comerica Incorporated','Financials','USD',71.28,3.82,1.37),\n" +
                "  ('NI US','NiSource Inc','Utilities','USD',25.71,3.42,1.01),\n" +
                "  ('PENN US','Penn National Gaming Inc.','Consumer Discretionary','USD',67.65,0,2.33),\n" +
                "  ('LW US','Lamb Weston Holdings Inc.','Consumer Staples','USD',65.08,1.44,1.33),\n" +
                "  ('DVA US','DaVita Inc.','Health Care','USD',132.65,0,1.03),\n" +
                "  ('JNPR US','Juniper Networks Inc.','Technology','USD',29.07,2.75,0.96),\n" +
                "  ('AOS US','A. O. Smith Corporation','Industrials','USD',71.06,1.46,1),\n" +
                "  ('WYNN US','Wynn Resorts Limited','Consumer Discretionary','USD',88.3,0,1.81),\n" +
                "  ('CF US','CF Industries Holdings Inc.','Materials','USD',44.47,2.7,1.24),\n" +
                "  ('SEE US','Sealed Air Corporation','Materials','USD',59.36,1.35,1.09),\n" +
                "  ('NWL US','Newell Brands Inc','Consumer Discretionary','USD',24.51,3.75,1.19),\n" +
                "  ('TAP US','Molson Coors Beverage Company Class B','Consumer Staples','USD',47.79,2.85,0.96),\n" +
                "  ('PNW US','Pinnacle West Capital Corporation','Utilities','USD',80.16,4.14,1.16),\n" +
                "  ('GL US','Globe Life Inc.','Financials','USD',94.98,0.83,1.25),\n" +
                "  ('DXC US','DXC Technology Co.','Technology','USD',35.44,0,1.59),\n" +
                "  ('WU US','Western Union Company','Technology','USD',21.61,4.35,1.07),\n" +
                "  ('IVZ US','Invesco Ltd.','Financials','USD',23.99,2.83,1.43),\n" +
                "  ('CPB US','Campbell Soup Company','Consumer Staples','USD',43.28,3.42,0.48),\n" +
                "  ('ZION US','Zions Bancorporation N.A.','Financials','USD',53.87,2.82,1.21),\n" +
                "  ('ROL US','Rollins Inc.','Industrials','USD',39.33,0.81,0.69),\n" +
                "  ('NWSA US','News Corporation Class A','Communications','USD',22.1,0.9,1.02),\n" +
                "  ('MRO US','Marathon Oil Corporation','Energy','USD',10.72,1.87,1.31),\n" +
                "  ('NCLH US','Norwegian Cruise Line Holdings Ltd.','Consumer Discretionary','USD',23.63,0,2.14),\n" +
                "  ('BEN US','Franklin Resources Inc.','Financials','USD',31.13,3.6,1.01),\n" +
                "  ('HII US','Huntington Ingalls Industries Inc.','Industrials','USD',202.73,2.25,0.97),\n" +
                "  ('NLSN US','Nielsen Holdings Plc','Technology','USD',22.5,1.07,0.95),\n" +
                "  ('FRT US','Federal Realty Investment Trust','Real Estate','USD',116.06,3.69,1.33),\n" +
                "  ('DISCK US','Discovery Inc. Class C','Communications','USD',26.82,0,1.02),\n" +
                "  ('PVH US','PVH Corp.','Consumer Discretionary','USD',104.55,0,2),\n" +
                "  ('ALK US','Alaska Air Group Inc.','Industrials','USD',54.35,0,1.71),\n" +
                "  ('PBCT US','People''s United Financial Inc.','Financials','USD',15.82,4.61,1.01),\n" +
                "  ('HBI US','Hanesbrands Inc.','Consumer Discretionary','USD',18.95,3.17,1.06),\n" +
                "  ('VNO US','Vornado Realty Trust','Real Estate','USD',40.89,5.18,1.49),\n" +
                "  ('LEG US','Leggett & Platt Incorporated','Consumer Discretionary','USD',47.8,3.52,1.39),\n" +
                "  ('APA US','APA Corp.','Energy','USD',16.07,0.62,2),\n" +
                "  ('IPGP US','IPG Photonics Corporation','Technology','USD',167.37,0,0.92),\n" +
                "  ('COG US','Cabot Oil & Gas Corporation','Energy','USD',14.42,3.05,0.79),\n" +
                "  ('GPS US','Gap Inc.','Consumer Discretionary','USD',28.27,1.7,1.85),\n" +
                "  ('RL US','Ralph Lauren Corporation Class A','Consumer Discretionary','USD',116.27,2.37,1.35),\n" +
                "  ('PRGO US','Perrigo Co. Plc','Health Care','USD',40.96,2.34,0.93),\n" +
                "  ('UNM US','Unum Group','Financials','USD',25.43,4.72,1.72),\n" +
                "  ('FOX US','Fox Corporation Class B','Communications','USD',34.01,1.41,1.06),\n" +
                "  ('NOV US','NOV Inc.','Energy','USD',12.19,0,1.17),\n" +
                "  ('DISCA US','Discovery Inc. Class A','Communications','USD',28.07,0,1),\n" +
                "  ('UAA US','Under Armour Inc. Class A','Consumer Discretionary','USD',23.45,0,1.41),\n" +
                "  ('UA US','Under Armour Inc. Class C','Consumer Discretionary','USD',19.74,0,1.39),\n" +
                "  ('NWS US','News Corporation Class B','Communications','USD',21.49,0.93,1.1);");
        jdbcTemplate.update("INSERT INTO users (name, age, type, initial_investment, cash, portfolio_generation_date, risk_tolerance) VALUES\n" +
                "  ('Alexander', 25, 'SUPER_RICH', 1000000.00, 1000000.00, NULL, 5),\n" +
                "  ('Tatyana', 32, 'UPPER_MIDDLE', 125000.00, 125000.00, NULL, 4),\n" +
                "  ('Vladimir', 78, 'LOWER_MIDDLE', 30000.00, 30000.00, NULL, 1),\n" +
                "  ('John', 55, 'MIDDLE', 50000.00, 50000.00, NULL, 3);");
    }

    @Test
    public void generateUserPortfolioTest1() {
        GenerateUserPortfolioRequest request1 = new GenerateUserPortfolioRequest("Alexander");
        GetUserPortfolioRequest request2 = new GetUserPortfolioRequest("Alexander");
        GenerateUserPortfolioResponse response1 = getGenerateUserPortfolioService().execute(request1);
        GetUserPortfolioResponse response2 = getGetUserPortfolioService().execute(request2);
        List<Position> portfolio = List.of(
                new Position(new Stock("CNP US", "CenterPoint Energy Inc.", "Utilities", "USD", 26.34, 2.43, 1.41), 569, 26.34),
                new Position(new Stock("PPL US", "PPL Corporation", "Utilities", "USD", 29.74, 5.58, 1.32), 504, 29.74),
                new Position(new Stock("HCA US", "HCA Healthcare Inc", "Health Care", "USD", 249.34, 0.77, 1.46), 120, 249.34),
                new Position(new Stock("ALGN US", "Align Technology Inc.", "Health Care", "USD", 681.57, 0, 1.46), 44, 681.57),
                new Position(new Stock("APA US", "APA Corp.", "Energy", "USD", 16.07, 0.62, 2), 3266, 16.07),
                new Position(new Stock("OXY US", "Occidental Petroleum Corporation", "Energy", "USD", 21.95, 0.18, 1.69), 2391, 21.95),
                new Position(new Stock("SYY US", "Sysco Corporation", "Consumer Staples", "USD", 76.79, 2.45, 1.66), 97, 76.79),
                new Position(new Stock("STZ US", "Constellation Brands Inc. Class A", "Consumer Staples", "USD", 213.83, 1.42, 1.34), 35, 213.83),
                new Position(new Stock("DXC US", "DXC Technology Co.", "Technology", "USD", 35.44, 0, 1.59), 1058, 35.44),
                new Position(new Stock("LRCX US", "Lam Research Corporation", "Technology", "USD", 565.97, 0.92, 1.50), 66, 565.97),
                new Position(new Stock("SPG US", "Simon Property Group Inc.", "Real Estate", "USD", 128.77, 4.66, 1.86), 524, 128.77),
                new Position(new Stock("VTR US", "Ventas Inc.", "Real Estate", "USD", 54.59, 3.3, 1.85), 1236, 54.59),
                new Position(new Stock("FCX US", "Freeport-McMoRan Inc.", "Materials", "USD", 32.8, 0.91, 1.59), 1371, 32.8),
                new Position(new Stock("LYB US", "LyondellBasell Industries NV", "Materials", "USD", 98.21, 4.6, 1.4), 458, 98.21),
                new Position(new Stock("LNC US", "Lincoln National Corporation", "Financials", "USD", 65.81, 2.55, 2.17), 911, 65.81),
                new Position(new Stock("DFS US", "Discover Financial Services", "Financials", "USD", 127.26, 1.57, 2.03), 471, 127.26),
                new Position(new Stock("EXPE US", "Expedia Group Inc.", "Communications", "USD", 137.79, 0, 1.64), 163, 137.79),
                new Position(new Stock("IPG US", "Interpublic Group of Companies Inc.", "Communications", "USD", 36.45, 2.96, 1.36), 617, 36.45),
                new Position(new Stock("BA US", "Boeing Company", "Industrials", "USD", 212.67, 0, 2.18), 352, 212.67),
                new Position(new Stock("UAL US", "United Airlines Holdings Inc.", "Industrials", "USD", 44.03, 0, 1.98), 1703, 44.03),
                new Position(new Stock("CZR US", "Caesars Entertainment Inc", "Consumer Discretionary", "USD", 85.64, 0, 2.93), 1021, 85.64),
                new Position(new Stock("PENN US", "Penn National Gaming Inc.", "Consumer Discretionary", "USD", 67.65, 0, 2.33), 1293, 67.65)
        );
        assertEquals(portfolio.size(), response1.getUser().getPortfolio().size());
        IntStream.rangeClosed(0, portfolio.size() - 1)
                .forEach(i -> assertTrue(portfolio.contains(response1.getUser().getPortfolio().get(i))));
        assertEquals(872.1, response1.getUser().getCash(), 0.01);

        assertEquals(portfolio.size(), response2.getUser().getPortfolio().size());
        IntStream.rangeClosed(0, portfolio.size() - 1)
                .forEach(i -> assertTrue(portfolio.contains(response2.getUser().getPortfolio().get(i))));
        assertEquals(872.1, response2.getUser().getCash(), 0.01);
    }

    public static class MyTrigger implements Trigger {

        @Override
        public void init(Connection conn, String schemaName, String triggerName,
                         String tableName, boolean before, int type) {
        }

        @Override
        public void fire(Connection conn, Object[] oldRow, Object[] newRow) {
            int quantity = (int) newRow[2];
            double purchasePrice = ((BigDecimal) newRow[3]).doubleValue();
            double amount = quantity * purchasePrice;
            try{
                PreparedStatement ps = conn.prepareStatement("UPDATE users SET cash = cash - ? WHERE id = ?");
                ps.setDouble(1, amount);
                ps.setLong(2, (Long) newRow[0]);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void close() {

        }

        @Override
        public void remove() {

        }

    }

    private GenerateUserPortfolioService getGenerateUserPortfolioService() {
        return appContext.getBean(GenerateUserPortfolioService.class);
    }

    private GetUserPortfolioService getGetUserPortfolioService() {
        return appContext.getBean(GetUserPortfolioService.class);
    }

}
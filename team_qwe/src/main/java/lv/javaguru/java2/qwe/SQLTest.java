package lv.javaguru.java2.qwe;

import java.sql.*;

public class SQLTest {

    private static String URL = "jdbc:mysql://localhost:3306/qwe";
    private static String str1 = "CREATE SCHEMA IF NOT EXISTS `qwe` DEFAULT CHARACTER SET utf8";
    private static String str2 = "USE `qwe`";
    private static String str3 = "CREATE TABLE IF NOT EXISTS `stocks` (\n" +
            "  `ticker` VARCHAR(10) NOT NULL,\n" +
            "  `name` VARCHAR(100) NOT NULL,\n" +
            "  `industry` VARCHAR(50) NOT NULL,\n" +
            "  `currency` CHAR(3) NOT NULL,\n" +
            "  `market_price` DECIMAL(8,2) NOT NULL,\n" +
            "  `dividend_yield` DECIMAL(4,2) NOT NULL,\n" +
            "  `risk_weight` DECIMAL(5,4) NOT NULL,\n" +
            "  PRIMARY KEY (`ticker`)\n" +
            ")\n" +
            "ENGINE = InnoDB;";
    private static String str4 = "CREATE TABLE IF NOT EXISTS `users` (\n" +
            "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(100) NOT NULL,\n" +
            "  `age` INTEGER NOT NULL,\n" +
            "  `type` VARCHAR(50) NOT NULL,\n" +
            "  `initial_investment` DECIMAL(11,2),\n" +
            "  `cash` DECIMAL(11,2),\n" +
            "  `portfolio_generation_date` DATE,\n" +
            "  `risk_tolerance` INTEGER,\n" +
            "  PRIMARY KEY(`id`)\n" +
            ")\n" +
            "ENGINE = InnoDB\n" +
            "AUTO_INCREMENT = 1;";
    private static String str5 = "CREATE TABLE IF NOT EXISTS `user1_portfolio` (\n" +
            "  `user_id` BIGINT NOT NULL,\n" +
            "  `security_ticker` VARCHAR(10) NOT NULL,\n" +
            "  `amount` INTEGER NOT NULL,\n" +
            "  `purchase_price` DECIMAL(8,2) NOT NULL,\n" +
            "  FOREIGN KEY(`user_id`) REFERENCES `users`(`id`),\n" +
            "  FOREIGN KEY(`security_ticker`) REFERENCES `stocks`(`ticker`)\n" +
            ")";
    private static String str6 = "CREATE TRIGGER ins_sec AFTER INSERT ON user1_portfolio\n" +
            "  FOR EACH ROW\n" +
            "  BEGIN\n" +
            "    UPDATE users SET cash = cash - NEW.amount * NEW.purchase_price WHERE id = 1;\n" +
            "  END;";
    private static String str7 = "INSERT INTO users (name, age, type, initial_investment, cash, portfolio_generation_date, risk_tolerance) VALUES\n" +
            "      ('Alexander', 25, 'SUPER_RICH', 1000000.00, 1000000.00, NULL, 5),\n" +
            "      ('Tatyana', 32, 'UPPER_MIDDLE', 125000.00, 125000.00, NULL, 4),\n" +
            "      ('Vladimir', 78, 'LOWER_MIDDLE', 30000.00, 30000.00, NULL, 1),\n" +
            "      ('John', 55, 'MIDDLE', 50000.00, 50000.00, NULL, 3);";
    private static String str8 = "INSERT INTO user1_portfolio (user_id, security_ticker, amount, purchase_price) VALUES\n" +
            "      (1, 'NOV US', 10000, (SELECT market_price FROM stocks WHERE ticker = 'NOV US')),\n" +
            "      (1, 'MRO US', 50000, (SELECT market_price FROM stocks WHERE ticker = 'MRO US'));";
    private static String str9 = "INSERT INTO user1_portfolio (user_id, security_ticker, amount, purchase_price) VALUES\n" +
            "      (1, 'AAPL US', 1000, (SELECT market_price FROM stocks WHERE ticker = 'AAPL US'));";
    private static String str10 = "SELECT name FROM stocks WHERE industry = ?";

    public static void main(String[] args) {

        try {
            Connection dbConn = DriverManager.getConnection(URL, "root", "DG8H-HPAS-9ZGR");
            Statement stmt = dbConn.createStatement();
            stmt.executeUpdate(str1);
            stmt.executeUpdate(str2);
//            stmt.executeUpdate(str3);
//            stmt.executeUpdate(str4);
//            stmt.executeUpdate(str5);
//            stmt.executeUpdate(str6);
//            stmt.executeUpdate(str7);
//            stmt.executeUpdate(str8);
//            stmt.executeUpdate(str9);
            PreparedStatement pstm1 = dbConn.prepareStatement(str10);
            pstm1.setString(1, "Technology");
            System.out.println(pstm1);
            ResultSet answer = pstm1.executeQuery();
            while(answer.next()) {
                String res = answer.getString(1);
                System.out.println(res);
            }


            dbConn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
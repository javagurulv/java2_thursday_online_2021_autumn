package lv.javaguru.java2.qwe;

import lv.javaguru.java2.qwe.core.domain.Type;
import lv.javaguru.java2.qwe.core.requests.user_requests.AddUserRequestSQL;

import java.sql.*;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.Map.entry;
import static lv.javaguru.java2.qwe.core.domain.Type.*;

public class SQLTest3 {

    private static final AddUserRequestSQL request =
            new AddUserRequestSQL("Marina", "42", "WEALTHY", "500000" );
    private static String query =
            "INSERT INTO users (name, age, type, initial_investment, cash, portfolio_generation_date, risk_tolerance) VALUES\n" +
                    "(?, ?, ?, ?, ?, ?, ?)";
    private static int riskTolerance;

    public static void main(String[] args) {
        calculateRiskTolerance();
        try {
            String URL = "jdbc:mysql://localhost:3306/qwe";
            Connection dbConn = DriverManager.getConnection(URL, "root", "DG8H-HPAS-9ZGR");
            PreparedStatement pstm1 = dbConn.prepareStatement(query);
            PreparedStatement pstm2 = dbConn.prepareStatement(createUserPortfolioTable());
            PreparedStatement pstm3 = dbConn.prepareStatement(createCashUpdateTriggerForUserPortfolio());
            pstm1.setString(1, request.getName());
            pstm1.setString(2, request.getAge());
            pstm1.setString(3, request.getType());
            pstm1.setDouble(4, Double.parseDouble(request.getInitialInvestment()));
            pstm1.setDouble(5, Double.parseDouble(request.getInitialInvestment()));
            pstm1.setDate(6, null);
            pstm1.setInt(7, riskTolerance);
            pstm1.executeUpdate();
            pstm2.executeUpdate();
            pstm3.executeUpdate();
            dbConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String createUserPortfolioTable() {
        return "CREATE TABLE IF NOT EXISTS `" +
                request.getName() + "_portfolio` (\n" +
                "  `user_id` BIGINT NOT NULL,\n" +
                "  `security_ticker` VARCHAR(10) NOT NULL,\n" +
                "  `amount` INTEGER NOT NULL,\n" +
                "  `purchase_price` DECIMAL(8,2) NOT NULL,\n" +
                "  FOREIGN KEY(`user_id`) REFERENCES `users`(`id`),\n" +
                "  FOREIGN KEY(`security_ticker`) REFERENCES `stocks`(`ticker`)\n" +
                ")";
    }

    private static String createCashUpdateTriggerForUserPortfolio() {
        return "CREATE TRIGGER ins_sec AFTER INSERT ON `" +
                request.getName() + "_portfolio`\n" +
                "  FOR EACH ROW\n" +
                "  BEGIN\n" +
                "    UPDATE users SET cash = cash - NEW.amount * NEW.purchase_price WHERE id = 13;\n" +
                "  END";
    }

    private static void calculateRiskTolerance() {
        Map<Predicate<Integer>, Integer> mapAge = Map.ofEntries(
                entry(age -> age >= 16 && age < 30, 5), // чем моложе, тем выше показатель толерантности к риску!
                entry(age -> age >= 30 && age < 40, 4),
                entry(age -> age >= 40 && age < 50, 3),
                entry(age -> age >= 50 && age < 60, 2),
                entry(age -> age >= 60, 1)
        );
        Map<Type, Integer> mapWealth = Map.ofEntries(
                entry(SUPER_RICH, 5), // чем богаче, тем выше показатель толерантности к риску!
                entry(WEALTHY, 4),
                entry(UPPER_MIDDLE, 3),
                entry(MIDDLE, 2),
                entry(LOWER_MIDDLE, 1)
        );
        mapAge.forEach((agePredicate, integer) -> {
            if (agePredicate.test(Integer.parseInt(request.getAge()))) {
                riskTolerance = integer;
            }
        });
        mapWealth.forEach((Type, integer) -> {
            if (Type.equals(valueOf(request.getType()))) {
                riskTolerance = (riskTolerance + integer) / 2; // вычисляет средний показатель
            }
        });
    }

}
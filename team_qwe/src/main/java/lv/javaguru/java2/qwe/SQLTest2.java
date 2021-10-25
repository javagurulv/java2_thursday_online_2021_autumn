package lv.javaguru.java2.qwe;

import lv.javaguru.java2.qwe.core.requests.data_requests.CoreRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.FilterStocksByAnyDoubleParameterRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.FilterStocksByIndustryRequest;
import lv.javaguru.java2.qwe.core.requests.data_requests.OrderingRequest;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class SQLTest2 {

    private static final List<CoreRequest> requestList = List.of(
            new FilterStocksByIndustryRequest("Technology"),
            new FilterStocksByAnyDoubleParameterRequest("dividend_yield", ">", "1"),
            new FilterStocksByAnyDoubleParameterRequest("risk_weight", ">", "0.8"),
            new FilterStocksByAnyDoubleParameterRequest("market_price", ">", "100"),
            new OrderingRequest("ticker", "DESC")
    );
    private static String query = "SELECT * FROM stocks";

    public static void main(String[] args) {

        try {
            String URL = "jdbc:mysql://localhost:3306/qwe";
            Connection dbConn = DriverManager.getConnection(URL, "root", "DG8H-HPAS-9ZGR");
            PreparedStatement pstm1 = dbConn.prepareStatement(getQuery(requestList));
            ResultSet answer = pstm1.executeQuery();
            while (answer.next()) {
                String res = String.join(", ",
                        answer.getString(1),
                        answer.getString(2),
                        answer.getString(3),
                        answer.getString(4),
                        answer.getString(5),
                        answer.getString(6),
                        answer.getString(7));
                System.out.println(res);
            }
            dbConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static private String getQuery(List<CoreRequest> requestList) {

        if (requestList.size() > 0) {
            query = query + "\n  WHERE";
            for (int i = 0; i < requestList.size(); i++) {
                if (i != 0 && !requestList.get(i).getClass().getSimpleName().equals("OrderingRequest")) {
                    query = query + "  AND";
                }
                if (requestList.get(i).getClass().getSimpleName().equals("OrderingRequest")) {
                    query = query + " ORDER BY " + ((OrderingRequest) requestList.get(i)).getOrderBy() + " " +
                            ((OrderingRequest) requestList.get(i)).getOrderDirection();
                }
                if (requestList.get(i).getClass().getSimpleName().equals("FilterStocksByIndustryRequest")) {
                    query = query + " industry = " + "'" + ((FilterStocksByIndustryRequest) requestList.get(i)).getIndustry() + "'" + "\n";
                }
                if (requestList.get(i).getClass().getSimpleName().equals("FilterStocksByAnyDoubleParameterRequest")) {
                    query = query + " " + ((FilterStocksByAnyDoubleParameterRequest) requestList.get(i)).getParameter() + " " +
                            ((FilterStocksByAnyDoubleParameterRequest) requestList.get(i)).getOperator() + " " +
                            ((FilterStocksByAnyDoubleParameterRequest) requestList.get(i)).getTargetAmount() + "\n";
                }
            }
        }
        System.out.println("====================================\n" + query + "\n====================================");
        return query;
    }

}
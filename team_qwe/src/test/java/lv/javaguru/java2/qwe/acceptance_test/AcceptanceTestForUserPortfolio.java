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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
@Sql({"/schema.sql"})
@Sql({"/big_data.sql"})
public class AcceptanceTestForUserPortfolio {

    @Autowired private ApplicationContext appContext;

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
        IntStream.rangeClosed(0, portfolio.size() - 1)
                        .forEach(i -> portfolio.get(i).setUserId(1L));
        assertEquals(portfolio.size(), response1.getPortfolio().size());
        IntStream.rangeClosed(0, portfolio.size() - 1)
                .forEach(i -> assertTrue(portfolio.contains(response1.getPortfolio().get(i))));
        assertEquals(872.1, response1.getUser().getCash(), 0.01);

        assertEquals(portfolio.size(), response2.getPortfolio().size());
        response2.getPortfolio().forEach(System.out::println);
        IntStream.rangeClosed(0, portfolio.size() - 1)
                .forEach(i -> assertTrue(portfolio.contains(response2.getPortfolio().get(i))));
        assertEquals(872.1, response2.getUser().getCash(), 0.01);
    }

    public static class MyTrigger implements Trigger {
        @Override
        public void init(Connection conn, String schemaName, String triggerName,
                         String tableName, boolean before, int type) {}
        @Override
        public void fire(Connection conn, Object[] oldRow, Object[] newRow) {
            double quantity = (double) newRow[3];
            double purchasePrice = (double) newRow[4];
            double amount = quantity * purchasePrice;
            try{
                PreparedStatement ps = conn.prepareStatement("UPDATE users SET cash = cash - ? WHERE id = ?");
                ps.setDouble(1, amount);
                ps.setLong(2, (Long) newRow[1]);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void close() {}
        @Override
        public void remove() {}
    }

    private GenerateUserPortfolioService getGenerateUserPortfolioService() {
        return appContext.getBean(GenerateUserPortfolioService.class);
    }

    private GetUserPortfolioService getGetUserPortfolioService() {
        return appContext.getBean(GetUserPortfolioService.class);
    }

}
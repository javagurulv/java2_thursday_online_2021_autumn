package lv.javaguru.java2.qwe.acceptance_test;

import lv.javaguru.java2.qwe.config.AppConfiguration;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByTickerOrNameRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.StockMarketOrderRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.StockMarketOrderResponse;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.data_services.FindSecurityByTickerOrNameService;
import lv.javaguru.java2.qwe.core.services.user_services.StockMarketOrderService;
import lv.javaguru.java2.qwe.core.services.user_services.FindUserByNameService;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserPortfolioService;
import org.h2.api.Trigger;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
@Sql({"/schema.sql"})
@Sql({"/data.sql"})
public class AcceptanceTestForStockMarketOrder {

    @Autowired private ApplicationContext appContext;

    @Test
    public void shouldBuyOneStockAtMarketPrice() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("Alexander")).getUser();
        Security security = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("AMZN US")).getSecurity();
        StockMarketOrderRequest request1 = new StockMarketOrderRequest(user, security,"150",3525.15);
        StockMarketOrderResponse response1 = getService().execute(request1);
        Position position = new Position(security, 150, 3525.15);
        position.setUser(user);
        List<Position> portfolio = List.of(position);
        GetUserPortfolioResponse response2 = getGetUserPortfolioService().execute(new GetUserPortfolioRequest("Alexander"));
        assertEquals(position, response1.getPosition());
        assertEquals(portfolio, response2.getPortfolio());
        assertEquals(471_227.50,
                getFindUserByNameService().execute(new FindUserByNameRequest("Alexander")).getUser().getCash(), 0.01);
    }

    @Test
    public void shouldBuyTwoDifferentStocksAtMarketPrice() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("Alexander")).getUser();
        Security security1 = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("AMZN US")).getSecurity();
        Security security2 = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("JPM US")).getSecurity();
        StockMarketOrderRequest request1 = new StockMarketOrderRequest(user, security1,"150",3525.15);
        StockMarketOrderRequest request2 = new StockMarketOrderRequest(user, security2,"755",166.91);
        Position position1 = new Position(security1, 150, 3525.15);
        position1.setUser(user);
        Position position2 = new Position(security2, 755, 166.91);
        position2.setUser(user);
        List<Position> portfolio = List.of(position1, position2);
        StockMarketOrderResponse response1 = getService().execute(request1);
        StockMarketOrderResponse response2 = getService().execute(request2);
        GetUserPortfolioResponse response3 = getGetUserPortfolioService().execute(new GetUserPortfolioRequest("Alexander"));
        assertEquals(position1, response1.getPosition());
        assertEquals(position2, response2.getPosition());
        assertEquals(portfolio, response3.getPortfolio());
        assertEquals(345_210.45,
                getFindUserByNameService().execute(new FindUserByNameRequest("Alexander")).getUser().getCash(), 0.01);
    }

    @Test
    public void shouldBuyTwoSameStockAtMarketPrice() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("Alexander")).getUser();
        Security security = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("AMZN US")).getSecurity();
        StockMarketOrderRequest request1 = new StockMarketOrderRequest(user, security,"150",3525.15);
        StockMarketOrderRequest request2 = new StockMarketOrderRequest(user, security,"55",3247.23);
        Position position1 = new Position(security, 150, 3525.15);
        position1.setUser(user);
        Position position2 = new Position(security, 55, 3247.23);
        position2.setUser(user);
        Position totalPosition = new Position(security, 205, 3450.59);
        totalPosition.setUser(user);
        List<Position> portfolio = List.of(totalPosition);
        StockMarketOrderResponse response1 = getService().execute(request1);
        StockMarketOrderResponse response2 = getService().execute(request2);
        GetUserPortfolioResponse response3 = getGetUserPortfolioService().execute(new GetUserPortfolioRequest("Alexander"));
        assertEquals(position1, response1.getPosition());
        assertEquals(position2, response2.getPosition());
        assertEquals(portfolio, response3.getPortfolio());
        assertEquals(292_629.05,
                getFindUserByNameService().execute(new FindUserByNameRequest("Alexander")).getUser().getCash(), 0.01);
    }

    @Test
    public void shouldSellOneStockAllQuantity() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("John")).getUser();
        Security security = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("MSFT US")).getSecurity();
        StockMarketOrderRequest request1 = new StockMarketOrderRequest(user, security,"-1000",325.12);
        StockMarketOrderResponse response1 = getService().execute(request1);
        Position position = new Position(security, -1000, 325.12);
        position.setUser(user);
        List<Position> portfolio = List.of();
        GetUserPortfolioResponse response2 = getGetUserPortfolioService().execute(new GetUserPortfolioRequest("John"));
        assertEquals(position, response1.getPosition());
        assertEquals(portfolio, response2.getPortfolio());
        assertEquals(195_640.00 + 325_120,
                getFindUserByNameService().execute(new FindUserByNameRequest("John")).getUser().getCash(), 0.01);
    }

    @Test
    public void shouldSellOneStockPartly() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("John")).getUser();
        Security security = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("MSFT US")).getSecurity();
        StockMarketOrderRequest request1 = new StockMarketOrderRequest(user, security,"-700",325.12);
        StockMarketOrderResponse response1 = getService().execute(request1);
        Position position = new Position(security, -700, 325.12);
        position.setUser(user);
        Position totalPosition = new Position(security, 300, 304.36);
        totalPosition.setUser(user);
        List<Position> portfolio = List.of(totalPosition);
        GetUserPortfolioResponse response2 = getGetUserPortfolioService().execute(new GetUserPortfolioRequest("John"));
        assertEquals(position, response1.getPosition());
        assertEquals(portfolio, response2.getPortfolio());
        assertEquals(195_640.00 + 227_584,
                getFindUserByNameService().execute(new FindUserByNameRequest("John")).getUser().getCash(), 0.01);
    }

    @Test
    public void shouldBuyAndSellMultipleDifferentStocks() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("Alexander")).getUser();
        Security security1 = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("AMZN US")).getSecurity();
        Security security2 = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("JPM US")).getSecurity();
        Security security3 = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("TSLA US")).getSecurity();
        StockMarketOrderRequest request1 = new StockMarketOrderRequest(user, security1,"150",3525.15);
        StockMarketOrderRequest request2 = new StockMarketOrderRequest(user, security2,"755",166.91);
        StockMarketOrderRequest request3 = new StockMarketOrderRequest(user, security1,"25",3364.91);
        StockMarketOrderRequest request4 = new StockMarketOrderRequest(user, security1,"-100",3621.48);
        StockMarketOrderRequest request5 = new StockMarketOrderRequest(user, security2,"1245",175.67);
        StockMarketOrderRequest request6 = new StockMarketOrderRequest(user, security1,"-75",3382.43);
        StockMarketOrderRequest request7 = new StockMarketOrderRequest(user, security3,"90",1125.13);
        getService().execute(request1);
        getService().execute(request2);
        getService().execute(request3);
        getService().execute(request4);
        getService().execute(request5);
        getService().execute(request6);
        getService().execute(request7);
        Position totalPosition1 = new Position(security2, 2000, 172.36);
        totalPosition1.setUser(user);
        Position totalPosition2 = new Position(security3, 90, 1125.13);
        totalPosition2.setUser(user);
        List<Position> portfolio = List.of(totalPosition1, totalPosition2);
        GetUserPortfolioResponse response8 = getGetUserPortfolioService().execute(new GetUserPortfolioRequest("Alexander"));
        assertEquals(portfolio, response8.getPortfolio());
        assertEquals(556_947.1,
                getFindUserByNameService().execute(new FindUserByNameRequest("Alexander")).getUser().getCash(), 10);
    }

    @Test
    public void shouldFailToBuyStockAtMarketPrice1() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("Alexander")).getUser();
        Security security = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("AMZN US")).getSecurity();
        StockMarketOrderRequest request1 = new StockMarketOrderRequest(user, security,"0",3525.15);
        List<Position> portfolio = List.of();
        StockMarketOrderResponse response1 = getService().execute(request1);
        GetUserPortfolioResponse response2 = getGetUserPortfolioService().execute(new GetUserPortfolioRequest("Alexander"));
        assertNull(response1.getPosition());
        assertTrue(response1.hasErrors());
        assertEquals("must be higher then 0!", response1.getErrors().get(0).getMessage());
        assertEquals(portfolio, response2.getPortfolio());
    }

    @Test
    public void shouldFailToBuyStockAtMarketPrice2() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("Alexander")).getUser();
        Security security = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("AMZN US")).getSecurity();
        StockMarketOrderRequest request1 = new StockMarketOrderRequest(user, security,"-150",3525.15);
        List<Position> portfolio = List.of();
        StockMarketOrderResponse response1 = getService().execute(request1);
        GetUserPortfolioResponse response2 = getGetUserPortfolioService().execute(new GetUserPortfolioRequest("Alexander"));
        assertNull(response1.getPosition());
        assertTrue(response1.hasErrors());
        assertEquals("not enough securities to sell!", response1.getErrors().get(0).getMessage());
        assertEquals(portfolio, response2.getPortfolio());
    }

    @Test
    public void shouldFailToBuyStockAtMarketPrice3() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("")).getUser();
        Security security = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("AMZN US")).getSecurity();
        StockMarketOrderRequest request1 = new StockMarketOrderRequest(user, security,"150",3525.15);
        List<Position> portfolio = List.of();
        StockMarketOrderResponse response1 = getService().execute(request1);
        GetUserPortfolioResponse response2 = getGetUserPortfolioService().execute(new GetUserPortfolioRequest("Alexander"));
        assertNull(response1.getPosition());
        assertTrue(response1.hasErrors());
        assertEquals("no user with such name exists in the database!", response1.getErrors().get(0).getMessage());
        assertEquals(portfolio, response2.getPortfolio());
    }

    @Test
    public void shouldFailToBuyStockAtMarketPrice4() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("Alexander")).getUser();
        Security security = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("INTC US")).getSecurity();
        StockMarketOrderRequest request1 = new StockMarketOrderRequest(user, security,"1250",52.13);
        List<Position> portfolio = List.of();
        StockMarketOrderResponse response1 = getService().execute(request1);
        GetUserPortfolioResponse response2 = getGetUserPortfolioService().execute(new GetUserPortfolioRequest("Alexander"));
        assertNull(response1.getPosition());
        assertTrue(response1.hasErrors());
        assertEquals("no security with such id in the database!", response1.getErrors().get(0).getMessage());
        assertEquals(portfolio, response2.getPortfolio());
    }

    public static class MyTrigger2 implements Trigger {
        @Override
        public void init(Connection conn, String schemaName, String triggerName,
                         String tableName, boolean before, int type) {}
        @Override
        public void fire(Connection conn, Object[] oldRow, Object[] newRow) {
            double quantity = (double) oldRow[3];
            double purchasePrice = (double) oldRow[4];
            double amount = quantity * purchasePrice;
            try{
                PreparedStatement ps = conn.prepareStatement("UPDATE users SET cash = cash + ? WHERE id = ?");
                ps.setDouble(1, amount);
                ps.setLong(2, (Long) oldRow[1]);
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

    private StockMarketOrderService getService() {
        return appContext.getBean(StockMarketOrderService.class);
    }

    private GetUserPortfolioService getGetUserPortfolioService() {
        return appContext.getBean(GetUserPortfolioService.class);
    }

    private FindUserByNameService getFindUserByNameService() {
        return appContext.getBean(FindUserByNameService.class);
    }

    private FindSecurityByTickerOrNameService getFindSecurityByTickerOrNameService() {
        return appContext.getBean(FindSecurityByTickerOrNameService.class);
    }

    private Database getDatabase() {
        return appContext.getBean(Database.class);
    }

}
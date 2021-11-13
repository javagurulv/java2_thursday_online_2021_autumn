package lv.javaguru.java2.qwe.acceptance_test;

import lv.javaguru.java2.qwe.config.AppConfiguration;
import lv.javaguru.java2.qwe.core.domain.Position;
import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.User;
import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByTickerOrNameRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.BuyStockMarketOrderRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserPortfolioRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.BuyStockMarketOrderResponse;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserPortfolioResponse;
import lv.javaguru.java2.qwe.core.services.data_services.FindSecurityByTickerOrNameService;
import lv.javaguru.java2.qwe.core.services.user_services.BuyStockMarketOrderService;
import lv.javaguru.java2.qwe.core.services.user_services.FindUserByNameService;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
@Sql({"/schema.sql"})
@Sql({"/data.sql"})
public class AcceptanceTestForBuyStockMarketOrder {

    @Autowired private ApplicationContext appContext;

    @Test
    public void shouldBuyOneStockAtMarketPrice() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("Alexander")).getUser();
        Security security = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("AMZN US")).getSecurity();
        BuyStockMarketOrderRequest request1 = new BuyStockMarketOrderRequest(user, security,"150",3525.15);
        BuyStockMarketOrderResponse response1 = getService().execute(request1);
        Position position = new Position(security, 150, 3525.15);
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
        BuyStockMarketOrderRequest request1 = new BuyStockMarketOrderRequest(user, security1,"150",3525.15);
        BuyStockMarketOrderRequest request2 = new BuyStockMarketOrderRequest(user, security2,"755",166.91);
        Position position1 = new Position(security1, 150, 3525.15);
        Position position2 = new Position(security2, 755, 166.91);
        List<Position> portfolio = List.of(position1, position2);
        BuyStockMarketOrderResponse response1 = getService().execute(request1);
        BuyStockMarketOrderResponse response2 = getService().execute(request2);
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
        BuyStockMarketOrderRequest request1 = new BuyStockMarketOrderRequest(user, security,"150",3525.15);
        BuyStockMarketOrderRequest request2 = new BuyStockMarketOrderRequest(user, security,"55",3247.23);
        Position position1 = new Position(security, 150, 3525.15);
        Position position2 = new Position(security, 55, 3247.23);
        Position totalPosition = new Position(security, 205, 3450.59);
        List<Position> portfolio = List.of(totalPosition);
        BuyStockMarketOrderResponse response1 = getService().execute(request1);
        BuyStockMarketOrderResponse response2 = getService().execute(request2);
        GetUserPortfolioResponse response3 = getGetUserPortfolioService().execute(new GetUserPortfolioRequest("Alexander"));
        assertEquals(position1, response1.getPosition());
        assertEquals(position2, response2.getPosition());
        assertEquals(portfolio, response3.getPortfolio());
    }

    @Test
    public void shouldFailToBuyStockAtMarketPrice1() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("Alexander")).getUser();
        Security security = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("AMZN US")).getSecurity();
        BuyStockMarketOrderRequest request1 = new BuyStockMarketOrderRequest(user, security,"0",3525.15);
        List<Position> portfolio = List.of();
        BuyStockMarketOrderResponse response1 = getService().execute(request1);
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
        BuyStockMarketOrderRequest request1 = new BuyStockMarketOrderRequest(user, security,"-150",3525.15);
        List<Position> portfolio = List.of();
        BuyStockMarketOrderResponse response1 = getService().execute(request1);
        GetUserPortfolioResponse response2 = getGetUserPortfolioService().execute(new GetUserPortfolioRequest("Alexander"));
        assertNull(response1.getPosition());
        assertTrue(response1.hasErrors());
        assertEquals("must be higher then 0!", response1.getErrors().get(0).getMessage());
        assertEquals(portfolio, response2.getPortfolio());
    }

    @Test
    public void shouldFailToBuyStockAtMarketPrice3() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("")).getUser();
        Security security = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("AMZN US")).getSecurity();
        BuyStockMarketOrderRequest request1 = new BuyStockMarketOrderRequest(user, security,"150",3525.15);
        List<Position> portfolio = List.of();
        BuyStockMarketOrderResponse response1 = getService().execute(request1);
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
        BuyStockMarketOrderRequest request1 = new BuyStockMarketOrderRequest(user, security,"1250",52.13);
        List<Position> portfolio = List.of();
        BuyStockMarketOrderResponse response1 = getService().execute(request1);
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

    private BuyStockMarketOrderService getService() {
        return appContext.getBean(BuyStockMarketOrderService.class);
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

}
package lv.javaguru.java2.qwe.acceptance_test;

import lv.javaguru.java2.qwe.config.SpringCoreConfiguration;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.domain.*;
import lv.javaguru.java2.qwe.core.requests.data_requests.FindSecurityByTickerOrNameRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.FindUserByNameRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetUserTradesRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.StockMarketOrderRequest;
import lv.javaguru.java2.qwe.core.responses.user_responses.GetUserTradesResponse;
import lv.javaguru.java2.qwe.core.services.data_services.FindSecurityByTickerOrNameService;
import lv.javaguru.java2.qwe.core.services.user_services.FindUserByNameService;
import lv.javaguru.java2.qwe.core.services.user_services.GetUserTradesService;
import lv.javaguru.java2.qwe.core.services.user_services.StockMarketOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringCoreConfiguration.class})
@Sql({"/schema.sql"})
@Sql({"/data.sql"})
public class AcceptanceTestForTradeTickets {

    @Autowired private ApplicationContext appContext;

    @Test
    public void shouldReturnOneBuyTradeTicket() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("Alexander")).getUser();
        Security security = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("AMZN US")).getSecurity();
        StockMarketOrderRequest request1 = new StockMarketOrderRequest(user, security,"150",3525.15);
        getStockMarketOrderService().execute(request1);
        GetUserTradesRequest request2 = new GetUserTradesRequest("Alexander");
        GetUserTradesResponse response2 = getService().execute(request2);
        List<TradeTicket> trades = List.of(new TradeTicket(
                user, security, TradeType.BUY, 150., 3525.15, LocalDateTime.now()
        ));
        assertEquals(1, response2.getTrades().size());
        IntStream.rangeClosed(0, 0)
                .forEach(i -> {
                    assertEquals(trades.get(i).getUser().getId(), response2.getTrades().get(i).getUser().getId());
                    assertEquals(trades.get(i).getSecurity().getTicker(), response2.getTrades().get(i).getSecurity().getTicker());
                    assertEquals(trades.get(i).getTradeType(), response2.getTrades().get(i).getTradeType());
                    assertEquals(trades.get(i).getQuantity(), response2.getTrades().get(i).getQuantity());
                    assertEquals(trades.get(i).getTradePrice(), response2.getTrades().get(i).getTradePrice());
                });
    }

    @Test
    public void shouldReturnOneSellTradeTicket() {
        User user = getFindUserByNameService().execute(new FindUserByNameRequest("John")).getUser();
        Security security = getFindSecurityByTickerOrNameService().execute(new FindSecurityByTickerOrNameRequest("MSFT US")).getSecurity();
        StockMarketOrderRequest request1 = new StockMarketOrderRequest(user, security,"-500",304.36);
        getStockMarketOrderService().execute(request1);
        GetUserTradesRequest request2 = new GetUserTradesRequest("John");
        GetUserTradesResponse response2 = getService().execute(request2);
        List<TradeTicket> trades = List.of(new TradeTicket(
                user, security, TradeType.SELL, -500., 304.36, LocalDateTime.now()
        ));
        assertEquals(1, response2.getTrades().size());
        IntStream.rangeClosed(0, 0)
                .forEach(i -> {
                    assertEquals(trades.get(i).getUser().getId(), response2.getTrades().get(i).getUser().getId());
                    assertEquals(trades.get(i).getSecurity().getTicker(), response2.getTrades().get(i).getSecurity().getTicker());
                    assertEquals(trades.get(i).getTradeType(), response2.getTrades().get(i).getTradeType());
                    assertEquals(trades.get(i).getQuantity(), response2.getTrades().get(i).getQuantity());
                    assertEquals(trades.get(i).getTradePrice(), response2.getTrades().get(i).getTradePrice());
                });
    }

    @Test
    public void shouldReturnMultipleBuyAndSellTradeTickets() {
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
        getStockMarketOrderService().execute(request1);
        getStockMarketOrderService().execute(request2);
        getStockMarketOrderService().execute(request3);
        getStockMarketOrderService().execute(request4);
        getStockMarketOrderService().execute(request5);
        getStockMarketOrderService().execute(request6);
        getStockMarketOrderService().execute(request7);
        getDatabase().updateStock((Stock) security2);
        getDatabase().updateStock((Stock) security3);
        GetUserTradesRequest request8 = new GetUserTradesRequest("Alexander");
        GetUserTradesResponse response8 = getService().execute(request8);
        List<TradeTicket> trades = List.of(
                new TradeTicket(user, security1, TradeType.BUY, 150., 3525.15, LocalDateTime.now()),
                new TradeTicket(user, security2, TradeType.BUY, 755., 166.91, LocalDateTime.now()),
                new TradeTicket(user, security1, TradeType.BUY, 25., 3364.91, LocalDateTime.now()),
                new TradeTicket(user, security1, TradeType.SELL, -100., 3621.48, LocalDateTime.now()),
                new TradeTicket(user, security2, TradeType.BUY, 1245., 175.67, LocalDateTime.now()),
                new TradeTicket(user, security1, TradeType.SELL, -75., 3382.43, LocalDateTime.now()),
                new TradeTicket(user, security3, TradeType.BUY, 90., 1125.13, LocalDateTime.now())
        );
        assertEquals(7, response8.getTrades().size());
        IntStream.rangeClosed(0, trades.size() - 1)
                .forEach(i -> {
                    assertEquals(trades.get(i).getUser().getId(), response8.getTrades().get(i).getUser().getId());
                    assertEquals(trades.get(i).getSecurity().getTicker(), response8.getTrades().get(i).getSecurity().getTicker());
                    assertEquals(trades.get(i).getTradeType(), response8.getTrades().get(i).getTradeType());
                    assertEquals(trades.get(i).getQuantity(), response8.getTrades().get(i).getQuantity());
                    assertEquals(trades.get(i).getTradePrice(), response8.getTrades().get(i).getTradePrice());
                });
    }

    private StockMarketOrderService getStockMarketOrderService() {
        return appContext.getBean(StockMarketOrderService.class);
    }

    private FindSecurityByTickerOrNameService getFindSecurityByTickerOrNameService() {
        return appContext.getBean(FindSecurityByTickerOrNameService.class);
    }

    private FindUserByNameService getFindUserByNameService() {
        return appContext.getBean(FindUserByNameService.class);
    }

    private GetUserTradesService getService() {
        return appContext.getBean(GetUserTradesService.class);
    }

    private Database getDatabase() {
        return appContext.getBean(Database.class);
    }

}
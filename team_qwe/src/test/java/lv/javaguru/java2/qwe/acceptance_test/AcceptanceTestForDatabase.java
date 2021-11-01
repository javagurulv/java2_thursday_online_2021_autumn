package lv.javaguru.java2.qwe.acceptance_test;

import lv.javaguru.java2.qwe.config.AppConfiguration;
import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.requests.data_requests.*;
import lv.javaguru.java2.qwe.core.responses.data_responses.FilterStocksByMultipleParametersResponse;
import lv.javaguru.java2.qwe.core.responses.data_responses.FindSecurityByTickerOrNameResponse;
import lv.javaguru.java2.qwe.core.responses.data_responses.GetAllSecurityListResponse;
import lv.javaguru.java2.qwe.core.responses.data_responses.RemoveSecurityResponse;
import lv.javaguru.java2.qwe.core.services.data_services.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static java.util.List.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
public class AcceptanceTestForDatabase {

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
        jdbcTemplate.update("INSERT INTO stocks VALUES('AAPL US','Apple Inc.','Technology','USD',148.19,0.59,1)");
        jdbcTemplate.update("INSERT INTO stocks VALUES('MSFT US','Microsoft Corporation','Technology','USD',304.36,0.74,0.88)");
        jdbcTemplate.update("INSERT INTO stocks VALUES('AMZN US','Amazon.com Inc.','Consumer Discretionary','USD',3199.95,0,0.69)");
        jdbcTemplate.update("INSERT INTO stocks VALUES('TSLA US','Tesla Inc','Consumer Discretionary','USD',680.26,0,1.63)");
        jdbcTemplate.update("INSERT INTO stocks VALUES('JPM US','JPMorgan Chase & Co.','Financials','USD',154.72,2.33,1.15)");
    }

    @Test
    public void addSecuritiesToDatabaseTest1() {
        AddStockRequest request = new AddStockRequest(
                "BABA US", "Alibaba", "Technology", "USD",
                "160.13", "0", "1.32"
        );
        getAddStockService().execute(request);
        GetAllSecurityListResponse response = getAllSecurityListService().execute(new GetAllSecurityListRequest());
        assertEquals(6, response.getList().size());
        assertEquals("Alibaba", response.getList().get(5).getName());
    }

    @Test
    public void addSecuritiesToDatabaseTest2() {
        AddStockRequest request = new AddStockRequest(
                "INTC US", "Intel", "Technology", "USD",
                "53.73", "2.o5", "1.12" //ошибка!
        );
        getAddStockService().execute(request);
        GetAllSecurityListResponse response = getAllSecurityListService().execute(new GetAllSecurityListRequest());
        assertEquals(5, response.getList().size());
    }

    @Test
    public void removeSecuritiesFromDatabaseTest1() {
        RemoveSecurityRequest request = new RemoveSecurityRequest("AAPL US");
        RemoveSecurityResponse response1 = getRemoveSecurityService().execute(request);
        GetAllSecurityListResponse response2 = getAllSecurityListService().execute(new GetAllSecurityListRequest());
        assertTrue(response1.isRemoved());
        assertEquals(4, response2.getList().size());
    }

    @Test
    public void removeSecuritiesFromDatabaseTest2() {
        RemoveSecurityRequest request = new RemoveSecurityRequest("AMZN"); //ошибка!
        RemoveSecurityResponse response1 = getRemoveSecurityService().execute(request);
        GetAllSecurityListResponse response2 = getAllSecurityListService().execute(new GetAllSecurityListRequest());
        assertFalse(response1.isRemoved());
        assertEquals(5, response2.getList().size());
        assertEquals("Amazon.com Inc.", response2.getList().get(2).getName());
    }

    @Test
    public void findSecurityByNameInDatabaseTest1() {
        FindSecurityByTickerOrNameRequest request1 = new FindSecurityByTickerOrNameRequest("Apple Inc.");
        FindSecurityByTickerOrNameResponse response1 = getFindSecurityByNameService().execute(request1);
        Stock apple = new Stock("AAPL US", "Apple Inc.",
                "Technology", "USD", 148.19, 0.59, 1);
        assertEquals(apple, response1.getSecurity());
    }

    @Test
    public void findSecurityByNameInDatabaseTest2() {
        FindSecurityByTickerOrNameRequest request2 = new FindSecurityByTickerOrNameRequest("AAPL");
        FindSecurityByTickerOrNameResponse response2 = getFindSecurityByNameService().execute(request2);
        assertNull(response2.getSecurity());
    }

    @Test
    public void findSecurityByNameInDatabaseTest3() {
        FindSecurityByTickerOrNameRequest request3 = new FindSecurityByTickerOrNameRequest("AMZN US");
        FindSecurityByTickerOrNameResponse response3 = getFindSecurityByNameService().execute(request3);
        Stock amazon = new Stock("AMZN US", "Amazon.com Inc.",
                "Consumer Discretionary", "USD", 3199.95, 0, 0.69);
        assertEquals(amazon, response3.getSecurity());
    }

    @Test
    public void filterStocksByMultipleParametersTest1() {
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(
                of(
                        new FilterStocksByIndustryRequest("Technology"),
                        new OrderingRequest("name", "DESC")
                )
        );
        FilterStocksByMultipleParametersResponse response1 =
                getFilterStocksByMultipleParametersService().execute(request);
        assertEquals(of(
                new Stock("MSFT US", "Microsoft Corporation", "Technology", "USD", 304.36, 0.74, 0.88),
                new Stock("AAPL US", "Apple Inc.", "Technology", "USD", 148.19, 0.59, 1)
        ), response1.getList());
    }

    @Test
    public void filterStocksByMultipleParametersTest2() {
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(
                of(
                        new FilterStocksByAnyDoubleParameterRequest("dividend_yield", ">", "0"),
                        new FilterStocksByAnyDoubleParameterRequest("risk_weight", "<", "1")
                )
        );
        FilterStocksByMultipleParametersResponse response2 =
                getFilterStocksByMultipleParametersService().execute(request);
        assertEquals(of(
                new Stock("MSFT US", "Microsoft Corporation", "Technology", "USD", 304.36, 0.74, 0.88)
        ), response2.getList());
    }



    private AddStockService getAddStockService() {
        return appContext.getBean(AddStockService.class);
    }

    private GetAllSecurityListService getAllSecurityListService() {
        return appContext.getBean(GetAllSecurityListService.class);
    }

    private RemoveSecurityService getRemoveSecurityService() {
        return appContext.getBean(RemoveSecurityService.class);
    }

    private FindSecurityByTickerOrNameService getFindSecurityByNameService() {
        return appContext.getBean(FindSecurityByTickerOrNameService.class);
    }

    private FilterStocksByMultipleParametersService getFilterStocksByMultipleParametersService() {
        return appContext.getBean(FilterStocksByMultipleParametersService.class);
    }

}
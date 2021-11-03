package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.config.AppConfiguration;
import lv.javaguru.java2.qwe.core.requests.data_requests.AddStockRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
public class AddStockValidatorTest {

    @Autowired private ApplicationContext appContext;
    @Autowired private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        jdbcTemplate.update("RUNSCRIPT FROM 'classpath:schema.sql'");
/*        jdbcTemplate.update("DROP TABLE IF EXISTS stocks, bonds, users, users_positions CASCADE");
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
                ")");*/
        jdbcTemplate.update("INSERT INTO stocks VALUES('BABA US','Alibaba','Technology','USD',170.13,0,1.32)");
    }

    @Test
    public void shouldReturnEmptyList() {
        AddStockRequest request = new AddStockRequest(
                "INTC US",
                "Intel",
                "Technology",
                "USD",
                "54.23",
                "1.15",
                "1.09"
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnNameError1() {
        AddStockRequest request = new AddStockRequest(
                "INTC US",
                "In",
                "Technology",
                "USD",
                "54.23",
                "1.15",
                "1.09"
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "3 to 100 symbols required!");
    }

    @Test
    public void shouldReturnNameError2() {
        AddStockRequest request = new AddStockRequest(
                "BABA US",
                "Alibaba",
                "Technology",
                "USD",
                "170.13",
                "0",
                "1.32"
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "security with such name already exists in the database!");
    }

    @Test
    public void shouldReturnIndustryError() {
        AddStockRequest request = new AddStockRequest(
                "INTC US",
                "Intel",
                "",
                "USD",
                "54.23",
                "1.15",
                "1.09"
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Industry");
        assertEquals(errorList.get(0).getMessage(), "is empty!");
    }

    @Test
    public void shouldReturnCurrencyError() {
        AddStockRequest request = new AddStockRequest(
                "INTC US",
                "Intel",
                "Technology",
                "",
                "54.23",
                "1.15",
                "1.09"
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Currency");
        assertEquals(errorList.get(0).getMessage(), "is empty!");
    }

    @Test
    public void shouldReturnMarketPriceError1() {
        AddStockRequest request = new AddStockRequest(
                "INTC US",
                "Intel",
                "Technology",
                "USD",
                "abc",
                "1.15",
                "1.09"
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Market price");
        assertEquals(errorList.get(0).getMessage(), "wrong format! Must be double!");
    }

    @Test
    public void shouldReturnMarketPriceError2() {
        AddStockRequest request = new AddStockRequest(
                "INTC US",
                "Intel",
                "Technology",
                "USD",
                "-54.23",
                "1.15",
                "1.09"
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Market price");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnDividendError1() {
        AddStockRequest request = new AddStockRequest(
                "INTC US",
                "Intel",
                "Technology",
                "USD",
                "54.23",
                "",
                "1.09"
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Dividend");
        assertEquals(errorList.get(0).getMessage(), "wrong format! Must be double!");
    }

    @Test
    public void shouldReturnDividendError2() {
        AddStockRequest request = new AddStockRequest(
                "INTC US",
                "Intel",
                "Technology",
                "USD",
                "54.23",
                "-1.15",
                "1.09"
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Dividend");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnRiskWeightError1() {
        AddStockRequest request = new AddStockRequest(
                "INTC US",
                "Intel",
                "Technology",
                "USD",
                "54.23",
                "1.15",
                "1.a"
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Risk weight");
        assertEquals(errorList.get(0).getMessage(), "wrong format! Must be double!");
    }

    @Test
    public void shouldReturnRiskWeightError2() {
        AddStockRequest request = new AddStockRequest(
                "INTC US",
                "Intel",
                "Technology",
                "USD",
                "54.23",
                "1.15",
                "-1.09"
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Risk weight");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnNameCurrencyRiskWeightErrors() {
        AddStockRequest request = new AddStockRequest(
                "INTC US",
                "In",
                "Technology",
                "",
                "54.23",
                "1.15",
                "-1.09"
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(errorList.size(), 3);
        assertTrue(errorList.contains(new CoreError("Risk weight", "cannot be negative!")));
        assertTrue(errorList.contains(new CoreError("Name", "3 to 100 symbols required!")));
        assertTrue(errorList.contains(new CoreError("Currency", "is empty!")));
    }

    @Test
    public void shouldReturnMarketPriceDividendErrors() {
        AddStockRequest request = new AddStockRequest(
                "INTC US",
                "Intel",
                "Technology",
                "USD",
                "54.a3",
                "-1.15",
                "1.09"
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(errorList.size(), 2);
        assertTrue(errorList.contains(new CoreError("Market price", "wrong format! Must be double!")));
        assertTrue(errorList.contains(new CoreError("Dividend", "cannot be negative!")));
    }

    @Test
    public void shouldReturnAllErrors() {
        AddStockRequest request = new AddStockRequest(
                "INTC US",
                "",
                "",
                "",
                "",
                "",
                ""
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(errorList.size(), 6);
        assertTrue(errorList.contains(new CoreError("Name", "3 to 100 symbols required!")));
        assertTrue(errorList.contains(new CoreError("Industry", "is empty!")));
        assertTrue(errorList.contains(new CoreError("Currency", "is empty!")));
        assertTrue(errorList.contains(new CoreError("Market price", "wrong format! Must be double!")));
        assertTrue(errorList.contains(new CoreError("Dividend", "wrong format! Must be double!")));
        assertTrue(errorList.contains(new CoreError("Risk weight", "wrong format! Must be double!")));
    }

    private AddStockValidator getValidator() {
        return appContext.getBean(AddStockValidator.class);
    }

}
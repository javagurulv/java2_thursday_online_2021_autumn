package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.config.SpringCoreConfiguration;
import lv.javaguru.java2.qwe.core.requests.data_requests.GetAllSecurityListRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.StockMarketOrderRequest;
import lv.javaguru.java2.qwe.core.requests.user_requests.GetAllUserListRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.services.data_services.GetAllSecurityListService;
import lv.javaguru.java2.qwe.core.services.user_services.GetAllUserListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringCoreConfiguration.class})
public class BuyStockMarketOrderValidatorTest {

    @Autowired private ApplicationContext appContext;
    @Autowired private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        jdbcTemplate.update("RUNSCRIPT FROM 'classpath:schema.sql'");
        jdbcTemplate.update("RUNSCRIPT FROM 'classpath:data.sql'");
    }

    @Test
    public void shouldReturnEmptyList1() {
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                getAllUserListService().execute(new GetAllUserListRequest()).getList().get(0),
                getAllSecurityListService().execute(new GetAllSecurityListRequest()).getList().get(3),
                "100",
                680.26
        );
        List<CoreError> errors = getValidator().validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList2() {
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                getAllUserListService().execute(new GetAllUserListRequest()).getList().get(3),
                getAllSecurityListService().execute(new GetAllSecurityListRequest()).getList().get(1),
                "-500",
                324.26
        );
        List<CoreError> errors = getValidator().validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnSecurityError() {
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                getAllUserListService().execute(new GetAllUserListRequest()).getList().get(0),
                null,
                "1000",
                163.24
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(1, errorList.size());
        assertEquals(errorList.get(0).getField(), "Security");
        assertEquals(errorList.get(0).getMessage(), "no security with such id in the database!");
    }

    @Test
    public void shouldReturnRealTimePriceError1() {
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                getAllUserListService().execute(new GetAllUserListRequest()).getList().get(0),
                getAllSecurityListService().execute(new GetAllSecurityListRequest()).getList().get(1),
                "1000",
                null
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(1, errorList.size());
        assertEquals(errorList.get(0).getField(), "Real-time price");
        assertEquals(errorList.get(0).getMessage(), "cannot get real-time quote from API! Check connection!");
    }

    @Test
    public void shouldReturnRealTimePriceError2() {
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                getAllUserListService().execute(new GetAllUserListRequest()).getList().get(0),
                getAllSecurityListService().execute(new GetAllSecurityListRequest()).getList().get(1),
                "1000",
                -1.
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(1, errorList.size());
        assertEquals(errorList.get(0).getField(), "Real-time price");
        assertEquals(errorList.get(0).getMessage(), "wrong format in http response data!");
    }

    @Test
    public void shouldReturnUserError() {
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                null,
                getAllSecurityListService().execute(new GetAllSecurityListRequest()).getList().get(1),
                "1000",
                163.24
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(1, errorList.size());
        assertEquals(errorList.get(0).getField(), "User");
        assertEquals(errorList.get(0).getMessage(), "no user with such name exists in the database!");
    }

    @Test
    public void shouldReturnQuantityError1() {
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                getAllUserListService().execute(new GetAllUserListRequest()).getList().get(0),
                getAllSecurityListService().execute(new GetAllSecurityListRequest()).getList().get(1),
                "0",
                163.24
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(1, errorList.size());
        assertEquals(errorList.get(0).getField(), "Quantity");
        assertEquals(errorList.get(0).getMessage(), "must be higher then 0!");
    }

    @Test
    public void shouldReturnQuantityError2() {
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                getAllUserListService().execute(new GetAllUserListRequest()).getList().get(3),
                getAllSecurityListService().execute(new GetAllSecurityListRequest()).getList().get(1),
                "-3000",
                324.83
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(1, errorList.size());
        assertEquals(errorList.get(0).getField(), "Quantity");
        assertEquals(errorList.get(0).getMessage(), "not enough securities to sell!");
    }

    @Test
    public void shouldReturnQuantityError3() {
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                getAllUserListService().execute(new GetAllUserListRequest()).getList().get(3),
                getAllSecurityListService().execute(new GetAllSecurityListRequest()).getList().get(3),
                "-100",
                324.83
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(1, errorList.size());
        assertEquals(errorList.get(0).getField(), "Quantity");
        assertEquals(errorList.get(0).getMessage(), "not enough securities to sell!");
    }

    @Test
    public void shouldReturnQuantityError4() {
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                getAllUserListService().execute(new GetAllUserListRequest()).getList().get(0),
                getAllSecurityListService().execute(new GetAllSecurityListRequest()).getList().get(1),
                "10o",
                163.24
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(1, errorList.size());
        assertEquals(errorList.get(0).getField(), "Quantity");
        assertEquals(errorList.get(0).getMessage(), "must be double!");
    }

    @Test
    public void shouldReturnCashError() {
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                getAllUserListService().execute(new GetAllUserListRequest()).getList().get(0),
                getAllSecurityListService().execute(new GetAllSecurityListRequest()).getList().get(1),
                "10000",
                163.24
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(1, errorList.size());
        assertEquals(errorList.get(0).getField(), "Cash");
        assertEquals(errorList.get(0).getMessage(), "not enough money to execute this trade!");
    }

    @Test
    public void shouldReturnMultipleErrors1() {
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                null,
                getAllSecurityListService().execute(new GetAllSecurityListRequest()).getList().get(1),
                "10o",
                163.24
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(2, errorList.size());
        assertTrue(errorList.contains(new CoreError("User", "no user with such name exists in the database!")));
        assertTrue(errorList.contains(new CoreError("Quantity", "must be double!")));
    }

    @Test
    public void shouldReturnMultipleErrors2() {
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                getAllUserListService().execute(new GetAllUserListRequest()).getList().get(0),
                null,
                "10000",
                163.24
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(2, errorList.size());
        assertTrue(errorList.contains(new CoreError("Security", "no security with such id in the database!")));
        assertTrue(errorList.contains(new CoreError("Cash", "not enough money to execute this trade!")));
    }

    @Test
    public void shouldReturnMultipleErrors3() {
        StockMarketOrderRequest request = new StockMarketOrderRequest(
                null,
                null,
                "10000",
                163.24
        );
        List<CoreError> errorList = getValidator().validate(request);
        assertEquals(2, errorList.size());
        assertTrue(errorList.contains(new CoreError("User", "no user with such name exists in the database!")));
        assertTrue(errorList.contains(new CoreError("Security", "no security with such id in the database!")));
    }

    private GetAllSecurityListService getAllSecurityListService() {
        return appContext.getBean(GetAllSecurityListService.class);
    }

    private GetAllUserListService getAllUserListService() {
        return appContext.getBean(GetAllUserListService.class);
    }

    private StockMarketOrderValidator getValidator() {
        return appContext.getBean(StockMarketOrderValidator.class);
    }
}
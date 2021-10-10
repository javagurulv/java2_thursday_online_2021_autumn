package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.acceptance_test.AcceptanceTestForDatabase;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.AddStockRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddStockValidatorTest extends AcceptanceTestForDatabase {

    private final Database database = super.getAppContext().getBean(Database.class);
    private final AddStockValidator validator = super.getAppContext().getBean(AddStockValidator.class);

    @Test
    public void shouldReturnEmptyList() {
        AddStockRequest request = new AddStockRequest(
                "Intel",
                "Technology",
                "USD",
                "54.23",
                "1.15",
                "1.09"
        );
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnNameError1() {
        AddStockRequest request = new AddStockRequest(
                "In",
                "Technology",
                "USD",
                "54.23",
                "1.15",
                "1.09"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "3 to 100 symbols required!");
    }

    @Test
    public void shouldReturnNameError2() {
        database.addStock(new Stock("Alibaba", "Technology", "USD", 165.23,
                0, 1.32));
        AddStockRequest request = new AddStockRequest(
                "Alibaba",
                "Technology",
                "USD",
                "54.23",
                "1.15",
                "1.09"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "security with such name already exists in the database!");
    }

    @Test
    public void shouldReturnIndustryError() {
        AddStockRequest request = new AddStockRequest(
                "Intel",
                "",
                "USD",
                "54.23",
                "1.15",
                "1.09"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Industry");
        assertEquals(errorList.get(0).getMessage(), "is empty!");
    }

    @Test
    public void shouldReturnCurrencyError() {
        AddStockRequest request = new AddStockRequest(
                "Intel",
                "Technology",
                "",
                "54.23",
                "1.15",
                "1.09"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Currency");
        assertEquals(errorList.get(0).getMessage(), "is empty!");
    }

    @Test
    public void shouldReturnMarketPriceError1() {
        AddStockRequest request = new AddStockRequest(
                "Intel",
                "Technology",
                "USD",
                "abc",
                "1.15",
                "1.09"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Market price");
        assertEquals(errorList.get(0).getMessage(), "wrong format! Must be double!");
    }

    @Test
    public void shouldReturnMarketPriceError2() {
        AddStockRequest request = new AddStockRequest(
                "Intel",
                "Technology",
                "USD",
                "-54.23",
                "1.15",
                "1.09"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Market price");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnDividendError1() {
        AddStockRequest request = new AddStockRequest(
                "Intel",
                "Technology",
                "USD",
                "54.23",
                "",
                "1.09"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Dividend");
        assertEquals(errorList.get(0).getMessage(), "wrong format! Must be double!");
    }

    @Test
    public void shouldReturnDividendError2() {
        AddStockRequest request = new AddStockRequest(
                "Intel",
                "Technology",
                "USD",
                "54.23",
                "-1.15",
                "1.09"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Dividend");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnRiskWeightError1() {
        AddStockRequest request = new AddStockRequest(
                "Intel",
                "Technology",
                "USD",
                "54.23",
                "1.15",
                "1.a"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Risk weight");
        assertEquals(errorList.get(0).getMessage(), "wrong format! Must be double!");
    }

    @Test
    public void shouldReturnRiskWeightError2() {
        AddStockRequest request = new AddStockRequest(
                "Intel",
                "Technology",
                "USD",
                "54.23",
                "1.15",
                "-1.09"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Risk weight");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnNameCurrencyRiskWeightErrors() {
        AddStockRequest request = new AddStockRequest(
                "In",
                "Technology",
                "",
                "54.23",
                "1.15",
                "-1.09"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 3);
        assertTrue(errorList.contains(new CoreError("Risk weight", "cannot be negative!")));
        assertTrue(errorList.contains(new CoreError("Name", "3 to 100 symbols required!")));
        assertTrue(errorList.contains(new CoreError("Currency", "is empty!")));
    }

    @Test
    public void shouldReturnMarketPriceDividendErrors() {
        AddStockRequest request = new AddStockRequest(
                "Intel",
                "Technology",
                "USD",
                "54.a3",
                "-1.15",
                "1.09"
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 2);
        assertTrue(errorList.contains(new CoreError("Market price", "wrong format! Must be double!")));
        assertTrue(errorList.contains(new CoreError("Dividend", "cannot be negative!")));
    }

    @Test
    public void shouldReturnAllErrors() {
        AddStockRequest request = new AddStockRequest(
                "",
                "",
                "",
                "",
                "",
                ""
        );
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 6);
        assertTrue(errorList.contains(new CoreError("Name", "3 to 100 symbols required!")));
        assertTrue(errorList.contains(new CoreError("Industry", "is empty!")));
        assertTrue(errorList.contains(new CoreError("Currency", "is empty!")));
        assertTrue(errorList.contains(new CoreError("Market price", "wrong format! Must be double!")));
        assertTrue(errorList.contains(new CoreError("Dividend", "wrong format! Must be double!")));
        assertTrue(errorList.contains(new CoreError("Risk weight", "wrong format! Must be double!")));
    }

}
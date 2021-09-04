package lv.javaguru.java2.qwe.services.validator;

import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.database.Database;
import lv.javaguru.java2.qwe.database.DatabaseImpl;
import lv.javaguru.java2.qwe.request.AddStockRequest;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddStockValidatorTest {

    private final Database database = new DatabaseImpl();
    private final AddStockValidator validator = new AddStockValidator(database);

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
        List<String> errorList = validator.validate(request);
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
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Name: 3 to 100 symbols required!");
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
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Name: security with such name already exists in the database!");
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
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Industry: is empty!");
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
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Currency: is empty!");
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
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Market price: wrong format! Must be double!");
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
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Market price: cannot be negative!");
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
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Dividend: wrong format! Must be double!");
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
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Dividend: cannot be negative!");
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
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Risk weight: wrong format! Must be double!");
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
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0), "Risk weight: cannot be negative!");
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
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 3);
        assertTrue(errorList.contains("Risk weight: cannot be negative!"));
        assertTrue(errorList.contains("Name: 3 to 100 symbols required!"));
        assertTrue(errorList.contains("Currency: is empty!"));
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
        List<String> errorList = validator.validate(request);
        assertEquals(errorList.size(), 2);
        assertTrue(errorList.contains("Market price: wrong format! Must be double!"));
        assertTrue(errorList.contains("Dividend: cannot be negative!"));
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
        List<String> errorList = validator.validate(request);
        errorList.forEach(System.out::println);
        assertEquals(errorList.size(), 6);
        assertTrue(errorList.contains("Name: 3 to 100 symbols required!"));
        assertTrue(errorList.contains("Industry: is empty!"));
        assertTrue(errorList.contains("Currency: is empty!"));
        assertTrue(errorList.contains("Market price: wrong format! Must be double!"));
        assertTrue(errorList.contains("Dividend: wrong format! Must be double!"));
        assertTrue(errorList.contains("Risk weight: wrong format! Must be double!"));
    }

}
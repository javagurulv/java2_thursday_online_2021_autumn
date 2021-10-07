package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.AddStockRequest;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.data_responses.AddStockResponse;
import lv.javaguru.java2.qwe.core.services.matchers.StockMatcher;
import lv.javaguru.java2.qwe.core.services.validator.AddStockValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddStockServiceTest {

    @Mock private Database database;
    @Mock private AddStockValidator validator;
    @InjectMocks private AddStockService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddStockRequest request = new AddStockRequest(
                "In", "Technology", "USD",
                "54.23", "1.15", "1.09");
        List<CoreError> errors = List.of(
                new CoreError("Name", "3 to 100 symbols required!")
        );
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddStockResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "3 to 100 symbols required!");

        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldAddStockToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddStockRequest request = new AddStockRequest(
                "Intel", "Technology", "USD",
                "54.23", "1.15", "1.09");
        AddStockResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(database).addStock(argThat(new StockMatcher(new Stock(
                "Intel", "Technology", "USD",
                54.23, 1.15, 1.09
        ))));
    }

}
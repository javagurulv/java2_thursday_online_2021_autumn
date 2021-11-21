package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.core.domain.Security;
import lv.javaguru.java2.qwe.core.domain.Stock;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.*;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.data_responses.FilterStocksByMultipleParametersResponse;
import lv.javaguru.java2.qwe.core.services.validator.FilterStocksByMultipleParametersValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FilterStocksByMultipleParametersServiceTest {

    @Mock private Database database;
    @Mock private FilterStocksByMultipleParametersValidator validator;
    @InjectMocks private FilterStocksByMultipleParametersService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {

        List<CoreRequest> requestList = List.of(
                new FilterStocksByIndustryRequest("Technology"),
                new FilterStocksByAnyDoubleParameterRequest("dividend_yield", ">", "-1"),
                new FilterStocksByAnyDoubleParameterRequest("risk_weight", "<=", "0.9"),
                new OrderingRequest("name", "DESC"),
                new PagingRequest("1", "")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(requestList);
        List<CoreError> errors = List.of(
                new CoreError("parameter target", "cannot be negative!"),
                new CoreError("Paging", "both fields must be empty or filled!")
        );
        Mockito.when(validator.validate(request)).thenReturn(errors);
        FilterStocksByMultipleParametersResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 2);
        assertTrue(errors.contains(new CoreError("parameter target", "cannot be negative!")));
        assertTrue(errors.contains(new CoreError("Paging", "both fields must be empty or filled!")));
        Mockito.verifyNoInteractions(database);

    }

    @Test
    public void shouldReturnFilteredSecurityList1() {

        List<CoreRequest> requestList = List.of(
                new FilterStocksByAnyDoubleParameterRequest("dividend_yield", ">", "1"),
                new FilterStocksByAnyDoubleParameterRequest("risk_weight", "<=", "0.9"),
                new FilterStocksByIndustryRequest("Technology"),
                new OrderingRequest("name", "ASC"),
                new PagingRequest("2", "3")
        );
        List<Security> list = List.of(
                new Stock("CTXS US", "Citrix Systems Inc.", "Technology", "USD", 103.22, 1.43, 0.62),
                new Stock("JKHY US", "Jack Henry & Associates Inc.", "Technology", "USD", 177.13, 1.04, 0.89),
                new Stock("NLOK US","NortonLifeLock Inc.", "Technology", "USD", 25.75, 1.94, 0.76)
        );
        FilterStocksByMultipleParametersRequest request =
                new FilterStocksByMultipleParametersRequest(requestList);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.filterStocksByMultipleParameters("SELECT * FROM stocks\n" +
                "  WHERE dividend_yield > 1\n" +
                "  AND risk_weight <= 0.9\n" +
                "  AND industry = 'Technology'\n" +
                " ORDER BY name ASC\n" +
                "  LIMIT 6, 3")).thenReturn(list);
        FilterStocksByMultipleParametersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(list, response.getList());

    }

    @Test
    public void shouldReturnFilteredSecurityList2() {

        List<CoreRequest> requestList = List.of(
                new FilterStocksByAnyDoubleParameterRequest("market_price", ">", "20"),
                new FilterStocksByAnyDoubleParameterRequest("risk_weight", ">", "1.2"),
                new FilterStocksByIndustryRequest("Energy"),
                new PagingRequest("4", "2")
        );
        List<Security> list = List.of(
                new Stock("OXY US", "Occidental Petroleum Corporation", "Energy", "USD", 21.95, 0.18, 1.69),
                new Stock("HES US", "Hess Corporation", "Energy", "USD", 64.32, 1.55, 1.22)
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(requestList);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.filterStocksByMultipleParameters("SELECT * FROM stocks\n" +
                "  WHERE market_price > 20\n" +
                "  AND risk_weight > 1.2\n" +
                "  AND industry = 'Energy'\n" +
                "  LIMIT 8, 2")).thenReturn(list);
        FilterStocksByMultipleParametersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(list, response.getList());
    }

}
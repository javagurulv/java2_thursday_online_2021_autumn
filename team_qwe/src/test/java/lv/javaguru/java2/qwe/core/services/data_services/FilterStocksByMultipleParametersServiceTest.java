package lv.javaguru.java2.qwe.core.services.data_services;

import lv.javaguru.java2.qwe.Security;
import lv.javaguru.java2.qwe.Stock;
import lv.javaguru.java2.qwe.core.database.Database;
import lv.javaguru.java2.qwe.core.requests.data_requests.*;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.core.responses.data_responses.FilterStocksByMultipleParametersResponse;
import lv.javaguru.java2.qwe.dependency_injection.ApplicationContext;
import lv.javaguru.java2.qwe.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static lv.javaguru.java2.qwe.utils.UtilityMethods.importDataForTests;

@RunWith(MockitoJUnitRunner.class)
public class FilterStocksByMultipleParametersServiceTest {

    private final ApplicationContext appContext =
            new DIApplicationContextBuilder().build();
    @Mock
    private Database database = appContext.getBean(Database.class);
    private final FilterStocksByMultipleParametersService service =
            appContext.getBean(FilterStocksByMultipleParametersService.class);

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {

        List<CoreRequest> requestList = List.of(
                new FilterStocksByIndustryRequest("Technology"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">", "-1"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "0.9"),
                new OrderingRequest("Name", "ASCENDING"),
                new PagingRequest("1", "")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(requestList);
        List<CoreError> errors = List.of(
                new CoreError("Dividend", "cannot be negative!"),
                new CoreError("Paging", "both fields must be empty or filled!")
        );
        FilterStocksByMultipleParametersResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 2);
        assertTrue(errors.contains(new CoreError("Dividend", "cannot be negative!")));
        assertTrue(errors.contains(new CoreError("Paging", "both fields must be empty or filled!")));

        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnFilteredSecurityList1() {

        importDataForTests(appContext);
        List<CoreRequest> requestList = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">", "1"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "0.9"),
                new FilterStocksByIndustryRequest("Technology"),
                new OrderingRequest("Name", "ASCENDING"),
                new PagingRequest("2", "3")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(requestList);
        FilterStocksByMultipleParametersResponse response = service.execute(request);
        assertFalse(response.hasErrors());

        List<Security> list = List.of(
                new Stock("Citrix Systems Inc.", "Technology", "USD", 103.22, 1.43, 0.62),
                new Stock("Jack Henry & Associates Inc.", "Technology", "USD", 177.13, 1.04, 0.89),
                new Stock("NortonLifeLock Inc.", "Technology", "USD", 25.75, 1.94, 0.76)
        );

        assertEquals(list, response.getList());
    }

    @Test
    public void shouldReturnFilteredSecurityList2() {

        importDataForTests(appContext);
        List<CoreRequest> requestList = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "20"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", ">", "1.2"),
                new FilterStocksByIndustryRequest("Energy"),
                new OrderingRequest("", ""),
                new PagingRequest("4", "2")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(requestList);
        FilterStocksByMultipleParametersResponse response = service.execute(request);
        assertFalse(response.hasErrors());

        List<Security> list = List.of(
                new Stock("Occidental Petroleum Corporation", "Energy", "USD", 21.95, 0.18, 1.69),
                new Stock("Hess Corporation", "Energy", "USD", 64.32, 1.55, 1.22)
        );

        assertEquals(list, response.getList());
    }

}
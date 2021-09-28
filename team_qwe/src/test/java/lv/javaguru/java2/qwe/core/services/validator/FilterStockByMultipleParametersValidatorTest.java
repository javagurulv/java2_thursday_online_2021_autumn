package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.data_requests.*;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import lv.javaguru.java2.qwe.dependency_injection.ApplicationContext;
import lv.javaguru.java2.qwe.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterStockByMultipleParametersValidatorTest {

    private final ApplicationContext appContext =
            new DIApplicationContextBuilder().build("lv.javaguru.java2.qwe");

    private final FilterStocksByMultipleParametersValidator validator =
            appContext.getBean(FilterStocksByMultipleParametersValidator.class);

    @Test
    public void shouldReturnEmptyList1() {
        List<CoreRequest> list = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStocksByIndustryRequest("Technology")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList2() {
        List<CoreRequest> list = List.of(
                new FilterStocksByIndustryRequest("Utility"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "0.8"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "1.")

        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList3() {
        List<CoreRequest> list = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "0"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "0"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "0"),
                new FilterStocksByIndustryRequest("Technology")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList4() {
        List<CoreRequest> list = List.of(
                new FilterStocksByIndustryRequest("Utility"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "0.8"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "1."),
                new OrderingRequest("Dividend", "ASCENDING"),
                new PagingRequest("3", "5")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList5() {
        List<CoreRequest> list = List.of(
                new FilterStocksByIndustryRequest("Utility"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "0.8"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "1."),
                new OrderingRequest("", ""),
                new PagingRequest("", "")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnNoParameterError() {
        List<CoreRequest> list = List.of();
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Choose parameter");
        assertEquals(errorList.get(0).getMessage(), "at least one parameter is required!");
    }

    @Test
    public void shouldReturnMarketPriceTargetError() {
        List<CoreRequest> list = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "-30.10"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStocksByIndustryRequest("Technology")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Market price target");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnDividendTargetError() {
        List<CoreRequest> list = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "-2.05"),
                new FilterStocksByIndustryRequest("Technology")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Dividend target");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnRiskWeightTargetError() {
        List<CoreRequest> list = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "-1.0"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStocksByIndustryRequest("Technology")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Risk weight target");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnIndustryTargetError() {
        List<CoreRequest> list = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStocksByIndustryRequest("")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Industry target");
        assertEquals(errorList.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnOrderingError1() {
        List<CoreRequest> list = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStocksByIndustryRequest("Technology"),
                new OrderingRequest("", "ASCENDING")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Ordering");
        assertEquals(errorList.get(0).getMessage(), "both fields must be empty or filled!");
    }

    @Test
    public void shouldReturnOrderingError2() {
        List<CoreRequest> list = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStocksByIndustryRequest("Technology"),
                new OrderingRequest("Name", "")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Ordering");
        assertEquals(errorList.get(0).getMessage(), "both fields must be empty or filled!");
    }

    @Test
    public void shouldReturnPagingError1() {
        List<CoreRequest> list = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStocksByIndustryRequest("Technology"),
                new OrderingRequest("Risk weight", "ASCENDING"),
                new PagingRequest("", "5")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Paging");
        assertEquals(errorList.get(0).getMessage(), "both fields must be empty or filled!");
    }

    @Test
    public void shouldReturnPagingError2() {
        List<CoreRequest> list = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStocksByIndustryRequest("Technology"),
                new OrderingRequest("Risk weight", "ASCENDING"),
                new PagingRequest("3", "")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Paging");
        assertEquals(errorList.get(0).getMessage(), "both fields must be empty or filled!");
    }

    @Test
    public void shouldReturnPagingError3() {
        List<CoreRequest> list = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStocksByIndustryRequest("Technology"),
                new OrderingRequest("Risk weight", "ASCENDING"),
                new PagingRequest("-3", "5")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Paging");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnPagingError4() {
        List<CoreRequest> list = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStocksByIndustryRequest("Technology"),
                new OrderingRequest("Risk weight", "ASCENDING"),
                new PagingRequest("3", "-5")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Paging");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnMultipleErrors() {
        List<CoreRequest> list = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "-30.10"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStocksByIndustryRequest(""),
                new PagingRequest("3", "-5")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 3);
        assertTrue(errorList.contains(new CoreError("Market price target", "cannot be negative!")));
        assertTrue(errorList.contains(new CoreError("Industry target", "must not be empty!")));
        assertTrue(errorList.contains(new CoreError("Paging", "cannot be negative!")));
    }

    @Test
    public void shouldReturnAllErrors() {
        List<CoreRequest> list = List.of(
                new FilterStocksByAnyDoubleParameterRequest("Market price", ">", "-30.10"),
                new FilterStocksByAnyDoubleParameterRequest("Risk weight", "<=", "-1.0"),
                new FilterStocksByAnyDoubleParameterRequest("Dividend", ">=", "-2.05"),
                new FilterStocksByIndustryRequest(""),
                new OrderingRequest("Risk weight", ""),
                new PagingRequest("-3", "")
        );
        FilterStocksByMultipleParametersRequest request = new FilterStocksByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 7);
        assertTrue(errorList.contains(new CoreError("Market price target", "cannot be negative!")));
        assertTrue(errorList.contains(new CoreError("Industry target", "must not be empty!")));
        assertTrue(errorList.contains(new CoreError("Dividend target", "cannot be negative!")));
        assertTrue(errorList.contains(new CoreError("Risk weight target", "cannot be negative!")));
        assertTrue(errorList.contains(new CoreError("Ordering", "both fields must be empty or filled!")));
        assertTrue(errorList.contains(new CoreError("Paging", "both fields must be empty or filled!")));
        assertTrue(errorList.contains(new CoreError("Paging", "cannot be negative!")));
    }

}
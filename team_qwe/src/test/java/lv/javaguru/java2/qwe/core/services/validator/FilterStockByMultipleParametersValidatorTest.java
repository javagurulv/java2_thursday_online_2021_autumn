package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.data_requests.*;
import lv.javaguru.java2.qwe.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterStockByMultipleParametersValidatorTest {

    private final FilterStockByMultipleParametersValidator validator = new FilterStockByMultipleParametersValidator();

    @Test
    public void shouldReturnEmptyList1() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStockByIndustryRequest("Technology")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList2() {
        List<SecurityRequest> list = List.of(
                new FilterStockByIndustryRequest("Utility"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "0.8"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "1.")

        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList3() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "0"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "0"),
                new FilterStockByIndustryRequest("Technology")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList4() {
        List<SecurityRequest> list = List.of(
                new FilterStockByIndustryRequest("Utility"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "0.8"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "1."),
                new OrderingRequest("Dividend", "ASCENDING"),
                new PagingRequest("3", "5")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList5() {
        List<SecurityRequest> list = List.of(
                new FilterStockByIndustryRequest("Utility"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "0.8"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "1."),
                new OrderingRequest("", ""),
                new PagingRequest("", "")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnNoParameterError() {
        List<SecurityRequest> list = List.of();
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Choose parameter");
        assertEquals(errorList.get(0).getMessage(), "at least one parameter is required!");
    }

    @Test
    public void shouldReturnMarketPriceTargetError() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "-30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStockByIndustryRequest("Technology")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Market price target");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnDividendTargetError() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "-2.05"),
                new FilterStockByIndustryRequest("Technology")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Dividend target");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnRiskWeightTargetError() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "-1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStockByIndustryRequest("Technology")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Risk weight target");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnIndustryTargetError() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStockByIndustryRequest("")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Industry target");
        assertEquals(errorList.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnOrderingError1() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStockByIndustryRequest("Technology"),
                new OrderingRequest("", "ASCENDING")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Ordering");
        assertEquals(errorList.get(0).getMessage(), "both fields must be empty or filled!");
    }

    @Test
    public void shouldReturnOrderingError2() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStockByIndustryRequest("Technology"),
                new OrderingRequest("Name", "")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Ordering");
        assertEquals(errorList.get(0).getMessage(), "both fields must be empty or filled!");
    }

    @Test
    public void shouldReturnPagingError1() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStockByIndustryRequest("Technology"),
                new OrderingRequest("Risk weight", "ASCENDING"),
                new PagingRequest("", "5")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Paging");
        assertEquals(errorList.get(0).getMessage(), "both fields must be empty or filled!");
    }

    @Test
    public void shouldReturnPagingError2() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStockByIndustryRequest("Technology"),
                new OrderingRequest("Risk weight", "ASCENDING"),
                new PagingRequest("3", "")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Paging");
        assertEquals(errorList.get(0).getMessage(), "both fields must be empty or filled!");
    }

    @Test
    public void shouldReturnPagingError3() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStockByIndustryRequest("Technology"),
                new OrderingRequest("Risk weight", "ASCENDING"),
                new PagingRequest("-3", "5")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Paging");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnPagingError4() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStockByIndustryRequest("Technology"),
                new OrderingRequest("Risk weight", "ASCENDING"),
                new PagingRequest("3", "-5")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Paging");
        assertEquals(errorList.get(0).getMessage(), "cannot be negative!");
    }

    @Test
    public void shouldReturnMultipleErrors() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "-30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStockByIndustryRequest(""),
                new PagingRequest("3", "-5")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 3);
        assertTrue(errorList.contains(new CoreError("Market price target", "cannot be negative!")));
        assertTrue(errorList.contains(new CoreError("Industry target", "must not be empty!")));
        assertTrue(errorList.contains(new CoreError("Paging", "cannot be negative!")));
    }

    @Test
    public void shouldReturnAllErrors() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "-30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "-1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "-2.05"),
                new FilterStockByIndustryRequest(""),
                new OrderingRequest("Risk weight", ""),
                new PagingRequest("-3", "")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
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
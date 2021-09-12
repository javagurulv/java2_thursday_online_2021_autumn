package lv.javaguru.java2.qwe.core.services.validator;

import lv.javaguru.java2.qwe.core.requests.FilterStockByAnyDoubleParameterRequest;
import lv.javaguru.java2.qwe.core.requests.FilterStockByIndustryRequest;
import lv.javaguru.java2.qwe.core.requests.FilterStockByMultipleParametersRequest;
import lv.javaguru.java2.qwe.core.requests.SecurityRequest;
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
    public void shouldReturnMultipleErrors() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "-30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "2.05"),
                new FilterStockByIndustryRequest("")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 2);
        assertTrue(errorList.contains(new CoreError("Market price target", "cannot be negative!")));
        assertTrue(errorList.contains(new CoreError("Industry target", "must not be empty!")));
    }

    @Test
    public void shouldReturnAllErrors() {
        List<SecurityRequest> list = List.of(
                new FilterStockByAnyDoubleParameterRequest("Market price", ">", "-30.10"),
                new FilterStockByAnyDoubleParameterRequest("Risk weight", "<=", "-1.0"),
                new FilterStockByAnyDoubleParameterRequest("Dividend", ">=", "-2.05"),
                new FilterStockByIndustryRequest("")
        );
        FilterStockByMultipleParametersRequest request = new FilterStockByMultipleParametersRequest(list);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 4);
        assertTrue(errorList.contains(new CoreError("Market price target", "cannot be negative!")));
        assertTrue(errorList.contains(new CoreError("Industry target", "must not be empty!")));
        assertTrue(errorList.contains(new CoreError("Dividend target", "cannot be negative!")));
        assertTrue(errorList.contains(new CoreError("Risk weight target", "cannot be negative!")));
    }

}
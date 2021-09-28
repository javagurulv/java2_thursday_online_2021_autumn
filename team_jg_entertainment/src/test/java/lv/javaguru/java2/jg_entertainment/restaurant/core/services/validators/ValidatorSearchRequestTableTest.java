package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.OrderingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.PagingTable;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.SearchTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class ValidatorSearchRequestTableTest {

    private ValidatorSearchRequestFieldTable fieldTable;
    private ValidatorOrdering orderingTable;
    private ValidatorPaging pagingTable;
    private ValidatorSearchRequestTable validator;

    @BeforeEach
    public void init() {
        fieldTable = Mockito.mock(ValidatorSearchRequestFieldTable.class);
        orderingTable = Mockito.mock(ValidatorOrdering.class);
        pagingTable = Mockito.mock(ValidatorPaging.class);
        validator = new ValidatorSearchRequestTable(fieldTable, orderingTable, pagingTable);
    }

    @Test
    public void shouldNotReturnOrderingErrors() {
        OrderingTable ordering = new OrderingTable("title", "direction");
        SearchTableRequest request = new SearchTableRequest("title", 1L, ordering);
        Mockito.when(orderingTable.validator(ordering)).thenReturn(new ArrayList<>());
        List<CoreError> errorList = validator.validator(request);
        assertEquals(errorList.size(), 0);
    }

    @Test
    public void shouldNotInvokePagingValidatorWhenNoPagingObjectPresentInRequest() {
        SearchTableRequest request = new SearchTableRequest("title", 1L);
        validator.validator(request);
        verifyNoMoreInteractions(pagingTable);
    }

    @Test
    public void shouldNotInvokePagingValidatorWhenNoOrderingObjectPresentInRequest() {
        SearchTableRequest request = new SearchTableRequest("title", 1L);
        validator.validator(request);
        verifyNoMoreInteractions(orderingTable);
    }

    @Test
    public void shouldReturnErrorWhenPresentEmptyOrderDirection() {
        OrderingTable ordering = new OrderingTable ("title", null);
        SearchTableRequest request = new SearchTableRequest("title", 1L, ordering);
        CoreError coreError = new CoreError("orderDirection", "can not be empty");
        when(orderingTable.validator(ordering)).thenReturn(List.of(coreError));
        List<CoreError>coreErrors = validator.validator(request);
        assertEquals(coreErrors.size(), 1);
        assertEquals(coreErrors.get(0).getField(), "orderDirection");
        assertEquals(coreErrors.get(0).getMessageError(), "can not be empty");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberIsEmptyAndPagingValidatorReturnErrors() {
        PagingTable paging = new PagingTable(null, 1);
        SearchTableRequest request = new SearchTableRequest("title", 1L, paging);
        CoreError coreError = new CoreError("pageNumber", "must not be empty!");
        when(pagingTable.validatorPaging(paging)).thenReturn(List.of(coreError));
        List<CoreError> errorList = validator.validator(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "pageNumber");
        assertEquals(errorList.get(0).getMessageError(), "must not be empty!");
    }

}
package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Ordering;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.Paging;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class SearchVisitorsRequestValidatorTest {

    @Mock
    private SearchVisitorsRequestFieldValidator fieldValidator;
    @Mock
    private OrderingValidator orderingValidator;
    @Mock
    private PagingValidator pagingValidator;
    @InjectMocks
    private SearchVisitorsRequestValidator validator;

    @BeforeEach
    public void init() {
        fieldValidator = Mockito.mock(SearchVisitorsRequestFieldValidator.class);
        orderingValidator = Mockito.mock(OrderingValidator.class);
        pagingValidator = Mockito.mock(PagingValidator.class);
        validator = new SearchVisitorsRequestValidator(fieldValidator, orderingValidator, pagingValidator);
    }

    @Test
    public void shouldNotReturnErrorListWhenFieldValidatorReturnNoErrors() {
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null);
        when(fieldValidator.validatorSearchField(request)).thenReturn(List.of());
        List<CoreError> errors = validator.validator(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorListWhenFieldValidatorReturnNoErrorsElse() {
        SearchVisitorsRequest request = new SearchVisitorsRequest(null, "surname");
        when(fieldValidator.validatorSearchField(request)).thenReturn(List.of());
        List<CoreError> coreErrors = validator.validator(request);
        assertEquals(coreErrors.size(), 0);
    }

    @Test
    public void shouldReturnErrorsListWhenFieldValidatorReturnErrors() {
        SearchVisitorsRequest request = new SearchVisitorsRequest(null, "surname");
        CoreError coreErrors = new CoreError("name", "can not be empty!");
        when(fieldValidator.validatorSearchField(request)).thenReturn(List.of(coreErrors));

        List<CoreError> errorList = validator.validator(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "name");
        assertEquals(errorList.get(0).getMessageError(), "can not be empty!");
    }

    @Test
    public void shouldReturnErrorsListWhenFieldValidatorReturnErrorsElse() {
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", null);
        CoreError coreError = new CoreError("surname", "can not be empty!");
        when(fieldValidator.validatorSearchField(request)).thenReturn(List.of(coreError));

        List<CoreError> errorList = validator.validator(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "surname");
        assertEquals(errorList.get(0).getMessageError(), "can not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPresentEmptyOrderDirection() {
        Ordering ordering = new Ordering("name", null);
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname", ordering);
        CoreError coreError = new CoreError("orderDirection", "can not be empty!");
        when(orderingValidator.validator(ordering)).thenReturn(List.of(coreError));

        List<CoreError> errors = validator.validator(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessageError(), "can not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderingValidationReturnErrors() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname", ordering);
        CoreError coreError = new CoreError("orderBy", "can not be empty!");
        when(orderingValidator.validator(ordering)).thenReturn(List.of(coreError));

        List<CoreError> errors = validator.validator(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessageError(), "can not be empty!");
    }

    @Test
    public void shouldErrorWhenNotValidValueInOrderBy() {
        Ordering ordering = new Ordering("notValidValue", "ASCENDING");
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname", ordering);
        CoreError coreError = new CoreError("orderBy", "might contain 'name' or 'surname' only!");
        when(orderingValidator.validator(ordering)).thenReturn(List.of(coreError));

        List<CoreError> errors = validator.validator(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessageError(), "might contain 'name' or 'surname' only!");
    }

    @Test
    public void shouldReturnErrorWhenNotValidValueInOrderDirection() {
        Ordering ordering = new Ordering("name", "notValidValue");
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname", ordering);
        CoreError coreError = new CoreError("orderDirection", "might contain 'ASCENDING' or 'DESCENDING' only!");
        when(orderingValidator.validator(ordering)).thenReturn(List.of(coreError));

        List<CoreError> errors = validator.validator(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessageError(), "might contain 'ASCENDING' or 'DESCENDING' only!");
    }

    @Test
    public void shouldNotInvokeOrderingValidatorWhenNoOrderingObjectPresentInRequest() {
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname");
        validator.validator(request);
        verifyNoMoreInteractions(orderingValidator);
    }

    @Test
    public void shouldReturnErrorWhenPageNumberContainNotValidValue() {
        Paging paging = new Paging(0, 1);
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname", paging);
        CoreError coreError = new CoreError("pageNumber", "must be greater then 0!");
        when(pagingValidator.validatorPaging(paging)).thenReturn(List.of(coreError));

        List<CoreError> errors = validator.validator(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessageError(), "must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeContainNotValidValue() {
        Paging paging = new Paging(1, 0);
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname", paging);
        CoreError coreError = new CoreError("pageSize", "must be greater then 0!");
        when(pagingValidator.validatorPaging(paging)).thenReturn(List.of(coreError));

        List<CoreError> errors = validator.validator(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getMessageError(), "must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberIsEmptyAndPagingValidatorReturnErrors() {
        Paging paging = new Paging(null, 1);
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname", paging);
        CoreError coreError = new CoreError("pageNumber", "must not be empty!");
        when(pagingValidator.validatorPaging(paging)).thenReturn(List.of(coreError));

        List<CoreError> errors = validator.validator(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessageError(), "must not be empty!");
    }

    @Test
    public void shouldNotInvokePagingValidatorWhenNoPagingObjectPresentInRequest() {
        SearchVisitorsRequest request = new SearchVisitorsRequest("name", "surname");
        validator.validator(request);
        verifyNoMoreInteractions(pagingValidator);
    }

}
package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.Ordering;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchPatientsValidatorTest {

    @Mock
    private SearchPatientsRequestFieldValidator fieldValidator;
    @Mock
    private OrderingValidator orderingValidator;
    @Mock
    private PagingValidator pagingValidator;
    @InjectMocks
    private SearchPatientsValidator validator;

    @BeforeEach
    public void init(){
        fieldValidator = Mockito.mock(SearchPatientsRequestFieldValidator.class);
        orderingValidator = Mockito.mock(OrderingValidator.class);
        pagingValidator = Mockito.mock(PagingValidator.class);
        validator = new SearchPatientsValidator();
    }

    @Test
    public void shouldReturnOrderingErrors() {
        Ordering ordering = new Ordering("name", "ASC");

        SearchPatientsRequest request = new SearchPatientsRequest(
                "name",
                "surname",
                "1234",
                ordering);

        CoreError error = new CoreError("orderBy", "bla bla bla");

        Mockito.when(orderingValidator.validate(ordering))
                .thenReturn(List.of(error));

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0), error);

        Mockito.verify(orderingValidator).validate(ordering);
    }

    @Test
    public void shouldNotReturnOrderingErrors() {
        Ordering ordering = new Ordering("name", "ASC");
        SearchPatientsRequest request = new SearchPatientsRequest(
                "name",
                "surname",
                "1234",
                ordering
        );

        Mockito.when(orderingValidator.validate(ordering))
                .thenReturn(new ArrayList<>());

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 0);
    }

   /* @Test
    public void shouldNotCheckOrdering() {
        Ordering ordering = null;
        SearchPatientsRequest request = new SearchPatientsRequest(
                "name",
                "surname",
                "1234",
                ordering
        );

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 0);

        Mockito.verifyNoMoreInteractions(orderingValidator);
    } */
}
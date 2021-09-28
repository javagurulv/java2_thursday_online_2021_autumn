package lv.javaguru.java2.hospital.doctor.core.services.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lv.javaguru.java2.hospital.doctor.core.requests.Ordering;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class SearchDoctorsRequestValidatorTest {

    private SearchDoctorsRequestFieldValidator fieldValidator;
    private OrderingValidator orderingValidator;
    private PagingValidator pagingValidator;
    private SearchDoctorsRequestValidator validator;

    @BeforeEach
    public void init() {
        fieldValidator = Mockito.mock(SearchDoctorsRequestFieldValidator.class);
        orderingValidator = Mockito.mock(OrderingValidator.class);
        pagingValidator = Mockito.mock(PagingValidator.class);
        validator = new SearchDoctorsRequestValidator(fieldValidator, orderingValidator, pagingValidator);
    }

    @Test
    public void shouldReturnOrderingErrors() {
        Ordering ordering = new Ordering("name", "ASC");

        SearchDoctorsRequest request = new SearchDoctorsRequest(
                1L, "name", "surname", "speciality", ordering
        );

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
        SearchDoctorsRequest request = new SearchDoctorsRequest(
                1L,
                "name",
                "surname",
                "speciality",
                ordering
        );

        Mockito.when(orderingValidator.validate(ordering))
                .thenReturn(new ArrayList<>());

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotCheckOrdering() {
        Ordering ordering = null;
        SearchDoctorsRequest request = new SearchDoctorsRequest(
                1L,
                "name",
                "surname",
                "speciality",
                ordering
        );

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 0);

        Mockito.verifyNoMoreInteractions(orderingValidator);
    }

}
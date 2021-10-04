package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.Ordering;
import lv.javaguru.java2.hospital.patient.core.requests.Paging;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class SearchPatientsValidatorTest {

    @Mock private SearchPatientsRequestFieldValidator fieldValidator;
    @Mock private OrderingValidator orderingValidator;
    @Mock private PagingValidator pagingValidator;
    @InjectMocks private SearchPatientsValidator validator;

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

    @Test
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
    }

    @Test
    public void shouldReturnPagingErrors() {
        Paging paging = new Paging(0, 0);

        SearchPatientsRequest request = new SearchPatientsRequest(
                "name",
                "surname",
                "1234",
                paging);

        CoreError error = new CoreError("orderBy", "bla bla bla");

        Mockito.when(pagingValidator.validate(paging))
                .thenReturn(List.of(error));

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0), error);

        Mockito.verify(pagingValidator).validate(paging);
    }

    @Test
    public void shouldNotReturnPagingErrors() {
        Paging paging = new Paging(1, 1);
        SearchPatientsRequest request = new SearchPatientsRequest(
                "name",
                "surname",
                "1234",
                paging
        );

        Mockito.when(pagingValidator.validate(paging))
                .thenReturn(new ArrayList<>());

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotCheckPaging() {
        Paging paging = null;
        SearchPatientsRequest request = new SearchPatientsRequest(
                "name",
                "surname",
                "1234",
                paging
        );

        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 0);

        Mockito.verifyNoMoreInteractions(pagingValidator);
    }
}
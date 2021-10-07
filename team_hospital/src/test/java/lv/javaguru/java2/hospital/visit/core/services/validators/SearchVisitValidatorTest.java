package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.Ordering;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
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
class SearchVisitValidatorTest {

    @Mock
    private SearchVisitFieldValidator fieldValidator;
    @Mock
    private OrderingValidator orderingValidator;
    @Mock
    private PagingValidator pagingValidator;
    @InjectMocks
    private SearchVisitValidator validator;

    @Test
    public void shouldReturnOrderingErrors() {
        Ordering ordering = new Ordering("date", "ASC");
        SearchVisitRequest request = new SearchVisitRequest
                (1L, 1L, 1L, "12/12/21 12:00", ordering);
        CoreError error = new CoreError("orderBy", "bla bla bla");

        Mockito.when(orderingValidator.validate(ordering)).thenReturn(List.of(error));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0), error);

        Mockito.verify(orderingValidator).validate(ordering);
    }

    @Test
    public void shouldNotReturnOrderingErrors() {
        Ordering ordering = new Ordering("date", "ASC");
        SearchVisitRequest request = new SearchVisitRequest
                (1L, 1L, 1L, "12/12/21 12:00", ordering);

        Mockito.when(orderingValidator.validate(ordering))
                .thenReturn(new ArrayList<>());
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotCheckOrdering() {
        Ordering ordering = null;
        SearchVisitRequest request = new SearchVisitRequest
                (1L, 1L, 1L, "12/12/21 12:00", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);

        Mockito.verifyNoMoreInteractions(orderingValidator);
    }

}
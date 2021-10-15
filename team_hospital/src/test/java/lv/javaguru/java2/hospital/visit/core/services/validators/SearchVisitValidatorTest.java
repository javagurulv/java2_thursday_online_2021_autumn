package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.VisitOrdering;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.VisitExistenceForSearchValidator;
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
    private VisitOrderingValidator visitOrderingValidator;
    @Mock
    private VisitPagingValidator visitPagingValidator;
    @Mock
    private VisitExistenceForSearchValidator visitExistenceForSearchValidator;
    @InjectMocks
    private SearchVisitValidator validator;

    @Test
    public void shouldReturnOrderingErrors() {
        VisitOrdering visitOrdering = new VisitOrdering("date", "ASC");
        SearchVisitRequest request = new SearchVisitRequest
                (1L, 1L, 1L, "12/12/21 12:00", visitOrdering);
        CoreError error = new CoreError("orderBy", "bla bla bla");

        Mockito.when(visitOrderingValidator.validate(visitOrdering)).thenReturn(List.of(error));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0), error);

        Mockito.verify(visitOrderingValidator).validate(visitOrdering);
    }

    @Test
    public void shouldNotReturnOrderingErrors() {
        VisitOrdering visitOrdering = new VisitOrdering("date", "ASC");
        SearchVisitRequest request = new SearchVisitRequest
                (1L, 1L, 1L, "12/12/21 12:00", visitOrdering);

        Mockito.when(visitOrderingValidator.validate(visitOrdering))
                .thenReturn(new ArrayList<>());
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotCheckOrdering() {
        VisitOrdering visitOrdering = null;
        SearchVisitRequest request = new SearchVisitRequest
                (1L, 1L, 1L, "12/12/21 12:00", visitOrdering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);

        Mockito.verifyNoMoreInteractions(visitOrderingValidator);
    }

}
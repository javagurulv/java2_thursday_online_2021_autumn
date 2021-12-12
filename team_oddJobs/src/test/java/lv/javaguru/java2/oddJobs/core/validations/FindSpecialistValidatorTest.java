package lv.javaguru.java2.oddJobs.core.validations;

import lv.javaguru.java2.oddJobs.core.requests.find.FindSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.requests.find.Ordering;
import lv.javaguru.java2.oddJobs.core.requests.find.Paging;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.validations.find.FindSpecialistValidator;
import lv.javaguru.java2.oddJobs.core.validations.find.FindSpecialistsFieldValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindSpecialistValidatorTest {

    @Mock
    private FindSpecialistsFieldValidator fieldValidator;
    @InjectMocks
    private FindSpecialistValidator validator;
    @Mock
    private SpecialistOrderingValidator specialistOrderingValidator;
    @Mock
    private PagingValidator specialistPagingValidator;


    @Test
    public void shouldNotReturnErrorsWhenFieldValidatorReturnNoErrors() {
        FindSpecialistRequest request = new FindSpecialistRequest("Name", null, null);
        when(fieldValidator.validate(request)).thenReturn(List.of());
        List<CoreError> errors = fieldValidator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorsWhenFieldValidatorReturnErrors() {
        FindSpecialistRequest request = new FindSpecialistRequest(null, "Surname", "Profession");
        CoreError error = new CoreError("Name", "Must not be empty!");
        when(fieldValidator.validate(request)).thenReturn(List.of(error));
        List<CoreError> errors = fieldValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorsWhenOrderingValidatorReturnNoErrors() {
        Ordering ordering = new Ordering("Name", "ASC");
        FindSpecialistRequest request = new FindSpecialistRequest("Name", "Surname", "Profession", ordering);
        when(specialistOrderingValidator.validate(ordering)).thenReturn(List.of());
        List<CoreError> errors = specialistOrderingValidator.validate(ordering);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorsWhenOrderingValidatorReturnErrors() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        FindSpecialistRequest request = new FindSpecialistRequest("Name", "Surname", "Profession", ordering);
        CoreError error = new CoreError("orderBy", "Must not be empty!");
        when(specialistOrderingValidator.validate(ordering)).thenReturn(List.of(error));
        List<CoreError> errors = specialistOrderingValidator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotInvokeOrderingValidatorWhenNoOrderingObjectPresentInRequest() {
        FindSpecialistRequest request = new FindSpecialistRequest("Name", "Surname", "Profession");
        validator.validate(request);
        verifyNoMoreInteractions(specialistOrderingValidator);
    }

    @Test
    public void shouldNotReturnErrorsWhenPagingValidatorReturnNoErrors() {
        Paging paging = new Paging(10, 10);
        FindSpecialistRequest request = new FindSpecialistRequest("Name", "Surname", "Profession", paging);
        when(specialistPagingValidator.validate(paging)).thenReturn(List.of());
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorsWhenPagingValidatorReturnErrors() {
        Paging paging = new Paging(null, 10);
        FindSpecialistRequest request = new FindSpecialistRequest("Name", "Surname", "Profession", paging);
        CoreError error = new CoreError("pageNumber", "Must not be empty!");
        when(specialistPagingValidator.validate(paging)).thenReturn(List.of(error));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotInvokePagingValidatorWhenNoPagingObjectPresentInRequest() {
        FindSpecialistRequest request = new FindSpecialistRequest("Name", "Surname", "Profession");
        validator.validate(request);
        verifyNoMoreInteractions(specialistPagingValidator);
    }

}
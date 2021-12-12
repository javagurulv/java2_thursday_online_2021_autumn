package lv.javaguru.java2.oddJobs.core.validations;

import lv.javaguru.java2.oddJobs.core.requests.find.FindSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.validations.find.FindSpecialistsFieldValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindSpecialistsFieldValidatorTest {

    private FindSpecialistsFieldValidator validator = new FindSpecialistsFieldValidator();

    @Test
    public void shouldNotReturnErrorsWhenNameIsProvided() {
        FindSpecialistRequest request = new FindSpecialistRequest("Name", null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenSurnameIsProvided() {
        FindSpecialistRequest request = new FindSpecialistRequest(null, "Surname", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenNameAndSurnameAndProfessionIsProvided() {
        FindSpecialistRequest request = new FindSpecialistRequest("Name", "Surname", "Profession");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenSearchFieldsAreEmpty() {
        FindSpecialistRequest request = new FindSpecialistRequest(null, null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 3);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "Surname");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");
        assertEquals(errors.get(2).getField(), "Profession");
        assertEquals(errors.get(2).getMessage(), "Must not be empty!");
    }

}
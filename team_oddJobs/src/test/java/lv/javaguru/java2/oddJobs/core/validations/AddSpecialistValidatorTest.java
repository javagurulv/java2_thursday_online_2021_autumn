package lv.javaguru.java2.oddJobs.core.validations;

import static org.junit.Assert.*;

import lv.javaguru.java2.oddJobs.core.requests.add.AddSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;

import java.util.List;

import lv.javaguru.java2.oddJobs.core.validations.add.AddSpecialistValidator;
import org.junit.Test;

public class AddSpecialistValidatorTest {
    private AddSpecialistValidator validator = new AddSpecialistValidator();

    @Test
    public void shouldReturnErrorWhenNameIsNull() {
        AddSpecialistRequest request = new AddSpecialistRequest(null, "Surname", "Profession");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");

    }


    @Test
    public void shouldReturnErrorWhenSurnameIsNull() {
        AddSpecialistRequest request = new AddSpecialistRequest("Name", null, "Profession");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Surname");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");

    }

    @Test
    public void shouldReturnErrorWhenProfessionIsNull() {
        AddSpecialistRequest request = new AddSpecialistRequest("Name", "Surname", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Profession");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");

    }

    @Test
    public void shouldReturnErrorsWhenNameAndSurnameAndProfessionIsNull() {
        AddSpecialistRequest request = new AddSpecialistRequest(null, null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 3);
    }

    @Test
    public void shouldSuccess() {
        AddSpecialistRequest request = new AddSpecialistRequest("Name", "Surname", "Profession");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}
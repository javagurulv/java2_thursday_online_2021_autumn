package lv.javaguru.java2.oddJobs.core.validations;

import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.validations.remove.RemoveSpecialistValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RemoveSpecialistValidatorTest {

    private RemoveSpecialistValidator validator = new RemoveSpecialistValidator();

    @Test
    public void shouldReturnErrorWhenIdIsNull() {
        RemoveSpecialistRequest request = new RemoveSpecialistRequest(null, "Name", "Surname");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Id");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenNameIsNull() {
        RemoveSpecialistRequest request = new RemoveSpecialistRequest(1L, null, "Surname");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenSurnameIsNull() {
        RemoveSpecialistRequest request = new RemoveSpecialistRequest(1L, "Name", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Surname");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldSuccess() {
        RemoveSpecialistRequest request = new RemoveSpecialistRequest(1L, "Name", "Surname");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

}
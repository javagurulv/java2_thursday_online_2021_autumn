package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Add.AddClientRequest;
import lv.javaguru.java2.core.responce.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddClientValidatorTest {

    private AddClientValidator validator = new AddClientValidator();

    @Test
    public void shouldReturnEmptyList() {
        AddClientRequest request = new AddClientRequest("Name", "Surname");
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnTitleError() {
        AddClientRequest request = new AddClientRequest("", "Surname");
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnAuthorError() {
        AddClientRequest request = new AddClientRequest("Name", "");
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Surname");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnTitleAndAuthorErrors() {
        AddClientRequest request = new AddClientRequest("", "");
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 2);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(errorList.get(1).getField(), "Surname");
        assertEquals(errorList.get(1).getMessage(), "Must not be empty!");
    }
}
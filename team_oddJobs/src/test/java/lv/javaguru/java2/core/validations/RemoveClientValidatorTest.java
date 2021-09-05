package lv.javaguru.java2.core.validations;


import lv.javaguru.java2.core.requests.Remove.RemoveClientRequest;
import lv.javaguru.java2.core.responce.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RemoveClientValidatorTest {
    private RemoveClientValidator validator = new RemoveClientValidator();


    @Test
    public void shouldReturnEmptyList() {
        RemoveClientRequest request = new RemoveClientRequest("Name","Surname",1L);
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnIdError() {
        RemoveClientRequest request = new RemoveClientRequest("Name","Surname", null);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Id");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnNameError() {
        RemoveClientRequest request = new RemoveClientRequest(null,"Surname", 1L);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Name");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }
    @Test
    public void shouldReturnSurnameError() {
        RemoveClientRequest request = new RemoveClientRequest("Name",null, 1L);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Surname");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

}
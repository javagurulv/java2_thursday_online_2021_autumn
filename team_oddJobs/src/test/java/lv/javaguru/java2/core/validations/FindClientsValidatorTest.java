package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Find.FindClientsRequest;
import lv.javaguru.java2.core.responce.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindClientsValidatorTest {

    private FindClientsValidator validator = new FindClientsValidator();

    @Test
    public void shouldReturnEmptyList() {

        FindClientsRequest request = new FindClientsRequest(1L,"NAME","Surname");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnIdError() {

        FindClientsRequest request = new FindClientsRequest(null,"NAME","Surname");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"ID");
        assertEquals(errors.get(0).getMessage(),"Must not be empty!");
    }

    @Test
    public void shouldReturnNameError() {

        FindClientsRequest request = new FindClientsRequest(1L,null,"Surname");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"NAME");
        assertEquals(errors.get(0).getMessage(),"Must not be empty!");
    }

    @Test
    public void shouldReturnSurnameError() {

        FindClientsRequest request = new FindClientsRequest(1L,"NAME",null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"SURNAME");
        assertEquals(errors.get(0).getMessage(),"Must not be empty!");
    }



}
package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchPatientsRequestFieldValidatorTest {

    private SearchPatientsRequestFieldValidator validator = new SearchPatientsRequestFieldValidator();

    @Test
    public void shouldNotReturnErrorWhenNameNotProvided(){
        SearchPatientsRequest request = new SearchPatientsRequest(null, "surname", "1212");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorWhenSurnameNotProvided(){
        SearchPatientsRequest request = new SearchPatientsRequest("name", null, "1212");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonalCodeNotProvided(){
        SearchPatientsRequest request = new SearchPatientsRequest("name", "surname", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
    @Test
    public void shouldNotReturnErrorWhenNameAndSurnameNotProvided(){
        SearchPatientsRequest request = new SearchPatientsRequest(null, null, "1212");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorWhenSurnameAndPersonalCodeNotProvided(){
        SearchPatientsRequest request = new SearchPatientsRequest("name", null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenFieldsNotProvided(){
        SearchPatientsRequest request = new SearchPatientsRequest(null, null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "At least one field (Name||Surname||PersonalCode)");
        assertEquals(errors.get(0).getDescription(), "must be filled!");
    }
}
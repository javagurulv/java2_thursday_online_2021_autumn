package lv.javaguru.java2.hospital.patient.services.validators;


import lv.javaguru.java2.hospital.patient.requests.Paging;
import lv.javaguru.java2.hospital.patient.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PagingValidatorTest {

    private PagingValidator validator = new PagingValidator();

    @Test
    public void shouldReturnErrorWhenPageNumberContainNotValidValue() {
        Paging paging = new Paging("0", "1");
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", paging);
        List<CoreError> errors = validator.validate(request.getPaging());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getDescription(), "must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeContainNotValidValue() {
        Paging paging = new Paging("1", "0");
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", paging);
        List<CoreError> errors = validator.validate(request.getPaging());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getDescription(), "must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberAreEmpty() {
        Paging paging = new Paging(null, "1");
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", paging);
        List<CoreError> errors = validator.validate(request.getPaging());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeAreEmpty() {
        Paging paging = new Paging("1", null);
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", paging);
        List<CoreError> errors = validator.validate(request.getPaging());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }
}
package lv.javaguru.java2.hospital.patient.services.validators;

import lv.javaguru.java2.hospital.patient.requests.Ordering;
import lv.javaguru.java2.hospital.patient.requests.Paging;
import lv.javaguru.java2.hospital.patient.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.responses.CoreError;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchPatientsValidatorTest {

    private final SearchPatientsValidator validator = new SearchPatientsValidator(new OrderingValidator(), new PagingValidator());


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
    public void shouldReturnErrorWhenFieldsNotProvided(){
        SearchPatientsRequest request = new SearchPatientsRequest(null, null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 3);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
        assertEquals(errors.get(1).getField(), "Surname");
        assertEquals(errors.get(1).getDescription(), "must not be empty!");
        assertEquals(errors.get(2).getField(), "Personal code");
        assertEquals(errors.get(2).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionISEmpty() {
        Ordering ordering = new Ordering("Name", null);
        SearchPatientsRequest request = new SearchPatientsRequest("name", "surname",
                "1212", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByISEmpty() {
        Ordering ordering = new Ordering(null, "ascending");
        SearchPatientsRequest request = new SearchPatientsRequest("name", "surname",
                "1212", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Order By");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        Ordering ordering = new Ordering("notValidValue", "ASCENDING");
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname","1212", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Order By");
        assertEquals(errors.get(0).getDescription(), "must contain 'NAME' or 'SURNAME' only!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        Ordering ordering = new Ordering("name", "notValidValue");
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Order Direction");
        assertEquals(errors.get(0).getDescription(), "must contain 'ASCENDING' or 'DESCENDING' only!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberContainNotValidValue() {
        Paging paging = new Paging("0", "1");
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname","1212", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getDescription(), "must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeContainNotValidValue() {
        Paging paging = new Paging("1", "0");
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getDescription(), "must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberAreEmpty() {
        Paging paging = new Paging(null, "1");
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeAreEmpty() {
        Paging paging = new Paging("1", null);
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }
}
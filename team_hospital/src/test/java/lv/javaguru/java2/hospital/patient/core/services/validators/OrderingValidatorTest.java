package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.Ordering;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderingValidatorTest {

    private OrderingValidator validator = new OrderingValidator();

    @Test
    public void shouldReturnErrorWhenOrderDirectionISEmpty() {
        Ordering ordering = new Ordering("Name", null);
        SearchPatientsRequest request = new SearchPatientsRequest("name", "surname",
                "1212", ordering);
        List<CoreError> errors = validator.validate(request.getOrdering());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByISEmpty() {
        Ordering ordering = new Ordering(null, "Ascending");
        SearchPatientsRequest request = new SearchPatientsRequest("name", "surname",
                "1212", ordering);
        List<CoreError> errors = validator.validate(request.getOrdering());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        Ordering ordering = new Ordering("notValidValue", "ASCENDING");
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", ordering);
        List<CoreError> errors = validator.validate(request.getOrdering());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getDescription(), "must contain 'NAME' or 'SURNAME' only!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        Ordering ordering = new Ordering("name", "notValidValue");
        SearchPatientsRequest request =
                new SearchPatientsRequest("Name", "Surname", "1212", ordering);
        List<CoreError> errors = validator.validate(request.getOrdering());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Order Direction");
        assertEquals(errors.get(0).getDescription(), "must contain 'ASCENDING' or 'DESCENDING' only!");
    }
}
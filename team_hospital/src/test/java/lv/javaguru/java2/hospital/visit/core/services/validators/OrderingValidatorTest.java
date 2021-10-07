package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.Ordering;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderingValidatorTest {

    private OrderingValidator validator = new OrderingValidator();

    @Test
    public void shouldReturnErrorWhenOrderDirectionAreEmpty() {
        Ordering ordering = new Ordering("date", null);
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByAreEmpty() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        Ordering ordering = new Ordering("notValidValue", "ASCENDING");
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getDescription(), "Must contain 'date' only!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        Ordering ordering = new Ordering("date", "notValidValue");
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getDescription(), "Must contain 'ASCENDING' or 'DESCENDING' only!");
    }

}
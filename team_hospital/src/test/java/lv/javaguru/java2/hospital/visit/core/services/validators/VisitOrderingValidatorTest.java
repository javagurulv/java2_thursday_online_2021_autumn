package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.VisitOrdering;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitOrderingValidatorTest {

    private VisitOrderingValidator validator = new VisitOrderingValidator();

    @Test
    public void shouldReturnErrorWhenOrderDirectionAreEmpty() {
        VisitOrdering visitOrdering = new VisitOrdering("date", null);
        List<CoreError> errors = validator.validate(visitOrdering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByAreEmpty() {
        VisitOrdering visitOrdering = new VisitOrdering(null, "ASCENDING");
        List<CoreError> errors = validator.validate(visitOrdering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        VisitOrdering visitOrdering = new VisitOrdering("notValidValue", "ASCENDING");
        List<CoreError> errors = validator.validate(visitOrdering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getDescription(), "Must contain 'date' only!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        VisitOrdering visitOrdering = new VisitOrdering("date", "notValidValue");
        List<CoreError> errors = validator.validate(visitOrdering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getDescription(), "Must contain 'ASCENDING' or 'DESCENDING' only!");
    }

}
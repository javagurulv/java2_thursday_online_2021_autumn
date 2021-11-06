package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.VisitOrdering;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VisitOrderingValidatorTest {

    private final VisitOrderingValidator validator = new VisitOrderingValidator();

    @Test
    public void shouldReturnErrorWhenOrderDirectionAreEmpty() {
        VisitOrdering visitOrdering = new VisitOrdering("date", null);
        List<CoreError> errors = validator.validate(visitOrdering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "OrderDirection");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByAreEmpty() {
        VisitOrdering visitOrdering = new VisitOrdering(null, "ASCENDING");
        List<CoreError> errors = validator.validate(visitOrdering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "OrderBy");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        VisitOrdering visitOrdering = new VisitOrdering("notValidValue", "ASCENDING");
        List<CoreError> errors = validator.validate(visitOrdering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "OrderBy");
        assertEquals(errors.get(0).getDescription(), "must contain 'id' or 'date' only!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        VisitOrdering visitOrdering = new VisitOrdering("date", "notValidValue");
        List<CoreError> errors = validator.validate(visitOrdering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "OrderDirection");
        assertEquals(errors.get(0).getDescription(), "must contain 'ASCENDING' or 'DESCENDING' only!");
    }

    @Test
    public void shouldNotReturnError() {
        VisitOrdering visitOrdering = new VisitOrdering("id", "ASCENDING");
        List<CoreError> errors = validator.validate(visitOrdering);
        assertTrue(errors.isEmpty());
    }
}
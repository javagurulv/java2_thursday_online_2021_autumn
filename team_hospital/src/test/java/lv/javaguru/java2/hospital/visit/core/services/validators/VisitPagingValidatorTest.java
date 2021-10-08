package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.VisitPaging;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitPagingValidatorTest {

    private VisitPagingValidator validator = new VisitPagingValidator();

    @Test
    public void shouldReturnErrorWhenPageNumberContainNotValidValue() {
        VisitPaging visitPaging = new VisitPaging(0, 1);
        List<CoreError> errors = validator.validate(visitPaging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getDescription(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeContainNotValidValue() {
        VisitPaging visitPaging = new VisitPaging(1, 0);
        List<CoreError> errors = validator.validate(visitPaging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getDescription(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberAreEmpty() {
        VisitPaging visitPaging = new VisitPaging(null, 1);
        List<CoreError> errors = validator.validate(visitPaging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeAreEmpty() {
        VisitPaging visitPaging = new VisitPaging(1, null);
        List<CoreError> errors = validator.validate(visitPaging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
    }
}
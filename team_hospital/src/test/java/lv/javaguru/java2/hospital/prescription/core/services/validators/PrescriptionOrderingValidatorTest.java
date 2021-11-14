package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.PrescriptionOrdering;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionOrderingValidatorTest {

    private PrescriptionOrderingValidator validator = new PrescriptionOrderingValidator();

    @Test
    public void shouldReturnErrorWhenOrderDirectionAreEmpty() {
        PrescriptionOrdering ordering = new PrescriptionOrdering("date", null);
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByAreEmpty() {
        PrescriptionOrdering ordering = new PrescriptionOrdering(null, "ASCENDING");
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        PrescriptionOrdering ordering = new PrescriptionOrdering("notValidValue", "ASCENDING");
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must contain 'date' only!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        PrescriptionOrdering ordering = new PrescriptionOrdering("date", "notValidValue");
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must contain 'ASCENDING' or 'DESCENDING' only!");
    }
}
package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.PrescriptionPaging;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionPagingValidatorTest {

    private PrescriptionPagingValidator validator = new PrescriptionPagingValidator();

    @Test
    public void shouldReturnErrorWhenPageNumberContainNotValidValue() {
        PrescriptionPaging paging = new PrescriptionPaging(0, 1);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeContainNotValidValue() {
        PrescriptionPaging paging = new PrescriptionPaging(1, 0);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberAreEmpty() {
        PrescriptionPaging paging = new PrescriptionPaging(null, 1);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeAreEmpty() {
        PrescriptionPaging paging = new PrescriptionPaging(1, null);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnEmptyList() {
        PrescriptionPaging paging = new PrescriptionPaging(1, 1);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(errors.size(), 0);
    }
}
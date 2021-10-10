package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.DoctorPaging;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoctorPagingValidatorTest {

    private DoctorPagingValidator validator = new DoctorPagingValidator();

    @Test
    public void shouldReturnErrorWhenPageNumberContainNotValidValue() {
        DoctorPaging doctorPaging = new DoctorPaging(0, 1);
        List<CoreError> errors = validator.validate(doctorPaging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeContainNotValidValue() {
        DoctorPaging doctorPaging = new DoctorPaging(1, 0);
        List<CoreError> errors = validator.validate(doctorPaging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberAreEmpty() {
        DoctorPaging doctorPaging = new DoctorPaging(null, 1);
        List<CoreError> errors = validator.validate(doctorPaging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeAreEmpty() {
        DoctorPaging doctorPaging = new DoctorPaging(1, null);
        List<CoreError> errors = validator.validate(doctorPaging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
}
package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.DoctorOrdering;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoctorOrderingValidatorTest {

    private DoctorOrderingValidator validator = new DoctorOrderingValidator();

    @Test
    public void shouldReturnErrorWhenOrderDirectionAreEmpty() {
        DoctorOrdering doctorOrdering = new DoctorOrdering("name", null);
        List<CoreError> errors = validator.validate(doctorOrdering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByAreEmpty() {
        DoctorOrdering doctorOrdering = new DoctorOrdering(null, "ASCENDING");
        List<CoreError> errors = validator.validate(doctorOrdering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        DoctorOrdering doctorOrdering = new DoctorOrdering("notValidValue", "ASCENDING");
        List<CoreError> errors = validator.validate(doctorOrdering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must contain 'name', 'surname' or 'speciality' only!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        DoctorOrdering doctorOrdering = new DoctorOrdering("name", "notValidValue");
        List<CoreError> errors = validator.validate(doctorOrdering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must contain 'ASCENDING' or 'DESCENDING' only!");
    }

}
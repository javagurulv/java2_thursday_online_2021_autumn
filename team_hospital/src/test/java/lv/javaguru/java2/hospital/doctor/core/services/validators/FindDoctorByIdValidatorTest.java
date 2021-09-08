package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.FindDoctorByIdRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindDoctorByIdValidatorTest {

    private FindDoctorByIdValidator validator = new FindDoctorByIdValidator();

    @Test
    public void shouldReturnEmptyList() {
        FindDoctorByIdRequest request = new FindDoctorByIdRequest(123);
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnIdError() {
        FindDoctorByIdRequest request = new FindDoctorByIdRequest(0);
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "id");
        assertEquals(errorList.get(0).getMessage(), "Must not be 0!");
    }

}
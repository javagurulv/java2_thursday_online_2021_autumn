package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.requests.EditOption;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EditDoctorRequestValidatorTest {

    private EditDoctorRequestValidator validator = new EditDoctorRequestValidator();

    @Test
    public void shouldReturnEmptyList() {
        EditDoctorRequest request = new EditDoctorRequest(123L, EditOption.valueOf("NAME"), "changes");
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnIdError() {
        EditDoctorRequest request = new EditDoctorRequest(null, EditOption.valueOf("NAME"), "changes");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "id");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnChangesError() {
        EditDoctorRequest request = new EditDoctorRequest(123L, EditOption.valueOf("NAME"), "");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "changes");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnIdAndChangesErrors() {
        EditDoctorRequest request = new EditDoctorRequest(null, EditOption.valueOf("NAME"), "");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 2);
        assertEquals(errorList.get(0).getField(), "id");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(errorList.get(1).getField(), "changes");
        assertEquals(errorList.get(1).getMessage(), "Must not be empty!");
    }

    /*
    @Test
    public void shouldReturnEditOptionError() {
        EditDoctorRequest request = new EditDoctorRequest(123L, EditOption.valueOf("namee"), "NewName");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "edit option");
        assertEquals(errorList.get(0).getMessage(), "Must contain 'NAME', 'SURNAME' or 'SPECIALITY' only!");
    }*/

}
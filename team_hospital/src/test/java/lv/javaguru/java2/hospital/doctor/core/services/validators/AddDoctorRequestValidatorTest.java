package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.services.validators.existence.DoctorExistenceForAddValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AddDoctorRequestValidatorTest {

    @Mock
    private DoctorExistenceForAddValidator doctorExistenceForAddValidator;
    @InjectMocks
    private AddDoctorRequestValidator validator;

    @Test
    public void shouldReturnEmptyList() {
        AddDoctorRequest request = new AddDoctorRequest("name", "surname", "speciality");
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnNameError() {
        AddDoctorRequest request = new AddDoctorRequest("", "surname", "speciality");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "name");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnSurnameError() {
        AddDoctorRequest request = new AddDoctorRequest("name", "", "speciality");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "surname");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnSpecialityError() {
        AddDoctorRequest request = new AddDoctorRequest("name", "surname", "");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "speciality");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnNameAndSurnameAndSpecialityErrors() {
        AddDoctorRequest request = new AddDoctorRequest("", "", "");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 3);
        assertEquals(errorList.get(0).getField(), "name");
        assertEquals(errorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(errorList.get(1).getField(), "surname");
        assertEquals(errorList.get(1).getMessage(), "Must not be empty!");
        assertEquals(errorList.get(2).getField(), "speciality");
        assertEquals(errorList.get(2).getMessage(), "Must not be empty!");
    }
}
package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DoctorEnumCheckerTest {

    @InjectMocks DoctorEnumChecker checker;

    @Test
    public void shouldReturnEmptyList() {
        EditDoctorRequest request = new EditDoctorRequest("13", "SPECIALITY", "changes");
        Optional<CoreError> error = checker.validateEnum(request.getUserInputEnum());
        assertTrue(error.isEmpty());
    }

    @Test
    public void shouldReturnEditErrorList() {
        EditDoctorRequest request = new EditDoctorRequest("13", "bla", "changes");
        Optional<CoreError> error = checker.validateEnum(request.getUserInputEnum());
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "User choice");
        assertEquals(error.get().getMessage(), "Must contain 'NAME', 'SURNAME' or 'SPECIALITY' only!");
    }
}
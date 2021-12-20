package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
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
class PatientEnumCheckerTest {

    @InjectMocks PatientEnumChecker checker;

    @Test
    public void shouldReturnEmptyList() {
        EditPatientRequest request = new EditPatientRequest("166", "SURNAME", "changes");
        Optional<CoreError> error = checker.validateEnum(request.getFieldToChange());
        assertTrue(error.isEmpty());
    }

    @Test
    public void shouldReturnEditErrorList() {
        EditPatientRequest request = new EditPatientRequest("166", "bla", "changes");
        Optional<CoreError> error = checker.validateEnum(request.getFieldToChange());
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "User choice");
        assertEquals(error.get().getDescription(), "must be NAME, SURNAME OR PERSONAL_CODE");
    }

}
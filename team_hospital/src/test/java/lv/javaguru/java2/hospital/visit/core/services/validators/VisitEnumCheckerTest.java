package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.checkers.VisitEnumChecker;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
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
class VisitEnumCheckerTest {

    @InjectMocks private VisitEnumChecker checker;

    @Test
    public void shouldReturnEmptyList() {
        EditVisitRequest request = new EditVisitRequest("12", "DESCRIPTION", "changes");
        Optional<CoreError> error = checker.validateEnum(request.getEditEnums());
        assertTrue(error.isEmpty());
    }

    @Test
    public void shouldReturnEditErrorList() {
        EditVisitRequest request = new EditVisitRequest("12", "blabla", "changes");
        Optional<CoreError> error = checker.validateEnum(request.getEditEnums());
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "Edit option");
        assertEquals(error.get().getDescription(), "must be DOCTOR_ID, PATIENT_ID, DATE OR DESCRIPTION!");
    }
}
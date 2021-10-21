package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.VisitExistenceByIdValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EditVisitValidatorTest {

    @Mock private VisitEnumChecker checker;
    @Mock private VisitExistenceByIdValidator existence;
    @InjectMocks private EditVisitValidator validator;

    @Test
    public void shouldReturnEmptyList() {
        EditVisitRequest request = new EditVisitRequest(1L, "DOCTOR", "changes");
        Mockito.when(existence.validateExistenceById(1L)).thenReturn(Optional.empty());
        Mockito.when(checker.validateEnum(request.getEditEnums())).thenReturn(Optional.empty());
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnIdError() {
        EditVisitRequest request = new EditVisitRequest(null, "DOCTOR", "changes");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "id");
        assertEquals(errorList.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnChangesError() {
        EditVisitRequest request = new EditVisitRequest(1L, "DOCTOR", "");
        Mockito.when(existence.validateExistenceById(1L)).thenReturn(Optional.empty());
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "changes");
        assertEquals(errorList.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnIdAndChangesErrors() {
        EditVisitRequest request = new EditVisitRequest(null, "DOCTOR", "");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 2);
        assertEquals(errorList.get(0).getField(), "id");
        assertEquals(errorList.get(0).getDescription(), "Must not be empty!");
        assertEquals(errorList.get(1).getField(), "changes");
        assertEquals(errorList.get(1).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnVisitError() {
        Mockito.when(existence.validateExistenceById(12L)).thenReturn
                (Optional.of(new CoreError("Visit", "Does not exist!")));
        EditVisitRequest request = new EditVisitRequest(12L, "DOCTOR", "changes");
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Visit");
        assertEquals(errorList.get(0).getDescription(), "Does not exist!");
    }

    @Test
    public void shouldReturnEnumError() {
        EditVisitRequest request = new EditVisitRequest(11L, "ENUM", "changes");
        Mockito.when(existence.validateExistenceById(11L)).thenReturn(Optional.empty());
        Mockito.when(checker.validateEnum(request.getEditEnums())).thenReturn(Optional.of(
                new CoreError("User choice", "must be DOCTOR, PATIENT OR DATE")
        ));
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "User choice");
        assertEquals(errorList.get(0).getDescription(), "must be DOCTOR, PATIENT OR DATE");
    }
}
package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
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
class EditPrescriptionValidatorTest {

    @Mock private PrescriptionExistenceByIDValidator prescriptionExistenceIDValidator;
    @Mock private PrescriptionEnumChecker enumChecker;
    @Mock private IDNumChecker idNumChecker;
    @InjectMocks EditPrescriptionValidator validator;

    @Test
    public void shouldReturnRequestIDError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest(null, "PATIENT", "1");
        Mockito.when(enumChecker.validate(request.getEditPrescriptionEnum())).thenReturn(Optional.empty());
        Mockito.when(idNumChecker.validate(request.getChanges())).thenReturn(Optional.empty());

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "ID field");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnRequestEnumError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest(1L, null, "1");
        Mockito.when(prescriptionExistenceIDValidator.execute(request.getPrescriptionID()))
                .thenReturn(Optional.empty());

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "User choice");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnRequestChangesError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest(1L, "PATIENT", "");
        Mockito.when(prescriptionExistenceIDValidator.execute(request.getPrescriptionID()))
                .thenReturn(Optional.empty());

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Changes");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnPrescriptionExistenceError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest(1L, "PATIENT", "changes");
        Mockito.when(prescriptionExistenceIDValidator.execute(request.getPrescriptionID()))
                .thenReturn(Optional.of(new CoreError("Prescription", "does not exist!")));

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Prescription");
        assertEquals(errors.get(0).getMessage(), "does not exist!");
    }

    @Test
    public void shouldReturnEnumError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest(1L, "enum", "changes");
        Mockito.when(prescriptionExistenceIDValidator.execute(request.getPrescriptionID()))
                .thenReturn(Optional.empty());
        Mockito.when(enumChecker.validate(request.getEditPrescriptionEnum()))
                .thenReturn(Optional.of(new CoreError("User choice", "must be PATIENT, MEDICATION_NAME or QUANTITY!")));

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "User choice");
        assertEquals(errors.get(0).getMessage(), "must be PATIENT, MEDICATION_NAME or QUANTITY!");
    }

    @Test
    public void shouldReturnIDInChangesError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest(1L, "PATIENT", "changes");
        Mockito.when(prescriptionExistenceIDValidator.execute(request.getPrescriptionID()))
                .thenReturn(Optional.empty());
        Mockito.when(idNumChecker.validate(request.getChanges()))
                .thenReturn(Optional.of(new CoreError("ID", "must be a number!")));

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "ID");
        assertEquals(errors.get(0).getMessage(), "must be a number!");
    }

    @Test
    public void shouldNotReturnError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest(1L, "PATIENT", "1");
        Mockito.when(prescriptionExistenceIDValidator.execute(request.getPrescriptionID()))
                .thenReturn(Optional.empty());

        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }
}
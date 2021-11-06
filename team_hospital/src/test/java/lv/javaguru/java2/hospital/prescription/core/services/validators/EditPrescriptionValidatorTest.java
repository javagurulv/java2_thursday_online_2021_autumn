package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.checkers.PrescriptionLongNumChecker;
import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.domain.Patient;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EditPrescriptionValidatorTest {

    @Mock private PatientDatabase patientDatabase;
    @Mock private PrescriptionExistenceByIDValidator prescriptionExistenceIDValidator;
    @Mock private PrescriptionLongNumChecker prescriptionLongNumChecker;
    @Mock private PrescriptionEnumChecker enumChecker;
    @InjectMocks EditPrescriptionValidator validator;

    @Test
    public void shouldReturnRequestIDError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest(null, "PATIENT", "1");
        Mockito.when(enumChecker.validate(request.getEditPrescriptionEnum())).thenReturn(Optional.empty());

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Prescription ID");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnPrescriptionIDError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest("str", "PATIENT", "1");
        Mockito.when(enumChecker.validate(request.getEditPrescriptionEnum())).thenReturn(Optional.empty());
        Mockito.when(prescriptionLongNumChecker.validate(request.getChanges(), "ID")).thenReturn(Optional.empty());
        Mockito.when(prescriptionLongNumChecker.validate(request.getPrescriptionID(), "Prescription ID"))
                .thenReturn(Optional.of(new CoreError("Prescription ID", "must be a number!")));

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Prescription ID");
        assertEquals(errors.get(0).getMessage(), "must be a number!");
    }

    @Test
    public void shouldReturnRequestEnumError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest("1", null, "1");

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "User choice");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnRequestChangesError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest("1", "PATIENT", "");

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Changes");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnPrescriptionExistenceError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest("1", "PATIENT", "2");
        Mockito.when(prescriptionExistenceIDValidator.execute(Long.valueOf(request.getPrescriptionID())))
                .thenReturn(Optional.of(new CoreError("Prescription", "does not exist!")));
        Mockito.when(patientDatabase.findById(Long.valueOf(request.getChanges())))
                .thenReturn(Collections.singletonList(new Patient("name", "surname", "speciality")));

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Prescription");
        assertEquals(errors.get(0).getMessage(), "does not exist!");
    }

    @Test
    public void shouldReturnEnumError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest("1", "enum", "changes");
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
        EditPrescriptionRequest request = new EditPrescriptionRequest("1", "PATIENT", "changes");
        Mockito.when(prescriptionLongNumChecker.validate(request.getPrescriptionID(), "Prescription ID"))
                .thenReturn(Optional.empty());
        Mockito.when(prescriptionLongNumChecker.validate(request.getChanges(), "ID"))
                .thenReturn(Optional.of(new CoreError("ID", "must be a number!")));

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "ID");
        assertEquals(errors.get(0).getMessage(), "must be a number!");
    }

    @Test
    public void shouldReturnPatientExistenceError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest("1", "PATIENT", "2");
        Mockito.when(prescriptionExistenceIDValidator.execute(Long.valueOf(request.getPrescriptionID())))
                .thenReturn(Optional.empty());
        Mockito.when(patientDatabase.findById(Long.valueOf(request.getChanges())))
                .thenReturn(new ArrayList<>());

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Patient");
        assertEquals(errors.get(0).getMessage(), "does not exist!");
    }

    @Test
    public void shouldNotReturnError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest("1", "PATIENT", "2");
        Mockito.when(prescriptionExistenceIDValidator.execute(Long.valueOf(request.getPrescriptionID())))
              .thenReturn(Optional.empty());
        Mockito.when(patientDatabase.findById(Long.valueOf(request.getChanges())))
                .thenReturn(Collections.singletonList(new Patient("name", "surname", "12345678901")));

        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }
}
package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.PrescriptionDatabase;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;
import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.responses.EditPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.validators.EditPrescriptionValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class EditPrescriptionServiceTest {

    @Mock private PrescriptionDatabase database;
    @Mock private EditPrescriptionValidator validator;
    @InjectMocks EditPrescriptionService service;

    @Test
    public void shouldReturnTrue(){
        EditPrescriptionRequest request = new EditPrescriptionRequest(1L, "PATIENT", "2");

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.EditPrescription(
                request.getPrescriptionID(),
                EditPrescriptionEnum.valueOf(request.getEditPrescriptionEnum()),
                request.getChanges())).thenReturn(true);

        EditPrescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isPrescriptionEdited());
    }

    @Test
    public void shouldReturnPatientDoesNotExistError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest(1L, "PATIENT", "2");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Prescription", "does not exist!"));

        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditPrescriptionResponse response = service.execute(request);
        assertFalse(response.isPrescriptionEdited());
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Prescription");
        assertEquals(errors.get(0).getMessage(), "does not exist!");
    }

    @Test
    public void shouldReturnEmptyIDError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest(null, "PATIENT", "2");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ID field", "must not be empty!"));

        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditPrescriptionResponse response = service.execute(request);
        assertFalse(response.isPrescriptionEdited());
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "ID field");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnUserChoiceEmptyError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest(1L, "", "2");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("User choice", "must not be empty!"));

        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditPrescriptionResponse response = service.execute(request);
        assertFalse(response.isPrescriptionEdited());
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "User choice");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnEnumError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest(1L, "enum", null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("User choice", "must be PATIENT, MEDICATION_NAME or QUANTITY!"));

        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditPrescriptionResponse response = service.execute(request);
        assertFalse(response.isPrescriptionEdited());
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "User choice");
        assertEquals(errors.get(0).getMessage(), "must be PATIENT, MEDICATION_NAME or QUANTITY!");
    }

    @Test
    public void shouldReturnIdNumError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest(1L, "PATIENT", "changes");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ID", "must be a number!"));

        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditPrescriptionResponse response = service.execute(request);
        assertFalse(response.isPrescriptionEdited());
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "ID");
        assertEquals(errors.get(0).getMessage(), "must be a number!");
    }

}
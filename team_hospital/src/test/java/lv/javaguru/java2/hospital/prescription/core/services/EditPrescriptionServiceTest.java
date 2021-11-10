package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
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

    @Mock private PrescriptionRepository database;
    @Mock private EditPrescriptionValidator validator;
    @InjectMocks EditPrescriptionService service;

    @Test
    public void shouldReturnTrue(){
        EditPrescriptionRequest request = new EditPrescriptionRequest("1", "PATIENT", "2");

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.editPrescription(
                Long.valueOf(request.getPrescriptionID()),
                EditPrescriptionEnum.valueOf(request.getEditPrescriptionEnum()),
                request.getChanges())).thenReturn(true);

        EditPrescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isPrescriptionEdited());
    }

    @Test
    public void shouldReturnPrescriptionDoesNotExistError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest("1", "PATIENT", "2");
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
        errors.add(new CoreError("Prescription ID", "must not be empty!"));

        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditPrescriptionResponse response = service.execute(request);
        assertFalse(response.isPrescriptionEdited());
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Prescription ID");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnUserChoiceEmptyError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest("1", "", "2");
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
        EditPrescriptionRequest request = new EditPrescriptionRequest("1", "enum", null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Changes", "must not be empty!"));

        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditPrescriptionResponse response = service.execute(request);
        assertFalse(response.isPrescriptionEdited());
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Changes");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnChangesIDError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest("str", "PATIENT", "1");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Prescription ID", "must be a number!"));

        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditPrescriptionResponse response = service.execute(request);
        assertFalse(response.isPrescriptionEdited());
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Prescription ID");
        assertEquals(errors.get(0).getMessage(), "must be a number!");
    }

    @Test
    public void shouldReturnChangesIdError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest("1", "PATIENT", "changes");
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

    @Test
    public void shouldReturnIdNumError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest("1", "PATIENT", "changes");
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

    @Test
    public void shouldReturnPatientExistError(){
        EditPrescriptionRequest request = new EditPrescriptionRequest("1", "PATIENT", "2");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Patient", "does not exist!"));

        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditPrescriptionResponse response = service.execute(request);
        assertFalse(response.isPrescriptionEdited());
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Patient");
        assertEquals(errors.get(0).getMessage(), "does not exist!");
    }

}
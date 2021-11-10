package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.DeletePatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.DeletePatientValidator;
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
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DeletePatientServiceTest {

    @Mock private PatientRepository patientRepository;
    @Mock private DeletePatientValidator validator;
    @InjectMocks DeletePatientService service;

    @Test
    public void shouldReturnErrorWhenPatientIdNotProvided() {
        DeletePatientRequest request = new DeletePatientRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ID", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeletePatientResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "ID");
        assertEquals(response.getErrors().get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPatientIdNotNum() {
        DeletePatientRequest request = new DeletePatientRequest("12qwe123");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ID", "must be a number!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeletePatientResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "ID");
        assertEquals(response.getErrors().get(0).getDescription(), "must be a number!");
    }

    @Test
    public void shouldReturnErrorWhenPatientDoesNotExist() {
        DeletePatientRequest request = new DeletePatientRequest("123");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Patient", "does not exist!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeletePatientResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Patient");
        assertEquals(response.getErrors().get(0).getDescription(), "does not exist!");
    }

    @Test
    public void shouldDeletePatientWithIdFromDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        DeletePatientRequest request = new DeletePatientRequest("1");
        DeletePatientResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }
}
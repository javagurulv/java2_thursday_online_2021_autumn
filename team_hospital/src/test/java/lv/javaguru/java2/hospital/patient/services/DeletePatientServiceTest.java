package lv.javaguru.java2.hospital.patient.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.responses.CoreError;
import lv.javaguru.java2.hospital.patient.responses.DeletePatientResponse;
import lv.javaguru.java2.hospital.patient.services.validators.DeletePatientValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class DeletePatientServiceTest {

    @Mock
    private PatientDatabaseImpl patientDatabase;
    @Mock
    private DeletePatientValidator validator;
    @InjectMocks
    DeletePatientService service;

    @BeforeEach
    public void init() {
        patientDatabase = Mockito.mock(PatientDatabaseImpl.class);
        validator = Mockito.mock(DeletePatientValidator.class);
        service = new DeletePatientService(patientDatabase, validator);
    }

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
    public void shouldDeleteBookWithIdFromDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(patientDatabase.deleteById(1L)).thenReturn(true);
        DeletePatientRequest request = new DeletePatientRequest("1");
        DeletePatientResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isPatientDeleted());
    }
}
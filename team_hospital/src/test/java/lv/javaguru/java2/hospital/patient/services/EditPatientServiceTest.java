package lv.javaguru.java2.hospital.patient.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.services.validators.EditPatientValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EditPatientServiceTest {

    private EditPatientValidator validator;
    private PatientDatabaseImpl database;
    private EditPatientService service;

    @BeforeEach
    public void init(){
        validator = Mockito.mock(EditPatientValidator.class);
        database = Mockito.mock(PatientDatabaseImpl.class);
        service = new EditPatientService(database, validator);
    }

    @Test
    public void shouldChangePatientName(){
        EditPatientRequest request = new EditPatientRequest("1", "1", "NewName");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.editActions(1L, 1, "NewName")).thenReturn(true);

        EditPatientResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isTrueOrNot());
        assertEquals(response.getChanges(), "NewName");
    }

    @Test
    public void shouldChangePatientSurname(){
        EditPatientRequest request = new EditPatientRequest("1", "2", "NewSurname");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.editActions(1L, 2, "NewSurname")).thenReturn(true);

        EditPatientResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isTrueOrNot());
        assertEquals(response.getChanges(), "NewSurname");
    }

    @Test
    public void shouldChangePatientPersonalCode(){
        EditPatientRequest request = new EditPatientRequest("1", "3", "New1234");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.editActions(1L, 3, "New1234")).thenReturn(true);

        EditPatientResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isTrueOrNot());
        assertEquals(response.getChanges(), "New1234");
    }

    @Test
    public void shouldReturnFalse(){
        EditPatientRequest request = new EditPatientRequest("1", "3", "New1234");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.editActions(2L, 3, "New1234")).thenReturn(false);

        EditPatientResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertFalse(response.isTrueOrNot());
    }
}
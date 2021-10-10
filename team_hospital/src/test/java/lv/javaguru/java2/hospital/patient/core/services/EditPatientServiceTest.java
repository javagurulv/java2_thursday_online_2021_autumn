package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;
import lv.javaguru.java2.hospital.patient.core.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.EditPatientValidator;
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
public class EditPatientServiceTest {

    @Mock private EditPatientValidator validator;
    @Mock private PatientDatabaseImpl database;
    @InjectMocks private EditPatientService service;

    @Test
    public void shouldChangePatientName() {
        EditPatientRequest request = new EditPatientRequest(1L, EditPatientEnum.CHANGE_NAME, "NewName");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.editActions(1L, EditPatientEnum.CHANGE_NAME, "NewName")).thenReturn(true);

        EditPatientResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isTrueOrNot());
        assertEquals(response.getChanges(), "NewName");
    }

    @Test
    public void shouldChangePatientSurname() {
        EditPatientRequest request = new EditPatientRequest(1L, EditPatientEnum.CHANGE_SURNAME, "NewSurname");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.editActions(1L, EditPatientEnum.CHANGE_SURNAME, "NewSurname")).thenReturn(true);

        EditPatientResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isTrueOrNot());
        assertEquals(response.getChanges(), "NewSurname");
    }

    @Test
    public void shouldChangePatientPersonalCode() {
        EditPatientRequest request = new EditPatientRequest(1L, EditPatientEnum.CHANGE_PERSONALCODE, "New1234");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.editActions(1L, EditPatientEnum.CHANGE_PERSONALCODE, "New1234")).thenReturn(true);

        EditPatientResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isTrueOrNot());
        assertEquals(response.getChanges(), "New1234");
    }

    @Test
    public void shouldReturnFalse() {
        EditPatientRequest request = new EditPatientRequest(1L, EditPatientEnum.CHANGE_PERSONALCODE, "New1234");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        Mockito.when(database.editActions(1L, EditPatientEnum.CHANGE_PERSONALCODE, "New1234")).thenReturn(false);

        EditPatientResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertFalse(response.isTrueOrNot());
    }
}
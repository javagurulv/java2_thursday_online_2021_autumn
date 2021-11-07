package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
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
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class EditPatientServiceTest {

    @Mock private EditPatientValidator validator;
    @Mock private PatientDatabase database;
    @InjectMocks private EditPatientService service;

    @Test
    public void shouldChangePatientName() {
        EditPatientRequest request = new EditPatientRequest("1", "NAME", "NewName");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        Mockito.when(database.editActions(1L, EditPatientEnum.NAME, "NewName")).thenReturn(true);

        EditPatientResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getChanges(), "NewName");
    }

    @Test
    public void shouldChangePatientSurname() {
        EditPatientRequest request = new EditPatientRequest("1", "SURNAME", "NewSurname");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        Mockito.when(database.editActions(1L, EditPatientEnum.SURNAME, "NewSurname")).thenReturn(true);

        EditPatientResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getChanges(), "NewSurname");
    }

    @Test
    public void shouldChangePatientPersonalCode() {
        EditPatientRequest request = new EditPatientRequest("1", "PERSONAL_CODE", "12345678901");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        Mockito.when(database.editActions(
                Long.valueOf(request.getPatientID()), EditPatientEnum.PERSONAL_CODE, "12345678901")).thenReturn(true);

        EditPatientResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getChanges(), "12345678901");
    }

    @Test
    public void shouldReturnFalse() {
        EditPatientRequest request = new EditPatientRequest("1", "PERSONAL_CODE", "123qwe1213");
        Mockito.when(validator.validate(request))
                .thenReturn(Collections.singletonList(new CoreError("field", "error")));
        EditPatientResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
    }
}
package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.responses.FindPatientByIDResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.FindPatientByIDValidator;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class FindPatientByIdServiceTest {

    @Mock private FindPatientByIDValidator validator;
    @Mock private PatientDatabase database;
    @InjectMocks private FindPatientByIdService service;

    @Test
    public void shouldReturnPatient() {
        FindPatientByIdRequest request = new FindPatientByIdRequest(1L);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.findById(1L)).thenReturn(patients);

        FindPatientByIDResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatient(), patients);
    }

    @Test
    public void shouldReturnOptionalEmpty() {
        FindPatientByIdRequest request = new FindPatientByIdRequest(2L);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.findById(2L)).thenReturn(new ArrayList<>());

        FindPatientByIDResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatient(), new ArrayList<>());
    }
}
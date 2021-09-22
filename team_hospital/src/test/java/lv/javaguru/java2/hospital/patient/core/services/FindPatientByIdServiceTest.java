package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.responses.FindPatientByIDResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.FindPatientByIDValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FindPatientByIdServiceTest {

    @Mock
    private FindPatientByIDValidator validator;
    @Mock
    private PatientDatabaseImpl database;
    @InjectMocks
    private FindPatientByIdService service;

    @BeforeEach
    public void init() {
        validator = Mockito.mock(FindPatientByIDValidator.class);
        database = Mockito.mock(PatientDatabaseImpl.class);
        service = new FindPatientByIdService(database, validator);
    }

    @Test
    public void shouldReturnPatient() {
        FindPatientByIdRequest request = new FindPatientByIdRequest("1");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.findById(1L)).thenReturn(Optional.ofNullable(patients.get(0)));

        FindPatientByIDResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatient(), Optional.of(patients.get(0)));
    }

    @Test
    public void shouldReturnOptionalEmpty() {
        FindPatientByIdRequest request = new FindPatientByIdRequest("2");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.findById(2L)).thenReturn(Optional.empty());

        FindPatientByIDResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatient(), Optional.empty());
    }
}
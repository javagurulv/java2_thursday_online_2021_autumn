package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class FindPatientByIdServiceTest {

    @Mock private FindPatientByIDValidator validator;
    @Mock private JpaPatientRepository database;
    @InjectMocks private FindPatientByIdService service;

    @Test
    public void shouldReturnPatient() {
        FindPatientByIdRequest request = new FindPatientByIdRequest("1");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        Optional<Patient> patient = Optional.of(new Patient("name", "surname", "1234"));
        Mockito.when(database.findById(1L)).thenReturn(patient);

        FindPatientByIDResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatient(), patient);
    }

    @Test
    public void shouldReturnOptionalEmpty() {
        FindPatientByIdRequest request = new FindPatientByIdRequest("2");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        Mockito.when(database.findById(2L)).thenReturn(Optional.empty());

        FindPatientByIDResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPatient(), Optional.empty());
    }

    @Test
    public void shouldReturnErrorWhenPatientIdNotNum() {
        FindPatientByIdRequest request = new FindPatientByIdRequest("12qwe123");

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ID", "must be a number!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        FindPatientByIDResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "ID");
        assertEquals(response.getErrors().get(0).getDescription(), "must be a number!");
    }
}
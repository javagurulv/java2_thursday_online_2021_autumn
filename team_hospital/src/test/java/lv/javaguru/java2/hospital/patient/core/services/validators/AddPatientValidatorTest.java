package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
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
class AddPatientValidatorTest {

    @Mock private PatientDatabaseImpl database;
    @InjectMocks private AddPatientValidator validator;

    @Test
    public void shouldReturnEmptyList(){
            AddPatientRequest request =
                    new AddPatientRequest("Name", "Surname", "1234");
            Mockito.when(database.findPatientByNameSurnamePersonalCode(
                    request.getName(),
                    request.getSurname(),
                    request.getPersonalCode())).thenReturn(new ArrayList<>());
            List<CoreError> errors = validator.validate(request);
            assertTrue(errors.isEmpty());
        }

    @Test
    public void shouldReturnNameError(){
        AddPatientRequest request =
                new AddPatientRequest("", "Surname", "1234");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnSurnameError(){
        AddPatientRequest request =
                new AddPatientRequest("Name", "", "1234");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Surname");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnPersonalCodeError(){
        AddPatientRequest request =
                new AddPatientRequest("Name", "Surname", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Personal code");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnAll3Errors(){
        AddPatientRequest request =
                new AddPatientRequest("", "", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 3);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "Surname");
        assertEquals(errors.get(1).getDescription(), "Must not be empty!");
        assertEquals(errors.get(2).getField(), "Personal code");
        assertEquals(errors.get(2).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnError() {
        AddPatientRequest request =
                new AddPatientRequest("Name", "Surname", "1234");
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.findPatientByNameSurnamePersonalCode(
                request.getName(),
                request.getSurname(),
                request.getPersonalCode())).thenReturn(patients);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Patient");
        assertEquals(errors.get(0).getDescription(), "already exist!");
    }
}
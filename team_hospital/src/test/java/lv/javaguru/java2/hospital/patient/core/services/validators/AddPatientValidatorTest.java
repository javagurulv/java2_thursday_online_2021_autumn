package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.checkers.PersonalCodeChecker;
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
class AddPatientValidatorTest {

    @Mock private PersonalCodeChecker personalCodeChecker;
    @Mock private JpaPatientRepository database;
    @InjectMocks private AddPatientValidator validator;

    @Test
    public void shouldReturnEmptyList() {
        AddPatientRequest request =
                new AddPatientRequest("Name", "Surname", "12345678901");
        Mockito.when(database.findByNameAndSurnameAndPersonalCode(
                request.getName(),
                request.getSurname(),
                request.getPersonalCode())).thenReturn(new ArrayList<>());
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnNameError() {
        AddPatientRequest request =
                new AddPatientRequest("", "Surname", "23456789012");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnSurnameError() {
        AddPatientRequest request =
                new AddPatientRequest("Name", "", "34567890123");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Surname");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnPersonalCodeError() {
        AddPatientRequest request =
                new AddPatientRequest("Name", "Surname", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Personal code");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnAll3Errors() {
        AddPatientRequest request =
                new AddPatientRequest("", "", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 3);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
        assertEquals(errors.get(1).getField(), "Surname");
        assertEquals(errors.get(1).getDescription(), "must not be empty!");
        assertEquals(errors.get(2).getField(), "Personal code");
        assertEquals(errors.get(2).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnPatientExistError() {
        AddPatientRequest request =
                new AddPatientRequest("Name", "Surname", "45678901234");
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        Mockito.when(database.findByNameAndSurnameAndPersonalCode(
                request.getName(),
                request.getSurname(),
                request.getPersonalCode())).thenReturn(patients);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Patient");
        assertEquals(errors.get(0).getDescription(), "already exist!");
    }

    @Test
    public void shouldReturnPersonalCodeLengthError() {
        AddPatientRequest request =
                new AddPatientRequest("Name", "Surname", "1234");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Personal code");
        assertEquals(errors.get(0).getDescription(), "must consist of 11 numbers!");
    }

    @Test
    public void shouldReturnNumFormatExceptionError() {
        AddPatientRequest request =
                new AddPatientRequest("Name", "Surname", "1234code567");
        Mockito.when(personalCodeChecker.execute(request.getPersonalCode()))
                .thenReturn(Optional.of(new CoreError("Personal code", "must consist from numbers only!")));
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Personal code");
        assertEquals(errors.get(0).getDescription(), "must consist from numbers only!");
    }
}
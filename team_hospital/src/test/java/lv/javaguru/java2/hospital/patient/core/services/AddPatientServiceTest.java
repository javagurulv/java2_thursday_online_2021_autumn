package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.validators.AddPatientValidator;
import lv.javaguru.java2.hospital.patient.matchers.PatientMatcher;
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
import static org.mockito.ArgumentMatchers.argThat;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AddPatientServiceTest {

    @Mock private PatientDatabase patientDatabase;
    @Mock private AddPatientValidator validator;
    @InjectMocks private AddPatientService addPatientService;

    @Test
    public void shouldReturnResponseWithErrorsWhenNameValidationFails() {
        AddPatientRequest request = new AddPatientRequest("", "surname", "personal code");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddPatientResponse response = addPatientService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Name");
        assertEquals(response.getErrors().get(0).getDescription(), "must not be empty!");

        Mockito.verifyNoInteractions(patientDatabase);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenSurnameValidationFails() {
        AddPatientRequest request = new AddPatientRequest("name", null, "personal code");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Surname", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddPatientResponse response = addPatientService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Surname");
        assertEquals(response.getErrors().get(0).getDescription(), "must not be empty!");

        Mockito.verifyNoInteractions(patientDatabase);
    }


    @Test
    public void shouldReturnResponseWithErrorsWhenPersonalCodeValidationFails() {
        AddPatientRequest request = new AddPatientRequest("name", "surname", null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Personal code", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddPatientResponse response = addPatientService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Personal code");
        assertEquals(response.getErrors().get(0).getDescription(), "must not be empty!");

        Mockito.verifyNoInteractions(patientDatabase);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenPersonalCodeLengthValidationFails() {
        AddPatientRequest request = new AddPatientRequest("name", "surname", "1234");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Personal code", "must consist of 11 symbols!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddPatientResponse response = addPatientService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Personal code");
        assertEquals(response.getErrors().get(0).getDescription(), "must consist of 11 symbols!");

        Mockito.verifyNoInteractions(patientDatabase);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenPersonalCodeNumValidationFails() {
        AddPatientRequest request = new AddPatientRequest("name", "surname", "1234code567");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Personal code", "must consist only from numbers!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddPatientResponse response = addPatientService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Personal code");
        assertEquals(response.getErrors().get(0).getDescription(), "must consist only from numbers!");

        Mockito.verifyNoInteractions(patientDatabase);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenPatientExistsValidationFails() {
        AddPatientRequest request = new AddPatientRequest("name", "surname", "12345678901");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Patient", "already exist!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddPatientResponse response = addPatientService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Patient");
        assertEquals(response.getErrors().get(0).getDescription(), "already exist!");

        Mockito.verifyNoInteractions(patientDatabase);
    }

    @Test
    public void shouldAddPatientToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddPatientRequest request = new AddPatientRequest("name", "surname", "12345678901");
        AddPatientResponse response = addPatientService.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(patientDatabase).add(argThat(new PatientMatcher("name", "surname", "12345678901")));
    }
}
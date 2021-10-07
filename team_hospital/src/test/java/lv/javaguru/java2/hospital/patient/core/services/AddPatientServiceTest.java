package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
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

    @Mock private PatientDatabaseImpl patientDatabase;
    @Mock private AddPatientValidator validator;
    @InjectMocks private AddPatientService addPatientService;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddPatientRequest request = new AddPatientRequest("name", null, "personal code");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Surname", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddPatientResponse response = addPatientService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Surname");
        assertEquals(response.getErrors().get(0).getDescription(), "Must not be empty!");

        Mockito.verifyNoInteractions(patientDatabase);
    }

    @Test
    public void shouldAddPatientToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddPatientRequest request = new AddPatientRequest("name", "surname", "personalCode");
        AddPatientResponse response = addPatientService.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(patientDatabase).add(argThat(new PatientMatcher("name", "surname", "personalCode")));
    }
}
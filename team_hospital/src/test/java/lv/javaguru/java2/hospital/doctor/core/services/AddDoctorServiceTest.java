package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.AddDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.services.validators.AddDoctorRequestValidator;
import lv.javaguru.java2.hospital.doctor.matchers.DoctorMatcher;
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
class AddDoctorServiceTest {

    @Mock private DoctorDatabaseImpl database;
    @Mock private AddDoctorRequestValidator validator;
    @InjectMocks private AddDoctorService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddDoctorRequest request = new AddDoctorRequest(null, "surname", "speciality");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("name", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddDoctorResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "name");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");

        Mockito.verifyNoMoreInteractions(database);
    }

    @Test
    public void shouldAddDoctorToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddDoctorRequest request = new AddDoctorRequest("name", "surname", "speciality");
        AddDoctorResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(database).addDoctor(argThat(new DoctorMatcher("name", "surname", "speciality")));
    }


}
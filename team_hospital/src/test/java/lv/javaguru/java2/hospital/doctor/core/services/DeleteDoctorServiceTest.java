package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.doctor.core.requests.DeleteDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.DeleteDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.DeleteDoctorRequestValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DeleteDoctorServiceTest {

    @Mock private DoctorRepository database;
    @Mock private DeleteDoctorRequestValidator validator;
    @InjectMocks private DeleteDoctorService service;

    @Test
    public void shouldReturnErrorWhenDoctorIdNotProvided() {
        DeleteDoctorRequest request = new DeleteDoctorRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("id", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteDoctorResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "id");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldDeleteDoctorWithIdFromDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.deleteDoctorById(1L)).thenReturn(true);
        DeleteDoctorRequest request = new DeleteDoctorRequest(1L);
        DeleteDoctorResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isDoctorDeleted());
    }

}
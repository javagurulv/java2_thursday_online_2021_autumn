package lv.javaguru.java2.hospital.doctor.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.doctor.core.requests.DeleteDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import lv.javaguru.java2.hospital.doctor.core.responses.DeleteDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.validators.DeleteDoctorValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class DeleteDoctorServiceTest {

    @Mock
    private DoctorDatabaseImpl database;
    @Mock
    private DeleteDoctorValidator validator;
    @InjectMocks
    private DeleteDoctorService service;

    @BeforeEach
    public void init() {
        database = Mockito.mock(DoctorDatabaseImpl.class);
        validator = Mockito.mock(DeleteDoctorValidator.class);
        service = new DeleteDoctorService(database, validator);
    }

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
    public void shouldDeleteBookWithIdFromDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.deleteDoctorById(1L)).thenReturn(true);
        DeleteDoctorRequest request = new DeleteDoctorRequest("1");
        DeleteDoctorResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isDoctorDeleted());
    }

}
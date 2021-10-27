package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.PrescriptionDatabase;
import lv.javaguru.java2.hospital.prescription.core.requests.DeletePrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.DeletePrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.validators.DeletePrescriptionValidator;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
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

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DeletePrescriptionServiceTest {

    @Mock private PrescriptionDatabase database;
    @Mock private DeletePrescriptionValidator validator;
    @InjectMocks private DeletePrescriptionService service;

    @Test
    public void shouldReturnErrorWhenPrescriptionIdNotProvided() {
        DeletePrescriptionRequest request = new DeletePrescriptionRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("id", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeletePrescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "id");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldDeletePrescriptionWithIdFromDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.deletePrescriptionById(16L)).thenReturn(true);
        DeletePrescriptionRequest request = new DeletePrescriptionRequest(16L);
        DeletePrescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isPrescriptionDeleted());
    }
}
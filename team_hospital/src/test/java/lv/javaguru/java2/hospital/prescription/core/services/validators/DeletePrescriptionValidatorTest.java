package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.DeletePrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.services.validators.existence.PrescriptionExistenceByIDValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DeletePrescriptionValidatorTest {

    @Mock
    private PrescriptionExistenceByIDValidator existence;
    @InjectMocks
    private DeletePrescriptionValidator validator;

    @Test
    public void shouldReturnEmptyList() {
        DeletePrescriptionRequest request = new DeletePrescriptionRequest(154L);
        Mockito.when(existence.execute(request.getPrescriptionIdToDelete())).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnIdError() {
        DeletePrescriptionRequest request = new DeletePrescriptionRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
}
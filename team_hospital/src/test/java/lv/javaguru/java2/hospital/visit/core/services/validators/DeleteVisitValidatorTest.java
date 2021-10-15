package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.DeleteVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.VisitExistenceByIdValidator;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DeleteVisitValidatorTest {

    @Mock private VisitExistenceByIdValidator existence;
    @InjectMocks private DeleteVisitValidator validator;

    @Test
    public void shouldReturnEmptyList() {
        DeleteVisitRequest request = new DeleteVisitRequest(1L);
        Mockito.when(existence.validateExistenceById(1L)).thenReturn(Optional.empty());
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnIdError() {
        DeleteVisitRequest request = new DeleteVisitRequest(null);
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "ID");
        assertEquals(errorList.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnVisitError() {
        Mockito.when(existence.validateExistenceById(12L)).thenReturn
                (Optional.of(new CoreError("Visit", "Does not exist!")));
        DeleteVisitRequest request = new DeleteVisitRequest(12L);
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Visit");
        assertEquals(errorList.get(0).getDescription(), "Does not exist!");
    }
}
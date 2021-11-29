package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.checkers.PatientLongNumChecker;
import lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.PatientExistenceByIDValidator;
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
class DeletePatientValidatorTest {

    @Mock private PatientLongNumChecker longNumChecker;
    @Mock private PatientExistenceByIDValidator existenceValidator;
    @InjectMocks private DeletePatientValidator validator;

    @Test
    public void shouldReturnEmptyList(){
        DeletePatientRequest request =
                new DeletePatientRequest("123");
        Mockito.when(existenceValidator.existenceByID(request.getIdRequest())).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnPatientExistenceError(){
        DeletePatientRequest request =
                new DeletePatientRequest("123");
        Mockito.when(existenceValidator.existenceByID(request.getIdRequest()))
                .thenReturn(Optional.of(new CoreError("Patient", "does not exist!")));
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.get(0).getField(), "Patient");
        assertEquals(errors.get(0).getDescription(), "does not exist!");
    }

    @Test
    public void shouldReturnPatientNumCheckError(){
        DeletePatientRequest request =
                new DeletePatientRequest("qwe");
        Mockito.when(existenceValidator.existenceByID(request.getIdRequest()))
                .thenReturn(Optional.empty());
        Mockito.when(longNumChecker.validate(request.getIdRequest(), "ID"))
                .thenReturn(Optional.of(new CoreError("ID", "must be a number!")));
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "ID");
        assertEquals(errors.get(0).getDescription(), "must be a number!");
    }

    @Test
    public void shouldReturnIDError(){
        DeletePatientRequest request = new DeletePatientRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "ID");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }
}
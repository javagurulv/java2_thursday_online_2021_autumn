package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
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
class FindPatientByIDValidatorTest {

    @Mock
    private PatientLongNumChecker longNumChecker;
    @Mock
    private PatientExistenceByIDValidator idValidator;
    @InjectMocks
    private FindPatientByIDValidator validator;

    @Test
    public void shouldReturnEmptyList() {
        FindPatientByIdRequest request = new FindPatientByIdRequest("1");
        Mockito.when(idValidator.existenceByID(request.getIDRequest())).thenReturn(Optional.empty());
        List<CoreError> errorsList = validator.validate(request);
        assertTrue(errorsList.isEmpty());
    }

    @Test
    public void shouldReturnNumError() {
        FindPatientByIdRequest request = new FindPatientByIdRequest("1");
        Mockito.when(idValidator.existenceByID(request.getIDRequest())).thenReturn(Optional.empty());
        Mockito.when(longNumChecker.validate(request.getIDRequest(), "ID"))
                .thenReturn(Optional.of(new CoreError("ID", "must be a number!")));
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "ID");
        assertEquals(errors.get(0).getDescription(), "must be a number!");
    }

    @Test
    public void shouldReturnIDError() {
        FindPatientByIdRequest request = new FindPatientByIdRequest(null);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "ID");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
    }
}
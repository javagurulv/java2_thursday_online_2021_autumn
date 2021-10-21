package lv.javaguru.java2.hospital.patient.core.services.validators;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
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
class EditPatientValidatorTest {

    @Mock private PatientEnumChecker checker;
    @Mock private PatientExistenceByIDValidator idValidator;
    @InjectMocks private EditPatientValidator validator;

    @Test
    public void shouldReturnEmptyList(){
        EditPatientRequest request = new EditPatientRequest(1L, "NAME", "Name");
        Mockito.when(idValidator.existenceByID(request.getPatientID())).thenReturn(Optional.empty());
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnPatientExistenceError(){
        EditPatientRequest request = new EditPatientRequest(1L, "NAME", "Name");
        Mockito.when(idValidator.existenceByID(request.getPatientID()))
                .thenReturn(Optional.of(new CoreError("Patient", "does not exist!")));
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get(0).getField(),"Patient");
        assertEquals(errorList.get(0).getDescription(), "does not exist!");
    }

    @Test
    public void shouldReturnPatientIDError(){
        EditPatientRequest request = new EditPatientRequest(null, "NAME", "Name");
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "ID");
        assertEquals(errorList.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnUserInputError(){
        EditPatientRequest request = new EditPatientRequest(1L, null,"Name");
        List<CoreError> errorsList = validator.validate(request);
        assertEquals(errorsList.size(), 1);
        assertEquals(errorsList.get(0).getField(), "User choice");
        assertEquals(errorsList.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnChangesError(){
        EditPatientRequest request = new EditPatientRequest(1L, "NAME", "");
        List<CoreError> errorsList = validator.validate(request);
        assertEquals(errorsList.size(), 1);
        assertEquals(errorsList.get(0).getField(), "Changes");
        assertEquals(errorsList.get(0).getDescription(), "Must not be empty!");
    }

    @Test public void shouldReturnEnumError(){
        String input = "input";
        EditPatientRequest request = new EditPatientRequest(1L, input, "changes");
        Mockito.when(checker.validateEnum(input))
                .thenReturn(Optional.of(new CoreError("User input", "must be NAME, SURNAME OR PERSONAL_CODE")));
        List<CoreError> errorList = validator.validate(request);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get(0).getField(), "User input");
        assertEquals(errorList.get(0).getDescription(), "must be NAME, SURNAME OR PERSONAL_CODE");
    }

    @Test
    public void shouldReturn3Errors(){
        EditPatientRequest request = new EditPatientRequest(null,null, null);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 3);
        assertEquals(errorList.get(0).getField(), "ID");
        assertEquals(errorList.get(0).getDescription(), "Must not be empty!");
        assertEquals(errorList.get(1).getField(), "User choice");
        assertEquals(errorList.get(1).getDescription(), "Must not be empty!");
        assertEquals(errorList.get(2).getField(), "Changes");
        assertEquals(errorList.get(2).getDescription(), "Must not be empty!");
    }
}
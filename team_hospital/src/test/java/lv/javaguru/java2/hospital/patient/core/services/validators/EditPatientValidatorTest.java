package lv.javaguru.java2.hospital.patient.core.services.validators;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.checkers.PatientLongNumChecker;
import lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.PatientExistenceByIDValidator;
import lv.javaguru.java2.hospital.patient.core.services.checkers.PersonalCodeChecker;
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

    @Mock private PatientLongNumChecker numChecker;
    @Mock private PersonalCodeChecker personalCodeChecker;
    @Mock private PatientEnumChecker checker;
    @Mock private PatientExistenceByIDValidator idValidator;
    @InjectMocks private EditPatientValidator validator;

    @Test
    public void shouldReturnEmptyList(){
        EditPatientRequest request = new EditPatientRequest("1", "NAME", "Name");
        Mockito.when(idValidator.existenceByID(request.getPatientID())).thenReturn(Optional.empty());
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnPatientExistenceError(){
        EditPatientRequest request = new EditPatientRequest("1", "NAME", "Name");
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
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnUserInputError(){
        EditPatientRequest request = new EditPatientRequest("1", null,"Name");
        List<CoreError> errorsList = validator.validate(request);
        assertEquals(errorsList.size(), 1);
        assertEquals(errorsList.get(0).getField(), "User choice");
        assertEquals(errorsList.get(0).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnChangesError(){
        EditPatientRequest request = new EditPatientRequest("1", "NAME", "");
        List<CoreError> errorsList = validator.validate(request);
        assertEquals(errorsList.size(), 1);
        assertEquals(errorsList.get(0).getField(), "Changes");
        assertEquals(errorsList.get(0).getDescription(), "must not be empty!");
    }

    @Test public void shouldReturnEnumError(){
        String input = "input";
        EditPatientRequest request = new EditPatientRequest("1", input, "changes");
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
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");
        assertEquals(errorList.get(1).getField(), "User choice");
        assertEquals(errorList.get(1).getDescription(), "must not be empty!");
        assertEquals(errorList.get(2).getField(), "Changes");
        assertEquals(errorList.get(2).getDescription(), "must not be empty!");
    }

    @Test
    public void shouldReturnPersonalCodeLengthError(){
        EditPatientRequest request = new EditPatientRequest("1", "PERSONAL_CODE", "1234");
        List<CoreError> errorsList = validator.validate(request);
        assertEquals(errorsList.size(), 1);
        assertEquals(errorsList.get(0).getField(), "Personal code");
        assertEquals(errorsList.get(0).getDescription(), "must consist of 11 numbers!");
    }

    @Test
    public void shouldReturnPersonalCodeInputError(){
        EditPatientRequest request = new EditPatientRequest("1", "PERSONAL_CODE", "1234qw34567");
        Mockito.when(personalCodeChecker.execute(request.getChanges()))
                .thenReturn(Optional.of(new CoreError("Personal code", "must consist from numbers only!")));
        List<CoreError> errorsList = validator.validate(request);
        assertEquals(errorsList.size(), 1);
        assertEquals(errorsList.get(0).getField(), "Personal code");
        assertEquals(errorsList.get(0).getDescription(), "must consist from numbers only!");
    }

    @Test
    public void shouldReturnIDNumError(){
        EditPatientRequest request = new EditPatientRequest("1asd", "PERSONAL_CODE", "12345678901");
        Mockito.when(numChecker.validate(request.getPatientID(), "ID"))
                .thenReturn(Optional.of(new CoreError("ID", "must be a number!")));
        List<CoreError> errorsList = validator.validate(request);
        assertEquals(errorsList.size(), 1);
        assertEquals(errorsList.get(0).getField(), "ID");
        assertEquals(errorsList.get(0).getDescription(), "must be a number!");
    }
}
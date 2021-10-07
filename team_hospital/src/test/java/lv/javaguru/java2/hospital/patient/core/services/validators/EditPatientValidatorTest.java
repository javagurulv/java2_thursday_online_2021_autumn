package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EditPatientValidatorTest {

    private final PatientDatabaseImpl database = new PatientDatabaseImpl();
    private final EditPatientValidator validator = new EditPatientValidator();

    @Test
    public void shouldReturnEmptyList(){
        database.add(new Patient("name", "surname", "1234"));
        EditPatientRequest request = new EditPatientRequest(1L, 1, "Name");
        List<CoreError> errorList = validator.validate(request);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnPatientIDError(){
        EditPatientRequest request = new EditPatientRequest(null, 1, "Name");
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "ID");
        assertEquals(errorList.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnUserInputError(){
        database.add(new Patient("name", "surname", "1234"));
        EditPatientRequest request = new EditPatientRequest(1L, null,"Name");
        List<CoreError> errorsList = validator.validate(request);
        assertEquals(errorsList.size(), 1);
        assertEquals(errorsList.get(0).getField(), "User choice");
        assertEquals(errorsList.get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnChangesError(){
        database.add(new Patient("name", "surname", "1234"));
        EditPatientRequest request = new EditPatientRequest(1L, 1, "");
        List<CoreError> errorsList = validator.validate(request);
        assertEquals(errorsList.size(), 1);
        assertEquals(errorsList.get(0).getField(), "Changes");
        assertEquals(errorsList.get(0).getDescription(), "Must not be empty!");
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
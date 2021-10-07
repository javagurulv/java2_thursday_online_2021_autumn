package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.FindPatientByIdRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FindPatientByIDValidatorTest {

   private final PatientDatabaseImpl database = new PatientDatabaseImpl();
   private final FindPatientByIDValidator validator = new FindPatientByIDValidator();

    @Test
    public void shouldReturnEmptyList(){
        database.add(new Patient("name", "surname", "1234"));
        FindPatientByIdRequest request = new FindPatientByIdRequest(1L);
        List<CoreError> errorsList = validator.validate(request);
        assertTrue(errorsList.isEmpty());
    }

    @Test
    public void shouldReturnIDError(){
        FindPatientByIdRequest request= new FindPatientByIdRequest(null);
        List<CoreError> errorList = validator.validate(request);
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "ID");
        assertEquals(errorList.get(0).getDescription(), "Must not be empty!");
    }
}
package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeletePatientValidatorTest {

    private final PatientDatabaseImpl database = new PatientDatabaseImpl();
    private final DeletePatientValidator validator = new DeletePatientValidator(database);

    @Test
    public void shouldReturnEmptyList(){
        database.add(new Patient("name", "surname", "1234"));
        DeletePatientRequest request =
                new DeletePatientRequest("1");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnIDError(){
        DeletePatientRequest request =
                new DeletePatientRequest("");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "ID");
        assertEquals(errors.get(0).getDescription(), "Must not be empty!");
    }
}
package lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.validators_for_search_criteria;

import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.database.patient_repository.InMemoryPatientRepositoryImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.existence_validators_for_search_criteria.NameAndSurnameSearchValidator;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class NameAndSurnameSearchValidatorTest {

    private PatientRepository database = new InMemoryPatientRepositoryImpl();
    private NameAndSurnameSearchValidator validator = new NameAndSurnameSearchValidator(database);

    @Test
    public void shouldReturnTrue(){
        Patient patient = new Patient("name", "surname", "1234");
        database.add(patient);
        SearchPatientsRequest request = new SearchPatientsRequest("name", "surname", "");
        assertTrue(validator.canProcess(request));
        assertEquals(validator.process(request), Optional.empty());
    }

    @Test
    public void shouldReturnFalse(){
        SearchPatientsRequest request = new SearchPatientsRequest("", "", "1234");
        assertFalse(validator.canProcess(request));
        Optional<CoreError> error = validator.process(request);
        assertEquals(error.get().getField(), "Patient");
        assertEquals(error.get().getDescription(), "does not exist!");
    }
}
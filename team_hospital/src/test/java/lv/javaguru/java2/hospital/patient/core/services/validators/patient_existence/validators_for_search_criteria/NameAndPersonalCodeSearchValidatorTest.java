package lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.validators_for_search_criteria;

import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.database.patient_repository.InMemoryPatientRepositoryImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.existence_validators_for_search_criteria.NameAndPersonalCodeSearchValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class NameAndPersonalCodeSearchValidatorTest {

    @Mock private JpaPatientRepository database;
    @InjectMocks private NameAndPersonalCodeSearchValidator validator;

    @Test
    public void shouldReturnTrue(){
        Patient patient = new Patient("name", "surname", "1234");
        Mockito.when(database.findAll())
                .thenReturn(Collections.singletonList(patient));
        SearchPatientsRequest request = new SearchPatientsRequest("name", "", "1234");
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
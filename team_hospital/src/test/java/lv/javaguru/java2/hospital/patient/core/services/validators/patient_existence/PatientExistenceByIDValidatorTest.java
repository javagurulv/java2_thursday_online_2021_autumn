package lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientExistenceByIDValidatorTest {

    @Mock PatientDatabaseImpl database;
    @InjectMocks PatientExistenceByIDValidator validator;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnError(){
        Optional<CoreError> error = validator.existenceByID(123L);
        assertEquals(error.get().getField(), "Patient");
        assertEquals(error.get().getDescription(), "does not exist!");
    }

    @Test
    public void shouldReturnEmptyList(){
        Patient patient = new Patient("name", "surname", "1234");
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        Mockito.when(database.getPatientsList()).thenReturn(patients);
        Optional<CoreError> error = validator.existenceByID(patient.getId());
        assertTrue(error.isEmpty());
    }
}
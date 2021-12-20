package lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence;

import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class PatientExistenceByIDValidatorTest {

    @Mock JpaPatientRepository database;
    @InjectMocks PatientExistenceByIDValidator validator;

    @Test
    public void shouldReturnError(){
        Optional<CoreError> error = validator.existenceByID("123");
        assertEquals(error.get().getField(), "Patient");
        assertEquals(error.get().getDescription(), "does not exist!");
    }

    @Test
    public void shouldReturnEmptyList(){
        Patient patient = new Patient("name", "surname", "1234");
        patient.setId(136L);
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        Mockito.when(database.findAll()).thenReturn(patients);
        Optional<CoreError> error = validator.existenceByID(patient.getId().toString());
        assertTrue(error.isEmpty());
    }
}
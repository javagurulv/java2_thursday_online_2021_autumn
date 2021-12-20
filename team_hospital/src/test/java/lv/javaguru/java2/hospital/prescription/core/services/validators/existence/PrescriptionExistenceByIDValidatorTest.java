package lv.javaguru.java2.hospital.prescription.core.services.validators.existence;

import lv.javaguru.java2.hospital.database.jpa.JpaPrescriptionRepository;
import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class PrescriptionExistenceByIDValidatorTest {

    @Mock private JpaPrescriptionRepository database;
    @InjectMocks private PrescriptionExistenceByIDValidator idValidator;

    @Test
    public void shouldNotReturnError(){
        Doctor doctor = new Doctor("name", "surname", "speciality");
        Patient patient = new Patient("name", "surname", "12345678901");
        Prescription prescription = new Prescription(doctor, patient, "medicationName", 8);
        prescription.setId(1L);
        List<Prescription> prescriptionsList = new ArrayList<>();
        prescriptionsList.add(prescription);

        Mockito.when(database.findAll()).thenReturn(prescriptionsList);

        Optional<CoreError> error = idValidator.execute(prescription.getId());
        assertTrue(error.isEmpty());
    }

    @Test
    public void shouldReturnError(){
        Mockito.when(database.findAll()).thenReturn(new ArrayList<>());
        Optional<CoreError> error = idValidator.execute(10L);
        assertFalse(error.isEmpty());
        assertEquals(error.get().getField(), "Prescription");
        assertEquals(error.get().getMessage(), "does not exist!");
    }
}
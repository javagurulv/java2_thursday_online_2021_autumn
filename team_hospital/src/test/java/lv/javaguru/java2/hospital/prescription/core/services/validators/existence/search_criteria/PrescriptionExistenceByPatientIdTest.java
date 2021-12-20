package lv.javaguru.java2.hospital.prescription.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.jpa.JpaPrescriptionRepository;
import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
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
class PrescriptionExistenceByPatientIdTest {

    @Mock
    private JpaPrescriptionRepository database;
    @InjectMocks
    private PrescriptionExistenceByPatientId existence;

    @Test
    public void shouldReturnTrue() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, null, 646L);
        assertTrue(existence.canValidate(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, null, null);
        assertFalse(existence.canValidate(request));
    }

    @Test
    public void shouldReturnPrescriptionError() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, null, 646L);
        Optional<CoreError> error = existence.validateExistence(request);
        assertFalse(error.isEmpty());
        assertEquals(error.get().getField(), "Prescription");
        assertEquals(error.get().getMessage(), "Does not exist!");
    }

    @Test
    public void shouldReturnEmptyList() {
        Doctor doctor = new Doctor("Name253", "Surname235", "Speciality746");
        Patient patient = new Patient("Name112", "Surname214", "121212-12365");
        patient.setId(646L);
        Prescription prescription = new Prescription(doctor, patient, "MedName", 2);

        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, null, 646L);
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription);
        Mockito.when(database.findAll()).thenReturn(prescriptions);

        Optional<CoreError> error = existence.validateExistence(request);
        assertTrue(error.isEmpty());
    }

}
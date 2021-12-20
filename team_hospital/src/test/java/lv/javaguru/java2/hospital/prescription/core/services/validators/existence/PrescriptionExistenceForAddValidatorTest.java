package lv.javaguru.java2.hospital.prescription.core.services.validators.existence;

import lv.javaguru.java2.hospital.database.jpa.JpaPrescriptionRepository;
import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.AddPrescriptionRequest;
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
class PrescriptionExistenceForAddValidatorTest {

    @Mock
    private JpaPrescriptionRepository database;
    @InjectMocks
    PrescriptionExistenceForAddValidator existence;

    @Test
    public void shouldReturnDoctorsErrorWhenPrescriptionExists() {
        Doctor doctor = new Doctor("Name2443", "Surname15435", "Speciality894739");
        doctor.setId(646L);
        Patient patient = new Patient("Name12", "Surname13", "120445-12568");
        patient.setId(945L);
        Prescription prescription = new Prescription(doctor, patient, "MedName1", 1);
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription);
        AddPrescriptionRequest request = new AddPrescriptionRequest("646", "945", "MedName1", "1");
        Mockito.when(database.findAll()).thenReturn(prescriptions);
        Optional<CoreError> errors = existence.validatePrescriptionExistence(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.get().getField(), "Prescription");
        assertEquals(errors.get().getMessage(), "Already exists!");
    }

    @Test
    public void shouldReturnEmptyList() {
        AddPrescriptionRequest request = new AddPrescriptionRequest("646", "945", "MedName1", "1");
        Mockito.when(database.findAll()).thenReturn(new ArrayList<>());
        Optional<CoreError> errors = existence.validatePrescriptionExistence(request);
        assertTrue(errors.isEmpty());
    }

}
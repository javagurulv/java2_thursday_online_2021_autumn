package lv.javaguru.java2.hospital.prescription.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PrescriptionIdSearchCriteriaTest {

    @Mock
    private PrescriptionRepository database;
    @InjectMocks
    private PrescriptionIdSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(123L, null, null);
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, null, null);
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectPrescription() {
        Doctor doctor = new Doctor("Name2443", "Surname15435", "Speciality894739");
        Patient patient = new Patient("Name12", "Surname13", "120445-12568");
        Prescription prescription1 = new Prescription(doctor, patient, "MedName", 1);
        Long prescriptionId = 656L;
        prescription1.setId(prescriptionId);
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription1);
        Mockito.when(database.findByPrescriptionId(prescriptionId)).thenReturn(prescriptions);
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(prescriptionId, null, null);
        Prescription prescription2 = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 1);
        assertEquals(prescription2.getId(), prescriptionId);
        assertEquals(prescription2.getDoctor(), doctor);
        assertEquals(prescription2.getPatient(), patient);
        assertEquals(prescription2.getMedication(), "MedName");
        assertEquals(prescription2.getQuantity(), 1);
    }
}
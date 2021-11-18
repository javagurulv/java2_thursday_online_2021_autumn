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
class DoctorIdPatientIdSearchCriteriaTest {

    @Mock
    private PrescriptionRepository database;
    @InjectMocks
    private DoctorIdAndPatientIdSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, 12L, 15L);
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
        Long doctorId = doctor.getId();
        Long patientId = patient.getId();
        Prescription prescription1 = new Prescription(doctor, patient, "MedName", 1);
        Long prescriptionId = prescription1.getId();
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription1);
        Mockito.when(database.findByDoctorAndPatientId(doctorId, patientId)).thenReturn(prescriptions);
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, doctorId, patientId);
        Prescription prescription2 = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 1);
        assertEquals(prescription2.getId(), prescriptionId);
        assertEquals(prescription2.getDoctor(), doctor);
        assertEquals(prescription2.getPatient(), patient);
        assertEquals(prescription2.getMedication(), "MedName");
        assertEquals(prescription2.getQuantity(), 1);
    }

    @Test
    public void shouldReturnCorrectPrescriptions() {
        Doctor doctor = new Doctor("Name2443", "Surname15435", "Speciality894739");
        Patient patient = new Patient("Name12", "Surname13", "120445-12568");
        Long doctorId = doctor.getId();
        Long patientId = patient.getId();
        Prescription prescription1 = new Prescription(doctor, patient, "MedName1", 1);
        Prescription prescription2 = new Prescription(doctor, patient, "MedName2", 2);
        Long prescription1Id = prescription1.getId();
        Long prescription2Id = prescription1.getId();
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription1);
        prescriptions.add(prescription2);
        Mockito.when(database.findByDoctorAndPatientId(doctorId, patientId)).thenReturn(prescriptions);
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, doctorId, patientId);
        Prescription prescription3 = searchCriteria.process(request).get(0);
        Prescription prescription4 = searchCriteria.process(request).get(1);
        assertEquals(searchCriteria.process(request).size(), 2);
        assertEquals(prescription3.getId(), prescription1Id);
        assertEquals(prescription3.getDoctor(), doctor);
        assertEquals(prescription3.getPatient(), patient);
        assertEquals(prescription3.getMedication(), "MedName1");
        assertEquals(prescription3.getQuantity(), 1);
        assertEquals(prescription4.getId(), prescription2Id);
        assertEquals(prescription4.getDoctor(), doctor);
        assertEquals(prescription4.getPatient(), patient);
        assertEquals(prescription4.getMedication(), "MedName2");
        assertEquals(prescription4.getQuantity(), 2);
    }

}
package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.jpa.JpaPrescriptionRepository;
import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.ShowAllPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.ShowAllPrescriptionResponse;
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
class ShowAllPrescriptionServiceTest {

    @Mock
    private JpaPrescriptionRepository database;
    @InjectMocks
    private ShowAllPrescriptionService service;

    @Test
    public void shouldGetPrescriptionsFromDb() {
        Doctor doctor = new Doctor("DoctorName12", "DoctorSurname12", "DoctorSpeciality12");
        Patient patient = new Patient("PatientName12", "PatientSurname12", "121212-12354");
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(new Prescription(doctor, patient, "Drug1", 1));
        prescriptions.add(new Prescription(doctor, patient, "Drug2", 2));
        Mockito.when(database.findAll()).thenReturn(prescriptions);
        ShowAllPrescriptionRequest request = new ShowAllPrescriptionRequest();
        ShowAllPrescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        System.out.println(response.getPrescriptions());
        assertEquals(response.getPrescriptions().size(), 2);
        assertEquals(response.getPrescriptions().get(0).getDoctor(), doctor);
        assertEquals(response.getPrescriptions().get(0).getPatient(), patient);
        assertEquals(response.getPrescriptions().get(0).getMedication(), "Drug1");
        assertEquals(response.getPrescriptions().get(0).getQuantity(), 1);
        assertEquals(response.getPrescriptions().get(1).getDoctor(), doctor);
        assertEquals(response.getPrescriptions().get(1).getPatient(), patient);
        assertEquals(response.getPrescriptions().get(1).getMedication(), "Drug2");
        assertEquals(response.getPrescriptions().get(1).getQuantity(), 2);
    }
}
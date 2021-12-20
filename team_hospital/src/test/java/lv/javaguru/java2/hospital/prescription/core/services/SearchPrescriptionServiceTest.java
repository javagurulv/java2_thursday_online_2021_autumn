package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.jpa.JpaPrescriptionRepository;
import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.PrescriptionOrdering;
import lv.javaguru.java2.hospital.prescription.core.requests.PrescriptionPaging;
import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.responses.SearchPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.services.validators.SearchPrescriptionValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class SearchPrescriptionServiceTest {

    @Mock
    private JpaPrescriptionRepository database;
    @Mock
    private SearchPrescriptionValidator validator;
    @InjectMocks
    private SearchPrescriptionService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("prescriptionId", "Must not be empty!"));
        errors.add(new CoreError("doctorId", "Must not be empty!"));
        errors.add(new CoreError("patientId", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        SearchPrescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 3);

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldSearchByPrescriptionId() {
        Doctor doctor = new Doctor("Name2443", "Surname15435", "Speciality894739");
        Patient patient = new Patient("Name12", "Surname13", "120445-12568");
        Prescription prescription = new Prescription(doctor, patient, "MedName1", 1);
        prescription.setId(98L);

        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription);

        SearchPrescriptionRequest request = new SearchPrescriptionRequest(98L, null, null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.getById(98L)).thenReturn(prescriptions);

        SearchPrescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPrescriptions().size(), 1);
        assertEquals(response.getPrescriptions().get(0).getId(), 98L);
        assertEquals(response.getPrescriptions().get(0).getDoctor(), doctor);
        assertEquals(response.getPrescriptions().get(0).getPatient(), patient);
        assertEquals(response.getPrescriptions().get(0).getMedication(), "MedName1");
        assertEquals(response.getPrescriptions().get(0).getQuantity(), 1);
    }

    @Test
    public void shouldSearchByDoctorId() {
        Doctor doctor = new Doctor("Name2443", "Surname15435", "Speciality894739");
        Patient patient = new Patient("Name12", "Surname13", "120445-12568");
        Prescription prescription = new Prescription(doctor, patient, "MedName1", 1);
        doctor.setId(28L);

        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription);

        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, 28L, null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findByDoctorId(28L)).thenReturn(prescriptions);

        SearchPrescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPrescriptions().size(), 1);
        assertEquals(response.getPrescriptions().get(0).getDoctor(), doctor);
        assertEquals(response.getPrescriptions().get(0).getPatient(), patient);
        assertEquals(response.getPrescriptions().get(0).getMedication(), "MedName1");
        assertEquals(response.getPrescriptions().get(0).getQuantity(), 1);
    }

    @Test
    public void shouldSearchByPatientId() {
        Doctor doctor = new Doctor("Name2443", "Surname15435", "Speciality894739");
        Patient patient = new Patient("Name12", "Surname13", "120445-12568");
        Prescription prescription = new Prescription(doctor, patient, "MedName1", 1);
        patient.setId(298L);

        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription);

        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, null, 298L);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findByPatientId(298L)).thenReturn(prescriptions);

        SearchPrescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPrescriptions().size(), 1);
        assertEquals(response.getPrescriptions().get(0).getDoctor(), doctor);
        assertEquals(response.getPrescriptions().get(0).getPatient(), patient);
        assertEquals(response.getPrescriptions().get(0).getMedication(), "MedName1");
        assertEquals(response.getPrescriptions().get(0).getQuantity(), 1);
    }

    @Test
    public void shouldSearchByDoctorAndPatientId() {
        Doctor doctor = new Doctor("Name2443", "Surname15435", "Speciality894739");
        Patient patient = new Patient("Name12", "Surname13", "120445-12568");
        Prescription prescription = new Prescription(doctor, patient, "MedName1", 1);
        doctor.setId(72L);
        patient.setId(298L);

        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription);

        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, 72L, 298L);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findByDoctorIdAndPatientId(72L, 298L)).thenReturn(prescriptions);

        SearchPrescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPrescriptions().size(), 1);
        assertEquals(response.getPrescriptions().get(0).getDoctor(), doctor);
        assertEquals(response.getPrescriptions().get(0).getPatient(), patient);
        assertEquals(response.getPrescriptions().get(0).getMedication(), "MedName1");
        assertEquals(response.getPrescriptions().get(0).getQuantity(), 1);
    }

    @Test
    public void shouldSearchByDoctorIdWithOrderingAscending() {
        Doctor doctor = new Doctor("Name2443", "Surname15435", "Speciality894739");
        Patient patient = new Patient("Name12", "Surname13", "120445-12568");
        doctor.setId(28L);
        Prescription prescription1 = new Prescription(doctor, patient, "MedName1", 1);
        Prescription prescription2 = new Prescription(doctor, patient, "MedName12", 2);
        prescription1.setDate(LocalDate.of(2021, 11, 13));

        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription1);
        prescriptions.add(prescription2);

        PrescriptionOrdering ordering = new PrescriptionOrdering("date", "ASCENDING");
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, 28L, null, ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findByDoctorId(28L)).thenReturn(prescriptions);

        SearchPrescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPrescriptions().size(), 2);
        assertEquals(response.getPrescriptions().get(0).getDoctor(), doctor);
        assertEquals(response.getPrescriptions().get(0).getPatient(), patient);
        assertEquals(response.getPrescriptions().get(0).getMedication(), "MedName1");
        assertEquals(response.getPrescriptions().get(0).getQuantity(), 1);
        assertEquals(response.getPrescriptions().get(1).getDoctor(), doctor);
        assertEquals(response.getPrescriptions().get(1).getPatient(), patient);
        assertEquals(response.getPrescriptions().get(1).getMedication(), "MedName12");
        assertEquals(response.getPrescriptions().get(1).getQuantity(), 2);
    }

    @Test
    public void shouldSearchByDoctorIdWithOrderingDescending() {
        Doctor doctor = new Doctor("Name2443", "Surname15435", "Speciality894739");
        Patient patient = new Patient("Name12", "Surname13", "120445-12568");
        doctor.setId(28L);
        Prescription prescription1 = new Prescription(doctor, patient, "MedName1", 1);
        Prescription prescription2 = new Prescription(doctor, patient, "MedName12", 2);
        prescription1.setDate(LocalDate.of(2021, 11, 13));

        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription1);
        prescriptions.add(prescription2);

        PrescriptionOrdering ordering = new PrescriptionOrdering("date", "DESCENDING");
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, 28L, null, ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findByDoctorId(28L)).thenReturn(prescriptions);

        SearchPrescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPrescriptions().size(), 2);
        assertEquals(response.getPrescriptions().get(0).getDoctor(), doctor);
        assertEquals(response.getPrescriptions().get(0).getPatient(), patient);
        assertEquals(response.getPrescriptions().get(0).getMedication(), "MedName12");
        assertEquals(response.getPrescriptions().get(0).getQuantity(), 2);
        assertEquals(response.getPrescriptions().get(1).getDoctor(), doctor);
        assertEquals(response.getPrescriptions().get(1).getPatient(), patient);
        assertEquals(response.getPrescriptions().get(1).getMedication(), "MedName1");
        assertEquals(response.getPrescriptions().get(1).getQuantity(), 1);
    }

    @Test
    public void shouldSearchByDoctorIdWithWithPagingFirstPage() {
        Doctor doctor = new Doctor("Name2443", "Surname15435", "Speciality894739");
        Patient patient = new Patient("Name12", "Surname13", "120445-12568");
        doctor.setId(28L);
        Prescription prescription1 = new Prescription(doctor, patient, "MedName1", 1);
        Prescription prescription2 = new Prescription(doctor, patient, "MedName12", 2);

        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription1);
        prescriptions.add(prescription2);

        PrescriptionPaging paging = new PrescriptionPaging(1, 1);
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, 28L, null, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findByDoctorId(28L)).thenReturn(prescriptions);

        SearchPrescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPrescriptions().size(), 1);
        assertEquals(response.getPrescriptions().get(0).getDoctor(), doctor);
        assertEquals(response.getPrescriptions().get(0).getPatient(), patient);
        assertEquals(response.getPrescriptions().get(0).getMedication(), "MedName1");
        assertEquals(response.getPrescriptions().get(0).getQuantity(), 1);
    }

    @Test
    public void shouldSearchByDoctorIdWithWithPagingSecondPage() {
        Doctor doctor = new Doctor("Name2443", "Surname15435", "Speciality894739");
        Patient patient = new Patient("Name12", "Surname13", "120445-12568");
        doctor.setId(28L);
        Prescription prescription1 = new Prescription(doctor, patient, "MedName1", 1);
        Prescription prescription2 = new Prescription(doctor, patient, "MedName12", 2);

        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription1);
        prescriptions.add(prescription2);

        PrescriptionPaging paging = new PrescriptionPaging(2, 1);
        SearchPrescriptionRequest request = new SearchPrescriptionRequest(null, 28L, null, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findByDoctorId(28L)).thenReturn(prescriptions);

        SearchPrescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getPrescriptions().size(), 1);
        assertEquals(response.getPrescriptions().get(0).getDoctor(), doctor);
        assertEquals(response.getPrescriptions().get(0).getPatient(), patient);
        assertEquals(response.getPrescriptions().get(0).getMedication(), "MedName12");
        assertEquals(response.getPrescriptions().get(0).getQuantity(), 2);
    }
}
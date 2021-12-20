package lv.javaguru.java2.hospital.prescription.core.services;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaDoctorRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaPrescriptionRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.prescription.core.requests.AddPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.AddPrescriptionResponse;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.services.validators.AddPrescriptionValidator;
import lv.javaguru.java2.hospital.prescription.matchers.PrescriptionMatcher;
import org.junit.Ignore;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AddPrescriptionServiceTest {

    @Mock private JpaPrescriptionRepository database;
    @Mock private JpaPatientRepository patientRepository;
    @Mock private JpaDoctorRepository doctorRepository;
    @Mock private AddPrescriptionValidator validator;
    @InjectMocks private AddPrescriptionService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        Long doctorId = 12L;
        Long patientId = 15L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), patientId.toString(), "MedicationName", "2");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Doctor", "does not exist!"));
        errors.add(new CoreError("Patient", "does not exist!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddPrescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 2);
        assertEquals(response.getErrors().get(0).getField(), "Doctor");
        assertEquals(response.getErrors().get(0).getMessage(), "does not exist!");
        assertEquals(response.getErrors().get(1).getField(), "Patient");
        assertEquals(response.getErrors().get(1).getMessage(), "does not exist!");

        Mockito.verifyNoMoreInteractions(database);
    }

    @Ignore
    public void shouldAddVisitToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Long doctorId = 12L;
        Long patientId = 15L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), patientId.toString(), "MedicationName", "2");

        Doctor doctor = new Doctor("DoctorName", "DoctorSurname", "DoctorSpeciality");
        doctor.setId(doctorId);
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        Optional<Patient> patient = Optional.of(new Patient("name", "surname", "1234"));
        patient.get().setId(patientId);

        Mockito.when(patientRepository.findById(patientId)).thenReturn(patient);
        Mockito.when(doctorRepository.getById(doctorId)).thenReturn(doctors);

        AddPrescriptionResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(database).save(argThat(new PrescriptionMatcher
                (response.getPrescription().getDoctor(),
                        response.getPrescription().getPatient(),
                        response.getPrescription().getMedication(),
                        response.getPrescription().getQuantity())));
    }
}
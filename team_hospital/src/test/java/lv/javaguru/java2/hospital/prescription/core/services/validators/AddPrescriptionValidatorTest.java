package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaDoctorRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.prescription.core.requests.AddPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.services.validators.existence.PrescriptionExistenceForAddValidator;
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

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AddPrescriptionValidatorTest {

    @Mock private JpaPatientRepository patientRepository;
    @Mock private JpaDoctorRepository doctorRepository;
    @Mock private PrescriptionExistenceForAddValidator existence;
    @InjectMocks private AddPrescriptionValidator validator;

    @Ignore
    public void shouldReturnDoctorIdError(){
        Long patientId = 21L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(null, patientId.toString(), "MedicationName", "1");
        Optional<Patient> patient = Optional.of(new Patient("Patient name", "PatientSurname", "121212-12342"));
        patient.get().setId(patientId);
        Mockito.when(patientRepository.findById(patientId)).thenReturn(patient);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Doctor id");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Ignore
    public void shouldReturnPatientIdError(){
        Long doctorId = 12L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), null, "MedicationName", "1");
        Doctor doctor = new Doctor("DoctorName", "DoctorSurname", "DoctorSpeciality");
        doctor.setId(doctorId);
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        Mockito.when(doctorRepository.getById(doctorId)).thenReturn(doctors);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Patient id");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }


    @Ignore
    public void shouldReturnMedicationError(){
        Long doctorId = 12L;
        Long patientId = 15L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), patientId.toString(), "", "1");

        Doctor doctor = new Doctor("DoctorName", "DoctorSurname", "DoctorSpeciality");
        doctor.setId(doctorId);
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        Optional<Patient> patient = Optional.of(new Patient("Patient name", "PatientSurname", "121212-12342"));
        patient.get().setId(patientId);
        Mockito.when(patientRepository.findById(patientId)).thenReturn(patient);
        Mockito.when(doctorRepository.getById(doctorId)).thenReturn(doctors);

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Medication name");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Ignore
    public void shouldReturnQuantityEmptyError(){
        Long doctorId = 12L;
        Long patientId = 15L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), patientId.toString(), "MedicationName", null);

        Doctor doctor = new Doctor("DoctorName", "DoctorSurname", "DoctorSpeciality");
        doctor.setId(doctorId);
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        Optional<Patient> patient = Optional.of(new Patient("Patient name", "PatientSurname", "121212-12342"));
        patient.get().setId(patientId);
        Mockito.when(patientRepository.findById(patientId)).thenReturn(patient);
        Mockito.when(doctorRepository.getById(doctorId)).thenReturn(doctors);

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Medication quantity");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Ignore
    public void shouldReturnQuantityZeroError(){
        Long doctorId = 12L;
        Long patientId = 15L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), patientId.toString(), "MedicationName", "0");

        Doctor doctor = new Doctor("DoctorName", "DoctorSurname", "DoctorSpeciality");
        doctor.setId(doctorId);
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        Optional<Patient> patient = Optional.of(new Patient("Patient name", "PatientSurname", "121212-12342"));
        patient.get().setId(patientId);
        Mockito.when(patientRepository.findById(patientId)).thenReturn(patient);
        Mockito.when(doctorRepository.getById(doctorId)).thenReturn(doctors);

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Medication quantity");
        assertEquals(errors.get(0).getMessage(), "must be greater than 0!");
    }

    @Ignore
    public void shouldReturnDoctorExistenceError(){
        Long doctorId = 12L;
        Long patientId = 15L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), patientId.toString(), "MedicationName", "2");

        Optional<Patient> patient = Optional.of(new Patient("Patient name", "PatientSurname", "121212-12342"));
        patient.get().setId(patientId);
        Mockito.when(patientRepository.findById(patientId)).thenReturn(patient);

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Doctor");
        assertEquals(errors.get(0).getMessage(), "does not exist!");
    }

    @Ignore
    public void shouldReturnPatientExistenceError(){
        Long doctorId = 12L;
        Long patientId = 15L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), patientId.toString(), "MedicationName", "1");

        Doctor doctor = new Doctor("DoctorName", "DoctorSurname", "DoctorSpeciality");
        doctor.setId(doctorId);
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        Mockito.when(doctorRepository.getById(doctorId)).thenReturn(doctors);

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Patient");
        assertEquals(errors.get(0).getMessage(), "does not exist!");
    }

    @Ignore
    public void shouldReturnNoError(){
        Long doctorId = 12L;
        Long patientId = 15L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), patientId.toString(), "MedicationName", "1");

        Doctor doctor = new Doctor("DoctorName", "DoctorSurname", "DoctorSpeciality");
        doctor.setId(doctorId);
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        Optional<Patient> patient = Optional.of(new Patient("Patient name", "PatientSurname", "121212-12342"));
        patient.get().setId(patientId);
        Mockito.when(patientRepository.findById(patientId)).thenReturn(patient);
        Mockito.when(doctorRepository.getById(doctorId)).thenReturn(doctors);

        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }
}
package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.database.jpa.JpaDoctorRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.prescription.core.requests.AddPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.services.validators.existence.PrescriptionExistenceForAddValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
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

    @Test
    public void shouldReturnPatientIdError(){
        Long doctorId = 12L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), null, "MedicationName", "1");
        Doctor doctor = new Doctor("DoctorName", "DoctorSurname", "DoctorSpeciality");
        doctor.setId(doctorId);
        Optional<Doctor> doctors = Optional.of(doctor);
        Mockito.when(doctorRepository.findById(doctorId)).thenReturn(doctors);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Patient id");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }


    @Test
    public void shouldReturnMedicationError(){
        Long doctorId = 12L;
        Long patientId = 15L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), patientId.toString(), "", "1");

        Doctor doctor = new Doctor("DoctorName", "DoctorSurname", "DoctorSpeciality");
        doctor.setId(doctorId);
        Optional<Doctor> doctors = Optional.of(doctor);
        Optional<Patient> patient = Optional.of(new Patient("Patient name", "PatientSurname", "121212-12342"));
        patient.get().setId(patientId);
        Mockito.when(patientRepository.findById(patientId)).thenReturn(patient);
        Mockito.when(doctorRepository.findById(doctorId)).thenReturn(doctors);

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Medication name");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnQuantityEmptyError(){
        Long doctorId = 12L;
        Long patientId = 15L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), patientId.toString(), "MedicationName", null);

        Doctor doctor = new Doctor("DoctorName", "DoctorSurname", "DoctorSpeciality");
        doctor.setId(doctorId);
        Optional<Doctor> doctors = Optional.of(doctor);
        Optional<Patient> patient = Optional.of(new Patient("Patient name", "PatientSurname", "121212-12342"));
        patient.get().setId(patientId);
        Mockito.when(patientRepository.findById(patientId)).thenReturn(patient);
        Mockito.when(doctorRepository.findById(doctorId)).thenReturn(doctors);

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Medication quantity");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnQuantityZeroError(){
        Long doctorId = 12L;
        Long patientId = 15L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), patientId.toString(), "MedicationName", "0");

        Doctor doctor = new Doctor("DoctorName", "DoctorSurname", "DoctorSpeciality");
        doctor.setId(doctorId);
        Optional<Doctor> doctors = Optional.of(doctor);
        Optional<Patient> patient = Optional.of(new Patient("Patient name", "PatientSurname", "121212-12342"));
        patient.get().setId(patientId);
        Mockito.when(patientRepository.findById(patientId)).thenReturn(patient);
        Mockito.when(doctorRepository.findById(doctorId)).thenReturn(doctors);

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Medication quantity");
        assertEquals(errors.get(0).getMessage(), "must be greater than 0!");
    }

    @Test
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

    @Test
    public void shouldReturnPatientExistenceError(){
        Long doctorId = 12L;
        Long patientId = 15L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), patientId.toString(), "MedicationName", "1");

        Doctor doctor = new Doctor("DoctorName", "DoctorSurname", "DoctorSpeciality");
        doctor.setId(doctorId);
        Optional<Doctor> doctors = Optional.of(doctor);
        Mockito.when(doctorRepository.findById(doctorId)).thenReturn(doctors);

        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Patient");
        assertEquals(errors.get(0).getMessage(), "does not exist!");
    }

    @Test
    public void shouldReturnNoError(){
        Long doctorId = 12L;
        Long patientId = 15L;
        AddPrescriptionRequest request = new AddPrescriptionRequest(doctorId.toString(), patientId.toString(), "MedicationName", "1");

        Doctor doctor = new Doctor("DoctorName", "DoctorSurname", "DoctorSpeciality");
        doctor.setId(doctorId);
        Optional<Doctor> doctors = Optional.of(doctor);
        Optional<Patient> patient = Optional.of(new Patient("Patient name", "PatientSurname", "121212-12342"));
        patient.get().setId(patientId);
        Mockito.when(patientRepository.findById(patientId)).thenReturn(patient);
        Mockito.when(doctorRepository.findById(doctorId)).thenReturn(doctors);

        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }
}
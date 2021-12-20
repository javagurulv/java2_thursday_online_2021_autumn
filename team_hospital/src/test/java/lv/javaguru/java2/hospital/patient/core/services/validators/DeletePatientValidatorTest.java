package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.database.jpa.JpaPrescriptionRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaVisitRepository;
import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.checkers.PatientLongNumChecker;
import lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.PatientExistenceByIDValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DeletePatientValidatorTest {

    @Mock private JpaPrescriptionRepository prescriptionRepository;
    @Mock private JpaVisitRepository visitRepository;
    @Mock private PatientLongNumChecker longNumChecker;
    @Mock private PatientExistenceByIDValidator existenceValidator;
    @InjectMocks private DeletePatientValidator validator;

    @Test
    public void shouldReturnEmptyList(){
        DeletePatientRequest request =
                new DeletePatientRequest("123");
        Mockito.when(existenceValidator.existenceByID(request.getIdRequest())).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnPatientExistenceError(){
        DeletePatientRequest request =
                new DeletePatientRequest("123");
        Mockito.when(existenceValidator.existenceByID(request.getIdRequest()))
                .thenReturn(Optional.of(new CoreError("Patient", "does not exist!")));
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.get(0).getField(), "Patient");
        assertEquals(errors.get(0).getDescription(), "does not exist!");
    }

    @Test
    public void shouldReturnPatientNumCheckError(){
        DeletePatientRequest request =
                new DeletePatientRequest("qwe");
        Mockito.when(longNumChecker.validate(request.getIdRequest(), "ID"))
                .thenReturn(Optional.of(new CoreError("ID", "must be a number!")));
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "ID");
        assertEquals(errors.get(0).getDescription(), "must be a number!");
    }

    @Test
    public void shouldReturnIDError(){
        DeletePatientRequest request = new DeletePatientRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "ID");
        assertEquals(errors.get(0).getDescription(), "must not be empty!");
    }
    @Test
    public void shouldReturnPatientExistenceInVisitsError(){
        Patient patient = new Patient("name", "surname", "12345678901");
        patient.setId(1L);
        Doctor doctor = new Doctor("name", "surname", "speciality");
        doctor.setId(1L);
        LocalDateTime dateTime = LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").parse("2023-12-12 12:00"));
        List<Visit> visits = new ArrayList<>();
        visits.add(new Visit(doctor, patient, dateTime));
        visits.get(0).setVisitID(1L);

        Mockito.when(prescriptionRepository.findByPatientId(patient.getId())).thenReturn(new ArrayList<>());
        Mockito.when(visitRepository.findByPatientId(patient.getId())).thenReturn(visits);

        DeletePatientRequest request = new DeletePatientRequest("1");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Patient");
        assertEquals(errors.get(0).getDescription(), "is in visits list, can`t delete him!");
    }

    @Test
    public void shouldReturnPatientExistenceInPrescriptionsError(){
        Patient patient = new Patient("name", "surname", "12345678901");
        patient.setId(1L);
        Doctor doctor = new Doctor("name", "surname", "speciality");
        doctor.setId(1L);
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(new Prescription(doctor, patient, "medication_name", 10));
        prescriptions.get(0).setId(1L);

        Mockito.when(prescriptionRepository.findByPatientId(patient.getId())).thenReturn(prescriptions);
        Mockito.when(visitRepository.findByPatientId(patient.getId())).thenReturn(new ArrayList<>());

        DeletePatientRequest request = new DeletePatientRequest("1");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Patient");
        assertEquals(errors.get(0).getDescription(), "is in prescriptions list, can`t delete him!");
    }
}
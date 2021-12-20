package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaDoctorRepository;
import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.visit.core.checkers.VisitLongNumChecker;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.date_validator.DateValidatorExecution;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators.VisitExistenceForAdding;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AddVisitValidatorTest {

    @Mock private JpaPatientRepository patientRepository;
    @Mock private JpaDoctorRepository doctorRepository;
    @Mock private DateValidatorExecution dateValidator;
    @Mock private VisitLongNumChecker longNumChecker;
    @Mock private VisitExistenceForAdding visitExistenceForAdding;
    @InjectMocks AddVisitValidator addVisitValidator;

    @Test
    public void shouldReturnPatientIDError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "2",
                "",
                "12-12-21 12:00");

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Patient ID");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");

        Mockito.verifyNoInteractions(patientRepository);
        Mockito.verifyNoInteractions(doctorRepository);
    }


    @Test
    public void shouldReturnPatientIDParseError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "qwe",
                "2",
                "12-12-21 12:00");

       Mockito.when(longNumChecker.validate(addVisitRequest.getPatientID(), "Patient ID"))
               .thenReturn(Optional.of(new CoreError("Patient ID", "must be a number!")));

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Patient ID");
        assertEquals(errorList.get(0).getDescription(), "must be a number!");

        Mockito.verifyNoInteractions(patientRepository);
        Mockito.verifyNoInteractions(doctorRepository);
    }

    @Test
    public void shouldDoctorIDError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "",
                "1",
                "12-12-21 12:00");

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Doctor ID");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");

        Mockito.verifyNoInteractions(patientRepository);
        Mockito.verifyNoInteractions(doctorRepository);
    }

    @Test
    public void shouldReturnDoctorIDParseError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1",
                "qwe",
                "12-12-21 12:00");

        Mockito.when(longNumChecker.validate(addVisitRequest.getPatientID(), "Patient ID"))
                .thenReturn(Optional.empty());
        Mockito.when(longNumChecker.validate(addVisitRequest.getDoctorsID(), "Doctor ID"))
                .thenReturn(Optional.of(new CoreError("Doctor ID", "must be a number!")));

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Doctor ID");
        assertEquals(errorList.get(0).getDescription(), "must be a number!");

        Mockito.verifyNoInteractions(patientRepository);
        Mockito.verifyNoInteractions(doctorRepository);
    }

    @Test
    public void shouldReturnDateError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1",
                "2",
                "");

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertEquals(errorList.size(), 1);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.get(0).getField(), "Visit date");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");

        Mockito.verifyNoInteractions(patientRepository);
        Mockito.verifyNoInteractions(doctorRepository);
    }

    @Test
    public void shouldReturnPatientDoesNotExistError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1",
                "2",
                "12-12-2021 12:00");

        Optional<Doctor> doctors = Optional.of(new Doctor("name", "surname", "speciality"));

        Mockito.when(patientRepository.findById(Long.valueOf(addVisitRequest.getPatientID())))
                .thenReturn(Optional.empty());
        Mockito.when(doctorRepository.findById(Long.valueOf(addVisitRequest.getDoctorsID())))
                .thenReturn(doctors);

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Patient");
        assertEquals(errorList.get(0).getDescription(), "does not exist!");
    }

    @Test
    public void shouldReturnDoctorDoesNotExistError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1",
                "2",
                "12-12-2021 12:00");

        Optional<Patient> patient = Optional.of(new Patient("name", "surname", "1234"));

        Mockito.when(patientRepository.findById(Long.valueOf(addVisitRequest.getPatientID())))
                .thenReturn(patient);
        Mockito.when(doctorRepository.findById(Long.valueOf(addVisitRequest.getDoctorsID())))
                .thenReturn(Optional.empty());

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Doctor");
        assertEquals(errorList.get(0).getDescription(), "does not exist!");
    }

    @Test
    public void shouldReturnDateParseError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1",
                "2",
                "121220211200");

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "input is incorrect!"));

        Mockito.when(dateValidator.validate(addVisitRequest.getVisitDate()))
                .thenReturn(errors);

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Date");
        assertEquals(errorList.get(0).getDescription(), "input is incorrect!");

        Mockito.verifyNoInteractions(patientRepository);
        Mockito.verifyNoInteractions(doctorRepository);
    }

    @Test
    public void shouldReturnFutureDateError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1",
                "2",
                "12-9-2021 12:00");

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "is not in the future!"));

        Mockito.when(dateValidator.validate(addVisitRequest.getVisitDate()))
                .thenReturn(errors);

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Date");
        assertEquals(errorList.get(0).getDescription(), "is not in the future!");

        Mockito.verifyNoInteractions(patientRepository);
        Mockito.verifyNoInteractions(doctorRepository);
    }

    @Test
    public void shouldReturnWorkingDayDateError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1",
                "2",
                "5-1-2025 12:00");

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "is not working day!"));

        Mockito.when(dateValidator.validate(addVisitRequest.getVisitDate()))
                .thenReturn(errors);

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Date");
        assertEquals(errorList.get(0).getDescription(), "is not working day!");

        Mockito.verifyNoInteractions(patientRepository);
        Mockito.verifyNoInteractions(doctorRepository);
    }

    @Test
    public void shouldReturnWorkingHoursDateError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1",
                "2",
                "1-1-2025 21:00");

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "is not working hour!"));

        Mockito.when(dateValidator.validate(addVisitRequest.getVisitDate()))
                .thenReturn(errors);

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Date");
        assertEquals(errorList.get(0).getDescription(), "is not working hour!");

        Mockito.verifyNoInteractions(patientRepository);
        Mockito.verifyNoInteractions(doctorRepository);
    }

    @Test
    public void shouldReturn3DateError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1",
                "2",
                "02-10-2021 06:00");

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "is not in the future!"));
        errors.add(new CoreError("Date", "is not working day!"));
        errors.add(new CoreError("Date", "is not working hour!"));

        Mockito.when(dateValidator.validate(addVisitRequest.getVisitDate()))
                .thenReturn(errors);

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 3);
        assertEquals(errorList.get(0).getField(), "Date");
        assertEquals(errorList.get(0).getDescription(), "is not in the future!");
        assertEquals(errorList.get(1).getField(), "Date");
        assertEquals(errorList.get(1).getDescription(), "is not working day!");
        assertEquals(errorList.get(2).getField(), "Date");
        assertEquals(errorList.get(2).getDescription(), "is not working hour!");

        Mockito.verifyNoInteractions(patientRepository);
        Mockito.verifyNoInteractions(doctorRepository);
    }

    @Test
    public void shouldNotReturnErrors(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1",
                "2",
                "12-12-2021 12:00");

        Optional<Patient> patient = Optional.of(new Patient("name", "surname", "1234"));
        Optional<Doctor> doctor = Optional.of(new Doctor("name", "surname", "speciality"));

        Mockito.when(patientRepository.findById(Long.valueOf(addVisitRequest.getPatientID())))
                .thenReturn(patient);
        Mockito.when(doctorRepository.findById(Long.valueOf(addVisitRequest.getDoctorsID())))
                .thenReturn(doctor);

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertEquals(errorList.size(), 0);
        assertTrue(errorList.isEmpty());
    }

    @Test
    public void shouldReturnVisitExistError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1",
                "2",
                "2021-12-12 12:00");

        LocalDateTime dateTime = LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").parse("2021-12-12 12:00"));
        Optional<Patient> patient = Optional.of(new Patient("name", "surname", "1234"));
        patient.get().setId(1L);
        Optional<Doctor> doctors = Optional.of(new Doctor("name", "surname", "speciality"));
        doctors.get().setId(2L);

        Mockito.when(patientRepository.findById(Long.valueOf(addVisitRequest.getPatientID())))
                .thenReturn(patient);
        Mockito.when(doctorRepository.findById(Long.valueOf(addVisitRequest.getDoctorsID())))
                .thenReturn(doctors);
        Mockito.when(visitExistenceForAdding.validateExistenceForAdding(addVisitRequest))
                .thenReturn(Optional.of(new CoreError("Visit", "already exist!")));

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Visit");
        assertEquals(errorList.get(0).getDescription(), "already exist!");
    }
}
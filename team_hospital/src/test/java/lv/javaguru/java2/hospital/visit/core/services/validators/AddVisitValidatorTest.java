package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.visit.core.checkers.VisitLongNumChecker;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.date_validator.DateValidatorExecution;
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
class AddVisitValidatorTest {

    @Mock PatientDatabase patientDatabase;
    @Mock DoctorDatabase doctorDatabase;
    @Mock DateValidatorExecution dateValidator;
    @Mock VisitLongNumChecker longNumChecker;
    @InjectMocks AddVisitValidator addVisitValidator;

    @Test
    public void shouldReturnPatientIDError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "",
                "2",
                "12-12-21 12:00");

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Patient ID");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");

        Mockito.verifyNoInteractions(patientDatabase);
        Mockito.verifyNoInteractions(doctorDatabase);
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

        Mockito.verifyNoInteractions(patientDatabase);
        Mockito.verifyNoInteractions(doctorDatabase);
    }

    @Test
    public void shouldDoctorIDError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1",
                "",
                "12-12-21 12:00");

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertFalse(errorList.isEmpty());
        assertEquals(errorList.size(), 1);
        assertEquals(errorList.get(0).getField(), "Doctor ID");
        assertEquals(errorList.get(0).getDescription(), "must not be empty!");

        Mockito.verifyNoInteractions(patientDatabase);
        Mockito.verifyNoInteractions(doctorDatabase);
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

        Mockito.verifyNoInteractions(patientDatabase);
        Mockito.verifyNoInteractions(doctorDatabase);
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

        Mockito.verifyNoInteractions(patientDatabase);
        Mockito.verifyNoInteractions(doctorDatabase);
    }

    @Test
    public void shouldReturnPatientDoesNotExistError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1",
                "2",
                "12-12-2021 12:00");

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("name", "surname", "speciality"));

        Mockito.when(patientDatabase.findById(Long.valueOf(addVisitRequest.getPatientID())))
                .thenReturn(new ArrayList<>());
        Mockito.when(doctorDatabase.findById(Long.valueOf(addVisitRequest.getDoctorsID())))
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

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));

        Mockito.when(patientDatabase.findById(Long.valueOf(addVisitRequest.getPatientID())))
                .thenReturn(patients);
        Mockito.when(doctorDatabase.findById(Long.valueOf(addVisitRequest.getDoctorsID())))
                .thenReturn(new ArrayList<>());

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

        Mockito.verifyNoInteractions(patientDatabase);
        Mockito.verifyNoInteractions(doctorDatabase);
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

        Mockito.verifyNoInteractions(patientDatabase);
        Mockito.verifyNoInteractions(doctorDatabase);
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

        Mockito.verifyNoInteractions(patientDatabase);
        Mockito.verifyNoInteractions(doctorDatabase);
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

        Mockito.verifyNoInteractions(patientDatabase);
        Mockito.verifyNoInteractions(doctorDatabase);
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

        Mockito.verifyNoInteractions(patientDatabase);
        Mockito.verifyNoInteractions(doctorDatabase);
    }

    @Test
    public void shouldNotReturnErrors(){
        AddVisitRequest addVisitRequest = new AddVisitRequest(
                "1",
                "2",
                "12-12-2021 12:00");

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("name", "surname", "1234"));
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("name", "surname", "speciality"));

        Mockito.when(patientDatabase.findById(Long.valueOf(addVisitRequest.getPatientID())))
                .thenReturn(patients);
        Mockito.when(doctorDatabase.findById(Long.valueOf(addVisitRequest.getDoctorsID())))
                .thenReturn(doctors);

        List<CoreError> errorList = addVisitValidator.validate(addVisitRequest);
        assertEquals(errorList.size(), 0);
        assertTrue(errorList.isEmpty());
    }
}
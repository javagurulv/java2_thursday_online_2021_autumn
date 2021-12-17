package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.AddVisitValidator;
import lv.javaguru.java2.hospital.visit.matchers.VisitMatcher;
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
import static org.mockito.ArgumentMatchers.argThat;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AddVisitServiceTest {

    @Mock private PatientRepository patientRepository;
    @Mock private DoctorRepository doctorRepository;
    @Mock private AddVisitValidator validator;
    @Mock private VisitRepository visitRepository;
    @InjectMocks private AddVisitService service;

    @Test
    public void shouldReturnResponseWithPatientIDError() {
        AddVisitRequest request = new AddVisitRequest
                ("", "1","21-12-2021 15:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Patient ID", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Patient ID");
        assertEquals(response.getErrors().get(0).getDescription(), "must not be empty!");

        Mockito.verifyNoMoreInteractions(visitRepository);
    }

    @Test
    public void shouldReturnResponseWithPatientIDParseError() {
        AddVisitRequest request = new AddVisitRequest
                ("qwe", "1","21-12-2021 15:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Patient ID", "must be a number!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Patient ID");
        assertEquals(response.getErrors().get(0).getDescription(), "must be a number!");

        Mockito.verifyNoMoreInteractions(visitRepository);
    }

    @Test
    public void shouldReturnResponseWithDoctorIDError() {
        AddVisitRequest request = new AddVisitRequest
                ("1", "","21-12-2021 15:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Doctor ID", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Doctor ID");
        assertEquals(response.getErrors().get(0).getDescription(), "must not be empty!");

        Mockito.verifyNoMoreInteractions(visitRepository);
    }

    @Test
    public void shouldReturnResponseWithDoctorIDParseError() {
        AddVisitRequest request = new AddVisitRequest
                ("1", "qwe","21-12-2021 15:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Doctor ID", "must be a number!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Doctor ID");
        assertEquals(response.getErrors().get(0).getDescription(), "must be a number!");

        Mockito.verifyNoMoreInteractions(visitRepository);
    }

    @Test
    public void shouldReturnResponseWithDateInputError() {
        AddVisitRequest request = new AddVisitRequest
                ("1", "2","02.12.2025 15:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "input is incorrect!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Date");
        assertEquals(response.getErrors().get(0).getDescription(), "input is incorrect!");

        Mockito.verifyNoMoreInteractions(visitRepository);
    }

    @Test
    public void shouldReturnResponseWithDateTimeError() {
        AddVisitRequest request = new AddVisitRequest
                ("1", "2","02-12-2025 07:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Time in the date", "is not working hour!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Time in the date");
        assertEquals(response.getErrors().get(0).getDescription(), "is not working hour!");

        Mockito.verifyNoMoreInteractions(visitRepository);
    }

    @Test
    public void shouldReturnResponseWithDateDayError() {
        AddVisitRequest request = new AddVisitRequest
                ("1", "2","06-12-2025 12:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "is not working day!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Date");
        assertEquals(response.getErrors().get(0).getDescription(), "is not working day!");

        Mockito.verifyNoMoreInteractions(visitRepository);
    }

    @Test
    public void shouldReturnResponseWithDateDayIsNotInTheFutureError() {
        AddVisitRequest request = new AddVisitRequest
                ("1", "2","13-10-2021 12:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "is not in the future!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Date");
        assertEquals(response.getErrors().get(0).getDescription(), "is not in the future!");

        Mockito.verifyNoMoreInteractions(visitRepository);
    }

    @Test
    public void shouldReturnResponseWithTimeInTheIncorrectError() {
        AddVisitRequest request = new AddVisitRequest
                ("1", "2","02-12-2025 12:25");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Minutes", "is not hourly visit!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Minutes");
        assertEquals(response.getErrors().get(0).getDescription(), "is not hourly visit!");

        Mockito.verifyNoMoreInteractions(visitRepository);
    }

    @Test
    public void shouldReturnResponseWithDateExistenceError() {
        AddVisitRequest request = new AddVisitRequest
                ("1", "2", "02-12-2025 12:00");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Date", "already is busy!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Date");
        assertEquals(response.getErrors().get(0).getDescription(), "already is busy!");

        Mockito.verifyNoMoreInteractions(visitRepository);
    }

    @Test
    public void shouldAddVisitToDatabase() {
        Doctor doctor = new Doctor("DoctorsName", "DoctorsSurname", "Speciality");
        doctor.setId(1L);
        Optional<Patient> patient = Optional.of(new Patient("PatientsName", "PatientsSurname", "12345678901"));
        patient.get().setId(2L);
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);

        Mockito.when(doctorRepository.findById(1L))
                .thenReturn((doctors));
        Mockito.when(patientRepository.findById(2L))
                .thenReturn(patient);

        AddVisitRequest request = new AddVisitRequest(doctor.getId().toString(), patient.get().getId().toString(),
                "2021-12-21 15:00");
        AddVisitResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(visitRepository).recordVisit(argThat(
                new VisitMatcher(response.getPatientVisit().getDoctor(),
                        response.getPatientVisit().getPatient(),
                        response.getPatientVisit().getVisitDate())));
    }
}

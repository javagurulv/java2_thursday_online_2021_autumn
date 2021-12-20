package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.jpa.JpaVisitRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitEnum;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.responses.EditVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.validators.EditVisitValidator;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EditVisitServiceTest {

    @Mock
    private JpaVisitRepository database;
    @Mock
    private EditVisitValidator validator;
    @InjectMocks
    private EditVisitService service;

    @Test
    public void shouldReturnErrorWhenVisitIdNotProvided() {
        EditVisitRequest request = new EditVisitRequest(null, "DOCTOR_ID", "123");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ID", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "ID");
        assertEquals(response.getErrors().get(0).getDescription(), "must not be empty!");

        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnErrorWhenVisitIdNotNumber() {
        EditVisitRequest request = new EditVisitRequest("123", "DOCTOR_ID", "123");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ID", "must be a number!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "ID");
        assertEquals(response.getErrors().get(0).getDescription(), "must be a number!");

        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnErrorWhenChangesNotProvided() {
        EditVisitRequest request = new EditVisitRequest("1", "DOCTOR_ID", "");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Changes", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Changes");
        assertEquals(response.getErrors().get(0).getDescription(), "must not be empty!");

        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldChangeDoctor() {
        EditVisitRequest request = new EditVisitRequest("1", "DOCTOR_ID", "123");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        Doctor doctor1 = new Doctor("DoctorsName1", "DoctorsSurname1", "Speciality1");
        doctor1.setId(1L);
        Patient patient = new Patient("PatientsName", "PatientsSurname", "120254-12636");
        patient.setId(1L);

        List<Visit> visits = new ArrayList<>();
        LocalDateTime date = LocalDateTime.from(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").parse("21-12-2021 15:00"));
        visits.add(new Visit(doctor1, patient, date));

        EditVisitResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isVisitEdited());
    }

    @Test
    public void shouldChangePatient() {
        EditVisitRequest request = new EditVisitRequest("1", "PATIENT_ID", "123");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        Doctor doctor = new Doctor("DoctorsName", "DoctorsSurname", "Speciality");
        doctor.setId(1L);
        Patient patient1 = new Patient("PatientsName1", "PatientsSurname1", "120254-12636");
        patient1.setId(1L);

        List<Visit> visits = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse("21-12-2021 15:00", formatter);
        visits.add(new Visit(doctor, patient1, date));

        EditVisitResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isVisitEdited());
    }

    @Test
    public void shouldChangeVisitDate() {
        EditVisitRequest request = new EditVisitRequest("1", "DATE", "23-12-2021 15:00");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        Doctor doctor = new Doctor("DoctorsName", "DoctorsSurname", "Speciality");
        doctor.setId(1L);
        Patient patient = new Patient("PatientsName", "PatientsSurname", "150254-12636");
        patient.setId(1L);

        List<Visit> visits = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse("21-12-2021 15:00", formatter);
        visits.add(new Visit(doctor, patient, date));

        EditVisitResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isVisitEdited());
    }

    @Test
    public void shouldReturnErrorWhenEnumIncorrect() {
        EditVisitRequest request = new EditVisitRequest("1", "INPUT", "123");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Edit option", "must be DOCTOR_ID, PATIENT_ID, DATE OR DESCRIPTION!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Edit option");
        assertEquals(response.getErrors().get(0).getDescription(), "must be DOCTOR_ID, PATIENT_ID, DATE OR DESCRIPTION!");

        Mockito.verifyNoInteractions(database);
    }
}
package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.VisitDatabase;
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
    private VisitDatabase database;
    @Mock
    private EditVisitValidator validator;
    @InjectMocks
    private EditVisitService service;

    @Test
    public void shouldReturnErrorWhenVisitIdNotProvided() {
        EditVisitRequest request = new EditVisitRequest(null, "DOCTOR_ID", "NewDoctorId");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("id", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "id");
        assertEquals(response.getErrors().get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenChangesNotProvided() {
        EditVisitRequest request = new EditVisitRequest("1", "DOCTOR_ID", "");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("changes", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "changes");
        assertEquals(response.getErrors().get(0).getDescription(), "Must not be empty!");
    }

    @Test
    public void shouldChangeDoctor() {
        EditVisitRequest request = new EditVisitRequest("1", "DOCTOR_ID", "2");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        Doctor doctor1 = new Doctor("DoctorsName1", "DoctorsSurname1", "Speciality1");
        doctor1.setId(1L);
        Patient patient = new Patient("PatientsName", "PatientsSurname", "120254-12636");
        patient.setId(1L);

        List<Visit> visits = new ArrayList<>();
        LocalDateTime date = LocalDateTime.from(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").parse("21-12-2021 15:00"));
        visits.add(new Visit(doctor1, patient, date));

        Mockito.when(database.editVisit(1L, EditVisitEnum.DOCTOR_ID, "2")).thenReturn(true);
        EditVisitResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isVisitEdited());
    }

    @Test
    public void shouldChangePatient() {
        EditVisitRequest request = new EditVisitRequest("1", "PATIENT_ID", "2");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        Doctor doctor = new Doctor("DoctorsName", "DoctorsSurname", "Speciality");
        doctor.setId(1L);
        Patient patient1 = new Patient("PatientsName1", "PatientsSurname1", "120254-12636");
        patient1.setId(1L);

        List<Visit> visits = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse("21-12-2021 15:00", formatter);
        visits.add(new Visit(doctor, patient1, date));

        Mockito.when(database.editVisit(1L, EditVisitEnum.PATIENT_ID, "2")).thenReturn(true);
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

        Mockito.when(database.editVisit(1L, EditVisitEnum.DATE, "23-12-2021 15:00")).thenReturn(true);
        EditVisitResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isVisitEdited());
    }

    @Test
    public void shouldReturnErrorWhenEnumIncorrect() {
        EditVisitRequest request = new EditVisitRequest("1", "INPUT", "changes");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("edit option", "must be DOCTOR_ID, PATIENT_ID, DATE OR DESCRIPTION!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        EditVisitResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "edit option");
        assertEquals(response.getErrors().get(0).getDescription(), "must be DOCTOR_ID, PATIENT_ID, DATE OR DESCRIPTION!");
    }

}

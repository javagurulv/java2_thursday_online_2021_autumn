package lv.javaguru.java2.hospital.visit.core.services.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DoctorIdAndPatientIdSearchCriteriaTest {

    @Mock VisitDatabase database;
    @InjectMocks DoctorIdAndPatientIdSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue() {
        SearchVisitRequest request = new SearchVisitRequest(null, 12L, 15L, "");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchVisitRequest request = new SearchVisitRequest(null, null, 1L, null);
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectVisit() {
        Doctor doctor = new Doctor("DoctorsName", "DoctorsSurname", "Speciality");
        Patient patient = new Patient("PatientsName", "PatientsSurname", "150254-12636");
        Long doctorId = doctor.getId();
        Long patientId = patient.getId();

        List<Visit> visits = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse("11/12/2021 13:00", formatter);
        visits.add(new Visit(doctor, patient, date));

        Mockito.when(database.findByDoctorIdAndPatientId(doctorId, patientId)).thenReturn(visits);
        SearchVisitRequest request = new SearchVisitRequest
                (null, doctorId, patientId, "");
        Visit visit = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 1);
        assertEquals(visit.getDoctor(), doctor);
        assertEquals(visit.getPatient(), patient);
        assertEquals(visit.getVisitID(), visits.get(0).getVisitID());
        assertEquals(visit.getVisitDate(), date);
    }

    @Test
    public void shouldReturnCorrectVisits() throws ParseException {
        Doctor doctor = new Doctor("DoctorsName", "DoctorsSurname", "Speciality");
        Patient patient = new Patient("PatientsName", "PatientsSurname", "150254-12636");
        Long doctorId = doctor.getId();
        Long patientId = patient.getId();

        List<Visit> visits = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("07/12/2021 13:00", formatter);
        LocalDateTime date2 = LocalDateTime.parse("17/12/2021 11:00", formatter);
        visits.add(new Visit(doctor, patient, date1));
        visits.add(new Visit(doctor, patient, date2));

        Mockito.when(database.findByDoctorIdAndPatientId(doctorId, patientId)).thenReturn(visits);
        SearchVisitRequest request = new SearchVisitRequest
                (null, doctorId, patientId, "");
        Visit visit1 = searchCriteria.process(request).get(0);
        Visit visit2 = searchCriteria.process(request).get(1);
        assertEquals(searchCriteria.process(request).size(), 2);
        assertEquals(visit1.getDoctor(), doctor);
        assertEquals(visit1.getPatient(), patient);
        assertEquals(visit1.getVisitID(), visits.get(0).getVisitID());
        assertEquals(visit1.getVisitDate(), date1);
        assertEquals(visit2.getDoctor(), doctor);
        assertEquals(visit2.getPatient(), patient);
        assertEquals(visit2.getVisitID(), visits.get(1).getVisitID());
        assertEquals(visit2.getVisitDate(), date2);
    }
}
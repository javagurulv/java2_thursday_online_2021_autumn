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
        doctor.setId(1L);
        Patient patient = new Patient("PatientsName", "PatientsSurname", "150254-12636");
        patient.setId(2L);
        Long doctorId = doctor.getId();
        Long patientId = patient.getId();

        List<Visit> visits = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse("11-12-2021 13:00", formatter);
        visits.add(new Visit(doctor.getId(), patient.getId(), date));

        Mockito.when(database.findByDoctorIdAndPatientId(doctorId, patientId)).thenReturn(visits);
        SearchVisitRequest request = new SearchVisitRequest
                (null, doctorId, patientId, "");
        Visit visit = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 1);
        assertEquals(visit.getDoctorID(), doctor.getId());
        assertEquals(visit.getPatientID(), patient.getId());
        assertEquals(visit.getVisitID(), visits.get(0).getVisitID());
        assertEquals(visit.getVisitDate(), date);
    }

    @Test
    public void shouldReturnCorrectVisits() {
        Doctor doctor = new Doctor("DoctorsName", "DoctorsSurname", "Speciality");
        doctor.setId(1L);
        Patient patient = new Patient("PatientsName", "PatientsSurname", "150254-12636");
        patient.setId(2L);
        Long doctorId = doctor.getId();
        Long patientId = patient.getId();

        List<Visit> visits = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("07-12-2021 13:00", formatter);
        LocalDateTime date2 = LocalDateTime.parse("17-12-2021 11:00", formatter);
        visits.add(new Visit(doctor.getId(), patient.getId(), date1));
        visits.add(new Visit(doctor.getId(), patient.getId(), date2));

        Mockito.when(database.findByDoctorIdAndPatientId(doctorId, patientId)).thenReturn(visits);
        SearchVisitRequest request = new SearchVisitRequest
                (null, doctorId, patientId, "");
        Visit visit1 = searchCriteria.process(request).get(0);
        Visit visit2 = searchCriteria.process(request).get(1);
        assertEquals(searchCriteria.process(request).size(), 2);
        assertEquals(visit1.getDoctorID(), doctor.getId());
        assertEquals(visit1.getPatientID(), patient.getId());
        assertEquals(visit1.getVisitID(), visits.get(0).getVisitID());
        assertEquals(visit1.getVisitDate(), date1);
        assertEquals(visit2.getDoctorID(), doctor.getId());
        assertEquals(visit2.getPatientID(), patient.getId());
        assertEquals(visit2.getVisitID(), visits.get(1).getVisitID());
        assertEquals(visit2.getVisitDate(), date2);
    }
}
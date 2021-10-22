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
class DateSearchCriteriaTest {

    @Mock
    VisitDatabase database;
    @InjectMocks
    DateSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue() {
        SearchVisitRequest request = new SearchVisitRequest(null, null, null, "27/12/2021 15:00");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchVisitRequest request = new SearchVisitRequest(null, null, null, null);
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectVisit() {
        Doctor doctor = new Doctor("DoctorsName", "DoctorsSurname", "Speciality");
        Patient patient = new Patient("PatientsName", "PatientsSurname", "150254-12636");

        List<Visit> visits = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse("27/12/2021 15:00", formatter);
        visits.add(new Visit(doctor, patient, date));

        Mockito.when(database.findByDate(date)).thenReturn(visits);
        SearchVisitRequest request = new SearchVisitRequest
                (null, null, null, "27/12/2021 15:00");
        Visit visit = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 1);
        assertEquals(visit.getDoctor(), doctor);
        assertEquals(visit.getPatient(), patient);
        assertEquals(visit.getVisitID(), visits.get(0).getVisitID());
        assertEquals(visit.getVisitDate(), date);
    }

    @Test
    public void shouldReturnCorrectVisits() throws ParseException {
        Doctor doctor1 = new Doctor("DoctorsName1", "DoctorsSurname1", "Speciality1");
        Patient patient1 = new Patient("PatientsName1", "PatientsSurname1", "150254-12636");
        Doctor doctor2 = new Doctor("DoctorsName2", "DoctorsSurname2", "Speciality2");
        Patient patient2 = new Patient("PatientsName2", "PatientsSurname2", "150454-12336");

        List<Visit> visits = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse("27/12/2021 16:00", formatter);
        visits.add(new Visit(doctor1, patient1, date));
        visits.add(new Visit(doctor2, patient2, date));

        Mockito.when(database.findByDate(date)).thenReturn(visits);
        SearchVisitRequest request = new SearchVisitRequest
                (null, null, null, "27/12/2021 16:00");
        Visit visit1 = searchCriteria.process(request).get(0);
        Visit visit2 = searchCriteria.process(request).get(1);
        assertEquals(searchCriteria.process(request).size(), 2);
        assertEquals(visit1.getDoctor(), doctor1);
        assertEquals(visit1.getPatient(), patient1);
        assertEquals(visit1.getVisitID(), visits.get(0).getVisitID());
        assertEquals(visit1.getVisitDate(), date);
        assertEquals(visit2.getDoctor(), doctor2);
        assertEquals(visit2.getPatient(), patient2);
        assertEquals(visit2.getVisitID(), visits.get(1).getVisitID());
        assertEquals(visit2.getVisitDate(), date);
    }
}
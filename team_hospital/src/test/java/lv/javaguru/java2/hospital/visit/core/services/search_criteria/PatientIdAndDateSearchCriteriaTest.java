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
class PatientIdAndDateSearchCriteriaTest {

    @Mock VisitDatabase database;
    @InjectMocks PatientIdAndDateSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue() {
        SearchVisitRequest request = new SearchVisitRequest(null, null, 16L, "29/12/2021 15:00");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchVisitRequest request = new SearchVisitRequest(null, null, 2L, null);
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectVisit() throws ParseException {
        Doctor doctor = new Doctor("DoctorsName", "DoctorsSurname", "Speciality");
        doctor.setId(1L);
        Patient patient = new Patient("PatientsName", "PatientsSurname", "200254-12636");
        patient.setId(2L);
        Long patientId = patient.getId();

        List<Visit> visits = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse("05/12/2021 13:00", formatter);
        visits.add(new Visit(doctor.getId(), patient.getId(), date));

        Mockito.when(database.findByPatientIdAndDate(patientId, date)).thenReturn(visits);
        SearchVisitRequest request = new SearchVisitRequest
                (null, null, patientId, "05/12/2021 13:00");
        Visit visit = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 1);
        assertEquals(visit.getDoctorID(), doctor.getId());
        assertEquals(visit.getPatientID(), patient.getId());
        assertEquals(visit.getVisitID(), visits.get(0).getVisitID());
        assertEquals(visit.getVisitDate(), date);
    }
}
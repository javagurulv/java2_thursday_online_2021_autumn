package lv.javaguru.java2.hospital.visit.core.services.search_visit_service.search_criteria;

import lv.javaguru.java2.hospital.database.jpa.JpaVisitRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.services.date_converter.GetVisitDate;
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
class PatientIdDateSearchCriteriaTest {

    @Mock
    GetVisitDate getVisitDate;
    @Mock
    JpaVisitRepository database;
    @InjectMocks
    PatientIdDateSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue() {
        SearchVisitRequest request = new SearchVisitRequest(null, null, "16", "2021-12-29 15:00");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchVisitRequest request = new SearchVisitRequest(null, null, "2", null);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse("2021-12-05 13:00", formatter);
        visits.add(new Visit(doctor, patient, date));

        SearchVisitRequest request = new SearchVisitRequest
                (null, null, patientId.toString(), "2021-12-05 13:00");
        Mockito.when(database.findByPatientIdAndDate(patientId, date)).thenReturn(visits);
        Mockito.when(getVisitDate.getVisitDateFromString(request.getVisitDate()))
                .thenReturn(date);

        Visit visit = searchCriteria.process(request).get(0);
        assertEquals(searchCriteria.process(request).size(), 1);
        assertEquals(visit.getDoctor(), doctor);
        assertEquals(visit.getPatient(), patient);
        assertEquals(visit.getVisitID(), visits.get(0).getVisitID());
        assertEquals(visit.getVisitDate(), date);
    }
}

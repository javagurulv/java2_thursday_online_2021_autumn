package lv.javaguru.java2.hospital.visit.core.services.search_visit_service.search_criteria;

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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class VisitIDPatientIDDoctorIDDateSearchCriteriaTest {

    @Mock
    GetVisitDate getVisitDate;
    @Mock
    private VisitRepository database;
    @InjectMocks
    private VisitIDPatientIDDoctorIDDateSearchCriteria searchCriteria;


    @Test
    public void shouldReturnTrue() {
        String date = "2022-12-12 15:00";
        SearchVisitRequest request = new SearchVisitRequest("45", "2", "2", date);
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
        doctor.setId(1L);
        Patient patient = new Patient("PatientsName", "PatientsSurname", "150254-12636");
        patient.setId(1L);

        List<Visit> visits = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse("2021-12-27 15:00", formatter);
        visits.add(new Visit(doctor, patient, date));
        visits.get(0).setVisitID(1L);

        SearchVisitRequest request = new SearchVisitRequest
                (visits.get(0).getVisitID().toString(), visits.get(0).getDoctor().getId().toString(),
                        visits.get(0).getPatient().getId().toString(), "2021-12-27 15:00");
        Mockito.when(database.findByVisitIDDoctorIDPatientIDDate(visits.get(0).getVisitID(), visits.get(0).getDoctor().getId(),
                visits.get(0).getPatient().getId(), visits.get(0).getVisitDate())).thenReturn(visits);
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
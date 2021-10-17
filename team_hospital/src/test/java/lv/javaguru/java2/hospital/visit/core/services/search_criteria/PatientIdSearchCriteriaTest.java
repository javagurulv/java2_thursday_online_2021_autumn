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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PatientIdSearchCriteriaTest {

    @Mock
    VisitDatabase database;
    @InjectMocks
    PatientIdSearchCriteria searchCriteria;

    @Test
    public void shouldReturnTrue() {
        SearchVisitRequest request = new SearchVisitRequest(null, null, 5L, "");
        assertTrue(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchVisitRequest request = new SearchVisitRequest(5L, null, null, null);
        assertFalse(searchCriteria.canProcess(request));
    }

    @Test
    public void shouldReturnCorrectVisit() throws ParseException {
        Doctor doctor = new Doctor("DoctorsName", "DoctorsSurname", "Speciality");
        Patient patient = new Patient("PatientsName", "PatientsSurname", "250254-12636");
        Long patientId = patient.getId();

        List<Visit> visits = new ArrayList<>();
        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("07/12/2021 11:00");
        visits.add(new Visit(doctor, patient, date));

        Mockito.when(database.findByPatientId(patientId)).thenReturn(visits);
        SearchVisitRequest request = new SearchVisitRequest
                (null, null, patientId, "");
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
        Patient patient = new Patient("PatientsName", "PatientsSurname", "250254-12636");
        Long patientId = patient.getId();

        List<Visit> visits = new ArrayList<>();
        Date date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("07/12/2021 11:00");
        Date date2 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("11/12/2021 13:00");
        visits.add(new Visit(doctor, patient, date1));
        visits.add(new Visit(doctor, patient, date2));

        Mockito.when(database.findByPatientId(patientId)).thenReturn(visits);
        SearchVisitRequest request = new SearchVisitRequest
                (null, null, patientId, "");
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
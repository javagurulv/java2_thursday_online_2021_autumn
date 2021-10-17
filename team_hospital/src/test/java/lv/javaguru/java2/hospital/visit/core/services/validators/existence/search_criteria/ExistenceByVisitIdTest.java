package lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ExistenceByVisitIdTest {


    @Mock
    private VisitDatabaseImpl database;
    @InjectMocks
    private ExistenceByVisitId existence;

    @Test
    public void shouldReturnTrue() {
        SearchVisitRequest request = new SearchVisitRequest(211L, null,null, "");
        assertTrue(existence.canValidate(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchVisitRequest request = new SearchVisitRequest(null, null,null, "");
        assertFalse(existence.canValidate(request));
    }

    @Test
    public void shouldReturnVisitError() {
        SearchVisitRequest request = new SearchVisitRequest(425L, null,null, "");
        Optional<CoreError> error = existence.validateExistence(request);
        assertFalse(error.isEmpty());
        assertEquals(error.get().getField(),"Visit", "Does not exist!");
    }

    @Test
    public void shouldReturnEmptyList() throws ParseException {
        Doctor doctor = new Doctor("DoctorsName1", "DoctorsSurname1", "Speciality1");
        Patient patient = new Patient("PatientsName1", "PatientsSurname1", "150254-12636");
        List<Visit> visits = new ArrayList<>();
        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("27/12/2021 16:00");
        visits.add(new Visit(doctor, patient, date));
        Long visitId = visits.get(0).getVisitID();

        SearchVisitRequest request = new SearchVisitRequest(visitId, null, null, "");
        Mockito.when(database.showAllVisits()).thenReturn(visits);
        Optional<CoreError> error = existence.validateExistence(request);
        assertTrue(error.isEmpty());
    }

}
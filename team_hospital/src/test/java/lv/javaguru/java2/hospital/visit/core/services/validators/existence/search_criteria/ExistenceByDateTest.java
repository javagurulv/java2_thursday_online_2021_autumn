package lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabase;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ExistenceByDateTest {

    @Mock private  GetVisitDate getVisitDate;
    @Mock private VisitDatabase database;
    @InjectMocks private ExistenceByDate existence;

    @Test
    public void shouldReturnTrue() {
        SearchVisitRequest request = new SearchVisitRequest(null, null,null, "27-12-2021 16:00");
        assertTrue(existence.canValidate(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchVisitRequest request = new SearchVisitRequest(null, null,null, "");
        assertFalse(existence.canValidate(request));
    }

    @Test
    public void shouldReturnVisitError() {
        SearchVisitRequest request = new SearchVisitRequest(null, null,null, "27-12-2021 16:00");
        Optional<CoreError> error = existence.validateExistence(request);
        assertFalse(error.isEmpty());
        assertEquals(error.get().getField(),"Visit", "Does not exist!");
    }

    @Test
    public void shouldReturnEmptyList() {
        Doctor doctor = new Doctor("DoctorsName1", "DoctorsSurname1", "Speciality1");
        doctor.setId(1L);
        Patient patient = new Patient("PatientsName1", "PatientsSurname1", "150254-12636");
        patient.setId(2L);
        List<Visit> visits = new ArrayList<>();
        LocalDateTime date = LocalDateTime.from(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").parse("27-12-2021 16:00"));
        visits.add(new Visit(doctor, patient, date));

        SearchVisitRequest request = new SearchVisitRequest(null, null, null, "27-12-2021 16:00");
        LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").parse(request.getVisitDate()));
        Mockito.when(getVisitDate.getVisitDateFromString(request.getVisitDate())).thenReturn(localDateTime);
        Mockito.when(database.getAllVisits()).thenReturn(visits);
        Optional<CoreError> error = existence.validateExistence(request);
        System.out.println(error);
        assertTrue(error.isEmpty());
    }
}

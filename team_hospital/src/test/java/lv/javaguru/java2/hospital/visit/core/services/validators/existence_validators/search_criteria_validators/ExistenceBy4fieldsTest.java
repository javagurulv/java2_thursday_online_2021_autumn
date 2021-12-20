package lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators.search_criteria_validators;

import lv.javaguru.java2.hospital.database.jpa.JpaVisitRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ExistenceBy4fieldsTest {

    @Mock private GetVisitDate getVisitDate;
    @Mock private JpaVisitRepository repository;
    @InjectMocks private ExistenceBy4fields existenceBy4fields;

    @Test
    public void shouldReturnTrue() {
        SearchVisitRequest request = new SearchVisitRequest("12", "12", "12", "2023-12-12 12:00");
        assertTrue(existenceBy4fields.canValidate(request));
    }

    @Test
    public void shouldReturnFalse() {
        SearchVisitRequest request = new SearchVisitRequest("12", "", "12", "2023-12-12 12:00");
        assertFalse(existenceBy4fields.canValidate(request));
    }

    @Test
    public void shouldReturnList() {
        SearchVisitRequest request = new SearchVisitRequest("12", "12", "12", "2023-12-12 12:00");
        LocalDateTime date = LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").parse(request.getVisitDate()));

        List<Visit> visits = new ArrayList<>();
        visits.add(new Visit(new Doctor("name", "surname", "speciality"),
                new Patient("name", "surname", "12345678901"),date));
        visits.get(0).setVisitID(12L);
        visits.get(0).getDoctor().setId(12L);
        visits.get(0).getPatient().setId(12L);

        Mockito.when(getVisitDate.getVisitDateFromString(request.getVisitDate())).thenReturn(date);
        Mockito.when(repository.findAll()).thenReturn(visits);

        Optional<CoreError> error = existenceBy4fields.validateExistence(request);
        assertTrue(error.isEmpty());
    }

    @Test
    public void shouldReturnError() {
        SearchVisitRequest request = new SearchVisitRequest("12", "12", "12", "2023-12-12 12:00");
        Optional<CoreError> error = existenceBy4fields.validateExistence(request);
        assertFalse(error.isEmpty());
        assertEquals(error.get().getField(), "Visit");
        assertEquals(error.get().getDescription(), "does not exist!");
    }
}
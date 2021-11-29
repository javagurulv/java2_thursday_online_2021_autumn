package lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators;

import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
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
class VisitExistenceForAddingTest {

    @Mock private GetVisitDate getVisitDate;
    @Mock private VisitRepository visitRepository;
    @InjectMocks VisitExistenceForAdding visitExistenceForAdding;

    @Test
    public void shouldReturnError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest("1", "2", "2022-12-12 12:00");

        LocalDateTime date = LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                .parse(addVisitRequest.getVisitDate()));

        List<Visit> visits = new ArrayList<>();
        visits.add(new Visit(new Doctor("name", "surname", "speciality"),
                new Patient("name", "surname", "12345678901"), date));
        visits.get(0).getDoctor().setId(1L);
        visits.get(0).getPatient().setId(2L);

        Mockito.when(getVisitDate.getVisitDateFromString(addVisitRequest.getVisitDate())).thenReturn(date);
        Mockito.when(visitRepository.getAllVisits()).thenReturn(visits);

        Optional<CoreError> error = visitExistenceForAdding.validateExistenceForAdding(addVisitRequest);
        assertFalse(error.isEmpty());
        assertEquals(error.get().getField(), "Visit");
        assertEquals(error.get().getDescription(), "already exist!");
    }

    @Test
    public void shouldNotReturnError(){
        AddVisitRequest addVisitRequest = new AddVisitRequest("1", "2", "2022-12-12 12:00");

        Mockito.when(visitRepository.getAllVisits()).thenReturn(new ArrayList<>());

        Optional<CoreError> error = visitExistenceForAdding.validateExistenceForAdding(addVisitRequest);
        assertTrue(error.isEmpty());
    }
}
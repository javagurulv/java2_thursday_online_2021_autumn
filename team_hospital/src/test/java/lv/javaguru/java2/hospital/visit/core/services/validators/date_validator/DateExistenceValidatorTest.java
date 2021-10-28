package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria.GetVisitDate;
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
class DateExistenceValidatorTest {

    @Mock private VisitDatabase database;
    @Mock private GetVisitDate getVisitDate;
    @InjectMocks DateExistenceValidator validator;

    @Test
    public void ShouldReturnDateExistenceError(){
        List<Visit> visits = new ArrayList<>();
        Doctor doctor = new Doctor("name", "surname", "speciality");
        Patient patient = new Patient("name", "surname", "1234");
        String date = "25/12/2025 12:00";
        Visit visit = new Visit(doctor, patient, LocalDateTime.from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").parse(date)));
        visits.add(visit);

        Mockito.when(getVisitDate.getVisitDateFromString(date))
                .thenReturn(LocalDateTime.from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").parse(date)));
        Mockito.when(database.getAllVisits()).thenReturn(visits);

        Optional<CoreError> error = validator.validate(date);
        assertFalse(error.isEmpty());
        assertEquals(error.get().getField(), "Date");
        assertEquals(error.get().getDescription(), "already is busy!");
    }

    @Test
    public void ShouldReturnEmptyError(){
        List<Visit> visits = new ArrayList<>();
        Doctor doctor = new Doctor("name", "surname", "speciality");
        Patient patient = new Patient("name", "surname", "1234");
        String date = "25/12/2025 12:00";
        Visit visit = new Visit(doctor, patient, LocalDateTime.from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").parse(date)));
        visits.add(visit);

        Mockito.when(getVisitDate.getVisitDateFromString(date))
                .thenReturn(LocalDateTime.from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").parse("25/12/2025 13:00")));
        Mockito.when(database.getAllVisits()).thenReturn(visits);

        Optional<CoreError> error = validator.validate(date);
        assertTrue(error.isEmpty());
    }
}
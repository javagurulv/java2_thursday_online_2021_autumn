package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
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
public class DateValidatorExecutionTest {

    @Mock
    private VisitDatabase database;
    @Mock
    private GetVisitDate getVisitDate;
    @Mock
    private DateFormatValidator formatValidator;
    @InjectMocks
    private DateValidatorExecution validator;

    @Test
    public void shouldReturnEmptyList() {
        AddVisitRequest request = new AddVisitRequest(
                "1234",
                "name",
                "surname",
                "17/01/2022 15:00"
        );
        Mockito.when(formatValidator.validateFormat(request.getVisitDate())).thenReturn(Optional.empty());
        LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").parse(request.getVisitDate()));
        Mockito.when(getVisitDate.getVisitDateFromString(request.getVisitDate())).thenReturn(localDateTime);
        List<CoreError> errors = validator.validate(request.getVisitDate());
        assertTrue(errors.isEmpty());
    }

    @Test
    public void ShouldReturnFormatError() {
        AddVisitRequest request = new AddVisitRequest(
                "1234",
                "name",
                "surname",
                "17.01.2022 15:00"
        );
        Mockito.when(
                formatValidator.validateFormat(request.getVisitDate()))
                .thenReturn(Optional.of(new CoreError("Date", "input is incorrect!")));

        List<CoreError> errors = validator.validate(request.getVisitDate());
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Date");
        assertEquals(errors.get(0).getDescription(), "input is incorrect!");
    }

    @Test
    public void ShouldReturnFutureError() {
        AddVisitRequest request = new AddVisitRequest(
                "1234",
                "name",
                "surname",
                "17/01/2020 15:00"
        );
        LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").parse(request.getVisitDate()));
        Mockito.when(getVisitDate.getVisitDateFromString(request.getVisitDate())).thenReturn(localDateTime);
        List<CoreError> errors = validator.validate(request.getVisitDate());
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Date");
        assertEquals(errors.get(0).getDescription(), "is not in the future!");
    }

    @Test
    public void ShouldReturnWorkingDayError() {
        AddVisitRequest request = new AddVisitRequest(
                "1234",
                "name",
                "surname",
                "16/01/2022 15:00"
        );
        LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").parse(request.getVisitDate()));
        Mockito.when(getVisitDate.getVisitDateFromString(request.getVisitDate())).thenReturn(localDateTime);
        List<CoreError> errors = validator.validate(request.getVisitDate());
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Date");
        assertEquals(errors.get(0).getDescription(), "is not working day!");
    }

    @Test
    public void ShouldReturnWorkingHoursError() {
        AddVisitRequest request = new AddVisitRequest(
                "1234",
                "name",
                "surname",
                "17/01/2022 20:00"
        );
        LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").parse(request.getVisitDate()));
        Mockito.when(getVisitDate.getVisitDateFromString(request.getVisitDate())).thenReturn(localDateTime);
        List<CoreError> errors = validator.validate(request.getVisitDate());
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Time in the date");
        assertEquals(errors.get(0).getDescription(), "is not working hour!");
    }

    @Test
    public void ShouldReturnDateExistError() {
        List<Visit> visits = new ArrayList<>();
        Doctor doctor = new Doctor("name", "surname", "speciality");
        Patient patient = new Patient("name", "surname", "1234");
        String date = "25/12/2025 12:00";
        Visit visit = new Visit(doctor, patient, LocalDateTime
                .from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").parse(date)));
        visits.add(visit);

        AddVisitRequest request = new AddVisitRequest(
                "1234",
                "name",
                "surname",
                "25/12/2025 12:00"
        );

        Mockito.when(getVisitDate.getVisitDateFromString(request.getVisitDate()))
                .thenReturn(LocalDateTime.from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                        .parse(request.getVisitDate())));
        Mockito.when(database.getAllVisits()).thenReturn(visits);

        List<CoreError> errors = validator.validate(request.getVisitDate());
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Date");
        assertEquals(errors.get(0).getDescription(), "already is busy!");
    }
}
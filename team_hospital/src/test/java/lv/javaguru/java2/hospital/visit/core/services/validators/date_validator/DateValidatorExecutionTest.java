package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

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

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class DateValidatorExecutionTest {

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
        Mockito.when(getVisitDate.getVisitDateFromString(request.getVisitDate())).thenReturn(new Date(request.getVisitDate()));
        Mockito.when(formatValidator.validateFormat(request.getVisitDate())).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(request);
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
        List<CoreError> errors = validator.validate(request);
        System.out.println(errors);
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
                "18/01/2020 15:00"
        );
        Mockito.when(getVisitDate.getVisitDateFromString(request.getVisitDate())).thenReturn(new Date(request.getVisitDate()));
        List<CoreError> errors = validator.validate(request);
        System.out.println(errors);
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
        Mockito.when(getVisitDate.getVisitDateFromString(request.getVisitDate())).thenReturn(new Date(request.getVisitDate()));
        List<CoreError> errors = validator.validate(request);
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
        Mockito.when(getVisitDate.getVisitDateFromString(request.getVisitDate())).thenReturn(new Date(request.getVisitDate()));
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Date");
        assertEquals(errors.get(0).getDescription(), "is not working hour!");
    }
}
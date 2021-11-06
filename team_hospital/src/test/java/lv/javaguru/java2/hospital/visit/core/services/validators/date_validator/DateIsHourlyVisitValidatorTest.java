package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DateIsHourlyVisitValidatorTest {

    @Mock
    private GetVisitDate getVisitDate;
    @InjectMocks
    private DateIsHourlyVisitValidator validator;

    @Test
    public void shouldReturnMinutesError() {
        String date = "17-01-2022 15:15";
        LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").parse(date));
        Mockito.when(getVisitDate.getVisitDateFromString(date)).thenReturn(localDateTime);
        Optional<CoreError> error = validator.validate(date);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "Minutes");
        assertEquals(error.get().getDescription(), "is not hourly visit!");
    }

    @Test
    public void shouldReturnOptionalEmpty() {
        String date = "17-01-2022 15:00";
        LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").parse(date));
        Mockito.when(getVisitDate.getVisitDateFromString(date)).thenReturn(localDateTime);
        Optional<CoreError> error = validator.validate(date);
        assertTrue(error.isEmpty());
    }

}
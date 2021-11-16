package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DateTimeIsInWorkingHoursValidatorTest {

    @Mock private GetVisitDate getVisitDate;
    @InjectMocks private DateTimeIsInWorkingHoursValidator workingHoursValidator;

    @Test
    public void shouldReturnDateWorkingHoursError() {
        String date = "17-01-2022 20:00";
        LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").parse(date));
        Mockito.when(getVisitDate.getVisitDateFromString(date)).thenReturn(localDateTime);
        Optional<CoreError> error = workingHoursValidator.validate(date);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "Time in the date");
        assertEquals(error.get().getDescription(), "is not working hour!");
    }

}
package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DateValidatorTest {

    @InjectMocks
    DateValidator dateValidator;

    @Test
    public void shouldReturnEmptyList() {
        String date = "17/01/2022 15:00";
        Optional<CoreError> error = dateValidator.validate(date);
        assertTrue(error.isEmpty());
    }

    @Test
    public void shouldReturnDateFormatError() {
        String date = "17.01.2022 15:00";
        Optional<CoreError> error = dateValidator.validate(date);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "Date");
        assertEquals(error.get().getDescription(), "input is incorrect!");
    }

    @Test
    public void shouldReturnDateFutureError() {
        String date = "17/01/2020 15:00";
        Optional<CoreError> error = dateValidator.validate(date);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "Date");
        assertEquals(error.get().getDescription(), "is not in the future!");
    }

    @Test
    public void shouldReturnDateWorkingDayError() {
        String date = "16/01/2022 15:00";
        Optional<CoreError> error = dateValidator.validate(date);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "Date");
        assertEquals(error.get().getDescription(), "is not working day!");
    }

    @Test
    public void shouldReturnDateWorkingHoursError() {
        String date = "17/01/2022 20:00";
        Optional<CoreError> error = dateValidator.validate(date);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "Date");
        assertEquals(error.get().getDescription(), "is not working hour!");
    }
}
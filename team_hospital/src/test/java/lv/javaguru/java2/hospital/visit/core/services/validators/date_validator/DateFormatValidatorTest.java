package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateFormatValidatorTest {

    private final DateFormatValidator dateFormatValidator = new DateFormatValidator();

    @Test
    public void shouldReturnDateFormatError() {
        String date = "17.01.2022 15:00";
        Optional<CoreError> error = dateFormatValidator.validateFormat(date);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "Date");
        assertEquals(error.get().getDescription(), "input is incorrect!");
    }

    @Test
    public void shouldReturnOptionalEmpty() {
        String date = "17/01/2022 15:00";
        Optional<CoreError> error = dateFormatValidator.validateFormat(date);
        assertTrue(error.isEmpty());
    }
}
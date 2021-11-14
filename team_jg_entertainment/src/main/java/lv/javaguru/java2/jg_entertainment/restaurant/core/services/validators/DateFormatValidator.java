package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Component
public class DateFormatValidator {
    public Optional<CoreError> validateFormat(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime.parse(date, formatter);
            return Optional.empty();
        } catch (DateTimeParseException exception) {
            return Optional.of(new CoreError("Date", "is not correct!"));
        }
    }
}

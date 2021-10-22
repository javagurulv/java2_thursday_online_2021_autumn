package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Component
public class DateFormatValidator {

    public Optional<CoreError> validateFormat(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime.parse(date, formatter);
            return Optional.empty();
        } catch (DateTimeParseException e) {
            return Optional.of(new CoreError("Date", "input is incorrect!"));
        }
    }
}

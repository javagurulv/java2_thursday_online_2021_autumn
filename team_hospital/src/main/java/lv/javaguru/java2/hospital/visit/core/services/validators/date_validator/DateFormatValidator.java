package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Component
public class DateFormatValidator {

    public Optional<CoreError> validateFormat(String date) {
        try {
            new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
            return Optional.empty();
        } catch (ParseException e) {
            return Optional.of(new CoreError("Date", "input is incorrect!"));
        }
    }
}

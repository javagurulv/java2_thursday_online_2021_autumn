package lv.javaguru.java2.hospital.patient.core.services.checkers;

import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PatientLongNumChecker {

    public Optional<CoreError> validate(String input, String field) {
        try {
            Long.parseLong(input);
            return Optional.empty();
        } catch (NumberFormatException e) {
            return Optional.of(new CoreError(field, "must be a number!"));
        }
    }
}

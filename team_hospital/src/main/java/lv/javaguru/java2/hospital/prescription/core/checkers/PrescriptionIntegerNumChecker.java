package lv.javaguru.java2.hospital.prescription.core.checkers;

import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PrescriptionIntegerNumChecker {

    public Optional<CoreError> validate(String input, String field) {

        try {
            Integer.parseInt(input);
            return Optional.empty();
        } catch (NumberFormatException e) {
            return Optional.of(new CoreError(field, "must be a number!"));
        }
    }
}

package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IntegerNumChecker {

    public Optional<CoreError> validate(String input) {

        try {
            Integer.parseInt(input);
            return Optional.empty();
        } catch (NumberFormatException e) {
            return Optional.of(new CoreError("ID", "must be a number!"));
        }
    }
}

package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LongNumChecker {

    public Optional<CoreError> validate(String input) {

        try {
            Long.parseLong(input);
            return Optional.empty();
        } catch (NumberFormatException e) {
            return Optional.of(new CoreError("Prescription ID", "must be a number!"));
        }
    }
}

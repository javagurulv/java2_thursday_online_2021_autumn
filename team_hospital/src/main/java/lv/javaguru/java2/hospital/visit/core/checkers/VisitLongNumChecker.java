package lv.javaguru.java2.hospital.visit.core.checkers;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VisitLongNumChecker {

    public Optional<CoreError> validate(String input, String field) {

        try {
            Long.parseLong(input);
            return Optional.empty();
        } catch (NumberFormatException e) {
            return Optional.of(new CoreError(field, "must be a number!"));
        }
    }
}

package lv.javaguru.java2.hospital.patient.core.services.checkers;

import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonalCodeChecker {

    public Optional<CoreError> execute(String input) {
        for (String s : input.split("")) {
            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return Optional.of(new CoreError("Personal code", "must consist from numbers only!"));
            }
        }
        return Optional.empty();
    }
}

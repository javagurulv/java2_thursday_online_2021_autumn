package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitEnum;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class VisitEnumChecker {

    public Optional<CoreError> validateEnum(String input) {

        try {
            EditVisitEnum.valueOf(input.toUpperCase(Locale.ROOT));
            return Optional.empty();
        } catch (IllegalArgumentException e) {
            return Optional.of(new CoreError("Edit option", "must be DOCTOR_ID, PATIENT_ID, DATE OR DESCRIPTION!"));
        }
    }
}
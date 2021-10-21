package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class PatientEnumChecker {

    public Optional<CoreError> validateEnum(String input) {

        try {
            EditPatientEnum.valueOf(input.toUpperCase(Locale.ROOT));
            return Optional.empty();
        } catch (IllegalArgumentException e) {
            return Optional.of(new CoreError("User choice", "must be NAME, SURNAME OR PERSONAL_CODE"));
        }
    }
}
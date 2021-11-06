package lv.javaguru.java2.hospital.doctor.core.services.validators;

import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorEnum;
import lv.javaguru.java2.hospital.doctor.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class DoctorEnumChecker {
    public Optional<CoreError> validateEnum(String input) {
        try {
            EditDoctorEnum.valueOf(input.toUpperCase(Locale.ROOT));
            return Optional.empty();
        } catch (IllegalArgumentException e) {
            return Optional.of(new CoreError("User choice", "Must contain 'NAME', 'SURNAME' or 'SPECIALITY' only!"));
        }
    }
}

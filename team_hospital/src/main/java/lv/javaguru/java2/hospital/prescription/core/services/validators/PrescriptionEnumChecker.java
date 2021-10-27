package lv.javaguru.java2.hospital.prescription.core.services.validators;

import lv.javaguru.java2.hospital.prescription.core.requests.EditPrescriptionEnum;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PrescriptionEnumChecker {

    public Optional<CoreError> validate(String input) {

        try {
            EditPrescriptionEnum.valueOf(input);
            return Optional.empty();
        } catch (IllegalArgumentException e) {
            return Optional.of(new CoreError("User choice", "must be PATIENT, MEDICATION_NAME or QUANTITY!"));
        }
    }
}

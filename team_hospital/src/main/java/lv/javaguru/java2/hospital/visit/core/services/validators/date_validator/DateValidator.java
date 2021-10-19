package lv.javaguru.java2.hospital.visit.core.services.validators.date_validator;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;

import java.util.Optional;

public interface DateValidator {
    Optional<CoreError> validate(String date);
}

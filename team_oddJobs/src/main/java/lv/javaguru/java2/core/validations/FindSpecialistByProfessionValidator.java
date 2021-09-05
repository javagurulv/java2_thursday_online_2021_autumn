package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Find.FindSpecialistByProfessionRequest;
import lv.javaguru.java2.core.responce.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindSpecialistByProfessionValidator {

    public List<CoreError> validate(FindSpecialistByProfessionRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateProfession(request).ifPresent(errors::add);
        validateProfessionNotContainsNumbers(request).ifPresent(errors::add);
        return errors;

    }

    private Optional<CoreError> validateProfession(FindSpecialistByProfessionRequest request) {
        return (request.getProfession() == null || request.getProfession().isEmpty())
                ? Optional.of(new CoreError("Profession", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProfessionNotContainsNumbers(FindSpecialistByProfessionRequest request) {
        return (request.getProfession().matches("[0-9]+"))
                ? Optional.of(new CoreError("Profession", "This field doesn't contain numbers!"))
                : Optional.empty();
    }

}

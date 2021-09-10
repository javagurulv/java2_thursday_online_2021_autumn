package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Find.FindSpecialistRequest;
import lv.javaguru.java2.core.responce.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindSpecialistValidator {

    public List<CoreError> validate(FindSpecialistRequest request) {

        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        return errors;

    }


    private Optional<CoreError> validateId(FindSpecialistRequest request) {
        return (request.getSpecialistId() == null)
                ? Optional.of(new CoreError("ID", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateName(FindSpecialistRequest request) {
        return (request.getSpecialistName() == null || request.getSpecialistName().isEmpty())
                ? Optional.of(new CoreError("NAME", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSurname(FindSpecialistRequest request) {
        return (request.getSpecialistSurname() == null || request.getSpecialistSurname().isEmpty())
                ? Optional.of(new CoreError("SURNAME", "Must not be empty!"))
                : Optional.empty();
    }
}

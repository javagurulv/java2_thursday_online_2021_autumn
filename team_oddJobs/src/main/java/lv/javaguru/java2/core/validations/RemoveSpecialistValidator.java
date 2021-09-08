package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Remove.RemoveSpecialistRequest;
import lv.javaguru.java2.core.responce.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveSpecialistValidator {
    public List<CoreError> validate(RemoveSpecialistRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }


    private Optional<CoreError> validateId(RemoveSpecialistRequest request) {
        return (request.getSpecialistId()==null)
                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }
}

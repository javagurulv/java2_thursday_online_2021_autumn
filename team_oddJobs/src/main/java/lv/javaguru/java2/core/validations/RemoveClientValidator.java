package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Remove.RemoveClientRequest;
import lv.javaguru.java2.core.responce.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveClientValidator {

    public List<CoreError> validate(RemoveClientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        return errors;
    }


    private Optional<CoreError> validateId(RemoveClientRequest request) {
        return (request.getClientId() == null)
                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateName(RemoveClientRequest request) {
        return (request.getClientName() == null)
                ? Optional.of(new CoreError("Name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSurname(RemoveClientRequest request) {
        return (request.getClientSurname() == null)
                ? Optional.of(new CoreError("Surname", "Must not be empty!"))
                : Optional.empty();
    }


}

package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Find.FindClientsRequest;
import lv.javaguru.java2.core.responce.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindClientsValidator {

    public List<CoreError> validate(FindClientsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        return errors;
    }


    private Optional<CoreError> validateId(FindClientsRequest request) {
        return (request.getClientId() == null)
                ? Optional.of(new CoreError("ID", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateName(FindClientsRequest request) {
        return (request.getClientName() == null)
                ? Optional.of(new CoreError("NAME", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSurname(FindClientsRequest request) {
        return (request.getClientSurname() == null)
                ? Optional.of(new CoreError("SURNAME", "Must not be empty!"))
                : Optional.empty();
    }

}

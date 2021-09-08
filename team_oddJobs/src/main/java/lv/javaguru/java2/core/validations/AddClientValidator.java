package lv.javaguru.java2.core.validations;
import lv.javaguru.java2.core.requests.Add.AddClientRequest;
import lv.javaguru.java2.core.responce.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddClientValidator {
    public List<CoreError> validate(AddClientRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        return errors;

    }
    private Optional<CoreError> validateName(AddClientRequest request) {
        return (request.getName() == null || request.getName().isEmpty())
                ? Optional.of(new CoreError("Name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSurname(AddClientRequest request) {
        return (request.getSurname() == null || request.getSurname().isEmpty())
                ? Optional.of(new CoreError("Surname", "Must not be empty!"))
                : Optional.empty();
    }
}

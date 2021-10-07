package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Remove.RemoveAdvertismentRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@DIComponent
public class RemoveAdvertismentValidator {

    public List<CoreError> validate(RemoveAdvertismentRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateTitle(request).ifPresent(errors::add);
        validateId(request).ifPresent(errors::add);
        return errors;

    }
    private Optional<CoreError> validateTitle(RemoveAdvertismentRequest request) {
        return (request.getAdvTitle() == null)
                ? Optional.of(new CoreError("Title", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateId(RemoveAdvertismentRequest request) {
        return (request.getAdvId() == null)

                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }

}

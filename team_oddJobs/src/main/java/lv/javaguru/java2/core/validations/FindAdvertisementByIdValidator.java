package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Find.FindAdvertisementByIdRequest;
import lv.javaguru.java2.core.responce.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FindAdvertisementByIdValidator {

    public List<CoreError> validate(FindAdvertisementByIdRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(FindAdvertisementByIdRequest request) {
        return (request.getAdvId() == 0)
                ? Optional.of(new CoreError("ID", "Must not be empty!"))
                : Optional.empty();
    }
}

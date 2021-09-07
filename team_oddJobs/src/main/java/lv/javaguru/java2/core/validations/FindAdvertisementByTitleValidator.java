package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Find.FindAdvertisementByTitleRequest;
import lv.javaguru.java2.core.responce.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindAdvertisementByTitleValidator {

    public List<CoreError> validate(FindAdvertisementByTitleRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateTitle(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateTitle(FindAdvertisementByTitleRequest request) {
        return (request.getTitle() == null || request.getTitle().isEmpty())
                ? Optional.of(new CoreError("Title", "Must not be empty!"))
                : Optional.empty();
    }
}

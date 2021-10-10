package lv.javaguru.java2.oddJobs.core.validations;

import lv.javaguru.java2.oddJobs.core.requests.find.FindAdvertisementByTitleRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
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

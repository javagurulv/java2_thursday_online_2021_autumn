package lv.javaguru.java2.oddJobs.core.validations.update;

import lv.javaguru.java2.oddJobs.core.requests.update.UpdateAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateAdvertisementRequestValidator {

    public List<CoreError> validate(UpdateAdvertisementRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateAdvTitle(request).ifPresent(errors::add);
        validateAdvDescription(request).ifPresent(errors::add);

        return errors;
    }
    private Optional<CoreError> validateAdvTitle(UpdateAdvertisementRequest request) {
        return (request.getNewAdvTitle() == null || request.getNewAdvTitle().isEmpty())
                ? Optional.of(new CoreError("newAdvTitle", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<CoreError> validateAdvDescription(UpdateAdvertisementRequest request) {
        return (request.getNewAdvDescription() == null || request.getNewAdvDescription().isEmpty())
                ? Optional.of(new CoreError("newAdvDescription", "Must not be empty!"))
                : Optional.empty();
    }
}

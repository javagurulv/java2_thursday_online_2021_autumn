package lv.javaguru.java2.oddJobs.core.validations.remove;

import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveAdvertismentRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class RemoveAdvertisementValidator {

    public List<CoreError> validate(RemoveAdvertismentRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateTitle(request).ifPresent(errors::add);
        validateId(request).ifPresent(errors::add);
        return errors;

    }

    private Optional<CoreError> validateId(RemoveAdvertismentRequest request) {
        return (request.getAdvId() == null)

                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTitle(RemoveAdvertismentRequest request) {
        return (request.getAdvTitle() == null)
                ? Optional.of(new CoreError("Title", "Must not be empty!"))
                : Optional.empty();
    }



}

package lv.javaguru.java2.oddJobs.core.validations.remove;

import lv.javaguru.java2.oddJobs.core.requests.remove.DeleteAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.requests.remove.DeleteClientRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeleteAdvertisementValidator {

    public List<CoreError> validate(DeleteAdvertisementRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(DeleteAdvertisementRequest request) {
        return (request.getId() == null)
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }
}

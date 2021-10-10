package lv.javaguru.java2.oddJobs.core.validations;

import lv.javaguru.java2.oddJobs.core.requests.find.FindAdvertisementByIdRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
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

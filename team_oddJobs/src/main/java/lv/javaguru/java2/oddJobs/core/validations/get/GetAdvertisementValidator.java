package lv.javaguru.java2.oddJobs.core.validations.get;

import lv.javaguru.java2.oddJobs.core.requests.get.GetAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetAdvertisementValidator {

    public List<CoreError> validate(GetAdvertisementRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(GetAdvertisementRequest request) {
        return (request.getId() == null)
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }
}

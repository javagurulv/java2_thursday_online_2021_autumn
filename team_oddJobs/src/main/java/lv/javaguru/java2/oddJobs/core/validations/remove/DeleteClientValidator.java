package lv.javaguru.java2.oddJobs.core.validations.remove;

import lv.javaguru.java2.oddJobs.core.requests.remove.DeleteClientRequest;
import lv.javaguru.java2.oddJobs.core.requests.remove.DeleteSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteClientValidator {

    public List<CoreError> validate(DeleteClientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(DeleteClientRequest request) {
        return (request.getId() == null)
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }
}

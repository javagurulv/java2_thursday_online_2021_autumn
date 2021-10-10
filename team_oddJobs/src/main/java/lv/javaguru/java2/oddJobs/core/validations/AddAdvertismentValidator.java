package lv.javaguru.java2.oddJobs.core.validations;
import lv.javaguru.java2.oddJobs.core.requests.add.AddAdvertismentRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class AddAdvertismentValidator {
    public List<CoreError> validate(AddAdvertismentRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateTitle(request).ifPresent(errors::add);
        return errors;

    }
    private Optional<CoreError> validateTitle(AddAdvertismentRequest request) {
        return (request.getAdvTitle() == null || request.getAdvTitle().isEmpty())
                ? Optional.of(new CoreError("Title", "Must not be empty!"))
                : Optional.empty();
    }


    }



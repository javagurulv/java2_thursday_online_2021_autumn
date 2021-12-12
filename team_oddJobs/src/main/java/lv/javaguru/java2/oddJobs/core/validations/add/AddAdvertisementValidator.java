package lv.javaguru.java2.oddJobs.core.validations.add;
import lv.javaguru.java2.oddJobs.core.requests.add.AddAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class AddAdvertisementValidator {
    public List<CoreError> validate(AddAdvertisementRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateTitle(request).ifPresent(errors::add);
        return errors;

    }
    private Optional<CoreError> validateTitle(AddAdvertisementRequest request) {
        return (request.getAdvTitle() == null || request.getAdvTitle().isEmpty())
                ? Optional.of(new CoreError("Title", "Must not be empty!"))
                : Optional.empty();
    }


    }



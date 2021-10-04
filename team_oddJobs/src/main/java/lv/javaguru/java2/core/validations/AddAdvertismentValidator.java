package lv.javaguru.java2.core.validations;
import lv.javaguru.java2.core.requests.Add.AddAdvertismentRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@DIComponent
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



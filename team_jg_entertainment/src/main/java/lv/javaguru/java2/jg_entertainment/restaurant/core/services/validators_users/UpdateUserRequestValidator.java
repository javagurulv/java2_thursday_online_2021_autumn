package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.UpdateUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateUserRequestValidator {

    public List<CoreError> validate(UpdateUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validateTelephone(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(UpdateUserRequest request) {
        return request.getNewUserName() == null || request.getNewUserName().isEmpty()
                ? Optional.of(new CoreError("newUserName", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSurname(UpdateUserRequest request) {
        return (request.getNewSurname() == null || request.getNewSurname().isEmpty())
                ? Optional.of(new CoreError("newSurname", "must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTelephone(UpdateUserRequest request) {
        return request.getNewTelephoneNumber() == null || request.getNewTelephoneNumber().isEmpty()
                ? Optional.of(new CoreError("newTelephoneNumber", "must not be empty!"))
                : Optional.empty();
    }
}

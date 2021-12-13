package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.AddUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AddUserValidator {

    public List<CoreError> coreErrors(AddUserRequest request) {
        List<CoreError> coreErrors = new ArrayList<>();
        validatorName(request).ifPresent(coreErrors::add);
        validatorSurname(request).ifPresent(coreErrors::add);
        validatorPhoneNumberEmpty(request).ifPresent(coreErrors::add);
        validatorPhoneNumber(request).ifPresent(coreErrors::add);
        return coreErrors;
    }

    private Optional<CoreError> validatorName(AddUserRequest requestUser) {
        return (requestUser.getName() == null || requestUser.getName().isEmpty())
                ? Optional.of(new CoreError("name", "Shouldn't be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorSurname(AddUserRequest requestUser) {
        return (requestUser.getSurname() == null || requestUser.getSurname().isEmpty())
                ? Optional.of(new CoreError("surname", "Shouldn't be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorPhoneNumberEmpty(AddUserRequest requestUser) {
        return (requestUser.getTelephone().isEmpty())
                ? Optional.of(new CoreError("telephoneNumber", "not correct, telephone can't be null"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorPhoneNumber(AddUserRequest requestUser) {
        return (requestUser.getTelephone().length() < 3
                || requestUser.getTelephone().length() > 15)
                ? Optional.of(new CoreError("telephoneNumber", "length must have figures from 3 to 15!"))
                : Optional.empty();
    }
}
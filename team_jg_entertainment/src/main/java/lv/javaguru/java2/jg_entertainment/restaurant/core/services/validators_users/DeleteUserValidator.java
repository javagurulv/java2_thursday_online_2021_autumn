package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.DeleteUserRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteUserValidator {

    public List<CoreError> coreErrors(DeleteUserRequest request) {
        List<CoreError> errorList = new ArrayList<>();
        validateByID(request).ifPresent(errorList::add);
        validateNameVisitor(request).ifPresent(errorList::add);
        return errorList;
    }

    private Optional<CoreError> validateByID(DeleteUserRequest request) {
        return (request.getUserId() == null)
                ? Optional.of(new CoreError("id visitor", "Can't be null"))
                : Optional.empty();
    }

    private Optional<CoreError> validateNameVisitor(DeleteUserRequest request) {
        return (request.getUserName() == null || request.getUserName().isEmpty())
                ? Optional.of(new CoreError("name visitor", "Can't be empty"))
                : Optional.empty();
    }
}
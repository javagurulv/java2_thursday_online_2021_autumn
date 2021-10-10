package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.DeleteVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteVisitorValidator {

    public List<CoreError> coreErrors(DeleteVisitorRequest request) {
        List<CoreError> errorList = new ArrayList<>();
        validateByID(request).ifPresent(errorList::add);
        validateNameVisitor(request).ifPresent(errorList::add);
        return errorList;
    }

    private Optional<CoreError> validateByID(DeleteVisitorRequest request) {
        return (request.getIdVisitor() == null)
                ? Optional.of(new CoreError("id visitor", "Can't be null"))
                : Optional.empty();
    }

    private Optional<CoreError> validateNameVisitor(DeleteVisitorRequest request) {
        return (request.getNameVisitors() == null || request.getNameVisitors().isEmpty())
                ? Optional.of(new CoreError("name visitor", "Can't be empty"))
                : Optional.empty();
    }
}
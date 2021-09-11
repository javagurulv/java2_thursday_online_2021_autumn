package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestDeleteVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ValidatorDeleteVisitor {

    public List<CoreError> coreErrors(RequestDeleteVisitor request) {
        List<CoreError> errorList = new ArrayList<>();
        validateByID(request).ifPresent(errorList::add);
        validateNameVisitor(request).ifPresent(errorList::add);
        return errorList;
    }

    private Optional<CoreError> validateByID(RequestDeleteVisitor request) {
        return (request.getIdVisitor() == null)
                ? Optional.of(new CoreError("id visitor", "Can't be null"))
                : Optional.empty();
    }

    private Optional<CoreError> validateNameVisitor(RequestDeleteVisitor request) {
        return (request.getNameVisitors() == null || request.getNameVisitors().isEmpty())
                ? Optional.of(new CoreError("name visitor", "Can't be empty"))
                : Optional.empty();
    }
}
package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.AddVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AddVisitorValidator {

    public List<CoreError> coreErrors(AddVisitorRequest request) {
        List<CoreError> coreErrors = new ArrayList<>();
        validatorName(request).ifPresent(coreErrors::add);
        validatorSurname(request).ifPresent(coreErrors::add);
        validatorPhoneNumberEmpty(request).ifPresent(coreErrors::add);
        validatorPhoneNumber(request).ifPresent(coreErrors::add);
        return coreErrors;
    }

    private Optional<CoreError> validatorName(AddVisitorRequest requestVisitor) {
        return (requestVisitor.getName() == null || requestVisitor.getName().isEmpty())
                ? Optional.of(new CoreError("name visitors", "Shouldn't be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorSurname(AddVisitorRequest requestVisitor) {
        return (requestVisitor.getSurname() == null || requestVisitor.getSurname().isEmpty())
                ? Optional.of(new CoreError("surname", "Shouldn't be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorPhoneNumberEmpty(AddVisitorRequest requestVisitor) {
        return (requestVisitor.getTelephone().isEmpty())
                ? Optional.of(new CoreError("telephoneNumber", "not correct, telephone can't be null"))
                : Optional.empty();
    }

    private Optional<CoreError> validatorPhoneNumber(AddVisitorRequest requestVisitor) {
        return (requestVisitor.getTelephone().length() < 3
                || requestVisitor.getTelephone().length() > 15)
                ? Optional.of(new CoreError("telephoneNumber", "length must have figures from 3 to 15!"))
                : Optional.empty();
    }
}
package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.AddVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ValidatorAddVisitor {

    public List<CoreError> coreErrors(AddVisitorRequest request) {
        List<CoreError> coreErrors = new ArrayList<>();
        validatorName(request).ifPresent(coreErrors::add);
        validatorSurname(request).ifPresent(coreErrors::add);
        validatorTelephone(request).ifPresent(coreErrors::add);
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

    private Optional<CoreError> validatorTelephone(AddVisitorRequest requestVisitor) {
        return (requestVisitor.getTelephone() == null)
                ? Optional.of(new CoreError("telephone", "not correct, telephone can't be null"))
                : Optional.empty();

    }
}
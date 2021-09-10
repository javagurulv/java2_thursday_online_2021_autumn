package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.FindVisitorInformationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindVisitorValidator {

    public List<CoreError> coreErrors (FindVisitorInformationRequest request){
        List<CoreError> errorList = new ArrayList<>();
        validatorNameVisitors(request).ifPresent(errorList::add);
        validatorIdVisitors(request).ifPresent(errorList::add);
        return errorList;
    }

    private Optional<CoreError> validatorNameVisitors(FindVisitorInformationRequest request){
        return (request.getName().isEmpty() || request.getName()==null)
                ?Optional.of(new CoreError("name visitors", "couldn't be empty"))
                :Optional.empty();
    }

    private Optional<CoreError>validatorIdVisitors(FindVisitorInformationRequest request){
        return (request.getIdVisitors()==null)
                ?Optional.of(new CoreError("id visitors", "couldn't be null"))
                :Optional.empty();
    }
}

package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.SearchVisitorsRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchVisitorsRequestFieldValidator {

    public List<CoreError> validatorSearchField(SearchVisitorsRequest request) { //+search field telephone

        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getNameVisitors()) && isEmpty(request.getSurnameVisitors())) {
            errors.add(new CoreError("name", "can not be empty!"));
            errors.add(new CoreError("surname", "can not be empty!"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}

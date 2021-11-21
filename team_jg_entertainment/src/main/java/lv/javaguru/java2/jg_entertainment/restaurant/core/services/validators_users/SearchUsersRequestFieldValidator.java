package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_users;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.users.SearchUsersRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.users.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchUsersRequestFieldValidator {

    public List<CoreError> validatorSearchField(SearchUsersRequest request) {

        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getUserName()) && isEmpty(request.getUsersSurname())) {
            errors.add(new CoreError("name", "can not be empty!"));
            errors.add(new CoreError("surname", "can not be empty!"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null
                || str.isEmpty();
    }
}

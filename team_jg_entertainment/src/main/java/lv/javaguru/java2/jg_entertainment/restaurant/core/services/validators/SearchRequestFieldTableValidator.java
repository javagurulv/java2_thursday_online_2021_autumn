package lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.tables.SearchTableRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.tables.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchRequestFieldTableValidator {
    
    public List<CoreError> validatorField(SearchTableRequest request){
        List<CoreError> coreErrors = new ArrayList<>();
        if(isEmpty(request.getTitleTable())
                && (request.getIdTable() == null)){
            coreErrors.add(new CoreError("titleTable", "can not be empty"));
            coreErrors.add(new CoreError("idTable", "must not be null"));
        }
        return coreErrors;
    }

    private boolean isEmpty(String str) {
        return str == null
                || str.isEmpty();
    }
}

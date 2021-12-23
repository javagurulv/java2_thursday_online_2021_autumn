package lv.javaguru.java2.oddJobs.core.validations.find;

import lv.javaguru.java2.oddJobs.core.requests.find.FindClientsRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindClientsFieldValidator {

    public List<CoreError> validate(FindClientsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getClientId()) && isEmpty(request.getClientName()) && isEmpty(request.getClientSurname())) {
            errors.add(new CoreError("Id", "Must not be empty!"));
            errors.add(new CoreError("Name", "Must not be empty!"));
            errors.add(new CoreError("Surname", "Must not be empty!"));
        }
        return errors;
    }


    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
    private boolean isEmpty(Long id) {
        return id == null ;
    }
}


package lv.javaguru.java2.oddJobs.core.validations.find;

import lv.javaguru.java2.oddJobs.core.requests.find.FindAdvertisementRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindAdvertisementFieldValidator {

    public List<CoreError> validate(FindAdvertisementRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getAdvId()) && isEmpty(request.getAdvTitle())) {
            errors.add(new CoreError("ID", "Must not be empty!"));
            errors.add(new CoreError("Title", "Must not be empty!"));
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

package lv.javaguru.java2.oddJobs.core.validations;

import lv.javaguru.java2.oddJobs.core.requests.find.FindSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class FindSpecialistsFieldValidator {

    public List<CoreError> validate(FindSpecialistRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getSpecialistName()) && isEmpty(request.getSpecialistSurname()) && isEmpty(request.getSpecialistProfession())) {
            errors.add(new CoreError("Name", "Must not be empty!"));
            errors.add(new CoreError("Surname", "Must not be empty!"));
            errors.add(new CoreError("Profession", "Must not be empty!"));
        }
        return errors;
    }


    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

}

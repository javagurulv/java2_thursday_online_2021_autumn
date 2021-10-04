package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Find.FindSpecialistRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
@DIComponent
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

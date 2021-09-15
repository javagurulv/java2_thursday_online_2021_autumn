package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Find.FindAdvertisementByTitleRequest;
import lv.javaguru.java2.core.responce.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindAdvertisementByTitleValidator {

    public List<CoreError> validate(FindAdvertisementByTitleRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateTitle(request));
        return errors;
    }

    private List<CoreError> validateTitle(FindAdvertisementByTitleRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if(request.isTitleProvided()) {
            errors.add(new CoreError("Title", "Must not be empty!"));
        }
        return errors;
    }
}

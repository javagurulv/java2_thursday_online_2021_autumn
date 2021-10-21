package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.VisitExistenceByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EditVisitValidator {

    @Autowired private VisitExistenceByIdValidator validator;
    @Autowired private VisitEnumChecker checker;

    public List<CoreError> validate(EditVisitRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateVisitExistence(request.getVisitID()).ifPresent(errors::add);
        validateChanges(request).ifPresent(errors::add);
        validateEnum(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(EditVisitRequest request) {
        return (request.getVisitID() == null)
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateVisitExistence(Long id)  {
        if(id == null) {
            return Optional.empty();
        }
        return validator.validateExistenceById(id);
    }

    private Optional<CoreError> validateChanges(EditVisitRequest request) {
        return (request.getChanges() == null || request.getChanges().isEmpty())
                ? Optional.of(new CoreError("changes", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEnum(EditVisitRequest request){
        return (request.getEditEnums() == null || request.getEditEnums().isEmpty())
                ? Optional.empty() : checker.validateEnum(request.getEditEnums());
    }
}

package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.checkers.VisitLongNumChecker;
import lv.javaguru.java2.hospital.visit.core.requests.DeleteVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators.VisitExistenceByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteVisitValidator {

    @Autowired private VisitExistenceByIdValidator validator;
    @Autowired private VisitLongNumChecker numChecker;

    public List<CoreError> validate(DeleteVisitRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateIDField(request).ifPresent(errors::add);
        validateIDForNum(request).ifPresent(errors::add);
        if(errors.isEmpty()){
            validateVisitExistence(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateIDField(DeleteVisitRequest request){
        return (request.getID() == null || request.getID().isEmpty())
                ? Optional.of(new CoreError("ID", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateIDForNum(DeleteVisitRequest request){
        return (request.getID() == null || request.getID().isEmpty()) ?
                Optional.empty() : numChecker.validate(request.getID(), "ID");
    }

    private Optional<CoreError> validateVisitExistence(DeleteVisitRequest request)  {
        return request.getID() == null || request.getID().isEmpty()
                ? Optional.empty() : validator.validateExistenceById(Long.parseLong(request.getID()));
    }
}

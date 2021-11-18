package lv.javaguru.java2.hospital.visit.core.services.validators;

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

    public List<CoreError> validate(DeleteVisitRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateIDField(request).ifPresent(errors::add);
        validateVisitExistence(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateIDField(DeleteVisitRequest request){
        return (request.getId() == null)
                ? Optional.of(new CoreError("ID", "Must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateVisitExistence(DeleteVisitRequest request)  {
        return request.getId() == null ? Optional.empty() : validator.validateExistenceById(request.getId());
    }
}

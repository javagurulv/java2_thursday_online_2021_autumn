package lv.javaguru.java2.hospital.visit.core.services.validators;

import lv.javaguru.java2.hospital.visit.core.requests.DeleteVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteVisitValidator {

    public List<CoreError> validate(DeleteVisitRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateIDField(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateIDField(DeleteVisitRequest request){
        return (request.getId() == null)
                ? Optional.of(new CoreError("ID", "Must not be empty!")) : Optional.empty();
    }
}

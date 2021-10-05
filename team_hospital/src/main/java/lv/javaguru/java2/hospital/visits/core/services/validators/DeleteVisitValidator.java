package lv.javaguru.java2.hospital.visits.core.services.validators;

import lv.javaguru.java2.hospital.visits.core.request.DeleteVisitRequest;
import lv.javaguru.java2.hospital.visits.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

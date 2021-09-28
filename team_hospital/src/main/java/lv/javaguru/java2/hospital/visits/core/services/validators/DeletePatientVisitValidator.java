package lv.javaguru.java2.hospital.visits.core.services.validators;

import lv.javaguru.java2.hospital.visits.core.request.DeletePatientVisitRequest;
import lv.javaguru.java2.hospital.visits.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeletePatientVisitValidator {

    public List<CoreError> validate(DeletePatientVisitRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateIDField(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateIDField(DeletePatientVisitRequest request){
        return (request.getId() == null || request.getId().isEmpty())
                ? Optional.of(new CoreError("ID", " must not be empty!")) : Optional.empty();
    }
}

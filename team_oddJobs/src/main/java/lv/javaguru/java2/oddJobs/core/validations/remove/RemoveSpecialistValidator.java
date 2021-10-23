package lv.javaguru.java2.oddJobs.core.validations.remove;

import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class RemoveSpecialistValidator {
    public List<CoreError> validate(RemoveSpecialistRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        return errors;
    }


    private Optional<CoreError> validateId(RemoveSpecialistRequest request) {
        return (request.getSpecialistId()==null)
                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateName(RemoveSpecialistRequest request) {
        return (request.getSpecialistName()==null)
                ? Optional.of(new CoreError("Name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSurname(RemoveSpecialistRequest request) {
        return (request.getSpecialistSurname()==null)
                ? Optional.of(new CoreError("Surname", "Must not be empty!"))
                : Optional.empty();
    }
}

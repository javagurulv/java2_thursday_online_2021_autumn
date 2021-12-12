package lv.javaguru.java2.oddJobs.core.validations.update;


import lv.javaguru.java2.oddJobs.core.requests.update.UpdateSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateSpecialistRequestValidator {

    public List<CoreError> validate(UpdateSpecialistRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validateProfession(request).ifPresent(errors::add);
        validatePersonalCode(request).ifPresent(errors::add);
        validateCity(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(UpdateSpecialistRequest request) {
        return (request.getNewName() == null || request.getNewName().isEmpty())
                ? Optional.of(new CoreError("newName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSurname(UpdateSpecialistRequest request) {
        return (request.getNewSurname() == null || request.getNewSurname().isEmpty())
                ? Optional.of(new CoreError("newSurname", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<CoreError> validateProfession(UpdateSpecialistRequest request) {
        return (request.getNewProfession() == null || request.getNewProfession().isEmpty())
                ? Optional.of(new CoreError("newProfession", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<CoreError> validatePersonalCode(UpdateSpecialistRequest request) {
        return (request.getNewPersonalCode() == null || request.getNewPersonalCode().isEmpty())
                ? Optional.of(new CoreError("newPersonalCode", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<CoreError> validateCity(UpdateSpecialistRequest request) {
        return (request.getNewCity() == null || request.getNewCity().isEmpty())
                ? Optional.of(new CoreError("newCity", "Must not be empty!"))
                : Optional.empty();
    }

}

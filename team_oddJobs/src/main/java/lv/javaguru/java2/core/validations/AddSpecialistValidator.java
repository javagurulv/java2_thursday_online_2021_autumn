package lv.javaguru.java2.core.validations;

import lv.javaguru.java2.core.requests.Add.AddSpecialistRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@DIComponent
public class AddSpecialistValidator {
    public List<CoreError> validate(AddSpecialistRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validateProfession(request).ifPresent(errors::add);
        return errors;

    }
    private Optional<CoreError> validateName(AddSpecialistRequest request) {
        return (request.getName() == null || request.getName().isEmpty())
                ? Optional.of(new CoreError("Name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSurname(AddSpecialistRequest request) {
        return (request.getSurname() == null || request.getSurname().isEmpty())
                ? Optional.of(new CoreError("Surname", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<CoreError> validateProfession(AddSpecialistRequest request) {
        return (request.getProfession() == null || request.getProfession().isEmpty())
                ? Optional.of(new CoreError("Profession", "Must not be empty!"))
                : Optional.empty();
    }
}

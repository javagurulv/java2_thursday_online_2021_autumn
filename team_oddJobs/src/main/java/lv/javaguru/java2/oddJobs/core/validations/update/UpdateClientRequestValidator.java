package lv.javaguru.java2.oddJobs.core.validations.update;

import lv.javaguru.java2.oddJobs.core.requests.update.UpdateClientRequest;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateClientRequestValidator {

    public List<CoreError> validate(UpdateClientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validatePersonalCode(request).ifPresent(errors::add);
        validateCity(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(UpdateClientRequest request) {
        return (request.getNewClientName() == null || request.getNewClientName().isEmpty())
                ? Optional.of(new CoreError("newClientName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSurname(UpdateClientRequest request) {
        return (request.getNewClientSurname() == null || request.getNewClientSurname().isEmpty())
                ? Optional.of(new CoreError("newClientSurname", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePersonalCode(UpdateClientRequest request) {
        return (request.getNewClientPersonalCode() == null || request.getNewClientPersonalCode().isEmpty())
                ? Optional.of(new CoreError("newClientPersonalCode", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateCity(UpdateClientRequest request) {
        return (request.getNewClientCity() == null || request.getNewClientCity().isEmpty())
                ? Optional.of(new CoreError("newClientCity", "Must not be empty!"))
                : Optional.empty();
    }

}

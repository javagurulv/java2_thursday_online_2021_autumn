package lv.javaguru.java2.hospital.patient.core.services.validators;

import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class SearchPatientsRequestFieldValidator {

    public List<CoreError> validate(SearchPatientsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validatePersonalCode(request).ifPresent(errors::add);
        return errors.size() > 0
                ? Collections.singletonList(new CoreError("All fields", "must be filled!"))
                : new ArrayList<>();
    }

    private Optional<CoreError> validateName(SearchPatientsRequest request){
        return request.getName() == null || request.getName().isEmpty()
                ? Optional.of(new CoreError("Name", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validateSurname(SearchPatientsRequest request){
        return request.getSurname() == null || request.getSurname().isEmpty()
                ? Optional.of(new CoreError("Surname", "must not be empty!")) : Optional.empty();
    }

    private Optional<CoreError> validatePersonalCode(SearchPatientsRequest request){
        return request.getPersonalCode() == null || request.getPersonalCode().isEmpty()
                ? Optional.of(new CoreError("Personal code", "must not be empty!")) : Optional.empty();
    }
}



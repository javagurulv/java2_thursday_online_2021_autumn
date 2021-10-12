package lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.search_criteria_validators;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SurnameSearchValidator implements SearchValidator {

    private PatientDatabaseImpl database;

    public SurnameSearchValidator(PatientDatabaseImpl database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchPatientsRequest request) {
        return !(request.isNameProvided() && request.isPersonalCodeProvided())
                && request.isSurnameProvided();
    }

    @Override
    public Optional<CoreError> process(SearchPatientsRequest request) {
        for (Patient p : database.getPatientsList()) {
            if (p.getSurname().equals(request.getSurname())) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Patient", "does not exist"));
    }
}


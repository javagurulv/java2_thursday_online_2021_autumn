package lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.validators_for_search_criteria;

import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NameSurnamePersonaCodeSearchValidator implements SearchValidator {

    private PatientDatabase database;

    public NameSurnamePersonaCodeSearchValidator(PatientDatabaseImpl database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchPatientsRequest request) {
        return request.isNameProvided()
                && request.isSurnameProvided()
                && request.isPersonalCodeProvided();
    }

    @Override
    public Optional<CoreError> process(SearchPatientsRequest request) {
        for (Patient p : database.getPatientsList()) {
            if (p.getName().equals(request.getName())
                    && p.getSurname().equals(request.getSurname())
                    && p.getPersonalCode().equals(request.getPersonalCode())) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Patient", "does not exist!"));
    }
}

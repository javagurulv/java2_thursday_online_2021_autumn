package lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.existence_validators_for_search_criteria;

import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NameAndSurnameSearchValidator implements SearchValidator {

    private PatientRepository database;

    public NameAndSurnameSearchValidator(PatientRepository database) {
        this.database = database;
    }

    @Override
    public boolean canProcess(SearchPatientsRequest request) {
        return request.isNameProvided()
                && request.isSurnameProvided()
                && !request.isPersonalCodeProvided();
    }

    @Override
    public Optional<CoreError> process(SearchPatientsRequest request) {
        for (Patient p : database.getAllPatients()) {
            if (p.getName().equals(request.getName()) && p.getSurname().equals(request.getSurname())) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Patient", "does not exist!"));
    }
}

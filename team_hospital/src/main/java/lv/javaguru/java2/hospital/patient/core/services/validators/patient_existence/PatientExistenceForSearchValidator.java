package lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence.existence_validators_for_search_criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientExistenceForSearchValidator {

    @Autowired private PatientDatabaseImpl database;

    public List<CoreError> execute(SearchPatientsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        for (SearchValidator s : getValidators()) {
            if (s.canProcess(request)) {
                s.process(request).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private SearchValidator[] getValidators() {
        return new SearchValidator[]{new NameSearchValidator(database),
                new SurnameSearchValidator(database), new PersonalCodeSearchValidator(database),
                new NameAndSurnameSearchValidator(database), new NameAndPersonalCodeSearchValidator(database),
                new SurnameAndPersonalCodeSearchValidator(database), new NameSurnamePersonaCodeSearchValidator(database)};
    }
}

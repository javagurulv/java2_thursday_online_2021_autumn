package lv.javaguru.java2.hospital.visit.core.services.validators.existence;

import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VisitExistenceForSearchValidator {

    @Autowired
    VisitDatabaseImpl database;

    public List<CoreError> validate(SearchVisitRequest request) {
        List<CoreError> errors = new ArrayList<>();

        VisitExistenceBySearchCriteria[] visitExistenceBySearchCriteria = getVisitExistenceBySearchCriteria();
        for (VisitExistenceBySearchCriteria processor : visitExistenceBySearchCriteria) {
            if (processor.canValidate(request)) {
                processor.validateExistence(request).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private VisitExistenceBySearchCriteria[] getVisitExistenceBySearchCriteria() {
        VisitExistenceBySearchCriteria[] visitExistenceBySearchCriteria = {
                new ExistenceByVisitId(database),
                new ExistenceByDoctorIdAndPatientIdAndDate(database),
                new ExistenceByDoctorIdAndPatientId(database),
                new ExistenceByDoctorIdAndDate(database),
                new ExistenceByPatientIdAndDate(database),
                new ExistenceByDoctorId(database),
                new ExistenceByPatientId(database),
                new ExistenceByDate(database)};
        return visitExistenceBySearchCriteria;
    }
}

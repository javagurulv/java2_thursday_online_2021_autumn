package lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.util.Optional;

public class ExistenceByVisitId implements VisitExistenceBySearchCriteria {

    private VisitDatabase database = new VisitDatabaseImpl();

    public ExistenceByVisitId(VisitDatabase database) {
        this.database = database;
    }

    @Override
    public boolean canValidate(SearchVisitRequest request) {
        return request.isVisitIdProvided();
    }

    @Override
    public Optional<CoreError> validateExistence(SearchVisitRequest request) {
        for (Visit visit : database.getVisits()) {
            if (visit.getVisitID().equals(request.getVisitId())) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Visit", "Does not exist!"));
    }
}

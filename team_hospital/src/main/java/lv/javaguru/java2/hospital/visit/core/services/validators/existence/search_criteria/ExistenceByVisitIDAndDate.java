package lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExistenceByVisitIDAndDate implements VisitExistenceBySearchCriteria {

    @Autowired private VisitRepository database;
    @Autowired private GetVisitDate getVisitDate;

    @Override
    public boolean canValidate(SearchVisitRequest request) {
        return request.isVisitIdProvided()
                && request.isDateProvided()
                && !request.isPatientIdProvided()
                && !request.isDoctorIdProvided();
    }

    @Override
    public Optional<CoreError> validateExistence(SearchVisitRequest request) {
        for (Visit visit : database.getAllVisits()) {
            if (visit.getVisitID().equals(Long.parseLong(request.getVisitId())) &&
                    visit.getVisitDate().equals(getVisitDate.getVisitDateFromString(request.getVisitDate()))) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Visit", "does not exist!"));
    }
}

package lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExistenceByPatientIdAndDate implements VisitExistenceBySearchCriteria {

    @Autowired private VisitDatabase database;
    @Autowired private GetVisitDate getVisitDate;

    @Override
    public boolean canValidate(SearchVisitRequest request) {
        return request.isPatientIdProvided()
                && request.isDateProvided()
                && !request.isVisitIdProvided()
                && !request.isDoctorIdProvided();
    }

    @Override
    public Optional<CoreError> validateExistence(SearchVisitRequest request) {
        for (Visit visit : database.showAllVisits()) {
            if (visit.getPatient().getId().equals(request.getPatientId())
                    && visit.getVisitDate().equals(getVisitDate.getVisitDateFromString(request.getVisitDate()))) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Visit", "Does not exist!"));
    }
}

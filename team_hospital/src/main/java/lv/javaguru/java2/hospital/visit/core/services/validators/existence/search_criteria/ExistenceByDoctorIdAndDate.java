package lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExistenceByDoctorIdAndDate implements VisitExistenceBySearchCriteria{

    @Autowired private VisitDatabase database;
    @Autowired private GetVisitDate getVisitDate;

    @Override
    public boolean canValidate(SearchVisitRequest request) {
        return request.isDoctorIdProvided()
                && request.isDateProvided()
                && !request.isVisitIdProvided()
                && !request.isPatientIdProvided();
    }

    @Override
    public Optional<CoreError> validateExistence(SearchVisitRequest request) {
        for (Visit visit : database.showAllVisits()) {
            if (visit.getDoctor().getId().equals(request.getDoctorId())
                    && visit.getVisitDate().equals(getVisitDate.getVisitDateFromString(request.getVisitDate()))) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Visit", "Does not exist!"));
    }
}

package lv.javaguru.java2.hospital.visit.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;

import java.util.Date;
import java.util.Optional;

public class ExistenceByDoctorIdAndPatientIdAndDate implements VisitExistenceBySearchCriteria{

    private VisitDatabase database = new VisitDatabaseImpl();

    public ExistenceByDoctorIdAndPatientIdAndDate(VisitDatabase database) {
        this.database = database;
    }

    @Override
    public boolean canValidate(SearchVisitRequest request) {
        return request.isDoctorIdProvided()
                && request.isPatientIdProvided()
                && request.isDateProvided()
                && !request.isVisitIdProvided();
    }

    @Override
    public Optional<CoreError> validateExistence(SearchVisitRequest request) {
        for (Visit visit : database.showAllVisits()) {
            if (visit.getDoctor().getId().equals(request.getDoctorId())
            && visit.getPatient().getId().equals(request.getPatientId())
            && visit.getVisitDate().equals(getVisitDate(request))) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Visit", "Does not exist!"));
    }

    private Date getVisitDate(SearchVisitRequest request) {
        GetVisitDate getVisitDate = new GetVisitDate();
        return getVisitDate.getVisitDateFromString(request.getVisitDate());
    }
}

package lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators.search_criteria_validators;

import lv.javaguru.java2.hospital.database.jpa.JpaVisitRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.date_converter.GetVisitDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExistenceBy4fields implements VisitExistenceBySearchCriteria {

    @Autowired private JpaVisitRepository database;
    @Autowired private GetVisitDate getVisitDate;

    @Override
    public boolean canValidate(SearchVisitRequest request) {
        return request.isDoctorIdProvided()
                && request.isPatientIdProvided()
                && request.isDateProvided()
                && request.isVisitIdProvided();
    }

    @Override
    public Optional<CoreError> validateExistence(SearchVisitRequest request) {

        for (Visit visit : database.findAll()) {
            if (visit.getVisitID().equals(Long.parseLong(request.getVisitId()))
                    && visit.getDoctor().getId().equals(Long.parseLong(request.getDoctorId()))
                    && visit.getPatient().getId().equals(Long.parseLong(request.getPatientId()))
                    && visit.getVisitDate().equals(getVisitDate.getVisitDateFromString(request.getVisitDate()))) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Visit", "does not exist!"));
    }
}

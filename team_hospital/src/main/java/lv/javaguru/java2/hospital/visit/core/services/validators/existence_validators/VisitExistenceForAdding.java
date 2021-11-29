package lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators;

import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.date_converter.GetVisitDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VisitExistenceForAdding {

    @Autowired private VisitRepository visitRepository;
    @Autowired private GetVisitDate getVisitDate;

    public Optional<CoreError> validateExistenceForAdding(AddVisitRequest request) {
        for (Visit v : visitRepository.getAllVisits()) {
            if (v.getPatient().getId().equals(Long.parseLong(request.getPatientID()))
            && v.getDoctor().getId().equals(Long.parseLong(request.getDoctorsID()))
            && v.getVisitDate().equals(getVisitDate.getVisitDateFromString(request.getVisitDate()))) {
                return Optional.of(new CoreError("Visit", "already exist!"));
            }
        } return Optional.empty();
    }
}

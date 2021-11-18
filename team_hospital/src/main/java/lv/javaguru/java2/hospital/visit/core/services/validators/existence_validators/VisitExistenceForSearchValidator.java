package lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators;

import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.existence_validators.search_criteria_validators.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VisitExistenceForSearchValidator {

    @Autowired private ExistenceByVisitId existenceByVisitId;
    @Autowired private ExistenceByDoctorIdPatientIdDate existenceByDoctorIdPatientIdDate;
    @Autowired private ExistenceByDate existenceByDate;
    @Autowired private ExistenceByDoctorIdAndPatientId existenceByDoctorIdAndPatientId;
    @Autowired private ExistenceByDoctorIdAndDate existenceByDoctorIdAndDate;
    @Autowired private ExistenceByPatientIdAndDate existenceByPatientIdAndDate;
    @Autowired private ExistenceByDoctorId existenceByDoctorId;
    @Autowired private ExistenceByPatientId existenceByPatientId;
    @Autowired private ExistenceByVisitIDAndDate existenceByVisitIDAndDate;
    @Autowired private ExistenceByVisitIDAndDoctorID existenceByVisitIDAndDoctorID;
    @Autowired private ExistenceByVisitIDAndPatientID existenceByVisitIDAndPatientID;
    @Autowired private ExistenceByVisitIDDoctorIDPatientID existenceByVisitIDDoctorIDPatientID;
    @Autowired private ExistenceBy4fields existenceBy4fields;

    public List<CoreError> validate(SearchVisitRequest request) {
        List<CoreError> errors = new ArrayList<>();

        VisitExistenceBySearchCriteria[] visitExistenceBySearchCriteria = getVisitExistenceBySearchCriteria();
        for (VisitExistenceBySearchCriteria processor : visitExistenceBySearchCriteria) {
            if (processor.canValidate(request)) {
                processor.validateExistence(request).ifPresent(errors::add);
                break;
            }
        }
        return errors;
    }

    private VisitExistenceBySearchCriteria[] getVisitExistenceBySearchCriteria() {
        return new VisitExistenceBySearchCriteria[]{
                existenceByVisitId,
                existenceByDoctorIdPatientIdDate,
                existenceByDoctorIdAndPatientId,
                existenceByDoctorIdAndDate,
                existenceByPatientIdAndDate,
                existenceByDoctorId,
                existenceByPatientId,
                existenceByDate,
                existenceByVisitIDAndDate,
                existenceByVisitIDAndDoctorID,
                existenceByVisitIDAndPatientID,
                existenceByVisitIDDoctorIDPatientID,
                existenceBy4fields};
    }
}

package lv.javaguru.java2.hospital.prescription.core.services.validators.existence;

import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import lv.javaguru.java2.hospital.prescription.core.services.validators.existence.search_criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrescriptionExistenceForSearchValidator {

    @Autowired private PrescriptionExistenceById existenceById;
    @Autowired private PrescriptionExistenceByDoctorIdAndPatientId existenceByDoctorIdAndPatientId;
    @Autowired private PrescriptionExistenceByPatientId existenceByPatientId;
    @Autowired private PrescriptionExistenceByDoctorId existenceByDoctorId;

    public List<CoreError> validate(SearchPrescriptionRequest request) {
        List<CoreError> errors = new ArrayList<>();

        PrescriptionExistenceBySearchCriteria[] prescriptionExistenceBySearchCriteria =
                getPrescriptionExistenceBySearchCriteria();

        for (PrescriptionExistenceBySearchCriteria processor : prescriptionExistenceBySearchCriteria) {
            if (processor.canValidate(request)) {
                processor.validateExistence(request).ifPresent(errors::add);
            }
        }
        return errors;
    }



    private PrescriptionExistenceBySearchCriteria[] getPrescriptionExistenceBySearchCriteria() {
        PrescriptionExistenceBySearchCriteria[] prescriptionExistenceBySearchCriteria = {
                existenceById,
                existenceByDoctorIdAndPatientId,
                existenceByPatientId,
                existenceByDoctorId};
        return prescriptionExistenceBySearchCriteria;
    }


}

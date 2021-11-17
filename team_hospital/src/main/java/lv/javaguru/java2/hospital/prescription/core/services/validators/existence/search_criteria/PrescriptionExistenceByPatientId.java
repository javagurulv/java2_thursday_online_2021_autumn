package lv.javaguru.java2.hospital.prescription.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PrescriptionExistenceByPatientId implements PrescriptionExistenceBySearchCriteria{

    @Autowired private PrescriptionRepository database;

    @Override
    public boolean canValidate(SearchPrescriptionRequest request) {
        return request.isPatientIdProvided();
    }

    @Override
    public Optional<CoreError> validateExistence(SearchPrescriptionRequest request) {
        for (Prescription prescription : database.getAllPrescriptions()) {
            if (prescription.getPatient().getId().equals(request.getPatientId())) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Prescription", "Does not exist!"));
    }
}

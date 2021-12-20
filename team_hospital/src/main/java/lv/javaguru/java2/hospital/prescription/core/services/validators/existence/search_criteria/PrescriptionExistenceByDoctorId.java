package lv.javaguru.java2.hospital.prescription.core.services.validators.existence.search_criteria;

import lv.javaguru.java2.hospital.database.jpa.JpaPrescriptionRepository;
import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.SearchPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PrescriptionExistenceByDoctorId implements PrescriptionExistenceBySearchCriteria{

    @Autowired private JpaPrescriptionRepository database;

    @Override
    public boolean canValidate(SearchPrescriptionRequest request) {
        return request.isDoctorIdProvided();
    }

    @Override
    public Optional<CoreError> validateExistence(SearchPrescriptionRequest request) {
        for (Prescription prescription : database.findAll()) {
            if (prescription.getDoctor().getId().equals(request.getDoctorId())) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Prescription", "Does not exist!"));
    }
}

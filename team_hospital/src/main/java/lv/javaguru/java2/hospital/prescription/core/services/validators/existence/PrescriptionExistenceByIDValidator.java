package lv.javaguru.java2.hospital.prescription.core.services.validators.existence;

import lv.javaguru.java2.hospital.database.jpa.JpaPrescriptionRepository;
import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PrescriptionExistenceByIDValidator {

    @Autowired private JpaPrescriptionRepository database;

    public Optional<CoreError> execute(Long ID) {
        for (Prescription prescription : database.findAll()) {
            if (prescription.getId().equals(ID)) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Prescription", "does not exist!"));
    }
}

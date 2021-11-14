package lv.javaguru.java2.hospital.prescription.core.services.validators.existence;

import lv.javaguru.java2.hospital.database.prescription_repository.PrescriptionRepository;
import lv.javaguru.java2.hospital.domain.Prescription;
import lv.javaguru.java2.hospital.prescription.core.requests.AddPrescriptionRequest;
import lv.javaguru.java2.hospital.prescription.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class PrescriptionExistenceForAddValidator {

    @Autowired
    PrescriptionRepository database;

    public Optional<CoreError> validatePrescriptionExistence(AddPrescriptionRequest request) {
        for (Prescription prescription : database.getAllPrescriptions()) {
            if (prescription.getDoctor().getId().equals(request.getDoctorId())
            && prescription.getPatient().getId().equals(request.getPatientId())
            && prescription.getMedication().equals(request.getMedicationName())
            && prescription.getQuantity() == request.getQuantity()
            && prescription.getDate().equals(LocalDate.now())) {
                return Optional.of(new CoreError("Prescription", "Already exists!"));
            }
        }
        return Optional.empty();
    }
}

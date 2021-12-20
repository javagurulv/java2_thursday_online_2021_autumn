package lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence;

import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PatientExistenceByIDValidator {

    @Autowired private JpaPatientRepository patientRepository;

    public Optional<CoreError> existenceByID(String id) {
        for (Patient p : patientRepository.findAll()) {
            if (p.getId().equals(Long.parseLong(id))) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Patient", "does not exist!"));
    }
}

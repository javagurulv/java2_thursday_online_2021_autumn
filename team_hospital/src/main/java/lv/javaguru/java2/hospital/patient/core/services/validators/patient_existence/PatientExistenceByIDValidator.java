package lv.javaguru.java2.hospital.patient.core.services.validators.patient_existence;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PatientExistenceByIDValidator {

    @Autowired private PatientDatabaseImpl patientDatabase;

    public Optional<CoreError> existenceByID(Long id) {
        for (Patient p : patientDatabase.getPatientsList()) {
            if (p.getId().equals(id)) {
                return Optional.empty();
            }
        }
        return Optional.of(new CoreError("Patient", "does not exist!"));
    }
}

package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.jpa.JpaPatientRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.DeletePatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.DeletePatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeletePatientService {

    @Autowired private JpaPatientRepository database;
    @Autowired private DeletePatientValidator validator;

    public DeletePatientResponse execute(DeletePatientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeletePatientResponse(errors);
        }
        database.deleteById(Long.parseLong(request.getIdRequest()));
        return new DeletePatientResponse(Long.parseLong(request.getIdRequest()));
    }
}

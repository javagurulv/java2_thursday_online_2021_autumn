package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.DeletePatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.DeletePatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeletePatientService {

    @Autowired private PatientDatabaseImpl database;
    @Autowired private DeletePatientValidator validator;

    public DeletePatientResponse execute(DeletePatientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeletePatientResponse(errors);
        }
        database.deleteById(request.getIdRequest());
        return new DeletePatientResponse(request.getIdRequest());
    }
}

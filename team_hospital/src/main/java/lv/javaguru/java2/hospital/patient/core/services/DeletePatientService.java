package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.core.requests.DeletePatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.DeletePatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.DeletePatientValidator;

import java.util.List;

public class DeletePatientService {
    private final PatientDatabaseImpl database;
    private final DeletePatientValidator validator;

    public DeletePatientService(PatientDatabaseImpl database, DeletePatientValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public DeletePatientResponse execute(DeletePatientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeletePatientResponse(errors);
        }
        boolean removedOrNot = database.deleteById(Long.parseLong(request.getIdRequest()));
        return new DeletePatientResponse(request.getIdRequest(), removedOrNot);
    }
}

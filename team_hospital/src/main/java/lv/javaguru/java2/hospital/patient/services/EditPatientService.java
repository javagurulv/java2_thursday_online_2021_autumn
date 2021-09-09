package lv.javaguru.java2.hospital.patient.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.responses.CoreError;
import lv.javaguru.java2.hospital.patient.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.services.validators.EditPatientValidator;

import java.util.List;

public class EditPatientService {
    private final PatientDatabaseImpl database;
    private final EditPatientValidator validator;

    public EditPatientService(PatientDatabaseImpl database, EditPatientValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public EditPatientResponse execute(EditPatientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new EditPatientResponse(errors);
        } else {
            database.editActions(
                    Long.parseLong(request.getPatientID()),
                    Integer.parseInt(request.getUserInput()),
                    request.getChanges());
            return new EditPatientResponse(
                    request.getPatientID(),
                    request.getUserInput(),
                    request.getChanges());
        }
    }
}

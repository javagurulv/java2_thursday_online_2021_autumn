package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.EditPatientValidator;

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

            return new EditPatientResponse(
                    request.getPatientID(),
                    request.getUserInput(),
                    request.getChanges(),
                    database.editActions(
                            request.getPatientID(),
                            request.getUserInput(),
                            request.getChanges()));
        }
    }
}

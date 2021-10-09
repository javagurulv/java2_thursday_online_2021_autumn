package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.EditPatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EditPatientService {

    @Autowired private PatientDatabaseImpl database;
    @Autowired private EditPatientValidator validator;

    public EditPatientResponse execute(EditPatientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new EditPatientResponse(errors);
        } else {

            return new EditPatientResponse(
                    request.getPatientID(),
                    request.getEnums(),
                    request.getChanges(),
                    database.editActions(
                            request.getPatientID(),
                            request.getEnums(),
                            request.getChanges()));
        }
    }
}

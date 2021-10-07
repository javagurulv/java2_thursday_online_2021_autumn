package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.dependency_injection.DIDependency;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.EditPatientValidator;

import java.util.List;

@DIComponent
public class EditPatientService {

    @DIDependency private PatientDatabaseImpl database;
    @DIDependency private EditPatientValidator validator;

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

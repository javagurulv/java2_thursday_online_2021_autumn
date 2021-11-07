package lv.javaguru.java2.hospital.patient.core.services;

import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.CoreError;
import lv.javaguru.java2.hospital.patient.core.responses.EditPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.validators.EditPatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class EditPatientService {

    @Autowired private PatientDatabase database;
    @Autowired private EditPatientValidator validator;

    public EditPatientResponse execute(EditPatientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new EditPatientResponse(errors);
        } else {
            database.editActions(
                    Long.valueOf(request.getPatientID()),
                    EditPatientEnum.valueOf(request.getUserInputEnum().toUpperCase(Locale.ROOT)),
                    request.getChanges());
            return new EditPatientResponse(
                    Long.parseLong(request.getPatientID()),
                    EditPatientEnum.valueOf(request.getUserInputEnum().toUpperCase(Locale.ROOT)),
                    request.getChanges()
                    );
        }
    }
}

package lv.javaguru.java2.hospital.visits.core.services;

import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.visits.core.request.EditPatientVisitRequest;
import lv.javaguru.java2.hospital.visits.core.responses.CoreError;
import lv.javaguru.java2.hospital.visits.core.responses.EditPatientVisitResponse;
import lv.javaguru.java2.hospital.visits.core.services.validators.EditPatientVisitValidator;

import java.util.List;

public class EditPatientVisitService {

    private VisitDatabaseImpl database;
    private EditPatientVisitValidator validator;

    public EditPatientVisitService(VisitDatabaseImpl database, EditPatientVisitValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public EditPatientVisitResponse execute(EditPatientVisitRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new EditPatientVisitResponse(errors);
        }
        boolean isVisitEdited = database.editVisit(request.getVisitID(), request.getUserInput(), request.getChanges());
        return new EditPatientVisitResponse(isVisitEdited);
    }
}

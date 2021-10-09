package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.responses.EditVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.validators.EditVisitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EditVisitService {

    @Autowired private VisitDatabaseImpl database;
    @Autowired private EditVisitValidator validator;

    public EditVisitResponse execute(EditVisitRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new EditVisitResponse(errors);
        }
        boolean isVisitEdited = database.editVisit(request.getVisitID(), request.getUserInput(), request.getChanges());
        return new EditVisitResponse(isVisitEdited);
    }
}

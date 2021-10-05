package lv.javaguru.java2.hospital.visits.core.services;

import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.visits.core.request.DeleteVisitRequest;
import lv.javaguru.java2.hospital.visits.core.responses.CoreError;
import lv.javaguru.java2.hospital.visits.core.responses.DeleteVisitResponse;
import lv.javaguru.java2.hospital.visits.core.services.validators.DeleteVisitValidator;

import java.util.List;

public class DeleteVisitService {
    private final VisitDatabaseImpl visitDatabase;
    private final DeleteVisitValidator validator;

    public DeleteVisitService(VisitDatabaseImpl visitDatabase, DeleteVisitValidator validator) {
        this.visitDatabase = visitDatabase;
        this.validator = validator;
    }

    public DeleteVisitResponse execute(DeleteVisitRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteVisitResponse(errors);
        }

        return new DeleteVisitResponse(visitDatabase.deleteVisit(request.getId()));
    }
}

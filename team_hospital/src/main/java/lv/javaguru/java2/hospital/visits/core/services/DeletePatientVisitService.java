package lv.javaguru.java2.hospital.visits.core.services;

import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.visits.core.request.DeletePatientVisitRequest;
import lv.javaguru.java2.hospital.visits.core.responses.CoreError;
import lv.javaguru.java2.hospital.visits.core.responses.DeletePatientVisitResponse;
import lv.javaguru.java2.hospital.visits.core.services.validators.DeletePatientVisitValidator;

import java.util.List;

public class DeletePatientVisitService {
    private final VisitDatabaseImpl visitDatabase;
    private final DeletePatientVisitValidator validator;

    public DeletePatientVisitService(VisitDatabaseImpl visitDatabase, DeletePatientVisitValidator validator) {
        this.visitDatabase = visitDatabase;
        this.validator = validator;
    }

    public DeletePatientVisitResponse execute(DeletePatientVisitRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeletePatientVisitResponse(errors);
        }

        return new DeletePatientVisitResponse(visitDatabase.DeleteVisit(Long.valueOf(request.getId())));
    }
}

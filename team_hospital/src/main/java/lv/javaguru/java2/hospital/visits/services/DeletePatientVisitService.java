package lv.javaguru.java2.hospital.visits.services;

import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.visits.request.DeletePatientVisitRequest;
import lv.javaguru.java2.hospital.visits.responses.CoreError;
import lv.javaguru.java2.hospital.visits.responses.DeletePatientVisitResponse;
import lv.javaguru.java2.hospital.visits.services.validators.DeletePatientVisitValidator;

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

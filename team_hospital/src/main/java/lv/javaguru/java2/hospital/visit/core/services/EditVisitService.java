package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitEnum;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.responses.EditVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.validators.EditVisitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class EditVisitService {

    @Autowired private VisitRepository database;
    @Autowired private EditVisitValidator validator;

    public EditVisitResponse execute(EditVisitRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new EditVisitResponse(errors);
        }
        boolean isVisitEdited = database.editVisit(Long.valueOf(request.getVisitID()),
                EditVisitEnum.valueOf(request.getEditEnums()),
                request.getChanges());
        return new EditVisitResponse(isVisitEdited);
    }
}

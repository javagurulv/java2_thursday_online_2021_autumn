package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
import lv.javaguru.java2.hospital.visit.core.requests.DeleteVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.responses.DeleteVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.validators.DeleteVisitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteVisitService {

    @Autowired private VisitRepository visitRepository;
    @Autowired private DeleteVisitValidator validator;

    public DeleteVisitResponse execute(DeleteVisitRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteVisitResponse(errors);
        }

        return new DeleteVisitResponse(visitRepository.deleteVisit(Long.parseLong(request.getID())));
    }
}

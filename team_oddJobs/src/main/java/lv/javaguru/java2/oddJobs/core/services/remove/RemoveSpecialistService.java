package lv.javaguru.java2.oddJobs.core.services.remove;


import lv.javaguru.java2.oddJobs.core.requests.remove.RemoveSpecialistRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.remove.RemoveSpecialistResponse;
import lv.javaguru.java2.oddJobs.core.validations.RemoveSpecialistValidator;
import lv.javaguru.java2.oddJobs.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveSpecialistService {
    @Autowired
    private Database database;
    @Autowired
    private RemoveSpecialistValidator validator;

    public RemoveSpecialistResponse execute(RemoveSpecialistRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveSpecialistResponse(errors);
        }
        boolean isSpecialistRemoved = database.removeSpecialist(request.getSpecialistId(), request.getSpecialistName(), request.getSpecialistSurname());
        return new RemoveSpecialistResponse(isSpecialistRemoved);
    }
}

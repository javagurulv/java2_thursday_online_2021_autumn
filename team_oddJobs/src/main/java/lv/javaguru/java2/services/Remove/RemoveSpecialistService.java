package lv.javaguru.java2.services.Remove;


import lv.javaguru.java2.core.requests.Remove.RemoveSpecialistRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.Remove.RemoveSpecialistResponse;
import lv.javaguru.java2.core.validations.RemoveSpecialistValidator;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class RemoveSpecialistService {
    @DIDependency
    private Database database;
    @DIDependency
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

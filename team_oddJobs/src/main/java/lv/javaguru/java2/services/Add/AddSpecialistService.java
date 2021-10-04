package lv.javaguru.java2.services.Add;


import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.core.requests.Add.AddSpecialistRequest;
import lv.javaguru.java2.core.responce.Add.AddSpecialistResponse;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.validations.AddSpecialistValidator;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class AddSpecialistService {
    @DIDependency
    private Database database;
    @DIDependency
    private AddSpecialistValidator validator;


    public AddSpecialistResponse execute(AddSpecialistRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddSpecialistResponse(errors);
        }
        Specialist specialist = new Specialist(request.getName(), request.getSurname(), request.getProfession());
        database.addSpecialist(specialist);
        return new AddSpecialistResponse(specialist);

    }

    @Override
    public String toString() {
        return "AddSpecialistService{" +
                "database=" + database +
                '}';
    }
}

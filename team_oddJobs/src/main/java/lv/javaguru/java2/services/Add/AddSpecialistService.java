package lv.javaguru.java2.services.Add;


import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.core.requests.Add.AddSpecialistRequest;
import lv.javaguru.java2.core.responce.Add.AddSpecialistResponse;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.validations.AddSpecialistValidator;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class AddSpecialistService {
    private Database database;
    private AddSpecialistValidator validator;

    public AddSpecialistService(Database database, AddSpecialistValidator validator) {
        this.database = database;
        this.validator = validator;
    }

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

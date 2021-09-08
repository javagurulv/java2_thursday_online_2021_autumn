package lv.javaguru.java2.services.Find;


import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.core.requests.Find.FindSpecialistByProfessionRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.Find.FindSpecialistByProfessionResponse;
import lv.javaguru.java2.core.validations.FindSpecialistByProfessionValidator;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class FindSpecialistByProfessionService {
    private Database database;
    private FindSpecialistByProfessionValidator validator;

    public FindSpecialistByProfessionService(Database database, FindSpecialistByProfessionValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public FindSpecialistByProfessionResponse execute(FindSpecialistByProfessionRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindSpecialistByProfessionResponse(errors);
        }

        Specialist specialist = new Specialist(request.getProfession());
        database.findSpecialistByProfession(specialist.getSpecialistProfession());
        return new FindSpecialistByProfessionResponse(specialist);

    }
}

package lv.javaguru.java2.services.Find;

import lv.javaguru.java2.Specialist;
import lv.javaguru.java2.core.requests.Find.FindSpecialistRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.Find.FindSpecialistResponse;
import lv.javaguru.java2.core.validations.FindSpecialistValidator;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class FindSpecialistService {

    private Database database;
    private FindSpecialistValidator specialistValidator;


    public FindSpecialistService(Database database, FindSpecialistValidator specialistValidator) {
        this.database = database;
        this.specialistValidator = specialistValidator;
    }

    public FindSpecialistResponse execute(FindSpecialistRequest request) {

        List<CoreError> errors = specialistValidator.validate(request);

        if (!errors.isEmpty()) {
            return new FindSpecialistResponse(null, errors);
        }

        List<Specialist> specialists = null;
        if (request.isIdProvided() && !request.isNameProvided() && !request.isSurnameProvided()) {
            specialists = database.findSpecialistById(request.getSpecialistId());
        }
        if (!request.isIdProvided() && request.isNameProvided() && !request.isSurnameProvided()) {
            specialists = database.findSpecialistByName(request.getSpecialistName());
        }
        if (!request.isIdProvided() && !request.isNameProvided() && request.isSurnameProvided()) {
            specialists = database.findSpecialistBySurname(request.getSpecialistSurname());
        }

        if (request.isIdProvided() && request.isNameProvided() && request.isSurnameProvided()) {
            specialists = database.findSpecialistByIdAndNameAndSurname(request.getSpecialistId(), request.getSpecialistName(), request.getSpecialistSurname());
        }

        return new FindSpecialistResponse(specialists, null);
    }
}

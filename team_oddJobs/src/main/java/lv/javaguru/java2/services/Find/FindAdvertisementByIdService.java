package lv.javaguru.java2.services.Find;

import lv.javaguru.java2.core.requests.Find.FindAdvertisementByIdRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.validations.FindAdvertisementByIdValidator;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class FindAdvertisementByIdService {

    private Database database;
    private FindAdvertisementByIdValidator validator;

    public FindAdvertisementByIdService(Database database, FindAdvertisementByIdValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public void execute(long advId) {
        FindAdvertisementByIdRequest findAdvertisementByIdRequest = new FindAdvertisementByIdRequest(advId);
        List<CoreError> errors = validator.validate(findAdvertisementByIdRequest);
        database.findAdvertisementById(advId);
    }

}

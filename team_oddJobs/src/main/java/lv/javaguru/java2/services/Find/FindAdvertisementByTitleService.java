package lv.javaguru.java2.services.Find;

import lv.javaguru.java2.core.requests.Find.FindAdvertisementByTitleRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.validations.FindAdvertisementByTitleValidator;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class FindAdvertisementByTitleService {

    private Database database;
    private FindAdvertisementByTitleValidator validator;

    public FindAdvertisementByTitleService(Database database, FindAdvertisementByTitleValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public void execute(String advTitle) {
        FindAdvertisementByTitleRequest request = new FindAdvertisementByTitleRequest(advTitle);
        List<CoreError> errors = validator.validate(request);
        database.findAdvertisementByTitle(advTitle);}
}

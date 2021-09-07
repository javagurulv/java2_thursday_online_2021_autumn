package lv.javaguru.java2.services.Find;

import lv.javaguru.java2.core.validations.FindAdvertisementByTitleValidator;
import lv.javaguru.java2.database.Database;

public class FindAdvertisementByTitleService {

    private Database database;
    private FindAdvertisementByTitleValidator validator;

    public FindAdvertisementByTitleService(Database database, FindAdvertisementByTitleValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public void execute(String advTitle) {database.findAdvertisementByTitle(advTitle);}
}

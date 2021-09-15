package lv.javaguru.java2.services.Find;

import lv.javaguru.java2.Advertisement;
import lv.javaguru.java2.core.requests.Find.FindAdvertisementByTitleRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.Find.FindAdvertisementByTitleResponse;
import lv.javaguru.java2.core.validations.FindAdvertisementByTitleValidator;
import lv.javaguru.java2.database.Database;

import java.util.ArrayList;
import java.util.List;

public class FindAdvertisementByTitleService {

    private Database database;
    private FindAdvertisementByTitleValidator validator;

    public FindAdvertisementByTitleService(Database database, FindAdvertisementByTitleValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public FindAdvertisementByTitleResponse execute(FindAdvertisementByTitleRequest request) {
        List<CoreError> errors = validator.validate(request);

        if(!errors.isEmpty()) {
            new FindAdvertisementByTitleResponse(null, errors);
        }

        List<Advertisement> advertisements = find(request);
        return new FindAdvertisementByTitleResponse(advertisements, null);
    }

    private List<Advertisement> find(FindAdvertisementByTitleRequest request) {
        List<Advertisement> advertisements = new ArrayList<>();

        if (!request.isTitleProvided()) {
            advertisements = database.findAdvertisementByTitle(request.getTitle());
        }

        return advertisements;
    }
}

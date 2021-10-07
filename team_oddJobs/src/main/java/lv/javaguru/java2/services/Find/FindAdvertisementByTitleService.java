package lv.javaguru.java2.services.Find;

import lv.javaguru.java2.Advertisement;
import lv.javaguru.java2.core.requests.Find.FindAdvertisementByTitleRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.Find.FindAdvertisementByTitleResponse;
import lv.javaguru.java2.core.validations.FindAdvertisementByTitleValidator;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.dependency_injection.DIComponent;
import lv.javaguru.java2.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class FindAdvertisementByTitleService {

    @DIDependency
    private Database database;
    @DIDependency
    private FindAdvertisementByTitleValidator validator;


    public FindAdvertisementByTitleResponse execute(FindAdvertisementByTitleRequest request) {
        //FindAdvertisementByTitleRequest request = new FindAdvertisementByTitleRequest(req);
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            new FindAdvertisementByTitleResponse(null, errors);
        }

        List<Advertisement> advertisements = find(request);
        return new FindAdvertisementByTitleResponse(advertisements, null);
    }

    private List<Advertisement> find(FindAdvertisementByTitleRequest request) {
        List<Advertisement> advertisements = new ArrayList<>();

        if (request.isTitleProvided()) {
            advertisements = database.findAdvertisementByTitle(request.getTitle());
        }

        return advertisements;
    }
}

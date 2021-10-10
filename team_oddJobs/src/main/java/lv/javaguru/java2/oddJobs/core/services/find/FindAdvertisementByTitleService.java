package lv.javaguru.java2.oddJobs.core.services.find;

import lv.javaguru.java2.oddJobs.core.requests.find.FindAdvertisementByTitleRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.find.FindAdvertisementByTitleResponse;
import lv.javaguru.java2.oddJobs.core.validations.FindAdvertisementByTitleValidator;
import lv.javaguru.java2.oddJobs.database.Database;
import lv.javaguru.java2.oddJobs.domain.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindAdvertisementByTitleService {

    @Autowired
    private Database database;
    @Autowired
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

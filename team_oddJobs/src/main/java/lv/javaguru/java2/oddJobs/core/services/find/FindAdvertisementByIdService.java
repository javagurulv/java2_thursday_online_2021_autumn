package lv.javaguru.java2.oddJobs.core.services.find;


import lv.javaguru.java2.oddJobs.core.requests.find.FindAdvertisementByIdRequest;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.find.FindAdvertisementByIdResponse;
import lv.javaguru.java2.oddJobs.core.validations.FindAdvertisementByIdValidator;
import lv.javaguru.java2.oddJobs.database.Database;
import lv.javaguru.java2.oddJobs.domain.Advertisement;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAdvertisementByIdService {

    @Autowired
    private Database database;
    @Autowired
    private FindAdvertisementByIdValidator validator;


    public FindAdvertisementByIdResponse execute(FindAdvertisementByIdRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new FindAdvertisementByIdResponse(null, errors);
        }

        List<Advertisement> advertisements = find(request);

        return new FindAdvertisementByIdResponse(advertisements, null);

    }

    private List<Advertisement> find(FindAdvertisementByIdRequest request) {
        List<Advertisement> advertisements = new ArrayList<>();

        if (request.isIdProvided()) {
            advertisements = database.findAdvertisementById(request.getAdvId());
        }
        return advertisements;
    }

    public void execute(long advId) {
        FindAdvertisementByIdRequest findAdvertisementByIdRequest = new FindAdvertisementByIdRequest(advId);
        List<CoreError> errors = validator.validate(findAdvertisementByIdRequest);
        database.findAdvertisementById(advId);
    }


}

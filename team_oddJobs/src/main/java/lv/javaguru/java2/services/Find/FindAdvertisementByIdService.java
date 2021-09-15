package lv.javaguru.java2.services.Find;


import lv.javaguru.java2.Advertisement;
import lv.javaguru.java2.core.requests.Find.FindAdvertisementByIdRequest;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.Find.FindAdvertisementByIdResponse;
import lv.javaguru.java2.core.validations.FindAdvertisementByIdValidator;
import lv.javaguru.java2.database.Database;

import java.util.ArrayList;

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

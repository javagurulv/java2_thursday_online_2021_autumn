package lv.javaguru.java2.jg_entertainment.restaurant.core.services;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.FindVisitorInformationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseFindVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.FindVisitorValidator;

import java.util.List;

public class ServiceFindByIdVisitors {

    private final DatabaseVisitors database;
    private final FindVisitorValidator validator;

    public ServiceFindByIdVisitors(DatabaseVisitors database, FindVisitorValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public ResponseFindVisitors execute(FindVisitorInformationRequest request) {
        List<CoreError> coreErrors = validator.coreErrors(request);
        if (!coreErrors.isEmpty()) {
            return new ResponseFindVisitors(coreErrors, null);
        }

        List<Visitors> visitors = database.findClientById(request.getIdVisitors());
        return new ResponseFindVisitors(null, visitors);
    }
}

package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestFindVisitorInformation;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseFindVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.Visitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.ValidatorFindVisitor;

import java.util.List;

public class ServiceFindByIdVisitors {

    private final DatabaseVisitors database;
    private final ValidatorFindVisitor validator;

    public ServiceFindByIdVisitors(DatabaseVisitors database, ValidatorFindVisitor validator) {
        this.database = database;
        this.validator = validator;
    }

    public ResponseFindVisitors execute(RequestFindVisitorInformation request) {
        List<CoreError> coreErrors = validator.coreErrors(request);
        if (!coreErrors.isEmpty()) {
            return new ResponseFindVisitors(coreErrors, null);
        }

        List<Visitors> visitors = database.findClientById(request.getIdVisitors());
        return new ResponseFindVisitors(null, visitors);
    }
}

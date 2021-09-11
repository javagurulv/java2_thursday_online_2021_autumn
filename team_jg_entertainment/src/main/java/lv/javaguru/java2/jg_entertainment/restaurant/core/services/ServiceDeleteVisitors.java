package lv.javaguru.java2.jg_entertainment.restaurant.core.services;


import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.DeleteVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseDeleteVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators.DeleteVisitorValidator;

import java.util.List;

public class ServiceDeleteVisitors {
    private DatabaseVisitors database;
    private DeleteVisitorValidator validator;

    public ServiceDeleteVisitors(DatabaseVisitors database,
                                 DeleteVisitorValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public ResponseDeleteVisitors execute(DeleteVisitorRequest request) {
        List<CoreError> coreErrors = validator.coreErrors(request);
        if (!coreErrors.isEmpty()) {
            return new ResponseDeleteVisitors(coreErrors);
        }
        boolean deleteId = database.deleteClientWithNameAndId(request.getIdVisitor(),request.getNameVisitors());
        return new ResponseDeleteVisitors(deleteId);
    }
}

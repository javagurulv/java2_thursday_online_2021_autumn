package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestDeleteVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseDeleteVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors.ValidatorDeleteVisitor;

import java.util.List;

public class ServiceDeleteVisitors {

    private DatabaseVisitors database;
    private ValidatorDeleteVisitor validator;

    public ServiceDeleteVisitors(DatabaseVisitors database,
                                 ValidatorDeleteVisitor validator) {
        this.database = database;
        this.validator = validator;
    }

    public ResponseDeleteVisitors execute(RequestDeleteVisitor request) {
        List<CoreError> coreErrors = validator.coreErrors(request);
        if (!coreErrors.isEmpty()) {
            return new ResponseDeleteVisitors(coreErrors);
        }
        boolean deleteId = database.deleteClientWithIDAndName(request.getIdVisitor(), request.getNameVisitors());
        return new ResponseDeleteVisitors(deleteId);
    }
}

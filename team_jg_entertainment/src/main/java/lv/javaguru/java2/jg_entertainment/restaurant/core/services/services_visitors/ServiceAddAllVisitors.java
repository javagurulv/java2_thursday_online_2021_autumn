package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestAddVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseAddVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors.ValidatorAddVisitor;

import java.util.List;

public class ServiceAddAllVisitors {

    private DatabaseVisitors database;
    private ValidatorAddVisitor validator;

    public ServiceAddAllVisitors(DatabaseVisitors database,
                                 ValidatorAddVisitor validator) {
        this.database = database;
        this.validator = validator;
    }

    public ResponseAddVisitor execute(RequestAddVisitor request) {
        List<CoreError> coreErrors = validator.coreErrors(request);
        if (!coreErrors.isEmpty()) {
            return new ResponseAddVisitor(coreErrors);
        }
        Visitors visitor = new Visitors(request.getName(), request.getSurname(), request.getTelephone());
        database.saveClientToRestaurantList(visitor);
        return new ResponseAddVisitor(visitor);
    }
}

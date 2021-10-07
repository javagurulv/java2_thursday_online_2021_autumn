package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.RequestDeleteVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.ResponseDeleteVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors.ValidatorDeleteVisitor;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIComponent;
import lv.javaguru.java2.jg_entertainment.restaurant.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class ServiceDeleteVisitors {

   @DIDependency private DatabaseVisitors database;
   @DIDependency private ValidatorDeleteVisitor validator;

    public ResponseDeleteVisitors execute(RequestDeleteVisitor request) {
        List<CoreError> coreErrors = validator.coreErrors(request);
        if (!coreErrors.isEmpty()) {
            return new ResponseDeleteVisitors(coreErrors);
        }
        boolean deleteId = database.deleteClientWithIDAndName(request.getIdVisitor(), request.getNameVisitors());
        return new ResponseDeleteVisitors(deleteId);
    }
}

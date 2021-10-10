package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.DeleteVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.DeleteVisitorsResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validatorsVisitors.ValidatorDeleteVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceDeleteVisitors {

    @Autowired
    private DatabaseVisitors database;
    @Autowired
    private ValidatorDeleteVisitor validator;

    public DeleteVisitorsResponse execute(DeleteVisitorRequest request) {
        List<CoreError> coreErrors = validator.coreErrors(request);
        if (!coreErrors.isEmpty()) {
            return new DeleteVisitorsResponse(coreErrors);
        }
        boolean deleteId =
                database.deleteClientWithIDAndName(request.getIdVisitor(), request.getNameVisitors());

        return new DeleteVisitorsResponse(deleteId);
    }
}

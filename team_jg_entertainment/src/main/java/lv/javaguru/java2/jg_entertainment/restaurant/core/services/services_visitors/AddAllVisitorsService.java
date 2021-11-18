package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository.VisitorsRepository;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.AddVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.AddVisitorResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_visitors.AddVisitorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddAllVisitorsService {

    @Autowired private VisitorsRepository database;
    @Autowired private AddVisitorValidator validator;

    public AddVisitorResponse execute(AddVisitorRequest request) {
        List<CoreError> coreErrors = validator.coreErrors(request);
        if (!coreErrors.isEmpty()) {
            return new AddVisitorResponse(coreErrors);
        }
        Visitor visitor = new Visitor(request.getName(), request.getSurname(), request.getTelephone());
        database.saveClientToRestaurantList(visitor);
        return new AddVisitorResponse(visitor);
    }
}

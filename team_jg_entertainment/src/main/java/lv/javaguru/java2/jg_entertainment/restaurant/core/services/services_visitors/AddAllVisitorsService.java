package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_visitors;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.AddReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.visitors.AddVisitorRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.visitors.AddVisitorResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_visitors.AddVisitorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class AddAllVisitorsService {

    @Autowired
    private DatabaseVisitors database;
    @Autowired
    private AddVisitorValidator validator;

    public AddVisitorResponse execute(AddVisitorRequest request) {
        List<CoreError> coreErrors = validator.coreErrors(request);
        if (!coreErrors.isEmpty()) {
            return new AddVisitorResponse(coreErrors);
        }
        Visitors visitor = new Visitors(request.getName(), request.getSurname(), request.getTelephone());
        database.saveClientToRestaurantList(visitor);
        return new AddVisitorResponse(visitor);
    }

//    private String phone(AddVisitorRequest request) {
//        String string = request.getTelephone();
//        try {
//            long tel = Long.parseLong(s);
//            System.out.println("long tel = " + tel);
//        } catch (NumberFormatException numberFormatException) {
//            System.out.println("NumberFormatException: " + numberFormatException.getMessage());
//        }
//       return string;
//    }
}

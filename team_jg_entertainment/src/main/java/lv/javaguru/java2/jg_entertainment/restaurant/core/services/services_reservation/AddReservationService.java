package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseMenuImpl;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseReservationImpl;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseTableImpl;
import lv.javaguru.java2.jg_entertainment.restaurant.core.database.DatabaseVisitorsImpl;
import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.AddReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.AddReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.CoreError;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.validators_reservations.AddReservationValidator;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Menu;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Reservation;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Table;
import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Component
public class AddReservationService {

    @Autowired    private DatabaseVisitorsImpl databaseVisitors;
    @Autowired    private DatabaseMenuImpl databaseMenu;
    @Autowired    private DatabaseTableImpl databaseTable;
    @Autowired    private DatabaseReservationImpl databaseReservation;
    @Autowired    private AddReservationValidator validator;

    public AddReservationResponse execute(AddReservationRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddReservationResponse(errors);
        }
        Visitors visitors = databaseVisitors.findClientById(Long.valueOf(request.getVisitorID())).get(0);
        Menu menu = databaseMenu.findById(Long.valueOf(request.getMenuID())).get(0);
        Table table = databaseTable.findTabletById(Long.valueOf(request.getTableID())).get(0);
        LocalDateTime date = LocalDateTime.from(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").parse(request.getReservationDate()));


        Reservation reservation = new Reservation(visitors, menu, table, date);
        databaseReservation.addReservation(reservation);
        return new AddReservationResponse(reservation);
    }
/*
    private Visitors visitorInformation(AddReservationRequest request) {
        Visitors visitors =
                databaseVisitors.findByNameVisitor(request.getVisitorName()).get(0);
        return visitors;
    }

    private Menu menuInformation(AddReservationRequest request) {
        Menu menu = databaseMenu.findByTitle(request.getMenuTitle()).get(0);
        return menu;
    }

    private Table tableInformation(AddReservationRequest request) {
        Table table = databaseTable.findByTitleTable(request.getTableTitle()).get(0);
        return table;
    }

    private Date dateInformation(AddReservationRequest request) {
        Date dateReservation = null;
        try {
            dateReservation = new SimpleDateFormat("dd/MM/yyyy").parse(request.getReservationDate());
        } catch (ParseException exception) {
            System.out.println("Date is not correct!");
        }
        return dateReservation;
    }

 */
}

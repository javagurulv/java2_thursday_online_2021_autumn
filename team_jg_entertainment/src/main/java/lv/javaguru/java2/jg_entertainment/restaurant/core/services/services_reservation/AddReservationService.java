package lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.database.*;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class AddReservationService {

    @Autowired private VisitorsRepository visitorsRepository;
    @Autowired private MenuRepository menuRepository;
    @Autowired private TableRepository tableRepository;
    @Autowired private ReservationRepository reservationRepository;
    @Autowired private AddReservationValidator validator;

    public AddReservationResponse execute(AddReservationRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddReservationResponse(errors);
        }
        Visitors visitors = visitorsRepository.findClientById(Long.valueOf(request.getVisitorID())).get(0);
        Menu menu = menuRepository.findById(Long.valueOf(request.getMenuID())).get(0);
        Table table = tableRepository.findTableById(Long.valueOf(request.getTableID())).get(0);
        LocalDateTime date = LocalDateTime.from(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").parse(request.getReservationDate()));


        Reservation reservation = new Reservation(visitors, menu, table, date);
        reservationRepository.addReservation(reservation);
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

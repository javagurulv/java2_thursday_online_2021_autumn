package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.EditReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.EditReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.EditReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class EditReservationUIAction implements ReservationUIAction{

    @Autowired private EditReservationService reservationService;

    @Override
    public void execute() {
        GetQueryWithConsole getInformScanner = new GetQueryWithConsole();
        String idReservation = getInformScanner.getNumberFromConsole("Enter reservation id: ");
        String informChange = getInformScanner.getNumberFromConsole("What do you like to change: ID_VISITOR, ID_TABLE, ID_MENU, RESERVATION_DATE ->")
                .toUpperCase(Locale.ROOT);
        String change = "";
        switch (informChange) {
            case "ID_VISITOR" -> change = getInformScanner.getNumberFromConsole("Enter new ID visitor:");
            case "ID_TABLE" -> change = getInformScanner.getNumberFromConsole("Enter new ID table:");
            case "ID_MENU" -> change = getInformScanner.getNumberFromConsole("Enter new ID menu:");
            case "RESERVATION_DATE" -> change = getInformScanner.getNumberFromConsole("Enter new DATE format is like-> yyyy-MM-dd HH:mm:");
        }
        EditReservationRequest request = new EditReservationRequest(idReservation, informChange, change);
        EditReservationResponse response = reservationService.execute(request);
        if(response.hasError()){
            response.getErrorList().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessageError()));
            System.out.println();
        }else {
            System.out.println("Reservation was changed! New inform: " + idReservation + ", " + informChange);
        }
    }
}

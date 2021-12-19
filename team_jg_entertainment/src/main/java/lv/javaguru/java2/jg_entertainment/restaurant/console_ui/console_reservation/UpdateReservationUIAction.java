package lv.javaguru.java2.jg_entertainment.restaurant.console_ui.console_reservation;

import lv.javaguru.java2.jg_entertainment.restaurant.core.requests.reservation.UpdateReservationRequest;
import lv.javaguru.java2.jg_entertainment.restaurant.core.responses.reservations.UpdateReservationResponse;
import lv.javaguru.java2.jg_entertainment.restaurant.core.services.services_reservation.UpdateReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UpdateReservationUIAction implements ReservationUIAction {

    @Autowired private UpdateReservationService reservationService;

    @Override
    public void execute() {
        GetUserChoosingFromConsole getInformScanner = new GetUserChoosingFromConsole();
        String idReservation = getInformScanner.getNumberFromConsole("Enter reservation ID: ");
        String informChange = getInformScanner.getNumberFromConsole("What do you like to change: ID_VISITOR, ID_TABLE, ID_MENU, RESERVATION_DATE ->").toUpperCase(Locale.ROOT);
        String change = "";
        switch (informChange) {
            case "ID_VISITOR" -> change = getInformScanner.getNumberFromConsole("Enter new user ID:");
            case "ID_TABLE" -> change = getInformScanner.getNumberFromConsole("Enter new ID table:");
            case "ID_MENU" -> change = getInformScanner.getNumberFromConsole("Enter new ID menu:");
            case "RESERVATION_DATE" -> change = getInformScanner.getNumberFromConsole("Enter new DATE format is like-> yyyy-MM-dd HH:mm:");
        }
        UpdateReservationRequest request = new UpdateReservationRequest(idReservation, informChange, change);
        UpdateReservationResponse response = reservationService.execute(request);
        if (response.hasError()) {
            response.getErrorList().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessageError()));
            System.out.println();
        } else {
            System.out.println("Reservation was changed! New inform: " + idReservation + ", " + informChange);
        }
    }
}
